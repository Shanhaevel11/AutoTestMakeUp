package generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import configurations.EndConfig;
import configurations.GeneralConfig;
import configurations.StartConfig;
import testTools.Tools;

public class GeneratorBeta {

	private Tools tools;
	
	public void generateBeta(GeneralConfig generalConfig, StartConfig startConfig, EndConfig endConfig, Vector<Entry> entries ){
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
		
		endOfTest(endConfig);
		
		
	}
	
	
	private void startOfTest(StartConfig startConfig){
		
		if(startConfig.isUseStart()){
			tools.startMethod();
		}
		
		if(startConfig.isUseOtherMethod()){
			//TODO:
		}
	}
	
	private void endOfTest(EndConfig endConfig){
		
		if(endConfig.isUseEnd()){
			tools.endMethod();
		}
		
		if(endConfig.isUseOtherMethod()){
			//TODO:
		}
		
	}
	
}

