package automation30days.day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class Widgets {

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

	@Test
	public void autocompleteTest() {
		driver.get("https://demo.automationtesting.in/AutoComplete.html");
		driver.findElement(By.id("searchbox")).sendKeys("os");
		List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@id= 'ui-id-1']/li"));
		wait.until(ExpectedConditions.visibilityOfAllElements(suggestions));
		int suggestionCount = 0;
		for (WebElement suggestion : suggestions) {
			suggestionCount++;
			String SuggestionName = suggestion.getText();
			System.out.println("Suggestion: " + SuggestionName);

		}

		System.out.println("Number of suggestions : " + suggestionCount++);

		for (WebElement suggestion : suggestions) {
			suggestionCount++;
			String SuggestionName = suggestion.getText();
			String expectedCountry = "Comoros";
			if (SuggestionName.equalsIgnoreCase(expectedCountry)) {
				suggestion.click();
				break;
			}
		}

		String actualname = driver.findElement(By.xpath("//div[text() = 'Comoros']")).getText();
		String expectname = "Comoros";
		Assert.assertEquals(actualname, expectname, "Country was not correctly selected");
	}
	@Test
	public void accordionTest() {
		//driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://demo.automationtesting.in/Accordion.html");
		List <WebElement> accords = driver.findElements(By.xpath("//div[@class = 'panel panel-default']"));	
		for(WebElement accord: accords) {
			wait.until(ExpectedConditions.visibilityOf(accord));
			accord.click();	
			
		}
		
	}

	
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
