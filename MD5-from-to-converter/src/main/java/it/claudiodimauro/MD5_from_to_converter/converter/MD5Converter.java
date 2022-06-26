package it.claudiodimauro.MD5_from_to_converter.converter;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Converter {

	/**
	 * Convert the passed String input in MD5
	 * @param string to convert as MD5
	 * @return a String of the converted input param
	 * @throws NoSuchAlgorithmException
	 */
	public static String encode(String stringToConvert) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		//reset the digest for further uses
		md.reset();

		//update the digest with the data we want to convert, passing the specified bytes array
		md.update(stringToConvert.getBytes());

		//get the digest as the hash computation 
		byte[] digest = md.digest();

		//define the digest as a BigInteger
		BigInteger bigInt = new BigInteger(1, digest);

		//get the bigInt as base 16 number (hexadecimal)
		String hashText = bigInt.toString(16);

		//check if the hashText has the right length (32 characters)
		while(hashText.length() < 32 ){
			hashText = "0"+hashText;
		}
		
		//return the hash converted string
		return hashText;
	}

	/**
	 * Convert the passed byte array input in MD5
	 * @param array of bytes to convert as MD5
	 * @return a String of the converted input param
	 * @throws NoSuchAlgorithmException
	 */
	public static String encode(byte[] byteArrayToConvert) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");

		//reset the digest for futhure uses
		md.reset();

		//update the digest with the data we want to convert, passing the specified bytes array
		md.update(byteArrayToConvert);

		//get the digest as the hash computation 
		byte[] digest = md.digest();		

		//define the digest as a BigInteger
		BigInteger bigInt = new BigInteger(1, digest);

		//get the bigInt as base 16 number (hexadecimal)
		String hashText = bigInt.toString(16);

		//check if the hashText has the right length (32 characters)
		while(hashText.length() < 32 ){
			hashText = "0"+hashText;
		}
		
		//return the hash converted string
		return hashText;
	}
}
