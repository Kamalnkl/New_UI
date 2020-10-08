package new_UI;

import java.util.List;

import org.json.JSONArray;
import org.testng.annotations.Test;

import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.storage.StorageException;

public class Setup  {

	@Test	
    public void compare() throws StorageException, Exception {

			com.microsoft.azure.documentdb.FeedOptions queryOptions = new com.microsoft.azure.documentdb.FeedOptions();
			queryOptions.setEnableCrossPartitionQuery(true);
			DocumentClient c= new DocumentClient(DB_Connection.HOST, DB_Connection.MASTER_KEY, new com.microsoft.azure.documentdb.ConnectionPolicy(), com.microsoft.azure.documentdb.ConsistencyLevel.Session);

			String valid="SELECT c.CaseNo FROM c WHERE c.OrganizationName= 'TransportForLondon'";
			System.out.println("Query:  "+ valid);


			List<com.microsoft.azure.documentdb.Document> documentList = c.queryDocuments("dbs/Case/colls/Case_TFL/", valid, queryOptions).getQueryIterable().toList();

			String doc=	documentList.toString();
			System.out.println("Data's for the Query:"+ " " + doc);
			JSONArray jsonArray = new JSONArray(doc);
	
			
			
		    String ActualcaseNo =jsonArray.getJSONObject(0).getString("CaseNo");
			String ActualcaseNo1 =jsonArray.getJSONObject(1).getString("CaseNo");
			System.out.println(ActualcaseNo);
			System.out.println(ActualcaseNo1);

}
	
}
