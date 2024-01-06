package com.lld.creational_pattern;

public class PrototypePatternClient{
	public static void main(String...strings) {
		
		StudentPrototype student = new StudentPrototype(001,"Kumar");
		StudentPrototype studentClone = (StudentPrototype) student.getClone();
		
		System.out.println("student Obj: "+student+" cloned student Obj: "+studentClone);
	}
}

interface Prototype{
	public Prototype getClone();
}

class StudentPrototype implements Prototype{
	
	private int id;
	private String name;
	
	StudentPrototype(int id,String name){
		this.id =id;
		this.name =name;
	}
	
	@Override
	public Prototype getClone() {
		// TODO Auto-generated method stub
		return new StudentPrototype(id,name);
	}
	
	
	
}