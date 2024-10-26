package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestcases {
	
	WebDriver driver = new ChromeDriver();
	String Website ="https://global.almosafer.com/en";
	String ExpectedDefaultLanguage ="en";
	
	Random rand = new Random();
	
	@BeforeTest
	public void mySetup() 
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(Website);
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']")).click();
	}
	
	@Test(priority = 1)
	public void CheckTheDefaultLanguageIsEnglish() {
	
		System.out.println(driver.findElement(By.tagName("html")).getAttribute("lang"));
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage , ExpectedDefaultLanguage);
	
	}
		
	@Test(priority = 2)
	public void CheckDefaultCurrency() {
		
		String ExpectedCurrency = "SAR";
		WebElement Currency = driver.findElement(By.xpath("//button[@data-testid=\"Header__CurrencySelector\"]"));
		String ActualCurrency = Currency.getText();
		System.out.println(ActualCurrency);
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}
	
	
	
	@Test(priority = 3)
	public void CheckContactNumber() {
		
		String ExpectedContactNumber = "+966554400000";
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		System.out.println(ActualContactNumber);
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}
	
	@Test(priority = 4)
	public void CheckQitafLogo() {
		
	WebElement theFooter = driver.findElement(By.tagName("footer"));
	boolean ActualResultForTheLogo = theFooter.findElement(By.cssSelector(".sc-ghsgMZ.hIElfs")).findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
	System.out.println(ActualResultForTheLogo);	
	Assert.assertEquals(ActualResultForTheLogo, true);
	
	}
	
	@Test(priority = 5)
	public void TestHotelTabNotSelected() {
	
		String ExpectedHotel = "false";
		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String ActualValue =HotelTab.getAttribute("aria-selected");
		System.out.println(HotelTab.getAttribute("aria-selected"));
		Assert.assertEquals(ActualValue, ExpectedHotel);

	}
	
	@Test(priority = 6)
	public void CheckDepatureDate() {
	
	LocalDate todayDate = LocalDate.now();
	System.out.println( LocalDate.now().getDayOfMonth());
	
	int Today = todayDate.getDayOfMonth();
	int ExpectedTomorrow = Today +1 ;
	int ExpectedThedayAfterTomorrow = ExpectedTomorrow +1 ;
	
	System.out.println(ExpectedTomorrow );
	System.out.println(ExpectedThedayAfterTomorrow );
	
	
	List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("hNjEjT"));
	String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
	String ActualFlightReturn = depatureAndArrivalDates.get(1).getText();
	
	System.out.println(ActualDepatureDate);
	System.out.println(ActualFlightReturn);
	
	int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
	int ActualFlightReturnAsInt = Integer.parseInt(ActualFlightReturn);
	
	Assert.assertEquals(ActualDepatureDateAsInt, ExpectedTomorrow);
	Assert.assertEquals(ActualFlightReturnAsInt, ExpectedThedayAfterTomorrow);
	
	}
	
	@Test(priority = 7)
	public void RandomChangeTheLanguage() {
		
		String [] URLs = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
		int RandomURL = rand.nextInt(URLs.length);
		driver.get(URLs[RandomURL]);
	}

}
