package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {

	//constructor
	public LoginPage(AppiumDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id="com.growfitter.fitkit:id/edit_id")
	public WebElement mobileNo;
	
	@AndroidFindBy(id="com.growfitter.fitkit:id/tncimage")
	public WebElement acceptTnc;
	
	@AndroidFindBy(id="com.growfitter.fitkit:id/tnctext")
	private WebElement tnc;
	
	@AndroidFindBy(xpath="//*[@text='Log in']")
	public WebElement login;
	
	@AndroidFindBy(id="com.growfitter.fitkit:id/sign_up_here")
	private WebElement signUp;
	
	@AndroidFindBy(xpath="//*[@text='Change language']")
	private WebElement changeLang;
	
	@AndroidFindBy(id="com.growfitter.fitkit:id/versionhome")
	private WebElement getVersion;

	@AndroidFindBy(id="com.growfitter.fitkit:id/linearLayout")
	public WebElement pin;
	
	@AndroidFindBy(xpath="//*[@text='SUBMIT']")
	public WebElement submitPin;
	
	@AndroidFindBy(xpath="//*[@text='Invalid Pin']")
	public WebElement invalidPin;
	
	@AndroidFindBy(xpath="//*[@text='Select Customer']")
	public WebElement selectCustomer;
	
	@AndroidFindBy(xpath="//*[@text='akshay.gymtrekker1@gmail.com']")
	public WebElement selectCustAcc;
	
	public String getPinPath() {
		System.out.println("Trying to get the namefield");
		String pinPath="com.growfitter.fitkit:id/textView";
		return pinPath;
	}
	
}
