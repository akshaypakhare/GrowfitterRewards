package fitkit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import growfitter.Base;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import pageObjects.HomePage;
import pageObjects.fitkitSessions;

public class Dashboard extends Base{
	
	@Test
	public void openDashboard() throws InterruptedException {
		System.out.println("Entered openDashboard test");
		HomePage hp = new HomePage(driver);
		boolean dashboardOption = hp.DashboardOption.isDisplayed();
		if(dashboardOption==true) {
			System.out.println("about to click on dash");
			Thread.sleep(3000);
			hp.DashboardOption.click();
			Thread.sleep(5000);
			//check if valid sessions & total sessions text is displayed.
			fitkitSessions fs = new fitkitSessions(driver);
			Boolean dashboardOpened = fs.SessionTimeline.isDisplayed();
			Assert.assertTrue(dashboardOpened);
			driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
		}
	}
	
	@Test
	public void validateRedeemedSessions() throws InterruptedException {
		
		System.out.println("Entered validateRedeemedSessions test");
		String valSesCount = getValidSessions();
		System.out.println("back to validateRedeemedSessions test");
		HomePage hp = new HomePage(driver);
		hp.DashboardOption.click();
		if(valSesCount!=null) {
			System.out.println("valid session count is : " +valSesCount);
			fitkitSessions fs = new fitkitSessions(driver);
			String redeemedSessions = fs.RedeemedSessions.getText();
			Assert.assertTrue(valSesCount.equalsIgnoreCase(redeemedSessions));
		}else {
			Assert.assertFalse(true);
		}
		
	}
	
	public String getValidSessions() throws InterruptedException {
		System.out.println("Entered getValidSessions methd");
		HomePage hp = new HomePage(driver);
		hp.SessionOption.click();
		Thread.sleep(5000);
		fitkitSessions fs = new fitkitSessions(driver);
		Boolean sessionTitle = fs.SessionsTitle.isDisplayed();
		if(sessionTitle) {
			String sessionTitleText = fs.SessionsTitle.getText();
			String validSessions = StringUtils.substringBetween(sessionTitleText, "Valid sessions: ", " | Total sessions:");
			String validCount = validSessions.trim();
			System.out.println("valid : " + validCount);
			driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			return validCount;
		}
		return null;
	}
	
	@Test
	public void validateSessionTimeline() {
		//In sessions timeline, validate the last 14 dates+days to be proper.
		System.out.println("Entered validateSessionTimeline");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd,E");
		LocalDateTime now = LocalDateTime.now();
		fitkitSessions fs = new fitkitSessions(driver);
		
		//get last element in the timeline.
		String lastExpected = dtf.format(now);
		System.out.println("last element value : " + lastExpected);
		String lastActual = fs.LastElement.getText();
		Boolean lastResult = lastActual.equals(lastExpected);
		System.out.println(lastResult);
		
		//get first element in the timeline.
		String firstExpected = dtf.format(now.minusDays(13));
		System.out.println("first element value : " + firstExpected);
		String firstActual = fs.FirstElement.getText();
		Boolean firstResult = firstActual.equals(firstExpected);
		
		Boolean finalResult = false;
		if(lastResult==true & firstResult==true) {
			finalResult = true;
		}
		Assert.assertTrue(finalResult);
	}

}
