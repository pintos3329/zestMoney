package pages.flipkart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import connector.Connector;

public class FlipkartHomePage extends Connector{

	WebDriver flipkartDriver=getChromeDriver();
	WebDriverWait wait= new WebDriverWait(flipkartDriver, 200);
	String parentWindow="";
	String newWindow="";
	
	public void launchFlipkart() {
		flipkartDriver.manage().window().maximize();
		flipkartDriver.get("https://www.flipkart.com");
		if(wait.until(ExpectedConditions.visibilityOf(flipkartDriver.findElement(By.xpath(".//button[text()='✕']")))).isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(flipkartDriver.findElement(By.xpath(".//button[text()='✕']")))).click();
		}
		WebElement searchBox= wait.until(ExpectedConditions.visibilityOf(flipkartDriver.findElement(By.xpath(".//input[@title='Search for products, brands and more']"))));
	}
	public void searchProduct(String product) {
		WebElement searchBox= wait.until(ExpectedConditions.visibilityOf(flipkartDriver.findElement(By.xpath(".//input[@title='Search for products, brands and more']"))));
		searchBox.sendKeys(product);
		WebElement buttonGo=flipkartDriver.findElement(By.xpath(".//button[@type='submit']"));
		buttonGo.click();		
	}
	public void clickProduct(String product) {
		String exactProd="";
		if(product.equalsIgnoreCase("iphone xr 64gb yellow")){
			exactProd="Apple iPhone XR (Yellow, 64 GB)";
		}				
		WebElement searchedProduct= wait.until(ExpectedConditions.elementToBeClickable(flipkartDriver.findElement(By.xpath("//img[@alt='"+exactProd+"']"))));
		//JavascriptExecutor js= JavascriptExecutor("arguments[0].click();")
		searchedProduct.click();	
		parentWindow=flipkartDriver.getWindowHandle();
		System.out.println(flipkartDriver.getWindowHandle());
	}
	public String checkProductPrice() {
		Set<String> windows= new HashSet<String>();
		windows.addAll(flipkartDriver.getWindowHandles());
		System.out.println(windows);
		List<String> allWindows= new ArrayList<String>();
		String currentWindow="";		
		for(String str: windows) {
			allWindows.add(str);
		}
		for(int i=0;i<allWindows.size();i++) {
			if(i==allWindows.size()-1) {
				currentWindow= allWindows.get(i);				
			}
		}
		System.out.println("currenetWindow:"+currentWindow);		
		flipkartDriver.switchTo().window(parentWindow);
		WebElement productValue= wait.until(ExpectedConditions.visibilityOf(flipkartDriver.findElement(By.xpath(".//div[@class='_1vC4OE _3qQ9m1']"))));
		String getValue= productValue.getText();
		System.out.println("vale:"+getValue);
		String productPrice=getValue.replace("₹", "");
		return productPrice;
	}
}
