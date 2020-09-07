package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	
	//constructor code below
	public HomePage(AppiumDriver<AndroidElement> driver){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.growfitter.fitkit:id/scan")
	public WebElement Menu;
	
	@AndroidFindBy(xpath="//*[@text='Logout']")
	public WebElement Logout;
	
	@AndroidFindBy(xpath="//*[@text='Book a slot']")
	public WebElement BookASlot;
	
	@AndroidFindBy(xpath="//*[@text='Rewards']")
	public WebElement RewardsOption;

	@AndroidFindBy(xpath="//*[@text='Sessions']")
	public WebElement SessionOption;
	
	@AndroidFindBy(xpath="//*[@text='Dashboard']")
	public WebElement DashboardOption;
}
