package new_UI;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.StreamSupport;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;



public class Login extends TwiloSMS{
	
	HSSFWorkbook book;
	static HSSFSheet sheet;
	public static WebDriver driver;
	
    public static final String ACCOUNT_SID = "AC23f8da5cf53bde8c9d4b11fcf31c797a";
    public static final String AUTH_TOKEN = "d331cfb47d30a24b35c2572c3dce7f82";
	
   
	
	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extentReport;
	public static ExtentTest testCase;

	@FindBy(id ="logonIdentifier")
	public static WebElement username;
	
	@FindBy(id ="password")
	public static WebElement password;

	@FindBy(id ="next")
	public static WebElement next;
	
	@FindBy(id ="verifyCode")
	public static WebElement verifyCode;
	

	@FindBy(xpath = "//*[name()='path' and contains(@d,'M3 13h8V3H')]")
	public static WebElement logo;
	
	@FindBy(xpath = "//div[@class='search_filter_root']//div[1]//div[1]//div[1]//div[1]")
	public static WebElement organization;
	

	

	public void excelread() throws IOException, InterruptedException {

//		FileInputStream fis = new FileInputStream("E:\\Datafile.xls");
//		book = new HSSFWorkbook(fis);
//		sheet = book.getSheet("SPRINT9");
//		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

		htmlreporter = new ExtentHtmlReporter("extentReport.html");
		extentReport =new ExtentReports();
		extentReport.attachReporter(htmlreporter);
		htmlreporter.config().setReportName("Automation Testing");

	}



	public void initiliaze() throws InterruptedException, IOException {
	
	
	    excelread();
		testCase=extentReport.createTest("JavaScriptError for Login page", "Browser initilization");			


//		String Browser=sheet.getRow(3).getCell(1).getStringCellValue();
		System.setProperty("webdriver.chrome.driver","C:\\Users\\kamal\\Downloads\\chromedriver_70.exe");
		     
	        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	        LoggingPreferences loggingprefs = new LoggingPreferences();
	        loggingprefs.enable(LogType.BROWSER, Level.INFO);
	        capabilities.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
	        
//	        driver = new ChromeDriver(capabilities);
	
			
			testCase.log(Status.INFO, "In this project we using Chrome Browser");
			
			testCase.log(Status.PASS,"Chrome is initilized");
			ChromeOptions chromeoptions=new  ChromeOptions();
			chromeoptions.addExtensions(new File("E:\\crx\\cx.crx"));
			
			
//			DesiredCapabilities desiredCapabilities= DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY,chromeoptions);
			
		    driver = new ChromeDriver(capabilities);
		    
		    PageFactory.initElements(driver,Login.class);
		    PageFactory.initElements(driver,Search_manager.class);
	}

	
	//To Find JavaScriptError's:
	  public void ExtractJSLogs() {
		  testCase=extentReport.createTest("Login page-JS ERROR", "initilization the URL,Check JS ERROR");
	        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
	        for (LogEntry entry : logEntries) {
	            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
	            testCase.log(Status.FAIL, new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
	        }
	    }

	  
		public void cors() throws InterruptedException, AWTException {

			testCase=extentReport.createTest("Freeflow", "initilization URL");	
			
			driver.get("chrome://extensions/shortcuts");
			Actions act = new Actions(driver);
			Thread.sleep(1000);
			act.sendKeys(Keys.TAB).build().perform();
			// act.sendKeys(Keys.RETURN).build().perform();
			act.sendKeys(Keys.TAB).build().perform();
			act.sendKeys(Keys.TAB).build().perform();
			act.keyDown(Keys.CONTROL).sendKeys("1").build().perform();
			Thread.sleep(1000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_1);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_1);
			Thread.sleep(2000);
			
		}
	public void verification() throws InterruptedException, AWTException {

		testCase=extentReport.createTest("Login page", "initilization the URL,Check whether able to login");			

		cors();

		try {
			testCase.log(Status.INFO,"URL Entered successfully");
			driver.get("https://freeflowstorageqa.z33.web.core.windows.net/Search");
			ExtractJSLogs();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

			Thread.sleep(6000);
		} catch (InterruptedException e) {
			testCase.log(Status.FAIL,"Unable to login" + e);
			e.printStackTrace();
		}
	


		
		try {
			
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('logonIdentifier').value='shanmathi@logicvalley.in';");
			
		  
			Thread.sleep(4000);
			js.executeScript("document.getElementById('password').value='Bohu3855';");
			js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@id='next']")));
			
			
			
//			testCase.log(Status.INFO,"username Entered successfully");
//			username.sendKeys("shanmathi@logicvalley.in");
//			testCase.log(Status.INFO,"password Entered successfully");
//			password.sendKeys("Bohu3855");
//			testCase.log(Status.INFO,"Click OK");
			
		} catch (Exception e) {
			testCase.log(Status.FAIL,"Unable to Enter Uname & Password" + e);
			e.printStackTrace();
		}
		
//		Thread.sleep(5000);
//		
//		next.click();
		Thread.sleep(5000);
		verifyCode.click();
		Thread.sleep(35000);
		
		twilo();
		testCase.log(Status.INFO,"Login successfully");
		
	}
//		JavascriptExecutor js = (JavascriptExecutor) driver;  
//		
//		testCase.log(Status.PASS, "Entering Password");
//		js.executeScript("document.getElementById('next').click();");
//		System.out.println("Clicking next");
//		Thread.sleep(6000);
//		// Fetching previous OTP
//		String OTPold = TwiloSMS.ReciverOTP();
//		System.out.println(OTPold);
//		// js.executeScript("document.getElementById('localNumber').value='4123124643';");
//		// System.out.println("Entering number");
//		js.executeScript("document.getElementById('verifyCode').click();");
//		System.out.println("Sending Code ");
//
//		String OTP = TwiloSMS.ReciverOTP();
//		System.out.println(OTP);
//		WebElement textbox = driver.findElement(By.xpath("//*[@id='verificationCode']"));
//		Boolean temp = true;
//
//		while (temp) {
//			if (OTP.contentEquals(OTPold)) {
//				// resend code
//				Thread.sleep(2000);
//				js.executeScript("document.getElementById('retryCode').click();");
//				System.out.println("reSending Code");
//				Thread.sleep(5000);
//				OTP = TwiloSMS.ReciverOTP();
//			} else {
//				System.out.println("***OTP Generated****");
//				temp = false;
//				System.out.println("!!!!!!!" + OTP + "!!!!!!");
//			}
//		}
//
//		System.out.println("OTP entering method snd its length is : " + OTP.length());
//		// TypeInField(OTP);
//		// System.out.println("Entered");
//		Thread.sleep(3000);
//		WebElement textbox1 = driver.findElement(By.xpath("//*[@id='verificationCode']"));
//		Thread.sleep(2000);
//		// textbox1.sendKeys(OTP);
//
//		System.out.println("Sendiing human values");
//		sendHumanKeys(textbox1, OTP);
//
//		
//
//
//		Thread.sleep(30000);
//	}
//	
	private static void sendHumanKeys(WebElement element, String text) throws InterruptedException {
		Random r = new Random();
		Boolean temp = true;

		for (int i = 0; i < text.length(); i++) {
			if (temp) {
				element.sendKeys(String.valueOf(text.charAt(0)));
				temp = false;
			}
			Thread.sleep((int) (r.nextGaussian() * 15 + 100));
			String s = new StringBuilder().append(text.charAt(i)).toString();
			System.out.println(" *** Entering " + s);
			// act.moveToElement(element).build().perform();
			Thread.sleep(500);
			element.sendKeys(s);
		}
		
//	public static String getMessage() {
//		
//		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
//				.filter(m -> m.getTo().equals("+12058946954")).map(Message::getBody).findFirst()
//				.orElseThrow(IllegalStateException::new);
//	}
//
//
//	public static java.util.stream.Stream<Message> getMessages() {
//		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
//		return StreamSupport.stream(messages.spliterator(), false);
		
		
	}

			public void twilo() throws InterruptedException, AWTException {
				
				 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
					String smsBody = getMessage();
					JavascriptExecutor js = (JavascriptExecutor) driver;
					
					
					System.out.println("The full Message : \n"+smsBody.trim());
					String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
					OTPNumber=OTPNumber.replaceAll("\\s+", "");
					System.out.println("Only numbers:\n"+OTPNumber);
					OTPNumber=OTPNumber.substring(0,6);
					System.out.println(OTPNumber);
					String OTPold = TwiloSMS.ReciverOTP();
					System.out.println(OTPold);
					
					String OTP = TwiloSMS.ReciverOTP();
					System.out.println(OTP);
					
					
					Thread.sleep(4000);
					js.executeScript("document.getElementById('verificationCode').value ='" + OTPNumber + "';");
					

					Robot rob=new Robot();
					rob.keyPress(KeyEvent.VK_2);
					rob.keyRelease(KeyEvent.VK_2);
					Thread.sleep(30000);
					
					
					Boolean temp = true;
					
					while (temp) {
					if (OTP.contentEquals(OTPold)) {
						// resend code
						Thread.sleep(2000);
						js.executeScript("document.getElementById('retryCode').click();");
						System.out.println("reSending Code");
						Thread.sleep(5000);
						OTP = TwiloSMS.ReciverOTP();
					} else {
						System.out.println("***OTP Generated****");
						temp = false;
						System.out.println("!!!!!!!" + OTP + "!!!!!!");
					}
				}
		
				System.out.println("OTP entering method snd its length is : " + OTP.length());
				// TypeInField(OTP);
				// System.out.println("Entered");
				Thread.sleep(3000);
				WebElement textbox1 = driver.findElement(By.xpath("//*[@id='verificationCode']"));
				Thread.sleep(2000);
				// textbox1.sendKeys(OTP);
		
				System.out.println("Sendiing human values");
				sendHumanKeys(textbox1, OTP);
		
				
		
		

			
			
			
			
			
			
			
			
			
			
			
			
			
			

	
	
	
	
	}	

}
