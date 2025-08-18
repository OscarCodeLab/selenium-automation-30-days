package automation30days.day1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DatePicker {
	String day = "17";
	String month = "April";
	String year = "2024";
	
	WebDriver driver;
	WebDriverWait wait;
	 Actions a;
	@BeforeClass
	public void setUp() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 driver.get("https://demo.automationtesting.in/Datepicker.html");
	}
	
	@Test(priority = 1)
	public void datePickerTest1() {
		driver.findElement(By.id("datepicker1")).click();
	
	while(true){
		String currentMonth = driver.findElement(By.xpath("//span[@class= 'ui-datepicker-month']")).getText();
		String currentYear = driver.findElement(By.xpath("//span[@class= 'ui-datepicker-year']")).getText();
		if(currentMonth.equals(month) && currentYear.equals(year)) {
			break;
		}
		
		driver.findElement(By.xpath("//span[@class= 'ui-icon ui-icon-circle-triangle-w']")).click();
	}
	
	List <WebElement> dates = 	driver.findElements(By.xpath("//tbody/tr/td[@data-handler = 'selectDay']"));
	
	for(WebElement date : dates) {
		if(date.getText().equals(day)) {
			date.click();
			break;
		}
	}
	
	}
	
	@Test(priority = 2)
	public void datePickerTest2() {
		driver.findElement(By.id("datepicker2")).click();
		WebElement curentmonth = driver.findElement(By.xpath("(//select[@class= 'datepick-month-year'])[1]"));
		
		Select s = new Select(curentmonth);
		s.selectByVisibleText(month);
		
		WebElement curentyear = driver.findElement(By.xpath("(//select[@class= 'datepick-month-year'])[2]"));	
		Select s1 = new Select(curentyear);
		s1.selectByVisibleText(year);
		
		List <WebElement> dates =driver.findElements(By.xpath("//tbody/tr/td/a[@href = 'javascript:void(0)']"));
		
		for (WebElement date : dates) {
			String days = date.getText();
			if(days.equals(day)) {
				date.click();
				break;
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
