package connector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.amazon.AmazonHomePage;
import pages.flipkart.FlipkartHomePage;

public class Connector {
	
	public AmazonHomePage amazonhomePage;
	public FlipkartHomePage flipkartHomePage;
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WebDriver getChromeDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
		driver= new ChromeDriver();
		return driver;
	}
	public WebDriverWait getGlobalWait(int sec) {
		wait= new WebDriverWait(driver, sec);
		return wait;
	}
	
	public void comparePrice(String price1, String price2) {		
		System.out.println("Price Difference is: "+(Integer.parseInt(price2)-Integer.parseInt(price1)));		
	}
	
	public void quitDriver() {		
		driver.quit();		
	}
}
