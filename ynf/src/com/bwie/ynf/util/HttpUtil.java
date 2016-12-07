package com.bwie.ynf.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Õ¯¬Á«Î«Û
public class HttpUtil {
	public static String getjson(String url) throws Exception {
		URL Url = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) Url.openConnection();
		InputStream is = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String len = "";
		while ((len = br.readLine()) != null) {
			sb.append(len);
		}
		String json = sb.toString();
		br.close();
		isr.close();
		is.close();
		return json;
	}
}
