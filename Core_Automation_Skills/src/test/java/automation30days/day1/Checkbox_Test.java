package automation30days.day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Checkbox_Test {
	WebDriver driver;
	@BeforeMethod
	public void setUp() {
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://the-internet.herokuapp.com/checkboxes");
	}
	
	@Test
	public void checkboxTest() {
	List <WebElement> checkboxes = driver.findElements(By.xpath("//input[@type = 'checkbox']"));
	for(int z = 0; z<checkboxes.size(); z++) {
		checkboxes.get(z).click();
	} 
	
	if (checkboxes.get(0).isSelected() && !checkboxes.get(1).isSelected()) {
		System.out.println("Checkbox handling successful");
	}
	else {
		System.out.println("Checkbox handling failed");
	}
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
