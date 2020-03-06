package tests;

import org.testng.annotations.Test;

import connector.Connector;

public class CompareIPhoneXR64GBYellowPrice extends Connector{
	
	@Test
	public void compareIPhoneXR64GBYellowPrice() {
		amazonhomePage.launchAmazon();
		amazonhomePage.searchProduct("iphone xr 64gb yellow");
		amazonhomePage.clickProduct("iphone xr 64gb yellow");
		String amazonPrice=amazonhomePage.checkProductPrice();
		quitDriver();
		flipkartHomePage.launchFlipkart();
		flipkartHomePage.searchProduct("iphone xr 64gb yellow");
		flipkartHomePage.clickProduct("iphone xr 64gb yellow");
		String flipakartPrice=flipkartHomePage.checkProductPrice();
		quitDriver();		
		comparePrice(flipakartPrice,amazonPrice);
	}

}
