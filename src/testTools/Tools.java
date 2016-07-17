package testTools;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;



import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.*;



public class Tools {
	
	private WebDriver driver;
	
	public void prepareWebDriver(String browser, String path){
		
		if(browser.equals("Chrome")){
		
			System.setProperty("webdriver.chrome.driver", path);
   		 	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
   		 	capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
   		 	HashMap<String, String> chromePreferences = new HashMap<String, String>();
   		 	chromePreferences.put("profile.password_manager_enabled", "false");
   		 	capabilities.setCapability("chrome.prefs", chromePreferences);
   		
   		 	driver = new ChromeDriver(capabilities);
			
			
		}else{
			
			File pathToBinary = new File(path);
			   FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			   FirefoxProfile firefoxProfile = new FirefoxProfile();
			   firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
			   driver=new FirefoxDriver(ffBinary,firefoxProfile);
			   
			
			
		}
		
	}
	
	
	public void startMethod(){
		
		System.out.println("This is execution of startMethod");
		
		driver.get("https://www.google.pl/");
		
	}

	public void endMethod(){
	
		System.out.println("This is execution of endMethod");
	
		driver.quit();
	}
	
}
