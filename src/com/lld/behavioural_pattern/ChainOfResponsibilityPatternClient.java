package com.lld.behavioural_pattern;

import java.util.HashMap;
import java.util.Map;

//this design pattern transforms particular behaviour into objects called as handlers

//use case : user authentication app 
//
//validate username, validate Password, check Role.

// to make a completion call for an action ,may be it is success or failure
// it need to be handled by some individual seperate parts of that action sequentially 
// based on it the success the and failure of the action depends.

class DataBaseCFR{
	private final Map<String, String> users;

    public DataBaseCFR() {
        users = new HashMap<>();
        users.put("admin_username", "admin_password");
        users.put("user_standard", "standard_password");
    }

    public boolean isValidUser(String username) {
        return users.containsKey(username);
    }

    public boolean isValidPassword(String username, String password) {
        return users.get(username).equals(password);
    }	
}


abstract class Handler {
	private Handler next;
	
	public Handler setNextHandler(Handler handler) {
		this.next = handler;
		return next;
	}
	
	public abstract boolean handle(String userName, String password);
	
	protected boolean handleNext(String userName, String password) {
		if(next == null ) {
			return false;
		}
		return next.handle(userName, password);
	}
}

class UserValidationHandler extends Handler{

	private final DataBaseCFR database;

    public UserValidationHandler(DataBaseCFR database) {
        this.database = database;
    }

    public boolean handle(String username, String password) {
        if (!database.isValidUser(username)) {
            System.out.println("This username is not registered!");
            System.out.println("Sign Up to our app now!");
            return false;
        }
        return handleNext(username, password);
    }
}

class PasswordValidationHandler extends Handler{

	private final DataBaseCFR database;

    public PasswordValidationHandler(DataBaseCFR database) {
        this.database = database;
    }

    public boolean handle(String username, String password) {
        if (!database.isValidPassword(username, password)) {
            System.out.println("Wrong Password!");
            return false;
        }
        return handleNext(username, password);
	}
	
}

class RoleValidationHandler extends Handler{

	@Override
    public boolean handle(String username, String password) {
        if ("admin_username".equals(username)) {
            System.out.println("Loading Admin Page...");
            return true;
        }
        System.out.println("Loading Default Page for standard/user...");
        return handleNext(username, password);
    }
	
}

class AuthService {

    private final Handler handler;

    public AuthService(Handler handler) {
        this.handler = handler;
    }

    public boolean logIn(String email, String password) {
        if (handler.handle(email, password)) {
            System.out.println("Authorization was successful!");
            // Do stuff for authorized users
            return true;
        }
        return false;
    }

}
public class ChainOfResponsibilityPatternClient{
	public static void main(String...strings) {
		    DataBaseCFR database = new DataBaseCFR();
	        Handler handler = new UserValidationHandler(database);
	        handler.setNextHandler(new PasswordValidationHandler(database))
	                .setNextHandler(new RoleValidationHandler());
	        AuthService service = new AuthService(handler);

	        System.out.println("==========================================");

	        System.out.println(service.logIn("username", "password"));

	        System.out.println("==========================================");

	        System.out.println(service.logIn("user_standard", "standard_password"));

	        System.out.println("==========================================");

	        System.out.println(service.logIn("admin_username", "admin_password"));

	        System.out.println("==========================================");
	}
}