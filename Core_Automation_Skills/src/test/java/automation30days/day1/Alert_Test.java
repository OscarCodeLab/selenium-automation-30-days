package automation30days.day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Alert_Test {

	WebDriver driver;
	@BeforeMethod
	public void setUp() {
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://the-internet.herokuapp.com/javascript_alerts");
	}
	
	@Test
	public void dropdownTest() throws InterruptedException {
	
	//JS Alert
	driver.findElement(By.xpath("//button[@onclick = 'jsAlert()']")).click();
	Alert alert = driver.switchTo().alert();
	alert.accept();
	String actualText = driver.findElement(By.id("result")).getText();
	String expectedText = "You successfully clicked an alert";
	Assert.assertEquals(actualText, expectedText);
	Thread.sleep(3000);
	
	//JS Confirm
	driver.findElement(By.xpath("//button[@onclick = 'jsConfirm()']")).click();
	 alert = driver.switchTo().alert();
	alert.dismiss();
	String comfirmText = driver.findElement(By.id("result")).getText();
	Assert.assertEquals(comfirmText, "You clicked: Cancel");
	Thread.sleep(3000);
	
	//JS Prompt
	driver.findElement(By.xpath("//button[@onclick = 'jsPrompt()']")).click();
	 alert = driver.switchTo().alert();
	alert.sendKeys("hello");
	alert.accept();
	String actText = driver.findElement(By.id("result")).getText();
	Assert.assertEquals(actText, "You entered: hello");
	Thread.sleep(3000);
	}
	

	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
