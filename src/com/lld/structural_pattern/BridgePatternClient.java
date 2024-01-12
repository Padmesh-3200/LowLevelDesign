package com.lld.structural_pattern;

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