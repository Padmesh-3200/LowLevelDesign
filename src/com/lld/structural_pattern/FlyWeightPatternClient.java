package com.lld.structural_pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


//FlyWeight is a structural design pattern that lets you 
//fit more objects into the available amount of RAM by sharing common parts
//of state between multiple objects instead of keeping all of the data in each object.

//extrinsic state (different values across all object) 
class BookF {

	private final String name;
    private final double price;
    private final BookTypeF type;
    public BookF(String name, double price, BookTypeF type) {
		this.name =name;
		this.price =price;
		this.type = type;
	}

}


//intrinsix state (same set of value across all object)  -- here mapping key is booktype 
class BookTypeF {
	private final String type;
    private final String distributor;
    private final String otherData;

    public BookTypeF(String type, String distributor, String otherData) {
    	this.distributor =distributor;
    	this.type = type;
    	this.otherData = otherData;
	}
}

//class involving for storing the books in list 
class Store {

    private final List<BookF> books = new ArrayList<>();

    
    public void storeBook(String name, double price, String type, String distributor, String otherData) {
        BookTypeF bookType = BookFactory.getBookType(type, distributor, otherData); // getting cache value
        books.add(new BookF(name, price, bookType));
    }

    public void displayBooks() {
        books.forEach(System.out::println);
    }

}


// The real hero of this pattern
// Cache the same set of value in a object for the first time, and caching it for next retrieval.
// here - booktype is mapping key for the same set values of object
class BookFactory {

    private static final Map<String, BookTypeF> bookTypes = new HashMap<>();

    public static BookTypeF getBookType(String type, String distributor, String otherData) {
        if (bookTypes.get(type) == null) {
            bookTypes.put(type, new BookTypeF(type, distributor, otherData));
        }
        return bookTypes.get(type);
    }

}


public class FlyWeightPatternClient{
	  private static final int BOOK_TYPES = 2;
	    private static final int BOOKS_TO_INSERT = 10_000_000;

	    public static void main(String[] args) {

	        Store store = new Store();
	        for (int i = 0; i < BOOKS_TO_INSERT / BOOK_TYPES; i++) {
	            store.storeBook(getRandomName(), getRandomPrice(), "Action", "Follett", "Stuff");
	            store.storeBook(getRandomName(), getRandomPrice(), "Fantasy", "Ingram", "Extra");
	        }
//	        store.displayBooks();
	        System.out.println(BOOKS_TO_INSERT + " Books Inserted");
	        System.out.println("==========================================");
	        System.out.println("Memory Usage: ");
	        System.out.println("Book Size (20 bytes) * " + BOOKS_TO_INSERT + " + BookTypes Size (30 bytes) * " + BOOK_TYPES + "");
	        System.out.println("==========================================");
	        System.out.println("Total: " + ((BOOKS_TO_INSERT * 20 + BOOK_TYPES * 30) / 1024 / 1024) + "MB (instead of " + ((BOOKS_TO_INSERT * 50) / 1024 / 1024) + "MB)");
	        // Tip: Try to comment out the @ToString annotation in the BookType class and check that indeed the same two objects are being referenced by all our books!

	    }

	    private static String getRandomName() {
	        List<String> books = List.of("book_1", "book_2", "book_3", "book_4", "book_5", "book_6", "book_7", "book_8", "book_9", "book_10");
	        return books.get(new Random().nextInt(books.size()));
	    }

	    private static double getRandomPrice() {
	        return new Random().nextDouble(10, 200);
	    }

}