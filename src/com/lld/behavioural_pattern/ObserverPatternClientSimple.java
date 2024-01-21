package com.lld.behavioural_pattern;

import java.util.ArrayList;
import java.util.List;

interface ISubscribe{
	public void notify(String msg) ;

	public String getName();
}

class User implements ISubscribe{
	private int userId;
	private String name;
	
	public User(String name,int userId){
		this.name = name;
		this.userId = userId;
	}

	@Override
	public void notify(String msg) {
		System.out.println("User "+userId+" recieved msg \""+msg+"\"" );
	}

	@Override
	public String getName() {
		return name;
	}
}

class GroupUser{
	private List<ISubscribe> users ;
	{
		users = new ArrayList();
	}
	
	void subscribe(ISubscribe user) {
		users.add(user);
		System.out.println("Hurray..! User : "+user.getName()+" has subscribed to the channel");
	}
	void unSubscribe(ISubscribe user) {
		users.remove(user);
		System.out.println("Sorry..! User : "+user.getName()+" has unsubscribed the channel");
	}
	
	void notify(String msg) {
		for (ISubscribe user : users) {
			user.notify(msg);
		}
	}
}

public class ObserverPatternClientSimple{
	public static void main(String... strings) {
		
		User user1 = new User("Padmesh",01);
		User user2 = new User("Ram",02);
		User user3 = new User("Sekar",03);
		User user4 = new User("Amar",04);
		
		GroupUser subscribers = new GroupUser();
		subscribers.subscribe(user1);
		subscribers.subscribe(user2);
		subscribers.subscribe(user3);
		subscribers.subscribe(user4);
		
		subscribers.notify("Have a Good Day..!");
		
		subscribers.unSubscribe(user3);
		
		subscribers.notify("Start a Day with a smile..!");
		
	}
}