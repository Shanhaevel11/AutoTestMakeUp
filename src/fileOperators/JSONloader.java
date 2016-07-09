package fileOperators;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import UI.*;

public class JSONloader {

public JSONloader(File file, GeneralConfiguration generalConfiguration, Options options, Start start, Elements elements, End end) throws FileNotFoundException, ParseException{
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(new Scanner(file).useDelimiter("\\Z").next());

		JSONObject data = new JSONObject(obj.toString());
		
		generalConfiguration.loadData(data.getJSONObject("generalConfiguration"));
		start.loadData(data.getJSONObject("start"));
		end.loadData(data.getJSONObject("end"));
		elements.loadData(data.getJSONObject("elements"));
		
	}	
	
}
