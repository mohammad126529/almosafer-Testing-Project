package homePage;

import java.time.Duration;

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
	
	@BeforeTest
	public void mySetup() 
	{
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(Website);
		driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']")).click();
	}
	
	@Test(priority = 1)
	public void CheckTheDefaultLanguageIsEnglish() 
	{
	
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
	
}
