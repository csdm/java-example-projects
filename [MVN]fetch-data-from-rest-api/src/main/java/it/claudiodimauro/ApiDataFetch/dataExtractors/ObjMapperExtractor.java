package it.claudiodimauro.ApiDataFetch.dataExtractors;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjMapperExtractor {
	
	private String apiURL;
	
	public ObjMapperExtractor(String apiURL) {
		this.apiURL = apiURL;
	}
	
	public JsonNode fetchDataAsNode() throws MalformedURLException, IOException {
		URL url = new URL(apiURL);
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
		
		String jsonString = tmpString.toString();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode node = objectMapper.readValue(jsonString, JsonNode.class);
		
		return node;
	}
	
}
