package com.lld.behavioural_pattern;


// This is Behavioural pattern , an it defines a family of algorithms,
// puts each of them in a seperate class , and makes their objects interchangeable.

//use case : Payments in delivery app using different payment gateways.


// Some of the strategy which will be common for all payments, but may be the process vary from payments.


// Note : STATE VS STRATEGY : State can be considered as an extension of Strategy as both patterns are based on composition, 
// they change the behaviour of the context(PaymentService) by delegating some work to helper Objects

// STATE : 
// 1) State can be dependent as you can easily jump from one state to another
// 2) The State Pattern is about doing different things based on the state, hence the result may vary.

// STRATEGIES : 
// 1) Strategies are completely independent and unaware of each other
// 2) the Strategy pattern is really about having different implementations(different process by subclass, 
//    but has same steps mentioned in superclass or interface) that accomplish the same thing
//    There are the different payments but does the same process called purchase item.
interface PaymentStrategy {

    void collectPaymentDetails();

    boolean validatePaymentDetails();

    void pay(int amount);

}

//Concrete payment (payment actions specific to payment gateway)
class PaymentByCreditCard implements PaymentStrategy {

    private CreditCard card;

    @Override
    public void collectPaymentDetails() {
        // Pop-up to collect card details...
        card = new CreditCard("4111111111111", "02/29", "455");
        System.out.println("Collecting Card Details...");
    }

    @Override
    public boolean validatePaymentDetails() {
        // Validate credit card...
        System.out.println("Validating Card Info: " + card);
        return true;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using Credit Card");
        card.setAmount(card.getAmount() - amount);
    }

}

//Concrete payment (payment actions specific to payment gateway)
class PaymentByPayPal implements PaymentStrategy {

    private String email;
    private String password;

    @Override
    public void collectPaymentDetails() {
        // Pop-up to collect PayPal mail and password...
        email = "PayPal Mail";
        password = "PayPal Password";
        System.out.println("Collecting PayPal Account Details...");
    }

    @Override
    public boolean validatePaymentDetails() {
        // Validate account...
        System.out.printf("Validating PayPal Info: %s | %s%n", email, password);
        return true;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using PayPal");
    }

}

//Concrete payment gateway
class CreditCard {

    private int amount = 1_000;
    private final String number;
    private final String date;
    private final String cvv;
    public CreditCard(String number, String date, String cvv) {
    	this.number = number;
    	this.date = date;
    	this.cvv = cvv;
    }
	public int getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}
	public void setAmount(int amount) {
		// TODO Auto-generated method stub
		this.amount = amount;
	}

    
}


// client only know the paymentService and the gateway ,
//the internal process for all the specific payments will be hidden from client.
class PaymentService {

    private int cost;
    private boolean includeDelivery = true;

    private PaymentStrategy strategy;

    public void processOrder(int cost) {
        this.cost = cost;
        strategy.collectPaymentDetails();
        if (strategy.validatePaymentDetails()) {
            strategy.pay(getTotal());
        }
    }

    private int getTotal() {
        return includeDelivery ? cost + 10 : cost;
    }

	public void setStrategy(PaymentStrategy paymentStrategy) {
		this.strategy = paymentStrategy;
	}

}



public class StrategyPatternClient{
	public static void main(String... string) {

        PaymentService paymentService = new PaymentService();
        // The strategy can now be easily picked at runtime

        paymentService.setStrategy(new PaymentByCreditCard());
        paymentService.processOrder(100);

        System.out.println("==========================================");

        paymentService.setStrategy(new PaymentByPayPal());
        paymentService.processOrder(100);

	}
}