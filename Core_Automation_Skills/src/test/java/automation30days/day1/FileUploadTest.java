package automation30days.day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileUploadTest {
	
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
	public void fileUploadTest() {
		driver.get("https://practice.expandtesting.com/upload");
		WebElement inputField = driver.findElement(By.id("fileInput"));
		JavascriptExecutor js = ((JavascriptExecutor)driver);
		String path = System.getProperty("user.dir") + "/src/test/resources/data/Urgent Restock Request.pdf";
		js.executeScript("arguments[0].scrollIntoView(true)", inputField);
		inputField.sendKeys(path);
		String name =inputField.getAttribute("value");
		System.out.println(name);
		Assert.assertTrue(name.contains("Urgent Restock"), "wrong upload of file or no file uploaded");
		
	}
	@Test(priority = 2)
	public void multipleUploadTest() {
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://davidwalsh.name/demo/multiple-file-upload.php");
		WebElement multi = driver.findElement(By.id("filesToUpload"));
		String file1 = System.getProperty("user.dir") + "/src/test/resources/data/Urgent Restock Request.pdf";
		String file2 = System.getProperty("user.dir") + "/src/test/resources/data/📄 Feature Requirement Document.docx";
		multi.sendKeys(file1 + "\n" + file2);
		
		List <WebElement> filenames =driver.findElements(By.cssSelector("#fileList li"));
		for(WebElement filename :filenames) {
			String name = filename.getText();
			String name1 = "Urgent Restock Request.pdf";
			String name2 = "📄 Feature Requirement Document.docx";
			if(name.contains(name1) || name.contains(name2)) {
				System.out.println("file name 1 : "+ name1);
				System.out.println("file name 2 : "+ name2);
			} else {
				System.out.println("Multiple not uploaded");
			}
		}
	}
	
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
