package automation30days.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Dropdown_Handling_Test {

	WebDriver driver;
	@BeforeMethod
	public void setUp() {
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://the-internet.herokuapp.com/dropdown");
	}
	
	@Test
	public void dropdownTest() throws InterruptedException {
	WebElement drpdown= 	driver.findElement(By.id("dropdown"));
	Select s = new Select(drpdown);
	s.selectByValue("2");
		Thread.sleep(3000);
		String selectOption = s.getFirstSelectedOption().getText();
		String expectedOption ="Option 2";
		if(selectOption.equals(expectedOption)) {
			System.out.println("Dropdown selection successful");
		} else {
			System.out.println("Dropdown selection failed");
		}
	}
	

	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
