package new_UI;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Main extends Login{


	Login login=new Login();
	Search_manager searchpage=new Search_manager();

	DB_Connection Dbconnect=new DB_Connection();


	
	@Test(priority=1,enabled=false)
	public void start() throws InterruptedException, IOException, AWTException {

		login.initiliaze();
		login.verification();
		Thread.sleep(20000);
//		searchpage.roles();
		
		
		
		searchpage.LicenceToggle();	
		searchpage.CaseToggle();
		searchpage.setContract();
		searchpage.Dropdown();
		searchpage.getvalue();

	}


	@Test(priority=2,enabled=true)
	public void DB() throws Exception {

		Dbconnect.excelread();
		Dbconnect.start_dvla();
		Dbconnect.validfile();

	}















	//@AfterTest
	//public void teardown() {
	//	extentReport.flush();
	//}

}