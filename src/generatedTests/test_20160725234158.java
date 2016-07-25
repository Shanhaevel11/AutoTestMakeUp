package generatedTests;

import java.io.IOException;
import org.testng.annotations.Test;
import testTools.Tools;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class test_20160725234158 extends Tools {

	WebElement element;

	@Test
	public void generatedTest1() throws InterruptedException, IOException {
		prepareWebDriver("Chrome", "D:\\magisterka\\drivers\\googlechrome\\chromedriver.exe");
		driver.get("http://www.google.pl");
		element = driver.findElement(By.id("lst-ib"));
		search(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		element = driver.findElement(By.xpath("//div[@class='g']/div/h3/a"));
		checkGoogleResult(element);
		waitAfter();
		element = driver.findElement(By.xpath("//td/a/span[contains(text(),'Next') or contains(text(),'Nastêpna') ]"));
		goToNextPage(element);
		waitAfter();
		endMethod();
	}
}
