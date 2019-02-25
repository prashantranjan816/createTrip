package ZipgoBeta.CreateTrip;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.util.RetryAnalyzerCount;

import jxl.read.biff.BiffException;
import library.Utility;

public class CreateTrip extends BasePage {

	private static final int DispalyCanName = 0;
	private final By corporateLoginButton = By.xpath("//button[@data-provider='corporate-login']");
	final By loginUserName 			= By.xpath("//*[@id='loginUsername']");

	final By loginPassword 			= By.id("loginPassword");
	final By signInButton 			= By.xpath("//input[@value='Sign in']");
	final By clipchevoronright 		= By.xpath("//*[@class='clip-chevron-right']");
	final By transportation 		= By.xpath("//span[text()='Transportation']");
	final By bustrips 				= By.xpath("//span[text()='Bus Trips']");
	final By createnew 				= By.xpath("//a[text()='Create New']");
	final By clickbusname 			= By.xpath("//span[text()='Type a bus name or phone']");
	final By sendbusname 			= By.xpath("(//input[@class='select2-search__field'])[4]");
	final By selectbusname 			= By.xpath("//li[@class='select2-results__option select2-results__option--highlighted']");
	final By waitforbusname 		= By.xpath("//li[@class='select2-results__option select2-results__option--highlighted']");
	final By maxseats 				= By.id("cab_trip_maximum_seats");
	final By racseats 				= By.id("cab_trip_max_rac_seats");
	final By actrip 				= By.id("select2-cab_trip_ac_bus-container");
	final By routegroup 			= By.id("select2-cab_trip_route_group_id-container");
	final By routegroup1 			= By.xpath("(//input[@class='select2-search__field'])[4]");
	final By waitforroute 			= By.xpath("//li[@class='select2-results__option select2-results__option--highlighted']");
	final By operatingdate 			= By.id("cab_trip_operating_date");
	final By waitfordate 			= By.xpath("//td[@class='today day']");
	final By starttime 				= By.id("select2-cab_trip_start_hour-container");
	final By starthour 				= By.xpath("(//input[@class='select2-search__field'])[4]");
	final By startminute 			= By.id("select2-cab_trip_start_minute-container");
	final By sendstartminute 		= By.xpath("(//input[@class='select2-search__field'])[4]");
	final By pricerule 				= By.id("select2-cab_trip_price_rule_id-container");
	final By selectuniform 			= By.xpath("(//li[@role='treeitem'])[3]");
	final By payementplan 			= By.id("select2-cab_trip_payment_plan-container");
	final By testplantrip 			= By.xpath("//li[text()='TestPlan-Trip']");
	final By categoryname 			= By.id("cab_trip_display_category_name");
	final By quicksave 				= By.xpath("//button[@class='btn btn-danger btn-sm pull-right']");

	final By close 					= By.xpath("(//button[@class='close'])[2]");

	String UserName 				= "prashant.zipgouser@gmail.com";
	String Password 				= "1234567891";
	String FileLocation 			= "./src/main/resources/CreateTrip.properties";
	String SelectURL 				= "BetaUrl";
	String mailid 					= "prashant.zipgouser@gmail.com";
	String mailpwd 					= "Zipgouser@321";
	String DisplayCabName;
	int TripCount;
	int TripTime;
	String RouteGroupName;
	String BusName;
	String statusmsg;
	String TC1MSG;
	String dateFinal;
	String DayName;
	int j = 0;
	int expectedTripCount=8;
	int tripno = 1;

	@Test(priority=1,invocationCount=1,enabled=true,retryAnalyzer = RetryAnalyzerCount.class)
	public void CreateTestTrip() throws IOException, InterruptedException, BiffException {
		
//	---	---- --- use Java calendar concept---------------------------
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 0);//insted of 0 we can use +-1 to indrease or decrease current date.
		dateFinal = df.format(cal.getTime());
		
		
//---- ----- ---Use java calendar for find day---- ------- -----------------
		 Date todayDay = new Date();
	        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
	        DayName=simpleDateformat.format(todayDay);
	        System.out.println(DayName);
//===================================================================================	 

		File src = new File(FileLocation);
		FileInputStream fis = new FileInputStream(src);
		Properties pro = new Properties();
		pro.load(fis);
		System.out.println("Property class loaded");
		WebDriver driver = new ChromeDriver();
		driver.get(pro.getProperty(SelectURL));
		Thread.sleep(3000);

		Utility.getScreenshot(driver,dateFinal+"firstScreenShot");			//Take screenshot & store in screenshot folder.

		driver.findElement(corporateLoginButton).click();
		driver.findElement(loginUserName).sendKeys(UserName, Keys.ENTER);
		driver.findElement(loginPassword).sendKeys(Password, Keys.ENTER);
		// driver.findElement(signInButton).click();
		Thread.sleep(4000);
		driver.findElement(clipchevoronright).click();
		driver.findElement(transportation).click();
		driver.findElement(bustrips).click();
		// ---------------------------------------------------------------------------------------
		
//		RouteGroupName = "Anekal To Rmz Eco World Via Jail Road";
		RouteGroupName = "Banashankari To Bagmane Tech Park Via BTM Layout, Koramangala 100 ft road, Indiranagar";
//		RouteGroupName = "Banashankari To Iblur Via Slik Board";
//		BusName = "autodriver";
		BusName = "prashant07";

		DisplayCabName = "Automated Trip No::";
		
//		--------For Reverse Route enable below code and disable above three line code!!!
		
		/*RouteGroupName = "bhagmane To banashankari Via via Indiranagar, Koramangla 100 ft Road, BTM Layout";
		BusName = "prashnat";
		DisplayCabName = "Automation>>Reverse trip>>>bhagmene to bansankri,No::";*/
		
		String SHr = "1";
		String SMnt = "1";
		
		try {
			for (int i = 1; i <= expectedTripCount; i++) {
				Thread.sleep(2000);
				driver.findElement(createnew).click();
				Thread.sleep(3000);

				driver.findElement(clickbusname).click();

				driver.findElement(sendbusname).sendKeys(BusName); // Bus Name Updating
				
				/*WebElement testquick= driver.findElement(sendbusname); // It is customize SendKey method
				sendkey(driver,testquick ,10,BusName);*/

				Thread.sleep(4000);

				driver.findElement(selectbusname).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(routegroup).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(routegroup1).sendKeys(RouteGroupName); // Update RouteGroupName
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(waitforroute).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(operatingdate).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(waitfordate).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(starttime).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(starthour).sendKeys(SHr + j, Keys.ENTER);
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(startminute).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(sendstartminute).sendKeys(SMnt + j, Keys.ENTER);
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(pricerule).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(selectuniform).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(payementplan).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(testplantrip).click();
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

				driver.findElement(categoryname).sendKeys(DisplayCabName + tripno +" for "+DayName);
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			driver.findElement(quicksave).click();
				Thread.sleep(5000);

				driver.navigate().refresh();

				tripno += 1;// Trip Number will increase and display section
				TripCount++;
				TripTime=10+j;
				
				j += 1;// Count in increasing order... till loop.
				
				
			

//	#TC1
if(TripCount==expectedTripCount){
			statusmsg="PASS";
			TC1MSG="TEST:PASS, Due to Trip count matched:";
			System.out.println("TEST:PASS, Trip count matched:"+TripCount);
}
else {
			statusmsg="FAIL";
			TC1MSG="TEST:FAIL, Due to Trip count missmatch";
			System.out.println("TEST:FAIL, Trip count missmatch"+TripCount);
}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Script intrupted here ( please refer fail screenshot or due to "+e);
			e.printStackTrace();
		}
		
		
		
		
		
		Utility.getScreenshot(driver,dateFinal+"SecondScreenShot");			//Take screenshot & store in screenshot folder.

//	#TC2
	
		
		
		driver.quit();
	}

	
	@Test(priority=2,invocationCount=1,enabled=true,retryAnalyzer = RetryAnalyzerCount.class)
	
	
	public void sendMail() throws EmailException, MalformedURLException {
		
		String mailSubject="AutomationReport of DailyTripCreat";
		String HostName="smtp.gmail.com";
		int SmtpPort=465;
		
		String mailaddto1="prashant.ranjan@zipgo.in";
		String mailaddto2="siddhartha@zipgo.in";
		String mailaddto3="dharmendra@zipgo.in";
		

		System.out.println("Sending attachement mail..");
		
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("./screenshot/"+dateFinal+"SecondScreenShot.png");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setName(dateFinal+"SecondScreenShot.png");
		
		
		
		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(HostName);
		email.setSmtpPort(SmtpPort); //smtpPort=465 fix for Gmail.
		email.setAuthenticator(new DefaultAuthenticator(mailid, mailpwd));
		email.setSSLOnConnect(true);
		email.setFrom(mailid);
		email.setSubject(mailSubject+" |[ "+dateFinal+"]:"+statusmsg);
		email.addTo("prashant.ranjan@zipgo.in");
	
		
		email.addReplyTo(mailaddto1);

		email.setMsg("#Total Trip Created = "+TripCount
					+"   |   "
					+ " #Beta-DriverID = "+BusName
					+ "  |   "
					+ " #Last Trip @ "+TripTime+":"+TripTime
					+ "  |   "
					+ " #Server = "+SelectURL
					+ "  |   "
					+ " #Status is "+TC1MSG+ ">Expected Trip count is : "+expectedTripCount+", Actual Trip count is: "+j
					+ "  |   "
					+ "#RouteGroup = "+RouteGroupName);
		//Attached the file
		email.attach(attachment);
		// send the email
		email.send();
		System.out.println("Sent!");
		//Commit Test
	}
	

}
