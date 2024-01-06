package com.lld.creational_pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//In this builder pattern Student is ultimate object 



public class BuilderPatternClient{
	public static void main (String... strings) {
		
		Director director1 = new Director(new EngineerStudentBuilder());
		Director director2 = new Director(new MBAStudentBuilder());
		
		Student mba = director2.createMBAStudent();
		Student engineer = director1.createEngineerStudent();
		
		System.out.println(mba);
		System.out.println(engineer);
		
	}
}

// it is used to create Student object of its type 
class Director{
	StudentBuilder studentBuilder;
	
	Director(StudentBuilder studentBuilder){
		this.studentBuilder = studentBuilder;
	}
	public Student createStudent() {
		if(studentBuilder instanceof EngineerStudentBuilder) {
			return createEngineerStudent();
		}else if(studentBuilder instanceof MBAStudentBuilder) {
			return createMBAStudent();
		}
		
		return null;
	}
	public Student createMBAStudent() {
		// TODO Auto-generated method stub
		return studentBuilder.setRollNumber(001).setAge(23).setName("MBAStudent1").setSubjects().build();
		
	}
	public Student createEngineerStudent() {
		// TODO Auto-generated method stub
		return studentBuilder.setRollNumber(002).setAge(22).setName("EngineerStudent1").setSubjects().build();
		
	}
	
}


// Replication of Student Object
abstract class StudentBuilder{
	int rollNumber;
	int age;
	String name;
	List<String> subjects;
	
	public StudentBuilder setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
		return this;
	}


	public StudentBuilder setAge(int age) {
		this.age = age;
		return this;
	}


	public StudentBuilder setName(String name) {
		this.name = name;
		return this;
	}


	abstract public StudentBuilder setSubjects();
	
	public Student build() {
		return new Student(this);
	}
	
	
}

//Student Builder Differ by Subjects
class MBAStudentBuilder extends StudentBuilder{

	public StudentBuilder setSubjects() {
		List<String> subjects = new ArrayList<String>();
		subjects.add("mba1");
		subjects.add("mba2");
		subjects.add("mba3");
		this.subjects = subjects;
		return this;
	}
}

//Student Builder Differ by Subjects
class EngineerStudentBuilder extends StudentBuilder{

	@Override
	public StudentBuilder setSubjects() {
		List<String> subjects = new ArrayList<String>();
		subjects.add("Eng1");
		subjects.add("Eng2");
		subjects.add("Eng3");
		this.subjects = subjects;
		return this;
	}
}

class Student{
	int rollNumber;
	int age;
	String name;
	List<String> subjects;
	
	Student(StudentBuilder studentBuilder){
		this.rollNumber = studentBuilder.rollNumber;
		this.age = studentBuilder.age;
		this.name = studentBuilder.name;
		this.subjects = studentBuilder.subjects;
	}
	public String toString() {
		return "Roll Number : "+this.rollNumber+" Age : "+this.age+" Name : "+this.name+" Subjects : "+this.subjects;
	}
}