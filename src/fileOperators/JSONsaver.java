package fileOperators;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

import UI.*;


public class JSONsaver {

	
	public JSONsaver(File file, GeneralConfiguration generalConfiguration, Options options, Start start, Elements elements, End end){
		
		JSONObject save = new JSONObject();
		
		save.put("start", start.saveData());
		save.put("generalConfiguration", generalConfiguration.saveData());
		save.put("end", end.saveData());
		save.put("elements", elements.saveData());
		
		
		try {
			String path = file.getAbsolutePath();
			file.delete();
			FileWriter filew = new FileWriter(path);
			save.write(filew);
			filew.flush();
			filew.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}	
}
