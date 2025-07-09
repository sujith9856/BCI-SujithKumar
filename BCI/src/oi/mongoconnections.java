package oi;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.*;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class mongoconnections {
	

	public void connections()
	{
		//MongoClient client = MongoClients.create("mongodb://10.123.222.144:27017");
		//MongoClient client = MongoClients.create("mongodb://Devloper_RW:DevPWD27017@10.123.222.144:27017/?authSource=SCRAM-SHA-1");
		MongoClient mongoClient = MongoClients.create("mongodb://Devloper_RW:DevPWD27017@10.123.222.144:27017/?authSource=admin&authMechanism=SCRAM-SHA-1");
		//MongoCredential credential; 
	     //credential = MongoCredential.createScramSha1Credential("Devloper_RW", "admin", "DevPWD27017".toCharArray());  
	     //System.out.println("Connected to the database successfully"); 
		MongoDatabase database = mongoClient.getDatabase("device_fp_autoML");
		 Iterable<String> collectionnames= database.listCollectionNames();
		 List<String> collnames = new ArrayList<String>();  
		 for(String i: collectionnames)
		 {
			 System.out.println(i);
			 collnames.add(i);
		 }
		 
		 String latestcollectionname=Collections.max(collnames);
		 System.out.println("The latest collection name is " + latestcollectionname);;
		 

/*
 * 
 * 	MongoCollection<Document> collection = database.getCollection("20221103");
	FindIterable<Document> idoc=collection.find();
		for (String name : database.listCollectionNames()) { 
	         System.out.println(name);         
	        
		}//
		BasicDBObject andQuery = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("cpyKey",87137));
		obj.add(new BasicDBObject("Risk_Rank", "MED"));
		andQuery.put("$and", obj);
	

		System.out.println(andQuery.toString());
		//collection.find(Filters.exists(cpyKey)).projection(projection).forEach(doc -> System.out.println(doc));
		FindIterable<Document> idoc2=collection.find(Filters.eq("Risk_Rank","HIGH"));
		MongoCursor<Integer> files = collection.distinct("cpyKey", Integer.class).iterator();
		int j=0;   
		
		while(files.hasNext()) {
		      //System.out.println(files.next());
		      j++;
		      int cpyKey=files.next();
		      long totaldevicecount = collection.count(Filters.eq("cpyKey",cpyKey));
		      
		      BasicDBObject andQueryHigh = new BasicDBObject();
				List<BasicDBObject> objHigh = new ArrayList<BasicDBObject>();
				objHigh.add(new BasicDBObject("cpyKey",cpyKey));
				objHigh.add(new BasicDBObject("Risk_Rank", "HIGH"));
				andQueryHigh.put("$and", objHigh);
				long highdevicecountcpykey=collection.count(andQueryHigh);
				
			  BasicDBObject andQueryMedium = new BasicDBObject();
					List<BasicDBObject> objMed = new ArrayList<BasicDBObject>();
					objMed.add(new BasicDBObject("cpyKey",cpyKey));
					objMed.add(new BasicDBObject("Risk_Rank", "MED"));
					andQueryMedium.put("$and", objMed);
					long Meddevicecountcpykey=collection.count(andQueryMedium);
					
			  BasicDBObject andQueryLow = new BasicDBObject();
						List<BasicDBObject> objLow = new ArrayList<BasicDBObject>();
						objLow.add(new BasicDBObject("cpyKey",cpyKey));
						objLow.add(new BasicDBObject("Risk_Rank", "LOW"));
						andQueryLow.put("$and", objLow);
						long Lowdevicecountcpykey=collection.count(andQueryLow);
				
		      System.out.println("The CPY key "+cpyKey+ "Total Device Count "+totaldevicecount +" , High Device count"+highdevicecountcpykey +" ,Medium Device Count"+Meddevicecountcpykey+", Low Device Count"+Lowdevicecountcpykey);
		      
		    }
		System.out.println("The total number of customers are"+j);
		
		*/
		
		/*
		int i = 1;
		// Getting the iterator
		Iterator it = idoc2.iterator();
		while (it.hasNext()) {
			System.out.println("the cpy keys are"+it.next());
			System.out.println(i);
			i++;
		}
		//collection.count("Risk_Rank":"HIGH")("cpyKey":"87137"));
		//long highdevicecountcpykey=collection.count(andQuery);

		 long Highdevicecount =collection.count(Filters.eq("Risk_Rank","HIGH"));
		 long Mediumdevicecount =collection.count(Filters.eq("Risk_Rank","MED"));
		  long Lowdevicecount = collection.count(Filters.eq("Risk_Rank", "LOW"));		  
		  System.out.println("High Device count for collection 20221103 "+Highdevicecount);
		  System.out.println("Medium Device count for collection 20221103 "+Mediumdevicecount);
		  System.out.println("Low Device count for collection 20221103 "+Lowdevicecount);
	*/

		
		
		
		
	
	}
	
}