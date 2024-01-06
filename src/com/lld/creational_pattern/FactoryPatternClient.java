package com.lld.creational_pattern;


public class FactoryPatternClient{
	
	public static void main(String... strings) {
		ShapeInstanceFactory shape = new ShapeInstanceFactory();
		Shape circleObj = shape.getShapeInstance("Circle");
		Shape squareObj = shape.getShapeInstance("Square");
		squareObj.computeArea();
		circleObj.computeArea();
	}
	
}

class ShapeInstanceFactory{
	
	public Shape getShapeInstance(String name) {
		if(name.equals("Circle")) {
		     	return new Circle();
		}else if(name.equals("Square")) {
			return new Square();
	    }
//      else if(name.equals("Many ")) {  ///add any type if extensions are there
//	     	
//	    }
		return null;
	}
}

interface Shape{
	public void computeArea();
}

class Square implements Shape{

	@Override
	public void computeArea() {
		System.out.println("Area of Square");
	}

}

class Circle implements Shape{

	@Override
	public void computeArea() {
		System.out.println("Area of Circle");
	}

}