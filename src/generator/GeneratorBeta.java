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
import fileOperators.TestSaver;
import testTools.Tools;

public class GeneratorBeta {

	private Tools tools;
	private TestSaver testSaver = null;
	
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
		//PrepareFile
		testSaver = new TestSaver(generalConfig.getTestDirectory());
		
		
		//Prepare tools
		tools = new Tools();
		
		testSaver.createTestFile();
		
		tools.prepareWebDriver(generalConfig.getBrowser(), generalConfig.getBrowserPath());
		
		testSaver.addStartToTestFile(startConfig.getStartingURL(), generalConfig.getBrowser(), generalConfig.getBrowserPath());
		
		readyEntries(entries);
		
		
		startOfTest(startConfig);
		
		//TODO: generator stuff
		ProcessEntries(generalConfig, entries);
		
		
		endOfTest(endConfig);
		
		testSaver.endTestFile();
		
		
	}
	
	
	private void readyEntries(Vector<Entry> entries) {
		// TODO Auto-generated method stub
		
		for(Entry entry : entries){
			entry.reload();
		}
		
	}


	private void startOfTest(StartConfig startConfig) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		tools.driver.get(startConfig.getStartingURL());
		
		if(startConfig.isUseStart()){
			tools.startMethod();
			
			testSaver.addMethodToTestFile("startMethod");
			
		}
		
		if(startConfig.isUseOtherMethod()){
			//TODO:
			
			Method method = tools.getClass().getMethod(startConfig.getOtherMethodName(), null);
			method.invoke(tools, null);
			
			testSaver.addMethodToTestFile(startConfig.getOtherMethodName());
			
		}
	}
	
	private void endOfTest(EndConfig endConfig) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		if(endConfig.isUseOtherMethod()){

			Method method = tools.getClass().getMethod(endConfig.getOtherMethodName(), null);
			method.invoke(tools, null);
			
			testSaver.addMethodToTestFile(endConfig.getOtherMethodName());
			
		}
		
		if(endConfig.isUseEnd()){
			tools.endMethod();
			
			testSaver.addMethodToTestFile("endMethod");
		}
		
		
	}
	
	private void ProcessEntries(GeneralConfig generalConfig, Vector<Entry> entries){
		
		int limit = 9999;
		boolean pageChanger=false;
		
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
			
			pageChanger=false;
			
			for(Entry entry : entries){
				
				if(pageChanger){
					break;
				}
				
				WebElement element = null;
				
				System.out.println("Checking element = " + entry.getDetectionValue());
				
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
							
							testSaver.addFindElementToTestFile(entry.getDetectionValue(), entry.getDetectionType());
							
							if(entry.isPassElement() && entry.isPassBrowser()){
								method = tools.getClass().getMethod(entry.getMethodName(), paramDriverAndElement);
								method.invoke(tools, tools.driver, element);
								
								testSaver.addMethodToTestFile(entry.getMethodName(), "this.driver", "element");
								
							}else if(entry.isPassElement()){
								method = tools.getClass().getMethod(entry.getMethodName(), paramElement);
								method.invoke(tools, element);
								
								testSaver.addMethodToTestFile(entry.getMethodName(), "element");
							}else if(entry.isPassBrowser()){
								method = tools.getClass().getMethod(entry.getMethodName(), paramDriver);
								method.invoke(tools, tools.driver);
							}else{
								method = tools.getClass().getMethod(entry.getMethodName(), null);
								method.invoke(tools, null);
								testSaver.addMethodToTestFile(entry.getMethodName());
							}
							
						} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("ELEMENT: " + entry.getName() + " generate error when reflecting method!");
						}
						
						//TODO: use wait method after execution
						if(entry.isWaitAfterMethod()){
							tools.waitAfter();
							testSaver.addMethodToTestFile("waitAfter");
						}
						
						//TODO: Page will change after handling this entry
						if(entry.isPageChanger()){
							pageChanger=true;
						}
						
					}
				
				
				}catch(NoSuchElementException e){
					
					//skip element
					
				}
				
				
				
			}
			
			
			
		}
		
		
	}
	
	
}

