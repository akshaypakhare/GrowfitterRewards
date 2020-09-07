package fitkit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import growfitter.Base;
import growfitter.LoginInputs;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class Login extends Base{
	
   	@BeforeSuite
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
//	@BeforeTest
	public void invokeApp() throws InterruptedException, IOException {
		System.out.println("entered invokeApp");
		service=startServer();
	}
	
	/*
	 * @AfterTest public void Close() { service.stop(); }
	 */
	
	@Test(dataProvider="DBlogincred", dataProviderClass=LoginInputs.class)
	public void loginFitkit(String number, String pin, String status) throws InterruptedException, IOException {
		// this test is currently used for reward active user entry case.
		System.out.println("entered loginFitkit");
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
		//service.stop();
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
