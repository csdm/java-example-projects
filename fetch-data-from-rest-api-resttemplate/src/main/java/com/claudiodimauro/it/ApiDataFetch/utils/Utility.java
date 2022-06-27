package com.claudiodimauro.it.ApiDataFetch.utils;

public class Utility {

	public static boolean isJsonArray(String str) {
		return str.indexOf('[')==0 ? true : false;
	}
}
