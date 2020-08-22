package growfitter.fitkit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import growfitter.fitkit.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class TestInvoke extends Base{

	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
	@Test()
	public void invokeFitkit() throws InterruptedException, IOException {
		service=startServer();
		AndroidDriver<AndroidElement> driver=capabilities("fitkit");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("successfully invoked fitkit.apk. Hurry!!");
		service.stop();
	}
}
