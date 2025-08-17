package automation30days.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KeyboardTest {

	WebDriver driver;
	WebDriverWait wait;
	 Actions a;

	@BeforeClass
	public void setUp() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	@Test(priority = 1)
	public void keyBoardTest() throws InterruptedException{
		driver.get("https://text-compare.com/");
		WebElement box1 = driver.findElement(By.id("inputText1"));
		box1.sendKeys("This is ok, I Like it.");
		a = new Actions(driver);
		a.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
		a.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
		
		WebElement box2 = driver.findElement(By.id("inputText2"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", box2);
		wait.until(ExpectedConditions.elementToBeClickable(box2));
        box2.click();
		a.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();	
	
	String text1 = box1.getAttribute("value");
	String text2 = box2.getAttribute("value");
	
	Assert.assertEquals(text1, text2, "The text in both are not thesame");
	
	/*
	 * note Input/textarea fields → element.getAttribute("value")
	Labels, divs,spans, li, headings → element.getText()
	 */
	}
	
	@Test(priority = 2)
	public void dragAndDropTest() {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://the-internet.herokuapp.com/drag_and_drop");
		WebElement box1 = driver.findElement(By.id("column-a"));
		WebElement box2 = driver.findElement(By.id("column-b"));
		a.dragAndDrop(box1, box2).perform();
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
