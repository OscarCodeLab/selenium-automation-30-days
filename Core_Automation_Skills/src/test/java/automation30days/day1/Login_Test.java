package automation30days.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login_Test {

	WebDriver driver;
	@BeforeMethod
	public void setUp() {
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://the-internet.herokuapp.com/login");
	}
	
	@Test
	public void loginTest() {
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		
		String actual_Value = driver.findElement(By.id("flash")).getText();
		System.out.println(actual_Value);
		String expected_Value = "You logged into a secure area!";
		
		if(actual_Value.contains(expected_Value)) {
			System.out.println("Login Successful");
		}else {
			System.out.println("Login Fail");
		}
		
		Assert.assertTrue(actual_Value.contains(expected_Value), "Expected success message not found!");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
