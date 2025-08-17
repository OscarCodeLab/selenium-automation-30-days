package automation30days.day1;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MouseOverTest {

	WebDriver driver;
	@BeforeClass
	public void setUp() {
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	}
	
	@Test(priority = 1)
	public void mouseover() {
		driver.get("https://the-internet.herokuapp.com/hovers");
		WebElement over = driver.findElement(By.xpath("(//img[@alt = 'User Avatar'])[1]"));
		Actions a = new Actions(driver);
		a.moveToElement(over).perform();
		driver.findElement(By.xpath("//a[@href = '/users/1']")).click();
		String actualUrl = driver.getCurrentUrl();
		System.out.println(actualUrl);
		Assert.assertTrue(actualUrl.contains("users/1"), "The URL doesn't contain users/1 ");
	}
	
	@Test(priority = 2)
	public void contextClickTest() { //Right click test using actions class
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://the-internet.herokuapp.com/context_menu");
		WebElement rightClick = driver.findElement(By.id("hot-spot"));
		Actions a = new Actions(driver);
		a.contextClick(rightClick).perform();
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		String expectedtext = "You selected a context menu";
		Assert.assertEquals(text, expectedtext);
		System.out.println(text);
		alert.accept();
		
	}
	
	@Test(priority = 3)
	public void ClickandContextClickTest() { //Right click test using actions class
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClick = driver.findElement(By.xpath("//span[text() = 'right click me']"));
		Actions a = new Actions(driver);
		a.contextClick(rightClick).perform();
		driver.findElement(By.xpath("//li[normalize-space() = 'Edit']")).click();
		
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		Assert.assertEquals(text, "clicked: edit", "Text not found");
		System.out.println(text);
		alert.accept();
		
	}
	
	
	@AfterClass
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
}
