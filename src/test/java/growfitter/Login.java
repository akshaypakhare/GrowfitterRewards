package growfitter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import growfitter.Base;
import growfitter.LoginInputs;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SignupPage;

public class Login extends Base{
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}

	@Test(dataProvider="LoginCredential", dataProviderClass=LoginInputs.class)
	public void puserLogin(String number, String pin) throws InterruptedException, IOException {
		System.out.println("entered tests");
		service=startServer();
		AndroidDriver<AndroidElement> driver= capabilities("fitkit");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp=new LoginPage(driver);
		lp.mobileNo.sendKeys(number);
		lp.acceptTnc.click();
		lp.login.click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//enter pin page
		for (int i = 3, j = 0; i <= 8; i++, j++) {
			//create path to each pin field, 6.
			String pinPath=lp.getPinPath();
			String pinPath1=pinPath+i;
			System.out.println(pinPath1);
			//input each value in separate pin fields
			char pinValue = pin.charAt(j);
			driver.findElementById(pinPath1).sendKeys(Character.toString(pinValue));
		}
		
		if(lp.submitPin.isDisplayed()) {
			lp.submitPin.click();
		}
		verifyLoginSuccess();
		service.stop();
	}
	
	public static void verifyLoginSuccess() {
		//check if logout button is displayed to know if login is successful.
		HomePage hp=new HomePage(driver);
		if(hp.Menu.isDisplayed()) {
			hp.Menu.click();
		}
		boolean logout=hp.Logout.isDisplayed();
		Assert.assertTrue(logout);
	}
	
	@Test(dataProvider="LoginCredential", dataProviderClass=LoginInputs.class)
	public void loginInvalPin(String number, String pin) throws InterruptedException, IOException {
		System.out.println("entered tests");
		service=startServer();
		AndroidDriver<AndroidElement> driver= capabilities("fitkit");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp=new LoginPage(driver);
		lp.mobileNo.sendKeys(number);
		lp.acceptTnc.click();
		lp.login.click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//enter pin page
		if(lp.submitPin.isDisplayed()) {
			for (int i = 3, j = 0; i <= 8; i++, j++) {
				//create path to each pin field, 6.
				String pinPath=lp.getPinPath();
				String pinPath1=pinPath+i;
				System.out.println(pinPath1);
				//input each value in separate pin fields
				char pinValue = pin.charAt(j);
				driver.findElementById(pinPath1).sendKeys(Character.toString(pinValue));
			}
			lp.submitPin.click();
		}
		verifyInvalidPin();
		service.stop();
	}
	
	@Test(dataProvider="LoginCredential", dataProviderClass=LoginInputs.class)
	public void loginInvalid(String number) throws InterruptedException, IOException {
		// this test is currently used for puser inactive entry case. can be used for non-reg gf rew too.
		service=startServer();
		AndroidDriver<AndroidElement> driver= capabilities("fitkit");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.mobileNo.sendKeys(number);
		lp.acceptTnc.click();
		lp.login.click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//check if the start now page is displayed
		isDisplayedStartNow();
		service.stop();
	}
	
	public void isDisplayedStartNow() {
		SignupPage sp = new SignupPage(driver);
		Boolean startnow = sp.StartNow.isDisplayed();
		Assert.assertTrue(startnow);
	}
	
	@Test(dataProvider="LoginCredential", dataProviderClass=LoginInputs.class)
	public void loginLive(String number, String pin) throws InterruptedException, IOException {
		// this test is currently used for puser inactive entry case. can be used for non-reg gf rew too.
		service=startServer();
		AndroidDriver<AndroidElement> driver= capabilities("fitkit");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.mobileNo.sendKeys(number);
		lp.acceptTnc.click();
		lp.login.click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//enter pin page
		if(lp.submitPin.isDisplayed()) {
			for (int i = 3, j = 0; i <= 8; i++, j++) {
				//create path to each pin field, 6.
				String pinPath=lp.getPinPath();
				String pinPath1=pinPath+i;
				System.out.println(pinPath1);
				//input each value in separate pin fields
				char pinValue = pin.charAt(j);
				driver.findElementById(pinPath1).sendKeys(Character.toString(pinValue));
			}
			lp.submitPin.click();
		}
		verifyBookASlot();
		service.stop();
	}

	public void verifyBookASlot() {
		//check if the login is successful for live corp user by verifying if the book a slot option is displayed.
		HomePage hp = new HomePage(driver);
		Boolean bookaslot = hp.BookASlot.isDisplayed();
		Assert.assertTrue(bookaslot);
	}
	
	@Test(dataProvider="LoginCredential", dataProviderClass=LoginInputs.class)
	public void loginRewardUser(String number, String pin, String status) throws InterruptedException, IOException {
		// this test is currently used for reward active user entry case.
		service=startServer();
		AndroidDriver<AndroidElement> driver= capabilities("fitkit");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		LoginPage lp = new LoginPage(driver);
		lp.mobileNo.sendKeys(number);
		lp.acceptTnc.click();
		lp.login.click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//enter pin page
		if(lp.selectCustomer.isDisplayed()) {
			lp.selectCustAcc.click();
		}
		if(lp.submitPin.isDisplayed()) {
			for (int i = 3, j = 0; i <= 8; i++, j++) {
				//create path to each pin field, 6.
				String pinPath=lp.getPinPath();
				String pinPath1=pinPath+i;
				System.out.println(pinPath1);
				//input each value in separate pin fields
				char pinValue = pin.charAt(j);
				driver.findElementById(pinPath1).sendKeys(Character.toString(pinValue));
			}
			lp.submitPin.click();
		}
		// check the status
		if(status.equals("1")) {
			verifyInvalidPin();
		}else {
			verifyRewardsOption();
		}
		service.stop();
	}
	
	public void verifyRewardsOption() {
		//check if the login is successful for live corp user by verifying if the book a slot option is displayed.
		HomePage hp = new HomePage(driver);
		Boolean rewardsOption = hp.RewardsOption.isDisplayed();
		Assert.assertTrue(rewardsOption);
	}
	
	public void verifyInvalidPin() {
		// check if the invalid pin error message is displayed.
		LoginPage lp = new LoginPage(driver);
		boolean invalidPin=lp.invalidPin.isDisplayed();
		Assert.assertTrue(invalidPin);
	}
	
}
