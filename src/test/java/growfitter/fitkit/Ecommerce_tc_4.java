package growfitter.fitkit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import growfitter.fitkit.LoginInputs;
import growfitter.fitkit.Utilities;
import growfitter.fitkit.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.checkoutPage;
import pageObjects.formPage;

public class Ecommerce_tc_4 extends Base {
	
	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
	@Test(dataProvider="Name", dataProviderClass=LoginInputs.class)
	public void totalValidation(String name) throws IOException, InterruptedException {
		service=startServer();
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApk");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		formPage fp=new formPage(driver);
		fp.getNameField().sendKeys(name);
		driver.hideKeyboard();
		fp.FemaleGender.click();
		fp.SomeText.click();
		Utilities u=new Utilities(driver);
		u.scrollToText("Argentina");
		fp.Country.click();
		fp.LetsShopButton.click();
		fp.AddToCart1.get(0).click();
		fp.AddToCart2.get(0).click();
		fp.CartIcon.click();
		
		Thread.sleep(4000);
		checkoutPage cp=new checkoutPage(driver);
		int count=cp.ProductPrice.size();
		double sum=0;
		for(int i=0;i<count;i++){
			String amount1=cp.ProductPrice.get(i).getText();
			double amount=getAmount(amount1);
			sum=sum+amount;
		}
		System.out.println(sum+"sum of products");
		//String total=cp.TotalAmount.getText();
		/*
		 * //mobile gestures test WebElement
		 * checkbox=driver.findElementByClassName("android.widget.CheckBox");
		 * TouchAction t=new TouchAction(driver);
		 * t.tap(TapOptions.tapOptions().withElement(element(checkbox))).perform();
		 * 
		 * 
		 * //WebElement
		 * tnc=driver.findElementById("com.androidsample.generalstore:id/termsButton");
		 * //t.longPress(LongPressOptions.longPressOptions().withElement(element(tnc))).
		 * perform(); driver.findElementById("android:id/button1").click(); driver.
		 * findElementByXPath("//android.widget.Button[@text='Visit to the website to complete purchase']"
		 * ).click();
		 * 
		 * //driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").
		 * click(); Thread.sleep(7000); Set<String> contexts=driver.getContextHandles();
		 * for(String contextName :contexts) { System.out.println(contextName); }
		 * driver.context("WEBVIEW_com.androidsample.generalstore");
		 * driver.findElementByName("q").sendKeys("Growfitterstore.com");
		 * driver.findElementByName("q").sendKeys(Keys.ENTER); driver.pressKey(new
		 * KeyEvent(AndroidKey.BACK));
		 */
		service.stop();
	}
	
	public static double getAmount(String value){
		value=value.substring(1);
		double amountValue=Double.parseDouble(value);
		return amountValue;
	}

}