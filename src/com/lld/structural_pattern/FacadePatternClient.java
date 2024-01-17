package com.lld.structural_pattern;

class UIService {

    public static String getLoggedInUserId() {
        return "";
    }

    public void logIn(String username, String password) {
        System.out.println("Logging In...");
    }

    public void logout() {
        System.out.println("Logging Out...");
    }

}

class DatabaseServiceCrypto {

    public User getUser(String userId) {
        return new User("001", "User 1", 1000, "INR");
    }

}

//container class using record -JAVA 16
class User {
    private final String id;
    private final String name;
    private final double balance;
    private final String currency;

    public User(String id, String name, double balance, String currency) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}

abstract class CryptoService {

    private DatabaseServiceCrypto databaseService;
    private SomeComplexStuff complexStuff;

    public abstract void buyCurrency(User user, double amount);

    public static class SomeComplexStuff {
    	public static void doOtherWorks() {
    		System.out.println("Other Complex works done during buying the crypto");
    	}
    }

}

class CryptoFactory {

    public static CryptoService getCryptoService(String currency) {
        if (currency.equals("BTC")) {
            return new BitcoinService();
        } else if (currency.equals("ETH")) {
            return new EthereumService();
        } else {
            // More Stuff
            return new BitcoinService();
        }
    }

}

class EthereumService extends CryptoService {

    @Override
    public void buyCurrency(User user, double amount) {
        System.out.println("Buying " + amount + " of Ethereum...");
        SomeComplexStuff.doOtherWorks();
    }

}

class BitcoinService extends CryptoService {

    @Override
    public void buyCurrency(User user, double amount) {
        System.out.println("Buying " + amount + " of Bitcoin...");
        SomeComplexStuff.doOtherWorks();
    }

}

class MailService {

    public void sendConfirmationMail(User user) {
        System.out.println("Sending mail to " + user.getName());
    }

}


/// abstraction - hiding the other process during buying of crypto , 
//so client dont want explicitly call loggined user obj and db obj mailService obj and checkbalance of user
//Facade pattern will do that all
class BuyCryptoFacade {

    public void buyCryptocurrency(double amount, String currency) {

    	DatabaseServiceCrypto dbService = new DatabaseServiceCrypto();
        User user = dbService.getUser(UIService.getLoggedInUserId());
        if (user.getBalance() < amount) {
            System.out.println("Insufficient balance to perform transaction");
            return;
        }
        CryptoFactory.getCryptoService(currency).buyCurrency(user, amount);
        MailService mailService = new MailService();
        mailService.sendConfirmationMail(user);
        System.out.println(amount + " of " + currency + " bought successfully!");

    }

}
public class FacadePatternClient{
	public static void main(String...strings ) {
		BuyCryptoFacade buyCrypto = new BuyCryptoFacade();
        buyCrypto.buyCryptocurrency(1000, "BTC");
	}
}