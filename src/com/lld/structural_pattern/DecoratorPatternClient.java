package com.lld.structural_pattern;

import java.util.HashMap;
import java.util.Map;

interface Notification{
	public String getUserName();
	public void send(String msg);
}

class DataBaseService{
	
	//Assume it as DB
	private final static Map<String , String> userAndMailId = new HashMap<String,String>();
	private final static Map<String , String> userAndPhoneNumber = new HashMap<String,String>();
	private final static Map<String , String> userAndFBUserName = new HashMap<String,String>();
	static {
			userAndMailId.put("padmesh","padmesh.nagararthinam@gmail.com");
			userAndMailId.put("ram","ram007@gmail.com");
			userAndMailId.put("amar","amar444@gmail.com");
			userAndPhoneNumber.put("padmesh","8300298433");
			userAndPhoneNumber.put("ram","122433545");
			userAndPhoneNumber.put("amar","0908982122");
			userAndFBUserName.put("padmesh","Padmesh_008");
			userAndFBUserName.put("ram","Ram_001");
			userAndFBUserName.put("amar","Amar_004");
	}
	
	public String getMailIdWithUsernameFromDB(String username) {

		// Assume as this method gets from db
		return userAndMailId.get(username);
	}

	public String getPhoneNumberWithUsernameFromDB(String username) {

		// Assume as this method gets from db
		return userAndPhoneNumber.get(username);
	}

	public String getFBUserNameWithUsernameFromDB(String username) {

		// Assume as this method gets from db
		return userAndFBUserName.get(username);
	}
	
}

class NotificationImplementation implements Notification{
	
	private final DataBaseService db = new DataBaseService();
	private String userName;
	
	public NotificationImplementation(String userName) {
		this.userName = userName;
	}
    
	@Override
	public String getUserName() {
	    return userName;
	}

	@Override
	public void send(String msg) { // 6) executed this method
		String mailId = db.getMailIdWithUsernameFromDB(userName); 
		System.out.println("Sending "+msg+" by mail to "+ mailId);
		
	}
	
}


abstract class BaseNoticationDecorator implements Notification{
	private final Notification wrapped;
	protected final DataBaseService db = new DataBaseService();
	
	public BaseNoticationDecorator(Notification wrapped) {
		this.wrapped = wrapped;
	}
    
	public String getUserName() {
	    return wrapped.getUserName();
	}

	public void send(String msg) {
		 wrapped.send(msg); // 3) calls the wrapped WhatsAppNotificationImplementation.send method, because it is called from FBNotificationImplementation object
		                    // 5) calls the wrapped NotificationImplementation.send method, because it is called from WhatsAppNotificationImplementation object
	}
	
}
class WhatsAppNotificationImplementation extends BaseNoticationDecorator{

	public WhatsAppNotificationImplementation(Notification wrapped) {
		super(wrapped);
	}
	public void send(String msg) {
		super.send(msg); // 4) calls BaseNoticationDecorator.send 
		String phoneNumber = db.getPhoneNumberWithUsernameFromDB(getUserName());
		System.out.println("Sending "+ msg +" on WhatsApp to "+phoneNumber);
	}
	
}
class FBNotificationImplementation  extends BaseNoticationDecorator{

	public FBNotificationImplementation(Notification wrapped) {
		super(wrapped);
	}
	public void send(String msg) {
		super.send(msg); // 2) calls the BaseNoticationDecorator.send method
		String fbUserName = db.getFBUserNameWithUsernameFromDB(getUserName());
		System.out.println("Sending "+ msg +" on FaceBook to "+fbUserName);
	}
}
	
public class DecoratorPatternClient{
	public static void main(String...strings) {
		
		NotificationImplementation simpleNotification = new NotificationImplementation("padmesh");
		BaseNoticationDecorator notification = new FBNotificationImplementation(new WhatsAppNotificationImplementation(simpleNotification));
		
		//Composition Plays a main role in this pattern.
		
		//new WhatsAppNotificationImplementation(simpleNotification) - this creates the object WhatsAppNotificationImplementation wrapped with simple Notification Object
		//new FBNotificationImplementation(new WhatsAppNotificationImplementation(simpleNotification)); 
		//- this creates the object FBNotificationImplementation wrapped with WhatsAppNotificationImplementation object,
		//inside this object Notification object is wrapped  
		
		notification.send("Hi I Have learned Decorator pattern"); // 1) calls the BaseNoticationDecorator.send method
		
	}
}
