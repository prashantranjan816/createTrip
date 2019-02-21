package library;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	
	
	/*
	 * By calling of This class we can take screenshot of browser page at any time. 
	 * We cab call this class like as: < Utility.getScreenshot(driver);>
	 * create a folder with name "screenshot" all screenshot will store there.
	*/
	public static void getScreenshot(WebDriver driver,String screenshotName) { 
		            
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);    //casting TakesScreenshot interface.       
		
		// now copy the  screenshot to desired location using copyFile method
		 
		try {
			FileUtils.copyFile(src, new File("./screenshot/"+screenshotName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Screenshot Taken");
		 
		 

	}
}

