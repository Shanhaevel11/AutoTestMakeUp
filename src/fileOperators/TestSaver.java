package fileOperators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestSaver {

	private String directory;
	private boolean canSave=true;
	private FileWriter writer;
	
	public TestSaver(String directory) {
		super();
		this.directory = directory;
		
		prepareDirectory();
		
	}

	public void addStartToTestFile(String URL, String browser, String browserPath){
		
		try {
			String path = browserPath.toString();
			path = path.replace("\\", "\\\\");
			writer.append("prepareWebDriver(\""+browser+"\", \""+ path +"\");\n");
			writer.append("driver.get(\""+ URL +"\");\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("SAVER HAS FAILED US");
			this.canSave=false;
		}
		//prepareWebDriver(generalConfig.getBrowser(), generalConfig.getBrowserPath());
		
	}
	
	public void addFindElementToTestFile(String element, String type){
		
		//element = tools.driver.findElement(By.xpath(entry.getDetectionValue()));
		
		try {
			writer.append("element = driver.findElement(By."+ type.toLowerCase() +"(\"" + element + "\"));\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("SAVER HAS FAILED US");
			this.canSave=false;
		}
		
	}
	
	public void addMethodToTestFile(String methodName){
		
		try {
			writer.append(methodName + "()" + ";\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("SAVER HAS FAILED US");
			this.canSave=false;
		}
		
	}
	
	public void addMethodToTestFile(String methodName, String arg1){
		
		try {
			writer.append(methodName + "(" + arg1 + ")" + ";\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("SAVER HAS FAILED US");
			this.canSave=false;
		}
		
	}
	
	public void addMethodToTestFile(String methodName, String arg1, String arg2){
		
		try {
			writer.append(methodName + "(" + arg1 + ", " + arg2 + ")" + ";\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("SAVER HAS FAILED US");
			this.canSave=false;
		}
		
	}

	public void prepareDirectory(){
		File file = new File(directory);
		file.mkdirs();
	}
	
	public void createTestFile(){
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String filename = "test_" + dateFormat.format(date);
		File savedTest = new File(directory + "\\" + filename +".java");
		try {
			writer = new FileWriter(savedTest);
			
			writer.write("package generatedTests;\n");
			writer.append("import java.io.IOException;\n");
			writer.append("import org.testng.annotations.Test;\n");
			writer.append("import testTools.Tools;\n");
			writer.append("import org.openqa.selenium.WebElement;\n");
			writer.append("import org.openqa.selenium.By;\n");
			writer.append("public class "+ filename +" extends Tools{\n\n");
			writer.append("WebElement element;\n\n");
			writer.append("@Test\n");
			writer.append("public void generatedTest1() throws InterruptedException, IOException{\n");

			//now we need to save generated test body;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("SAVER HAS FAILED US");
			this.canSave=false;
		}
	}
	
	public void endTestFile(){
		

		try {
			writer.append("}\n");
			writer.append("}\n");
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
