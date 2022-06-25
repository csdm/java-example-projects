package it.claudiodimauro.ApiReadData;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class App {

	public static void main(String[] args) {
		
		List<User> userList = new ArrayList<>();
		
		try {
			URL url = new URL("https://jsonplaceholder.typicode.com/users");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			if(connection.getResponseCode() != 200) {
				throw new RuntimeException(
						"Connection error: " 
						+ connection.getResponseMessage() 
						+ " "
						+ connection.getResponseCode()
						);
			}
			
			StringBuilder tmpString = new StringBuilder();
			Scanner scanner = new Scanner(url.openStream());
			
			while(scanner.hasNext()) {
				tmpString.append(scanner.nextLine());
			}
			
			scanner.close();
			
//			System.out.println(tmpString);
			
			JSONParser parser = new JSONParser();
			JSONArray dataObject = (JSONArray) parser.parse(String.valueOf(tmpString));
			
//			System.out.println(dataObject.get(0));
			
//			JSONObject userData = (JSONObject) dataObject.get(0);
//			
//			System.out.println(userData.get("name"));
//			
//			System.out.println(dataObject.size());
			
			for(int i=0; i<dataObject.size(); i++) {
				User user = new User();
				JSONObject userData = (JSONObject) dataObject.get(i);
				
				user.setId(Integer.parseInt(userData.get("id").toString()));
				user.setName(userData.get("name").toString());
				user.setUsername(userData.get("username").toString());
				user.setEmail(userData.get("email").toString());
				user.setAddress(new Address(
						userData.get("address").get("street").toString(), //come prelevare oggetti innestat?
						null,
						null,
						null,
						null
						));
				
				userList.add(user);
			}
			
			for(User u : userList) {
				System.out.println(u.getName());
			}
			
			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
		
	}
	
}
