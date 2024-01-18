package com.lld.behavioural_pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//this pattern notifies the mutiples objects or subscribers,
//about any events that happen to the object they are observing or publisher.

//use case : there are multiple customers are the for the store and for any new item came to the store or any sale fo the products going on ,
// should be informed to them via mail for that.


//Event happening in the store based on it we send the notification
enum Event {
	NEW_ITEM ,
	SALE;
}


interface Listener {
	void update(Event eventType);
}

//This class is to send mail to the consumers
class EmailMsgListener implements Listener {
	private final String email;

	public EmailMsgListener(String email) {
		this.email = email;
	}

	@Override
	public void update(Event eventType) {
		// Actually send the mail
		System.out.println("Sending mail to " + email + " concerning " + eventType);
	}
}

//This class is to send notification to the consumers via mobile
class MobileAppListener implements Listener {
	private final String username;

	public MobileAppListener(String username) {
		this.username = username;
	}

	@Override
	public void update(Event eventType) {
		// Actually send the push notification to username
		System.out.println("Sending mobile app notification to " + username + " concerning " + eventType);
	}
}

//this is intermediate class plays as providing the notificationservice
//and make a notification to the listeners based on the event type
class Store {

	private final NotificationService notificationService;

	public Store() {
		notificationService = new NotificationService();
	}

	public void newItemPromotion() {
		notificationService.notifyCustomers(Event.NEW_ITEM);
	}

	public void salePromotion() {
		notificationService.notifyCustomers(Event.SALE);
	}

	public NotificationService getService() {
		return notificationService;
	}

}

/// this is to keep track of customer , subscribers or listeners which is like to get the notification via mail,
class NotificationService {

	private final Map<Event, List<Listener>> customers;

	public NotificationService() {
		customers = new HashMap<>();
		Arrays.stream(Event.values()).forEach(event -> customers.put(event, new ArrayList<>())); 
		// adding the Event type as key to HashMap customers and initializing it with arraylist
		// to categories and store the listneres acording to it
	}

	public void subscribe(Event eventType, Listener listener) {
		customers.get(eventType).add(listener);
	}

	public void unsubscribe(Event eventType, Listener listener) {
		customers.get(eventType).remove(listener);
	}

	public void notifyCustomers(Event eventType) {
		customers.get(eventType).forEach(listener -> listener.update(eventType));
	}

}

public class ObserverPatternClient {
	public static void main(String... strings) {
		Store store = new Store();
		
		EmailMsgListener email1 = new EmailMsgListener("padmesh@good.com");
		EmailMsgListener email2 = new EmailMsgListener("amar@good.com");
		EmailMsgListener email3 = new EmailMsgListener("ram@good.com");
		MobileAppListener mobile1 = new MobileAppListener("Padmesh");
		
		// subcribing in the notificationservice by user and email
		store.getService().subscribe(Event.NEW_ITEM,email1);
		store.getService().subscribe(Event.SALE, email2);
		store.getService().subscribe(Event.SALE, email3);
		store.getService().subscribe(Event.NEW_ITEM, mobile1);

		//sending notification to the customers who subscribe for new_item event
		store.newItemPromotion(); // 

		System.out.println("==========================================");

		//sending notification to the customers who subscribe for sale event
		store.salePromotion();

		System.out.println("==========================================");

		// unsubcribing in the notificationservice by user and email
		store.getService().unsubscribe(Event.SALE,email2);
		store.salePromotion();
	}
}