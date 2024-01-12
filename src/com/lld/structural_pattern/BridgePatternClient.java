package com.lld.structural_pattern;

//Divides and orgainizes a single class that has 
//mutiple variates of some functionality into two hierarchies: 

//ABSTRACTION and IMPLEMENTATION

//use case : 
//Assume u work in pizza delivery app and in that it shows multiple restaurants and different kinds of pizza.

//Assume there is Italian and American restaurant each has their own way of preparing pizza 
//and there are VegPizza and PepperoniPizza 

//it is hard to create classes as ItalianVegPizza, ItalianPepperoniPizza , AmericanVegPizza, AmericanPepperoniPizza

//In this case the variants of a pizza will also grow and the restaurants 
//also grow, for example in future Mushroom Pizza and Chicken Pizza will be added
//and Indian and Chinese Restaurants may be added so we want to create combinations of all pizza and restaurants.
//here the BRIDGE pattern is used.

//Here :
//Pizza : abstract class for pizza 
//VegPizza, PepperoniPizza : Concrete class for pizza impl

//Restaurant : abstract class for Restaurant
//AmericanRestaurant, ItallianRestaurant : concrete class for Restaurant impl

abstract class Pizza {

	protected String sauce;
	protected String toppings;
	protected String crust;

	public abstract void assemble();

	public abstract void qualityCheck();

	public void setToppings(String toppings) {
		this.toppings = toppings;
	}

	public void setSauce(String sauce) {
		this.sauce = sauce;
	}

	public void setCrust(String crust) {
		this.crust = crust;
	}

}

class PepperoniPizza extends Pizza {

	@Override
	public void assemble() {
		System.out.println("Adding Sauce: " + sauce);
		System.out.println("Adding Toppings: " + toppings);
		System.out.println("Adding Pepperoni");
	}

	@Override
	public void qualityCheck() {
		System.out.println("Crust is: " + crust);
	}

}

class VeggiePizza extends Pizza {

	@Override
	public void assemble() {
		System.out.println("Adding Sauce: " + sauce);
		System.out.println("Adding Toppings: " + toppings);
		System.out.println("Adding Cheese");
	}

	@Override
	public void qualityCheck() {
		System.out.println("Crust is: " + crust);
	}

}

abstract class Restaurant {

	protected Pizza pizza;
	protected String restaurantName;

	protected Restaurant(Pizza pizza) {
		this.pizza = pizza;
	}

	abstract void addSauce();

	abstract void addToppings();

	abstract void makeCrust();

	public void deliver() {
		System.out.println("Order is taken in "+restaurantName);
		makeCrust();
		addSauce();
		addToppings();
		pizza.assemble();
		pizza.qualityCheck();
		System.out.println("Order in Progress!");
	}

}

class ItalianRestaurant extends Restaurant {
	
	private final String name = "Italian Restaurant";

	public ItalianRestaurant(Pizza pizza) {
		super(pizza);
		setingRestaurantName();
	}

	private void setingRestaurantName() {
		super.restaurantName = name;
	}

	@Override
	public void addToppings() {
		pizza.setToppings("-");
	}

	@Override
	public void addSauce() {
		pizza.setSauce("Oil");
	}

	@Override
	public void makeCrust() {
		pizza.setCrust("Thin");
	}

}

class AmericanRestaurant extends Restaurant {
	
	private final String name = "American Restaurant";

	public AmericanRestaurant(Pizza pizza) {
		super(pizza);
		setingRestaurantName();
	}

	private void setingRestaurantName() {
		super.restaurantName = name;
	}
	
	@Override
	public void addToppings() {
		pizza.setToppings("Everything");
	}

	@Override
	public void addSauce() {
		pizza.setSauce("Super Secret Recipe");
	}

	@Override
	public void makeCrust() {
		pizza.setCrust("Thick");
	}

}

public class BridgePatternClient {

	public static void main(String... strings) {

		Restaurant americanRestaurant = new AmericanRestaurant(new PepperoniPizza());
		americanRestaurant.deliver();

		System.out.println("==========================================");

		Restaurant italianRestaurant = new ItalianRestaurant(new VeggiePizza());
		italianRestaurant.deliver();

	}

}