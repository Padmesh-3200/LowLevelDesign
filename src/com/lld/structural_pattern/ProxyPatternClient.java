package com.lld.structural_pattern;

import java.util.ArrayList;
import java.util.List;

//a class behaves as a proxy to restrict or allow access to do some operations in action based classes.
//Do a action before or after, while accessing the original object

interface Internet{
	void connectTo(String host);
}


class InternetImplementation implements Internet {

	@Override
	public void connectTo(String host) {
		System.out.println("Opened Connection to "+host);
		
	}
	
}
class ProxyInternetImplementation implements Internet{

	
	private static final List<String> bannedSites = new ArrayList<>();
	private final Internet internet = new InternetImplementation();
	
	static {
		bannedSites.add("banned.com");
	}
	
	@Override
	public void connectTo(String host) {
		// TODO Auto-generated method stub
		if(bannedSites.contains(host)) {
			System.out.println("Access Denied");
			return;
		}
		internet.connectTo(host);
		
	}
}


public class ProxyPatternClient{
	public static void main(String... strings) {
		Internet internet = new ProxyInternetImplementation();
		internet.connectTo("google.com");
		internet.connectTo("banned.com");	
	}
}

 