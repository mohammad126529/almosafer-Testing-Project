package homePage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestcases extends Parameters {
	
	
	@BeforeTest
	public void mySetup() 
	{
		GeneralSetup();
		WebElement GreenButton = driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary"));
		GreenButton.click();	
	}
	
	@Test(priority = 1)
	public void CheckTheDefaultLanguageIsEnglish()
	{
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		Assert.assertEquals(ActualLanguage , ExpectedDefaultLanguage);
	}
		
	@Test(priority = 2)
	public void CheckDefaultCurrency() 
	{	
		String ActualCurrency = driver.findElement(By.xpath("//button[@data-testid=\"Header__CurrencySelector\"]")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);
	}
	
	
	@Test(priority = 3)
	public void CheckContactNumber() 
	{
		String ActualContactNumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
	}
	
	@Test(priority = 4)
	public void CheckQitafLogo() 
	{
		WebElement theFooter = driver.findElement(By.tagName("footer"));
		boolean ActualResultForTheLogo = theFooter.findElement(By.cssSelector(".sc-ghsgMZ.hIElfs")).findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-ciodno.lkfeIG")).isDisplayed();
		Assert.assertEquals(ActualResultForTheLogo, ExpectedResultsForTheLogo);
	}
	
	@Test(priority = 5)
	public void TestHotelTabNotSelected() 
	{
		String ActualValue = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		Assert.assertEquals(ActualValue, ExpectedHotel);
	}
	
	@Test(priority = 6)
	public void CheckDepatureDate() 
	{
		List<WebElement> depatureAndArrivalDates = driver.findElements(By.className("hNjEjT"));
		String ActualDepatureDate = depatureAndArrivalDates.get(0).getText();
		String ActualFlightReturn = depatureAndArrivalDates.get(1).getText();

		int ActualDepatureDateAsInt = Integer.parseInt(ActualDepatureDate);
		int ActualFlightReturnAsInt = Integer.parseInt(ActualFlightReturn);
		
		Assert.assertEquals(ActualDepatureDateAsInt, ExpectedTomorrow);
		Assert.assertEquals(ActualFlightReturnAsInt, ExpectedThedayAfterTomorrow);
	}
	
	@Test(priority = 7)
	public void RandomChangeTheLanguage()
	{
		RandomSelectTheLanguageOfTheWebSite();
	}
	
	@Test(priority = 8)
	public void FillHotleTab() 
	{
		WebElement HotelTap = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelTap.click();
		
		String WebsiteURL = driver.getCurrentUrl();
		WebElement SearchHotelInputField = driver.findElement(By.xpath("//input[@data-testid=\"AutoCompleteInput\"]"));

		if(WebsiteURL.contains("ar"))
		{
			SearchHotelInputField.sendKeys(ArabicCities[randimArabicCity]);
		}
		
		else 
		{
			SearchHotelInputField.sendKeys(EnglishCities[randimEnglishCity]);
		}
	
		WebElement ListOfLocations =driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
		WebElement firstResult = ListOfLocations.findElements(By.tagName("li")).get(1);
		firstResult.click();
	}
	
	@Test(priority = 9)
	public void RandomSelectThenumberofVistor() 
	{
		WebElement SelectorofTheVistor = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select select = new Select(SelectorofTheVistor);

		// By index
		int randomIndex = rand.nextInt(2);
		select.selectByIndex(randomIndex);


		WebElement SearchHotelButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		SearchHotelButton.click();
	}
	
	@Test(priority = 10,enabled = false)
	public void CheckThePageFullyLoaded() 
	{
		String results = driver.findElement(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")).getText();
		boolean finished = results.contains("وجدنا") || results.contains("found");
		Assert.assertEquals(finished, expectedResult);
	}

	@Test(priority = 11, enabled = false)

	public void SortItemsLowestToHighestPrice()
	{
		boolean expectedResults = true;
		WebElement LowestPriceSortButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
		LowestPriceSortButton.click();

		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> AllPrices = PricesContainer.findElements(By.className("Price__Value"));
	
		String LowestPrice = AllPrices.get(0).getText();
		String HighestPrice = AllPrices.get(AllPrices.size() - 1).getText();
		
		int LowestPriceAsInt = Integer.parseInt(LowestPrice);
		int HighestPriceAsInt = Integer.parseInt(HighestPrice);

		boolean ActualResults = LowestPriceAsInt < HighestPriceAsInt;
		Assert.assertEquals(ActualResults, expectedResults);
	}

}
