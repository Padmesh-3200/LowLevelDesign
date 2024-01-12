package com.lld.structural_pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

interface Box{
	double calculatePrice();
}

class CompositeBox implements Box{
	private final List<Box> children = new ArrayList<>();
	public CompositeBox(Box... boxes) {
		children.addAll(Arrays.asList(boxes));
	}
	@Override
	public double calculatePrice() {
		return children.stream().mapToDouble(Box::calculatePrice).sum();
	}
}

abstract class Product implements Box{
	
	protected String title ;
	protected double price ;
	
	public Product(String title, double price) {
		this.title = title;
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
	public String getTitle() {
		return title;
	}
	
}

class Book extends Product{
	public Book(String title,double price) {
		super(title,price);
	}

	@Override
	public double calculatePrice() {
		return getPrice();
	}
}

class VideoGame extends Product{
	public VideoGame(String title,double price) {
		super(title,price);
	}

	@Override
	public double calculatePrice() {
		return getPrice();
	}

	
}

class DeliveryService{
	private Box box;
	public DeliveryService() {
		
	}
	
	public void setupOrder(Box... boxes) {
		this.box = new CompositeBox(boxes);
	}
	public double calculateOrderPrice() {
		return box.calculatePrice();
	}
}
public class CompositePatternClient{
	public static void main(String...strings) {
		
		DeliveryService delivery = new DeliveryService();
		delivery.setupOrder(
				new CompositeBox(
						new VideoGame("1",100)
				),
				new CompositeBox(
						new CompositeBox(
								new Book("2",200),
								new Book("3",300)
						),
						new VideoGame("4",400),
						new VideoGame("4",600)
				)
		);
		System.out.println("The Delivery box total price :" +delivery.calculateOrderPrice());	
		
	}
}
