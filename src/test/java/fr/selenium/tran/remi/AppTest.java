package fr.selenium.tran.remi;

import java.time.LocalDateTime;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/*
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	private static final String URL="http://www.fr.jal.co.jp/frl/en/";
	private static final String PATH_CHROME_DRIVER="C:\\chromedriver_win32\\chromedriver.exe";
	public static WebDriver driver;
	
	private static String flightNumberAllerDispo="";
	private static String flightNumberAllerFin="";
	private static String flightNumberRetourDispo="";
	private static String flightNumberRetourFin="";
	private static String dateAllerPageDispo="";
	private static String dateAllerPageFin="";
	private static String heureAllerPageDispo="";
	private static String heureAllerPageFin="";
	private static String dateRetour="";
	private static String prixTotalConfirm="";
	private static String prixTotalFin="";
	
	//private String flightNumber="";
	
    public static void firstTest() {
    	System.setProperty("webdriver.chrome.driver", PATH_CHROME_DRIVER);
    	driver= new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    	driver.get(URL);
    	
    	pause(1);  //permet d'attendre que le pop-up sur l'information des cookies s'affiche  	
    	// avec le .cssSelector on peut faire suivre les sélecteurs css avec un espace entre:
    	driver.findElement(By.cssSelector("#JS_ciBox_contents .JS_ciBox_submit")).click();
    	// ou faire:
    	//driver.findElement(By.className("JS_ciBox_submit")).click();
    	
    	//page2: sélection des destinations et dates
    	Select s= new Select(driver.findElement(By.id("mdlDepArea1")));
    	s.selectByValue("GB_CM");
    	s= new Select(driver.findElement(By.id("mdlDepLocation1")));
    	s.selectByValue("GLA");
    	s= new Select(driver.findElement(By.id("mdlArrArea1")));
    	s.selectByValue("AU_CM");
    	s= new Select(driver.findElement(By.id("mdlArrLocation1")));
    	s.selectByValue("MEL");
    	s= new Select(driver.findElement(By.id("DEPARTURE_DATE_1_MONTH")));
    	s.selectByValue("3");
    	s= new Select(driver.findElement(By.id("DEPARTURE_DATE_1_DAY")));
    	s.selectByValue("15");
    	s= new Select(driver.findElement(By.id("DEPARTURE_DATE_2_MONTH")));
    	s.selectByValue("3");
    	s= new Select(driver.findElement(By.id("DEPARTURE_DATE_2_DAY")));
    	s.selectByValue("30");
    	
    	driver.findElement(By.id("mdlFormSubmit")).click();
    	pause(1);
    	//clic sur le bouton du pop-up choix de la langue
    	driver.findElements(By.cssSelector(".ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only")).get(1).click();
    	
    	if (driver.findElement(By.cssSelector("#flightNumber-1-0 .flight-identifier")).isDisplayed()) {
			System.out.println("le flight number retour s'affiche");
		}
    	else {
    		System.out.println("le flight number retour ne s'affiche pas et on clic sur le bouton 'more details'");
    		driver.findElements(By.cssSelector(".medium-pattern.mb1.p5")).get(1).click();
		}
    	pause(1);
    	flightNumberAllerDispo=driver.findElement(By.cssSelector(".flight-no .flight-identifier")).getText();
    	dateAllerPageDispo=driver.findElement(By.cssSelector("#departureDateTime-0 .screenreader-only.sr-only")).getText();
    	heureAllerPageDispo=driver.findElement(By.cssSelector(".col.departure.col_1_of_3.border-right.pl05.pr05 .worldClock.hour .screenreader-only.sr-only")).getText();
    	flightNumberRetourDispo= driver.findElement(By.cssSelector("#flightNumber-1-0 .flight-identifier")).getText();
    	//clic sur bouton continue de la page de sélection des vols
    	driver.findElement(By.id("continueButton")).click();
    	//passenger information
    	s= new Select(driver.findElement(By.id("0-title")));
    	s.selectByValue("PROF");
    	driver.findElement(By.id("0-last-name")).sendKeys("PRESLEY");
    	driver.findElement(By.id("0-first-name")).sendKeys("Elvis");
    	s= new Select(driver.findElement(By.id("0-gender")));
    	s.selectByValue("string:MALE");
    	s= new Select(driver.findElement(By.id("0-birth-date-day")));
    	s.selectByValue("string:15");
    	s= new Select(driver.findElement(By.id("0-birth-date-month")));
    	s.selectByValue("string:11");
    	s= new Select(driver.findElement(By.id("0-birth-date-year")));
    	s.selectByValue("string:1971");
    	s= new Select(driver.findElement(By.id("0-nationality")));
    	s.selectByValue("string:US");
    	s= new Select(driver.findElement(By.id("phone1-phone-type-0")));
    	s.selectByValue("string:APB");
    	s= new Select(driver.findElement(By.id("phone1-phone-country-0")));
    	s.selectByValue("ARG");
    	
    	driver.findElement(By.id("phone1-phone-number-0")).sendKeys("0664121571");
    	driver.findElement(By.id("email-guest-address")).sendKeys("e.presley@gmail.com");
    	driver.findElement(By.id("email-confirm-new")).sendKeys("e.presley@gmail.com");
    	    	
    	driver.findElement(By.id("continueButton")).click();
    	//pop-up confirm
    	//pause(2);
    	driver.findElement(By.id("continueButton-PCOF")).click();
    	//pause(1);
    	//select seat
    	driver.findElement(By.id("seatContinue")).click();
    	pause(1);
    	//confirmation
    	prixTotalConfirm =driver.findElement(By.id("sidebarPriceSummaryTotalPrice")).getText();
    	driver.findElement(By.id("purchaseButton")).click();
    	
    	driver.findElement(By.id("CCnb")).sendKeys("0664121571010203");
    	s= new Select(driver.findElement(By.id("expiration-month-id")));
    	s.selectByValue("number:12");
    	s= new Select(driver.findElement(By.id("expiration-year-id")));
    	s.selectByValue("number:2020");
    	driver.findElement(By.id("sec-code")).sendKeys("571");
    	
    	try {
    		System.out.println("début du wait: "+LocalDateTime.now());
    		WebDriverWait wait = new WebDriverWait(driver, 8);
        	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier"))));
		} catch (TimeoutException e) {
			// TODO: handle exception
			System.out.println("fin du wait, il est: "+ LocalDateTime.now()+" et j'ai pas trouvé ton webElemnt");
		}
    	
    	if (driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier")).isDisplayed()) {
			System.out.println("le flight number aller s'affiche");
		}
    	else {
    		System.out.println("le flight number aller ne s'affiche pas et on clic sur le bouton 'more details'");
        	driver.findElements(By.cssSelector(".medium-pattern.mb1.p5")).get(0).click();

		}
    	flightNumberAllerFin=driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier")).getText();
    	//pause(1);   
    	if (driver.findElement(By.cssSelector("#flightNumber-1-0 .flight-identifier")).isDisplayed()) {
			System.out.println("le flight number retour s'affiche");
		}
    	else {
    		System.out.println("le flight number retour ne s'affiche pas et on clic sur le bouton 'more details'");
    		driver.findElements(By.cssSelector(".medium-pattern.mb1.p5")).get(1).click();
		}
    	
    	try {
    		System.out.println("j'ai bien trouvé ton webElement");
    		WebDriverWait wait = new WebDriverWait(driver, 15);
        	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#flightNumber-0-0 .flight-identifier"))));
		} catch (TimeoutException e) {
			// TODO: handle exception
			System.out.println("j'ai pas trouvé ton webElement");
		}
    	flightNumberRetourFin=driver.findElement(By.cssSelector("#flightNumber-1-0 .flight-identifier")).getText();

    	prixTotalFin =driver.findElement(By.id("sidebarPriceSummaryTotalPrice")).getText();
    	
    	dateAllerPageFin=driver.findElement(By.cssSelector("#segmentOriginDate-0-0 .screenreader-only.sr-only")).getText();
    	
    	dateRetour=driver.findElement(By.cssSelector("#segmentOriginDate-1-0 .screenreader-only.sr-only")).getText();
    	
    	heureAllerPageFin=driver.findElements(By.cssSelector("time .screenreader-only.sr-only")).get(1).getText();
    	//nomPassager=
    	/*System.out.println("ville de départ: "+villeDepart);
    	System.out.println("ville d'arrivée: "+villeArrivee);*/
    	System.out.println("prix total confirm: "+prixTotalConfirm);
    	System.out.println("prix total fin: "+prixTotalFin);
    	System.out.println("date aller(1): "+dateAllerPageDispo);
    	System.out.println("date aller(2): "+dateAllerPageFin);
    	System.out.println("heure aller(1): "+heureAllerPageDispo);    	
    	System.out.println("heure aller(2): "+heureAllerPageFin);
    	System.out.println("vol aller1: "+flightNumberAllerDispo);
    	System.out.println("vol aller2: "+flightNumberAllerFin);
    	System.out.println("vol retour1: "+flightNumberRetourDispo);
    	System.out.println("vol retour2: "+flightNumberRetourFin);
    	
    	assertEquals( "num vol aller",flightNumberAllerDispo , flightNumberAllerFin);
    	assertEquals("date aller",dateAllerPageDispo , dateAllerPageFin );
    	assertEquals("prix total",prixTotalConfirm , prixTotalFin);
    	assertEquals("heure aller",heureAllerPageDispo , heureAllerPageFin);
    	assertEquals("num vol retour",flightNumberRetourDispo , flightNumberRetourFin);
    }
    
    private static void pause(int seconds) {
    	try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
			Thread.currentThread().interrupt();
		}
    }
}
