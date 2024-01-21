package com.lld.behavioural_pattern;

import java.util.ArrayList;
import java.util.List;

//This pattern , provies a way to access element of a Collection sequentially 
// without exposing the underlying representations of the collection

// use case : Collections java

// Each and Every Concrete Class in Collection has its own way of storing the data

// 

// Interface for Iterating operation
interface Iterator {    
	public boolean hasNext();
	public Object next();
}

// Interface for obtaining Iterating Container to do iterator operation on the Collection object
interface Container {
	public Iterator getIterator();
}

// Class for Collecting the Object  (Assume it is an ArrayList , or LinkedList)
class CollectionContainer implements Container {
	
	//Hard coded values for the CollectionContainer (just to the know the Iterator pattern)
	public String names[] = { "Robert", "John", "Julie", "Lora" };

	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}

	// getting the specific iterator for CollectionContainer class 
	private class NameIterator implements Iterator {

		int index;

		@Override
		public boolean hasNext() {

			if (index < names.length) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {

			if (this.hasNext()) {
				return names[index++];
			}
			return null;
		}
	}
}

public class IteratorPatternClient {

	public static void main(String[] args) {
		CollectionContainer collection = new CollectionContainer();
		
		Iterator iter = collection.getIterator();
				
		while (iter.hasNext()) {
			String name = (String) iter.next();
			System.out.println("Name : " + name);
		}
	}
}
