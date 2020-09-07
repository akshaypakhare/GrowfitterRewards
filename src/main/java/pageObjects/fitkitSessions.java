package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class fitkitSessions {

	//constructor code below
		public fitkitSessions(AppiumDriver<AndroidElement> driver){
			PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
		
		@AndroidFindBy(id="com.growfitter.fitkit:id/cardView7")
		public List<WebElement> SessionsBox;
		
		@AndroidFindBy(id="com.growfitter.fitkit:id/sessions")
		public WebElement SessionsTitle;
		
		//-- Dashboard page objects below --
		
		@AndroidFindBy(id="com.growfitter.fitkit:id/progresstext")
		public WebElement RedeemedSessions;
		
		@AndroidFindBy(id="com.growfitter.fitkit:id/scan")
		public WebElement Back;
		
		@AndroidFindBy(xpath="//*[@text='Session timeline']")
		public WebElement SessionTimeline;
		
		@AndroidFindBy(id="com.growfitter.fitkit:id/d7")
		public WebElement LastElement;
		
		@AndroidFindBy(id="com.growfitter.fitkit:id/d21")
		public WebElement FirstElement;
}
