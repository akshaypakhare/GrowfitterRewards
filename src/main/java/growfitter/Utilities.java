package growfitter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utilities {
	AndroidDriver<AndroidElement> driver;
	public Utilities(AndroidDriver<AndroidElement> driver) {
		this.driver=driver;
	}
	
	public void scrollToText(String containedText) {
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\""+ containedText + "\").instance(0))");
	}
}