package generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import configurations.EndConfig;
import configurations.GeneralConfig;
import configurations.StartConfig;
import testTools.Tools;

public class GeneratorBeta {

	private Tools tools;
	
	public void generateBeta(GeneralConfig generalConfig, StartConfig startConfig, EndConfig endConfig, Vector<Entry> entries ) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		/**
		Tools test = new Tools();
		
		try {
			Method method = test.getClass().getMethod(methodName, null);
			method.invoke(test, null);
			
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR!");
				e.printStackTrace();
			}
			
		 **/
		
		
		//Prepare tools
		tools = new Tools();
		
		tools.prepareWebDriver(generalConfig.getBrowser(), generalConfig.getBrowserPath());
		
		startOfTest(startConfig);
		
		//TODO: generator stuff
		ProcessEntries(generalConfig, entries);
		
		
		
		endOfTest(endConfig);
		
		
	}
	
	
	private void startOfTest(StartConfig startConfig) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		tools.driver.get(startConfig.getStartingURL());
		
		if(startConfig.isUseStart()){
			tools.startMethod();
		}
		
		if(startConfig.isUseOtherMethod()){
			//TODO:
			
			Method method = tools.getClass().getMethod(startConfig.getOtherMethodName(), null);
			method.invoke(tools, null);
			
		}
	}
	
	private void endOfTest(EndConfig endConfig) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		if(endConfig.isUseOtherMethod()){

			Method method = tools.getClass().getMethod(endConfig.getOtherMethodName(), null);
			method.invoke(tools, null);
			
		}
		
		if(endConfig.isUseEnd()){
			tools.endMethod();
		}
		
		
	}
	
	private void ProcessEntries(GeneralConfig generalConfig, Vector<Entry> entries){
		
		int limit = 9999;
		
		Class[] paramDriver = new Class[1];	
		paramDriver[0] = WebDriver.class;
			
		Class[] paramElement = new Class[1];	
		paramElement[0] = WebElement.class;
		
		Class[] paramDriverAndElement = new Class[2];	
		paramDriverAndElement[0] = WebDriver.class;
		paramDriverAndElement[1] = WebElement.class;
		
		if(generalConfig.isChkLimitOfSteps())limit=generalConfig.getLimitOfSteps();
		
		//main loop to take care of whole test
		while(--limit>=0){
			
			for(Entry entry : entries){
				
				WebElement element = null;
				
				try{
				
					switch(entry.getDetectionType()){
					
						case "ID":{
							
							element = tools.driver.findElement(By.id(entry.getDetectionValue()));
							
							break;
						}
						
						case "NAME":{
						
							element = tools.driver.findElement(By.name(entry.getDetectionValue()));
							
							break;
						}
						
						case "XPATH":{
							
							element = tools.driver.findElement(By.xpath(entry.getDetectionValue()));
							
							break;
						}
						
						default : {
							
							System.out.println("ELEMENT: " + entry.getName() + " does not have detection type set!");
							
							break;
						}
					
					}
				
					if(element==null)throw new NoSuchElementException("detection fail");
					if(entry.getUsages()<=0){
						System.out.println("ELEMENT: " + entry.getName() + " is used up!");
					}else{
					
						entry.lowerUsages();
						System.out.println("USING ELEMENT: " + entry.getName() + "|||  USAGES LEFT: " + entry.getUsages());
						Method method = null;
						try {
							
							if(entry.isPassElement() && entry.isPassBrowser()){
								method = tools.getClass().getMethod(entry.getMethodName(), paramDriverAndElement);
								method.invoke(tools, tools.driver, element);
							}else if(entry.isPassElement()){
								method = tools.getClass().getMethod(entry.getMethodName(), paramElement);
								method.invoke(tools, element);
							}else if(entry.isPassBrowser()){
								method = tools.getClass().getMethod(entry.getMethodName(), paramDriver);
								method.invoke(tools, tools.driver);
							}else{
								method = tools.getClass().getMethod(entry.getMethodName(), null);
								method.invoke(tools, null);
							}
							
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("ELEMENT: " + entry.getName() + " generate error when reflecting method!");
						}
						
						//TODO: use wait method after execution
						//TODO: Page will change after handling this entry
					}
				
				
				}catch(NoSuchElementException e){
					
					//skip element
					
				}
				
				
				
			}
			
			
			
		}
		
		
	}
	
	
}

