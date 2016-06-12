package fileOperators;

import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

public class Test {

	public void JSONExample(){
//		
//		{
//			   "pageInfo": {
//			         "pageName": "abc",
//			         "pagePic": "http://example.com/content.jpg"
//			    }
//			    "posts": [
//			         {
//			              "post_id": "123456789012_123456789012",
//			              "actor_id": "1234567890",
//			              "picOfPersonWhoPosted": "http://example.com/photo.jpg",
//			              "nameOfPersonWhoPosted": "Jane Doe",
//			              "message": "Sounds cool. Can't wait to see it!",
//			              "likesCount": "2",
//			              "comments": [],
//			              "timeOfPost": "1234567890"
//			         }
//			    ]
//			}
		
//		JSONObject obj = new JSONObject(" .... ");
//		String pageName = obj.getJSONObject("pageInfo").getString("pageName");
//	
//		JSONArray arr = obj.getJSONArray("posts");
//		for (int i = 0; i < arr.length(); i++)
//		{
//		    String post_id = arr.getJSONObject(i).getString("post_id");
//		}
		
		JSONObject obj = new JSONObject();
		JSONObject obj2 = new JSONObject();
		obj2.put("Name", "crunchify.com");
		obj2.put("Author", "App Shah");
		
		
		JSONArray company = new JSONArray();
		company.put("Compnay: eBay");
		company.put("Compnay: Paypal");
		company.put("Compnay: Google");
		obj2.put("Company List", company);
 
		
		obj.put("test", obj2);
		obj.put("test2", obj2);
		
		// try-with-resources statement based on post comment below :)
		try {

			FileWriter file = new FileWriter("d:\\test.json");
			obj.write(file);
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}