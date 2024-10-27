package homePage;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	String Website ="https://global.almosafer.com/en";
	Random rand = new Random();
	String ExpectedDefaultLanguage ="en";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	boolean ExpectedResultsForTheLogo = true;
	String ExpectedHotel = "false";
	LocalDate todayDate = LocalDate.now();	
	int Today = todayDate.getDayOfMonth();
	int ExpectedTomorrow = Today +1 ;
	int ExpectedThedayAfterTomorrow = ExpectedTomorrow +1 ;
	boolean expectedResult = true;
	
	
	String [] EnglishCities = {"Dubai","Jeddah","Riyadh"};
	int randimEnglishCity = rand.nextInt(EnglishCities.length);
	String [] ArabicCities = {"دبي","جدة"};
	int randimArabicCity = rand.nextInt(ArabicCities.length);

	
	public void RandomSelectTheLanguageOfTheWebSite() 
	{
		String[] URLs = { "https://www.almosafer.com/en", "https://www.almosafer.com/ar", };
		int RandomIndex = rand.nextInt(URLs.length);
		driver.get(URLs[RandomIndex]);
	}
	
	
	public void GeneralSetup () 
	{
		driver.manage().window().maximize();		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(Website);
	}
	
}
