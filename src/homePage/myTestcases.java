package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
	
	@Test(priority = 8)
	public void FillHotleTab() {
		WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTap.click();
		
		String [] EnglishCities = {"Dubai","Jeddah","Riyadh"};
		int randimEnglishCity = rand.nextInt(EnglishCities.length);
		String [] ArabicCities = {"دبي","جدة"};
		int randimArabicCity = rand.nextInt(ArabicCities.length);
	
		String WebsiteURL = driver.getCurrentUrl();
		WebElement SearchHotelInputField = driver.findElement(By.xpath("//input[@data-testid=\"AutoCompleteInput\"]"));

		if(WebsiteURL.contains("ar")) {
		SearchHotelInputField.sendKeys(ArabicCities[randimArabicCity]);
		}
		
		else {
			SearchHotelInputField.sendKeys(EnglishCities[randimEnglishCity]);
		}
	
		
		WebElement ListOfLocations =driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		System.out.println(ListOfLocations.findElements(By.tagName("li")).size());
		WebElement firstResult = ListOfLocations.findElements(By.tagName("li")).get(1);
		firstResult.click();

	}
	
	@Test(priority = 9)
	public void RandomSelectThenumberofVistor() {
		
		WebElement SelectorofTheVistor = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		
		Select select = new Select(SelectorofTheVistor);

		// By index
		int randomIndex = rand.nextInt(2);
		select.selectByIndex(randomIndex);


		WebElement SearchHotelButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelButton.click();
	}
	
	@Test(priority = 10,enabled = false)

	public void CheckThePageFullyLoaded() throws InterruptedException {

		boolean expectedResult = true;
		Thread.sleep(10000);
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']"))
				.getText();

		boolean finished = results.contains("وجدنا") || results.contains("found");

		Assert.assertEquals(finished, expectedResult);

	}

	@Test(priority = 11, enabled = false)

	public void SortItemsLowestToHighestPrice() {

		boolean expectedResults = true;
		WebElement LowestPriceSortButton = driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));

		LowestPriceSortButton.click();

		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));

		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));

//		List<WebElement> thePrices = driver.findElements(By.cssSelector(".Price__Wrapper.PriceDisplay__FinalRate.sc-dRCTWM.GFIG"));
//		
//		
		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();

		System.out.println(LowestPrice);
		System.out.println(HighestPrice);

//		

		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
		int HighestPriceAsInt = Integer.parseInt(HighestPrice);

		boolean ActualResults = LowestPriceAsInt < HighestPriceAsInt;

		Assert.assertEquals(ActualResults, expectedResults);

	}

}
