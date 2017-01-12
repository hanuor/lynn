package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class ControlPanelMethods {
	public static ArrayList<String> getList(){
		String jsonString = null;
		HashMap<String, String> _mapList = new HashMap<String, String>();
     	String appId = "8662F7F0-FA42-2800-FFDB-8A331467EF00";
     	String listPath = GetMac.getMac() + "/data/userData/tempList";
     	String apiCall = "https://api.backendless.com/" + appId + "/v1/files/" + listPath ;
     	URL obj;
			try {
				obj = new URL(apiCall);
			
 		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

 		// optional default is GET
 		con.setRequestMethod("GET");

 		//add request header
 		//con.setRequestProperty("User-Agent", USER_AGENT);

 		int responseCode = con.getResponseCode();
 		System.out.println("\nSending 'GET' request to URL : " + apiCall);
 		System.out.println("Response Code : " + responseCode);

 		BufferedReader in = new BufferedReader(
 		        new InputStreamReader(con.getInputStream()));
 		String inputLine;
 		StringBuffer response = new StringBuffer();

 		while ((inputLine = in.readLine()) != null) {
 			response.append(inputLine);
 		}
 		in.close();
 		jsonString = response.toString();
 		
 		} catch (Exception e) {
				// TODO Auto-generated catch block
 		
 			System.out.println("Here");
				e.printStackTrace();
				return null;
				
			}        		
			try {
				return jsonToList(jsonString);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}	
	}
	public static ArrayList<String> jsonToList(String t) throws JSONException {
		

        ArrayList<String> map = new ArrayList<String>();
        JSONObject jObject = new JSONObject(t);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key); 
            map.add(key);

        }
        	return map;
    }
	
	public static HashMap<String, String> getSubEmail(String _key){
		String jsonString = null;
		HashMap<String, String> _mapList = new HashMap<String, String>();
     	String appId = "8662F7F0-FA42-2800-FFDB-8A331467EF00";
     	
		 String path = GetMac.getMac() + "/data/userData/temp/" + _key;
			String apiCall = "https://api.backendless.com/" + appId + "/v1/files/" + path ;
     	URL obj;
			try {
				obj = new URL(apiCall);
			
 		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

 		// optional default is GET
 		con.setRequestMethod("GET");

 		//add request header
 		//con.setRequestProperty("User-Agent", USER_AGENT);

 		int responseCode = con.getResponseCode();
 		System.out.println("\nSending 'GET' request to URL : " + path);
 		System.out.println("Response Code : " + responseCode);

 		BufferedReader in = new BufferedReader(
 		        new InputStreamReader(con.getInputStream()));
 		String inputLine;
 		StringBuffer response = new StringBuffer();

 		while ((inputLine = in.readLine()) != null) {
 			response.append(inputLine);
 		}
 		in.close();
 		jsonString = response.toString();
 		
 		} catch (Exception e) {
				// TODO Auto-generated catch block
 		
 			System.out.println("Here");
				e.printStackTrace();
				return null;
				
			}        		
			try {
				return jsonToMap(jsonString);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	}
	public static HashMap<String, String> jsonToMap(String t) throws JSONException {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(t);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key); 
            map.put(key, value);

        }
        	return map;
    }
	public static int getCount(String subject){
		int count = 0;
		try{
			
		
		int index = subject.indexOf("#*#");
	
		while (index != -1) {
		    count++;
		    subject = subject.substring(index + 1);
		    index = subject.indexOf("#*#");
		}
		}catch(Exception e){
			//count= -111;
			e.printStackTrace();
			
		}
		return count;
	}
	public static void separatorToFields(String _string){
		int count = 0;
		String _vamos = _string;
		String _newstring = _string;
		//Bug is poccuroing here
		int lastIndex = 0;
		int countStep = 0;
		StringBuilder strB = new StringBuilder();
		
		try{
		
		
		while(lastIndex != -1){

		    lastIndex = _string.indexOf("#*",lastIndex);
		    System.out.println("Whisper");
		    System.out.println("Lights s " + lastIndex);
		   
		    
		    if(lastIndex != -1){
		        count ++;
		        _newstring = _string.replace("#*#", " Field #" + count); 
		        if((count-1) == 0){
		        	System.out.println("Smile " + _newstring);
		        	strB.append(_newstring.substring(0, 4));

		        	System.out.println("Smile@gain " + strB.toString());
		        }else{
		        	System.out.println("Smile " + _newstring);
		        	strB.append(_newstring.substring(countStep, lastIndex+1));
		        	System.out.println("Smile@gain " + strB.toString());
		        }
		        
		        countStep = lastIndex;
		        System.out.print("sdad   " + count );
		        lastIndex += "#*#".length();
		    }
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println(_vamos +"to " + strB.toString());
		
		
	
	}
	
} 
