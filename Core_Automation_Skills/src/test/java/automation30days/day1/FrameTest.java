package automation30days.day1;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FrameTest {

	WebDriver driver;
	@BeforeMethod
	public void setUp() {
	driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://the-internet.herokuapp.com/nested_frames");
	}
	
	@Test
	public void frameTest() {
		 // Switch to TOP -> LEFT
        WebElement topFrame = driver.findElement(By.xpath("//frame[@name='frame-top']"));
        driver.switchTo().frame(topFrame);
        driver.switchTo().frame("frame-left");
        String leftFrameText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Left Frame: " + leftFrameText);
        Assert.assertTrue(leftFrameText.equals("LEFT"), "Left frame text mismatch");

        // Switch to TOP -> MIDDLE
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-middle");
        String middleFrameText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Middle Frame: " + middleFrameText);
        Assert.assertEquals(middleFrameText, "MIDDLE", "Middle frame text mismatch");

        // Switch to TOP -> RIGHT
        driver.switchTo().parentFrame();
        driver.switchTo().frame("frame-right");
        String rightFrameText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Right Frame: " + rightFrameText);
        Assert.assertEquals(rightFrameText, "RIGHT", "Right frame text mismatch");

        // Switch to BOTTOM frame
        driver.switchTo().defaultContent();
        WebElement bottomFrame = driver.findElement(By.xpath("//frame[@name='frame-bottom']"));
        driver.switchTo().frame(bottomFrame);
        String bottomFrameText = driver.findElement(By.tagName("body")).getText();
        System.out.println("Bottom Frame: " + bottomFrameText);
        Assert.assertEquals(bottomFrameText, "BOTTOM", "Bottom frame text mismatch");
		
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
	
}
