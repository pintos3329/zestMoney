package pages.amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import connector.Connector;

public class AmazonHomePage extends Connector{
	WebDriver amazonDriver=getChromeDriver();
	WebDriverWait wait= new WebDriverWait(amazonDriver, 200);
	String parentWindow="";
	String newWindow="";
	
	public void launchAmazon() {
		amazonDriver.manage().window().maximize();
		amazonDriver.get("https://www.amazon.in");
		WebElement searchBox= wait.until(ExpectedConditions.visibilityOf(amazonDriver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))));
	}
	public void searchProduct(String product) {
		WebElement searchBox= wait.until(ExpectedConditions.visibilityOf(amazonDriver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))));
		searchBox.sendKeys(product);
		WebElement buttonGo=amazonDriver.findElement(By.xpath("//input[@value='Go']"));
		buttonGo.click();		
	}
	public void clickProduct(String product) {
		String exactProd="";
		if(product.equalsIgnoreCase("iphone xr 64gb yellow")){
			exactProd="Apple iPhone XR (64GB) - Yellow";
		}				
		WebElement searchedProduct= wait.until(ExpectedConditions.visibilityOf(amazonDriver.findElement(By.xpath("//span[text()='"+exactProd+"']"))));
		searchedProduct.click();	
		parentWindow=amazonDriver.getWindowHandle();
		System.out.println(amazonDriver.getWindowHandle());
	}
	public String checkProductPrice() {
		Set<String> windows= new HashSet<String>();
		windows.addAll(amazonDriver.getWindowHandles());
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
		amazonDriver.switchTo().window(parentWindow);
		WebElement productValue= wait.until(ExpectedConditions.visibilityOf(amazonDriver.findElement(By.xpath("//span[@id='priceblock_ourprice']"))));
		String getValue= productValue.getText();
		System.out.println("vale:"+getValue);
		String productPrice="";
		if(getValue.contains("?")) {
			String[] valueArr=getValue.split("?");
			productPrice=valueArr[1].trim();
		}
		return productPrice;
	}

}
