package it.claudiodimauro.MD5_from_to_converter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang3.StringUtils;

import it.claudiodimauro.MD5_from_to_converter.converter.MD5Converter;


public class App {

	public static void main(String[] args){
		
		final String TO_CONVERT = "Hello World";
		String convertedString = "";
		String convertedByteArray = "";
		
		try {
			convertedString = MD5Converter.encode(TO_CONVERT); //conversion from String
			convertedByteArray = MD5Converter.encode(TO_CONVERT.getBytes()); //conversion from byte[]
		} catch(NoSuchAlgorithmException ex) {
			ex.getMessage();
		}
		
		System.out.println(convertedString);
		System.out.println(convertedByteArray);
		
	}

}

