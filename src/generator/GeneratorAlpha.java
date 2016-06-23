package generator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import testTools.Tools;

public class GeneratorAlpha {

	public void generateAlpha(String methodName){
		
		Tools test = new Tools();
		
		try {
			Method method = test.getClass().getMethod(methodName, null);
			method.invoke(test, null);
			
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR!");
				e.printStackTrace();
			}
			

		
	}
	
	
}

