package new_UI;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.microsoft.azure.documentdb.DocumentClient;

import junit.framework.AssertionFailedError;

public class Search_manager extends Login{
	

	@FindBy(xpath = "//button[@id='Search_Toggle_Licence']")
	public static WebElement Licence;

	@FindBy(xpath = "//button[@id='Search_Toggle_Case']")
	public static WebElement Case;

	@FindBy(xpath = "//button[@id='Search_Toggle_FPN']")
	public static WebElement FCN;

	@FindBy(xpath = "//button[@id='Search_Toggle_PCN']")
	public static WebElement PCN;

	@FindBy(xpath = "//button[@id='Search_Toggle_Debt']")
	public static WebElement DEBT;

	@FindBy(xpath = "//div[@class='search_filter_root']//div[3]//div[1]//div[1]//div[1]")
	public static WebElement Organisation;

	@FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div/div[1]/div[4]/div/div/div")
	public static WebElement Contract;

	@FindBy(xpath = "//li[@id='Search_MulSel_8eca9029-3ec1-4c7f-99d4-cc0a38e6a05b']")
	public static WebElement FreeFlow;

	@FindBy(xpath = "//li[@id='Search_MulSel_a47921f2-1e1a-443a-baa9-71b5514c56d5']")
	public static WebElement TFL;

	@FindBy(xpath = "//li[@id='Search_MulSel_e8748a42-8cf6-4991-954e-e2f3a50bd27b']")
	public static WebElement PENALTYCHARGENOTICE;

	@FindBy(xpath = "//li[@id='Search_MulSel_360c1084-fea1-4f61-8c69-b67be7817dc3']")
	public static WebElement FF_PCN;

	@FindBy(xpath = "//li[@id='Search_MulSel_9617fb5f-8454-4222-b562-39ed281c35ca']")
	public static WebElement TFL_PCN;

	@FindBy(xpath = "//li[@id='Search_MulSel_3346bd94-78da-41fe-b3f3-c0423ff944cb']")
	public static WebElement TFL_PCN2;

	@FindBy(xpath = "//li[@id='Search_MulSel_6a4b71b6-fc49-46db-8a3a-52cda4d61d40']")
	public static WebElement TFL_PCN3;

	@FindBy(xpath = "//li[@id='Search_MulSel_6a4b71b6-fc49-46db-8a3a-52cda4d61d11']")
	public static WebElement CITIZEN_CONTRACT;

	@FindBy(xpath = "//span[@class='MuiButton-label']")
	public static WebElement filter_button;


	@FindBy(xpath = "//button[@id='Search_Toggle_Customer']")
	public static WebElement Toggle_Customer;
	
	@FindBy(xpath = "//button[@id='Search_Toggle_Driver']")
	public static WebElement Toggle_Driver;
	
	@FindBy(xpath = "//button[@id='Search_Toggle_Vehicle']")
	public static WebElement Toggle_Vehicle;
	
	@FindBy(xpath = "//button[@id='Search_Toggle_Operator']")
	public static WebElement Toggle_Operator;
	
	
	@FindBy(xpath = "//div[contains(text(),'All')]")
	public static WebElement Status_Dropdown;
	
	@FindBy(xpath = "//li[@id='Search_drp_Active']")
	public static WebElement Active;
	
	@FindBy(xpath = "//li[@id='Search_drp_Request']")
	public static WebElement Request;
	
	@FindBy(xpath = "//li[@id='Search_drp_All']")
	public static WebElement ALL;
	
String cases;
	
	public void CaseToggle() throws InterruptedException, AWTException {
		testCase=extentReport.createTest("Search Manager Screen", "Case_Toggle");			

				try {
					FCN.click();
					Thread.sleep(3000);
					
				//	filter_button.click();
				//	Thread.sleep(8000);
					
					DEBT.click();
					testCase.log(Status.PASS,"FPN & Debt buttons are clicked successfully");
				} catch (InterruptedException e) {
					testCase.log(Status.FAIL,"Unable to Click FPN button");
					e.printStackTrace();
				}

				try {
					PCN.click();
					Thread.sleep(3000);
				//	filter_button.click();
					Thread.sleep(8000);
					testCase.log(Status.PASS,"PCN button clicked successfully");
				} catch (InterruptedException e) {
					testCase.log(Status.FAIL,"Unable to Click PCN button");
					e.printStackTrace();
				}


		try {
			Organisation.click();
			testCase.log(Status.PASS,"Organization Dropdown clicked successfully");
		} catch (Exception e) {
			testCase.log(Status.FAIL,"Unable to Click Organization Dropdown");
			e.printStackTrace();
		}
		try {
			FreeFlow.click();
			Thread.sleep(3000);
			TFL.click();

			Robot rob=new Robot();
			rob.keyPress(KeyEvent.VK_ESCAPE);
			rob.keyRelease(KeyEvent.VK_ESCAPE);
			Thread.sleep(3000);


			testCase.log(Status.PASS,"Get case's against TFL successfully");
		} catch (InterruptedException e) {
			testCase.log(Status.FAIL,"Unable to Select Freeflow");
			e.printStackTrace();
		}

	}

public void LicenceToggle() throws InterruptedException {
	testCase=extentReport.createTest("Search Manager Screen", "License_Toggle");
	Thread.sleep(10000);
	
	
	Licence.click();
	Thread.sleep(3000);
	testCase.log(Status.INFO,"License_Toggle clicked successfully");
	
	try {
		Toggle_Customer.click();
		Thread.sleep(2000);
		
		Toggle_Driver.click();
		Thread.sleep(2000);
			
		Toggle_Vehicle.click();
		Thread.sleep(2000);
		
		Toggle_Operator.click();
		Thread.sleep(2000);
		
		filter_button.click();
		
		driver.navigate().refresh();
		Thread.sleep(6000);
		
		testCase.log(Status.PASS,"License_Toggle menus clicked successfully");
	} catch (InterruptedException e) {
		
		testCase.log(Status.FAIL,"License_Toggle menus are NOT clicked successfully");		
	}
	
}
	public void setContract() throws InterruptedException, AWTException {

		try {
			Contract.click();
			Thread.sleep(2000);

			PENALTYCHARGENOTICE.click();
			Thread.sleep(2000);

			FF_PCN.click();
			Thread.sleep(2000);

			TFL_PCN.click();
			Thread.sleep(2000);

			TFL_PCN2.click();
			Thread.sleep(2000);

			TFL_PCN3.click();
			Thread.sleep(2000);

			CITIZEN_CONTRACT.click();
			Thread.sleep(2000);
			
			FF_PCN.click();
			Thread.sleep(2000);
			
			TFL_PCN3.click();
			Thread.sleep(2000);
			
			FF_PCN.click();
			Thread.sleep(2000);

			testCase.log(Status.PASS,"Set Contract successfully");
		} catch (InterruptedException e) {

			testCase.log(Status.FAIL,"Failed to select contract");
		}

		Robot rob=new Robot();
		rob.keyPress(KeyEvent.VK_ESCAPE);
		rob.keyRelease(KeyEvent.VK_ESCAPE);
		Thread.sleep(3000);

		filter_button.click();

	}

	public String getvalue() {
		
		// DB Connection
		
		com.microsoft.azure.documentdb.FeedOptions queryOptions = new com.microsoft.azure.documentdb.FeedOptions();
		queryOptions.setEnableCrossPartitionQuery(true);
		DocumentClient c= new DocumentClient(DB_Connection.HOST, DB_Connection.MASTER_KEY, new com.microsoft.azure.documentdb.ConnectionPolicy(), com.microsoft.azure.documentdb.ConsistencyLevel.Session);

		String valid="SELECT c.CaseNo FROM c WHERE c.OrganizationName= 'TransportForLondon'";
		System.out.println("Query:  "+ valid);


		List<com.microsoft.azure.documentdb.Document> documentList = c.queryDocuments("dbs/Case/colls/Case_TFL/", valid, queryOptions).getQueryIterable().toList();

		testCase.log(Status.INFO, "Succesfully get the data's from Database and shown in below");
		
		

		
		String doc=	documentList.toString();
		System.out.println("Data's for the Query:"+ " " + doc);
		
		testCase.log(Status.INFO, "Data's for the Query:"+ " " + doc);
		JSONArray jsonArray = new JSONArray(doc);

		
		
		
		
	    String ActualcaseNo =jsonArray.getJSONObject(0).getString("CaseNo");
		String ActualcaseNo1 =jsonArray.getJSONObject(1).getString("CaseNo");
		System.out.println(ActualcaseNo);
		System.out.println(ActualcaseNo1);

	//UI Connection	
		
		List<WebElement> grid= driver.findElements(By.xpath("//*[@class='ag-body-viewport ag-layout-normal ag-row-no-animation']//div[@class='ag-center-cols-container']//div[@col-id='CaseNo']"));
		int rowlist=grid.size();
		System.out.println("The Row size is:" +rowlist);
		if(rowlist !=0) {


			for(int i=0;i<rowlist;i++) {

			    String cases=grid.get(i).getText();
				System.out.println(cases);
				
			
				return cases;
			}
		}
		
			
			System.out.println("In above Expectedvalid & Actual Result are matched");

	
			
				Assert.assertEquals(ActualcaseNo, cases);
				System.out.println("Passed");
				testCase.log(Status.PASS, "The Expected and Actual Result are Equal");
				return ActualcaseNo1;
	
	
		
	
}	


	public void Dropdown() throws InterruptedException {
		
		Status_Dropdown.click();
		filter_button.click();
		Thread.sleep(8000);
		
		Active.click();
		filter_button.click();
		Thread.sleep(8000);
				
		Request.click();
		filter_button.click();
		Thread.sleep(8000);
		
		ALL.click();
		filter_button.click();
		Thread.sleep(8000);
		
		
	}

   public void roles() throws InterruptedException {
	
	   driver.findElement(By.xpath("//li[@id='Inavlink_AdminSettings']//*[local-name()='svg']")).click();
	   driver.findElement(By.xpath("//li[@id='submenulink_Rolesmanagement']")).click();
	   Thread.sleep(4000);
	   driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[2]/span[1]")).click();
	   Thread.sleep(4000);
	   driver.findElement(By.xpath("//body/div[2]/div[3]/div[1]/div[1]/ul[1]/div[2]/div[2]/li[1]/div[2]/div[1]/label[1]/span[1]/span[1]/input[1]")).click();
	   Thread.sleep(4000);
	   driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[3]/div[2]/div[2]/div[1]/button[1]/span[1]")).click();
	   Thread.sleep(4000);
	   
	   
	   
	 //body/div[2]/div[3]/div[1]/div[1]/ul[1]/div[2]/div[2]/li[1]/div[2]/div[1]/label[1]/span[1]/span[1]/input[1]
	   
	   
	   
}




























}
