package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class formPage {
		//constructor code below
		public formPage(AppiumDriver<AndroidElement> driver){
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}

		@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
		private WebElement NameField;
		
		@AndroidFindBy(xpath="//*[@text='Female']")
		public WebElement FemaleGender;
		
		@AndroidFindBy(id="android:id/text1")
		public WebElement SomeText;
		
	/*
	 * public void scrollToText(String containedText) { driver.findElement(MobileBy.
	 * AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""
	 * + containedText + "\").instance(0))")); }
	 */
		
		@AndroidFindBy(xpath="//*[@text='Argentina']")
		public WebElement Country;
		
		@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
		public WebElement LetsShopButton;
		
		@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
		public List<WebElement> AddToCart1;
		
		@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
		public List<WebElement> AddToCart2;
		
		@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
		public WebElement CartIcon;
		
		public WebElement getNameField() {
			System.out.println("Trying to get the namefield");
			return NameField;
		}
}
