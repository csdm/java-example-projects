package it.claudiodimauro.ApiDataFetch.dataExtractors;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.claudiodimauro.ApiDataFetch.utils.Utility;

public class ObjMapperExtractor {
	
	private String apiURL;
	private String jsonString = null;
	
	public ObjMapperExtractor(String apiURL) {
		this.apiURL = apiURL;
	}
	
	public JsonNode fetchDataAsNode() {		
		try {
			jsonString = connectAndRetrieve(apiURL);
		} catch (MalformedURLException ex) {
			ex.getMessage();
		} catch (IOException ex) {
			ex.getMessage();
		} catch (Exception ex) {
			ex.getMessage();
		}		
		
		String mockedJsonStr = "{ \"name\" : \"Raja\", \"age\" : 30," +
                " \"technologies\" : [\"Java\", \"Scala\", \"Python\"]," +
                " \"nestedObject\" : { \"field\" : \"value\" } }";		
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode node = null;
		
		try {
			node = objectMapper.readValue(jsonString, JsonNode.class);
		} catch ( JsonMappingException ex) {
			ex.getMessage();
		} catch ( JsonProcessingException ex) {
			ex.getMessage();
		}
		
		return node;
	}
	
	private String connectAndRetrieve(String apiUrl)  throws MalformedURLException, IOException  {		
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
		
		return tmpString.toString();
	}
	
	public boolean isReturningJsonArray() {
		return Utility.isJsonArray(jsonString);
	}
	
}
