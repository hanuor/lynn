package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONSerializer;

import org.json.JSONObject;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

public class DatabasePing {
	 public static ArrayList<String> getEmailandPassword(String MacId){
     	String jsonString = null;
     	ArrayList<String> _arrList = new ArrayList<String>();
     	String appId = "8662F7F0-FA42-2800-FFDB-8A331467EF00";
     	String apiCall = "https://api.backendless.com/" + appId + "/v1/files/" + GetMac.getMac() + "/data/user" ;
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
 		JSONObject jObj = new JSONObject(jsonString);
 		String password = (String) jObj.get("password");
 		String email = (String) jObj.get("email");
 		_arrList.add(email);
 		_arrList.add(password);
 		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        		
			return _arrList;	
     }
	public static void userRegistration(String email, String password){
		
		Backendless.initApp( "8662F7F0-FA42-2800-FFDB-8A331467EF00", "21B58D09-56A2-3158-FF75-EB1B1237E500", "v1" );
		  BackendlessUser user = new BackendlessUser();
		  user.setProperty( "email", email.toString() );
		  user.setPassword( password.toString() );
		   
		  Backendless.UserService.register( user, new AsyncCallback<BackendlessUser>()
		  {

			@Override
			public void handleFault(BackendlessFault bFault) {
				// TODO Auto-generated method stub
				System.out.println("Error " +bFault.getMessage());
				bFault.getMessage();
				
			}

			@Override
			public void handleResponse(BackendlessUser bUser) {
				GetMac.getMac();
				System.out.print(bUser + "");		
			}
		  });
		  //Upload a separate meta of their profile
		  String path = GetMac.getMac() + "/data/user";
		  String remoteName = "userDetails";
		  Map<String, String> map = new HashMap<String, String>();
		  map.put("email", email);
		  map.put("password", password);
		  JSONObject jobj =new JSONObject(map);
		 Backendless.Files.saveFile(path, jobj.toString().getBytes(), true,new AsyncCallback<String>(){

			@Override
			public void handleFault(BackendlessFault arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getMessage());
				
			}

			@Override
			public void handleResponse(String arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
			}
			 
		 });

	}
	
	public static void saveTemplateMessage(String subject, String message){
		Backendless.initApp( "8662F7F0-FA42-2800-FFDB-8A331467EF00", "21B58D09-56A2-3158-FF75-EB1B1237E500", "v1" );
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("subject", subject);
		hMap.put("message", message);
		 JSONObject jobj =new JSONObject(hMap);
		 String path = GetMac.getMac() + "/data/userData/temp";
		 Backendless.Files.saveFile(path, jobj.toString().getBytes(), true,new AsyncCallback<String>(){

			@Override
			public void handleFault(BackendlessFault arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getMessage());
				
			}

			@Override
			public void handleResponse(String arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0);
			}
			 
		 });

	}
	
	
	
}
