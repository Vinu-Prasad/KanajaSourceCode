package com.kanaja.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtility {
	
	public static List<String> generateTokens(String content){
		String []tokens = content.split(" ");
		List<String> tokenList = new ArrayList<String>();
		for(String token : tokens){
			tokenList.add(token);
		}
		return tokenList ;
	}
	

}
