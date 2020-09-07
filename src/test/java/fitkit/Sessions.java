package fitkit;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import growfitter.Base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import pageObjects.HomePage;
import pageObjects.fitkitSessions;

public class Sessions extends Base{

	//@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
	@Test
	public void openSessions() throws InterruptedException, IOException {
		System.out.println("Entered openSessions test");
		HomePage hp = new HomePage(driver);
		boolean sessionsOption = hp.SessionOption.isDisplayed();
		if(sessionsOption==true) {
			hp.SessionOption.click();
			Thread.sleep(5000);
			//check if valid sessions & total sessions text is displayed.
			fitkitSessions fs = new fitkitSessions(driver);
			Boolean sessionsOpened = fs.SessionsTitle.isDisplayed();
			Assert.assertTrue(sessionsOpened);
		}
		//service.stop();
	}
	
	public void validateSessions() {
		//Assert.assertTrue(sessionsOption);
		fitkitSessions fs = new fitkitSessions(driver);
		List<WebElement> objList = fs.SessionsBox;
		System.out.println(objList);
//		Utilities u=new Utilities(driver);
//		u.scrollToText("Argentina");
		
		org.openqa.selenium.Point p1=getPoint(objList.get(objList.size()-1));
		org.openqa.selenium.Point p2=getPoint(objList.get(0));
			 
        new TouchAction<>(driver)
                .press(PointOption.point(p1.x, p1.y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(5000)))
                .moveTo(PointOption.point(p2.x, p2.y)).release().perform();
	
	}
	
	private org.openqa.selenium.Point getPoint(WebElement element)
	{
		
		return element.getRect().getPoint();
		
	}
	
	@Test
	public void validateSessionCount() {
		System.out.println("entered validateSessionCount");
		fitkitSessions fs = new fitkitSessions(driver);
		Boolean SessionsTrue = fs.SessionsTitle.isDisplayed();
		if(SessionsTrue) {
			String sessionTitle = fs.SessionsTitle.getText();
			String validSessions = StringUtils.substringBetween(sessionTitle, "Valid sessions: ", " | Total sessions:");
			String totalSessions = StringUtils.substringAfter(sessionTitle, " | Total sessions:");
			String validCount = validSessions.trim();
			String totalCount = totalSessions.trim();
			System.out.println("valid : " + validCount);
			System.out.println("total : " + totalCount);
			if (validCount != null && Integer.valueOf(validCount) < Integer.valueOf(totalCount)) {
				Assert.assertTrue(true);
		    }else {
		    	Assert.assertFalse(true);
			}
			System.out.println("clicking back");
			fs.Back.click();

		}
	}
	
}
