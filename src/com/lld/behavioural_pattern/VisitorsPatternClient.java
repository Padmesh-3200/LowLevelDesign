package com.lld.behavioural_pattern;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


//use case : a software company Dev working in insurance company
//to represent the client of this company , so create class for each one based on the client category here( Bank, Resident, Company, Restaurant)
//
// Now you want to send the mail to all the client based thier category 

// for ex : if it is resident the medical insurance ad will be sent, if it is bank a theft insurance mail will be sent.
// first approch we will add sendMail method to all the client and will send the mail reagarding to the client.

//actually by doing this we voilated open/close priciple and Single responsibility principle

//Our client plays as the model object as getters and setter , but a functionality is introduced in it.

//Suppose in future we need to change any flow in email send for some client 
//we need to modify the flow in every class we introduced the email send functionality

//So now the visitor pattern comes to the play , it is behavioral pattern.
// It seperates the algorithms or behaviors from the objects on which they operate

interface Visitor {

    void visitMailAction(Bank bank);

    void visitMailAction(Company company);

    void visitMailAction(Resident resident);

    void visitMailAction(Restaurant restaurant);

}

abstract class Client {

    private final String name;
    private final String address;
    private final String number;

    public abstract void acceptMailAction(Visitor visitor);
    
    protected Client (String name, String address, String number) {
    	this.name = name;
    	this.number =number;
    	this.address = address;
    }
    
    public String getName() {
		return name;
	}

}

class Bank extends Client {

    private final int branchesInsured;

    public Bank(String name, String address, String number, int branchesInsured) {
        super(name, address, number);
        this.branchesInsured = branchesInsured;
    }

    @Override
    public void acceptMailAction(Visitor visitor) {
        visitor.visitMailAction(this);
    }

}

class Resident extends Client {

    private final String insuranceClass;

    public Resident(String name, String address, String number, String insuranceClass) {
        super(name, address, number);
        this.insuranceClass = insuranceClass;
    }

    @Override
    public void acceptMailAction(Visitor visitor) {
        visitor.visitMailAction(this);
    }

}

class Company extends Client {

    private final int nbrOfEmployees;

    public Company(String name, String address, String number, int nbrOfEmployees) {
        super(name, address, number);
        this.nbrOfEmployees = nbrOfEmployees;
    }

    @Override
    public void acceptMailAction(Visitor visitor) {
        visitor.visitMailAction(this);
    }

}

class Restaurant extends Client {

    public final boolean availableAbroad;

    public Restaurant(String name, String address, String number, boolean availableAbroad) {
        super(name, address, number);
        this.availableAbroad = availableAbroad;
    }

    @Override
    public void acceptMailAction(Visitor visitor) {
        visitor.visitMailAction(this);
    }

}

class InsuranceMessagingVisitor implements Visitor {

    public void sendInsuranceMails(List<Client> clients) {
        for (Client client : clients) {
            client.acceptMailAction(this); 
          // visit(this) 
          // we should not handle like this
          // because we can't know which kind of class instance at runtime to downcast the Object (this)
          
          // client.accept(this); 
          // DOUBLE DISPATCH : So we get the client object and call 
          // the accept method on it to get the instance of that specific client object to get the correct visit method of it.
        }
    }

    public void visitMailAction(Bank bank) {
        System.out.println("Sending mail about theft insurance to " + bank.getName());
    }

    public void visitMailAction(Company company) {
        System.out.println("Sending employees and equipment insurance mail to " + company.getName());
    }

    public void visitMailAction(Resident resident) {
        System.out.println("Sending mail about medical insurance to " + resident.getName());
    }

    public void visitMailAction(Restaurant restaurant) {
        System.out.println("Sending mail about fire and food insurance to " + restaurant.getName());
    }

}

public class VisitorsPatternClient{
	public static void main(String... str) {
		
		String a[]
                = new String[] { "A", "B", "C", "D" };
		  List<Client> clients = new ArrayList<Client>(Arrays.asList(
	                new Bank("bank_name", "bank_address", "bank_number", 10),
	                new Resident("resident_name", "resident_address", "resident_number", "A"),
	                new Company("company_name", "company_address", "company_number", 1000),
	                new Restaurant("resto_name", "resto_address", "resto_number", true)
	        ));

		  
	 
	            // Getting the list view of Array
	            List<String> list = Arrays.asList(a);
	        InsuranceMessagingVisitor visitor = new InsuranceMessagingVisitor();
	        visitor.sendInsuranceMails(clients);
	}
}