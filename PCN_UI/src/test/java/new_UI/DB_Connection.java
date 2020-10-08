package new_UI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.azure.cosmos.ConnectionPolicy;
import com.azure.cosmos.ConsistencyLevel;
import com.azure.cosmos.CosmosClient;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.implementation.Document;
import com.google.common.collect.Lists;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.file.CloudFile;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileDirectory;
import com.microsoft.azure.storage.file.CloudFileShare;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;




public class DB_Connection {
	

	public static ExtentHtmlReporter htmlreporter;
	public static ExtentReports extentReport;
	public static ExtentTest testCase;
	public CosmosClient client;
    public static String MASTER_KEY =
            System.getProperty("ACCOUNT_KEY", 
                    StringUtils.defaultString(StringUtils.trimToNull(
                            System.getenv().get("ACCOUNT_KEY")),
                            "GYvuNuBlVutHsvDfqUMMPqSx3Hhhs94dlLILWZYD6edcWnGJ1WOeEptilBvBQvn1a73WsksvF59EWBRo74AeZg=="));

    public static String HOST =
            System.getProperty("ACCOUNT_HOST",
                    StringUtils.defaultString(StringUtils.trimToNull(
                            System.getenv().get("ACCOUNT_HOST")),
                            "https://ffdbcaseqa.documents.azure.com:443/"));


	String storageConnectionString ="DefaultEndpointsProtocol=https;AccountName=ffsaqa;AccountKey=RdCzLswVIyfwvlVh9ZyzJxjho41+AZBenThrW5jqjfAGBc3y84M0nl07Wc5QWl/t6EgmuEkV542WDO5fc6GNFg==;EndpointSuffix=core.windows.net";
	CloudStorageAccount storageAccount = null;
	CloudFileClient fileClient=null;
	CloudFileShare share = null;
	CloudFileDirectory rootDir = null;
	CloudFileDirectory caseDir = null;
	CloudFile cloudFile = null;

	public void excelread() throws IOException, InterruptedException {



		htmlreporter = new ExtentHtmlReporter("extentReport.html");
		extentReport =new ExtentReports();
		extentReport.attachReporter(htmlreporter);
		htmlreporter.config().setReportName("Automation Testing");

	}
	


public void start_dvla() throws IOException {


	try {


		storageAccount = CloudStorageAccount.parse(storageConnectionString);  //Connect to Azure Storage

		fileClient = storageAccount.createCloudFileClient(); // Establish connection 

		share = fileClient.getShareReference("freeflowtfl"); //Select Main Folderr

		rootDir = share.getRootDirectoryReference(); // Establish folder

		caseDir = rootDir.getDirectoryReference("Inbound/Contract1/Case"); // Select sub folder



	} catch (InvalidKeyException e4) {
		

		e4.printStackTrace();
	} catch (URISyntaxException e4) {
		

		e4.printStackTrace();
	} catch (StorageException e) {
		

		e.printStackTrace();
	}
}


public void validfile() throws Exception, StorageException {
	testCase=extentReport.createTest("Fetch data from Database By uploading valid File", "we use query to retrieve the data");			
	testCase.log(Status.INFO, "Upload a file in SFTP Location");




//	try {
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



//		String expectedvalid= sheet.getRow(3).getCell(1).getStringCellValue();
//		System.out.println(expectedvalid);
//		System.out.println("In above Expectedvalid & Actual Result are matched");
//		testCase.log(Status.INFO, "Here we Use assertion to match Expected & Actual");
//		testCase.log(Status.INFO, "The Expected is:"+"   "+expectedvalid);
//		testCase.log(Status.INFO, "The Actual result is:"+"   "+ActualcaseNo);
//
//		try {
//			Assert.assertEquals(expectedvalid, ActualcaseNo);
//			System.out.println("Passed");
//			testCase.log(Status.PASS, "The Expected and Actual Result are Equal");
//
//		}catch(AssertionFailedError e) {
//			System.out.println("Failed"+e.getMessage());
//			testCase.log(Status.FAIL, "The Expected and Actual Result are NotEqual:"+"  "+ e.getMessage());
//		}		
//	}catch(Exception e) {
//		testCase.log(Status.FAIL, "The Particular Data is not get from DataBase:"+"  "+ e.getMessage());
//		System.out.println(e.getMessage());
//	}
	
}































}