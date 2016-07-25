package testTools;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;



import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.Keys;


public class Tools {
	
	public WebDriver driver;
	public WebElement element;
	private int timeout = 10;
	
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
		
		//driver.get("https://www.google.pl/");
		
	}

	public void endMethod(){
	
		System.out.println("This is execution of endMethod");
	
		driver.quit();
	}
	
	public void getGoogleCom(){
		
		driver.get("http://www.google.com");
		
	}
	
	public void getGooglePL(){
		
		driver.get("http://www.google.pl");
		
	}
	
	public void checkGoogleResult(WebElement element){
		
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='g']/div/h3[@class='r']/a")));
		
		String oldURL = driver.getCurrentUrl();
		
		element.click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='g']/div/h3[@class='r']/a")));
		
		Assert.assertFalse("Didn't transfer to result page!", oldURL==driver.getCurrentUrl());
		
		driver.get(oldURL);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='g']/div/h3[@class='r']/a")));
		
	}
	
	public void search(WebElement element){
		
		element.sendKeys("magia internetu");

		element.sendKeys(Keys.RETURN);
		
		element.click();
		
		
		
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("sblsbb")));
		
		waitASec();

		
	}
	
	public void goToNextPage(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		String page = driver.findElement(By.xpath("//td[@class='cur']/span")).getText();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='cur']/span")));
		
		element.click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[@class='cur']/span[text()='"+ page +"']")));
		waitASec();
	}
	
	public void waitAfter(){
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rcnt")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("rcnt")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='cur']/span")));
		
	}
	
	public void waitASec(){
		
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
