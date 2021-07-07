package controller;

import java.util.HashMap;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsSend {

	public void smsSend(String phoneNum, String textMassage) {
		String api_key = "NCSW6VCZXODXGPOX";
		String api_secret = "UZMGJ9ADCK2LCTIEWQRITSVRXW3J0B3T";
		Message coolsms = new Message(api_key, api_secret);
		 
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", phoneNum);
		params.put("from", "01041739350");
		
		if(textMassage.length() > 80) {
			params.put("type", "LMS");
		} else {
			params.put("type", "SMS");
		}
		
		params.put("text", textMassage);
		params.put("app_version", "JAVA SDK v2.2");
		
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
	        System.out.println(obj.toString());
		} catch (CoolsmsException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCode());
		}
	}
}
