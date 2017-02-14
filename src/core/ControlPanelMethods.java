package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.hanuor.main.Hub;

public class ControlPanelMethods {
	public static ArrayList<String> getList() {
		String jsonString = null;
		HashMap<String, String> _mapList = new HashMap<String, String>();
		String appId = "8662F7F0-FA42-2800-FFDB-8A331467EF00";
		String listPath = GetMac.getMac() + "/data/userData/tempList";
		String apiCall = "https://api.backendless.com/" + appId + "/v1/files/"
				+ listPath;
		URL obj;
		try {
			obj = new URL(apiCall);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			// con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + apiCall);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			jsonString = response.toString();
			automationDynalitic(jsonString);
			System.out.println("Vamos han !" + jsonString);

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
	public static void automationDynalitic(String jsonData){
		try {
			ArrayList<String> _addNodes = new ArrayList<String>();
			JSONObject jObj = new JSONObject(jsonData);
			
			Iterator<?> keys = jObj.keys();
			
			while( keys.hasNext() ) {
			    String key = (String)keys.next();
			    _addNodes.add(key);
			   
			}
			   Hub.initializeNodes(_addNodes, GetMac.getMac());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static ArrayList<String> jsonToList(String t) throws JSONException {

		ArrayList<String> map = new ArrayList<String>();
		JSONObject jObject = new JSONObject(t);
		Iterator<?> keys = jObject.keys();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = jObject.getString(key);
			map.add(key);

		}
		return map;
	}

	public static HashMap<String, String> getSubEmail(String _key) {
		String jsonString = null;
		HashMap<String, String> _mapList = new HashMap<String, String>();
		String appId = "8662F7F0-FA42-2800-FFDB-8A331467EF00";

		String path = GetMac.getMac() + "/data/userData/temp/" + _key;
		String apiCall = "https://api.backendless.com/" + appId + "/v1/files/"
				+ path;
		URL obj;
		try {
			obj = new URL(apiCall);

			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			// con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + path);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
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
			System.out.println("Hey I know its you : " + jsonString);
			return jsonToMap(jsonString);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static HashMap<String, String> jsonToMap(String t)
			throws JSONException {

		HashMap<String, String> map = new HashMap<String, String>();

		JSONObject jObject = new JSONObject(t);
		Iterator<?> keys = jObject.keys();

		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = jObject.getString(key);

			map.put(key, value);

		}
		System.out.println("We will rock you" + map.toString());
		return map;
	}

	public static int getCount(String subject) {
		int count = 0;
		try {
			System.out.println("Clementine  " + subject);
			int index = subject.indexOf("#*#");
			if (index != -1) {
				try {
					while (index != -1) {
						count++;
						subject = subject.substring(index + 1);
						index = subject.indexOf("#*#");
					}
				} catch (Exception e) {
					// count= -111;
					e.printStackTrace();

				}
				return count;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	public static String separatorToFields(String _string) {
		System.out.println("String is    " + _string);
		String newS = _string.replace("#*#", "#Field#");
		System.out.println("Whateee  " + newS);
		return newS;

	}

}
