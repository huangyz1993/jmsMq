package test;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;

public class Code {
	
	public static void main(String agrs[]) throws UnsupportedEncodingException, UnknownHostException{
		String utf8Str = "ȫ������";
		utf8Str = URLEncoder.encode(utf8Str, "UTF-8");
		System.out.println("utf-8 ���룺" + utf8Str);
		
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println(inetAddress.getHostAddress());
		System.out.println(inetAddress.getHostName());
	}

}
