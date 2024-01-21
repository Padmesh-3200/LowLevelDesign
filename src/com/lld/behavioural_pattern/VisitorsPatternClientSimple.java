package com.lld.behavioural_pattern;


// Use case:  activate the computer by parts

interface ComputerPart{
	public void acceptActivation(ComputerActivationVisitor visitor);
}

class KeyBoard implements ComputerPart{

	@Override
	public void acceptActivation(ComputerActivationVisitor visitor) {
		visitor.visit(this);
	}
	
}

class Mouse implements ComputerPart{

	@Override
	public void acceptActivation(ComputerActivationVisitor visitor) {
		visitor.visit(this);
	}
	
}

class Monitor implements ComputerPart{

	@Override
	public void acceptActivation(ComputerActivationVisitor visitor) {
		visitor.visit(this);
	}
	
}

class CPU implements ComputerPart{

	@Override
	public void acceptActivation(ComputerActivationVisitor visitor) {
		visitor.visit(this);
	}
	
}

class Computer {

	ComputerPart[] parts;
	Computer(){
		parts = new ComputerPart[] {
				new CPU(),
				new Monitor(),
				new KeyBoard(),
				new Mouse()
				};
	}
	
	
	public void startUpTheComputer(ComputerActivationVisitor visitor) {
		 
		for (ComputerPart part : parts) {
			part.acceptActivation(visitor);
	      }
		visitor.visit(this);
	}
	
}

interface IComputerActivationVisitor {
	public void visit(CPU computer);
	public void visit(Mouse mouse);
	public void visit(KeyBoard keyboard);
	public void visit(Monitor monitor);
}

class ComputerActivationVisitor implements IComputerActivationVisitor{

	@Override
	public void visit(CPU computer) {
		System.out.println("CPU is activating");
		// CPU related operations
	}

	public void visit(Computer computer) {
		System.out.println("Computer is Ready to Use :)");
	}

	@Override
	public void visit(Mouse mouse) {
		System.out.println("Mouse is activating");
		// Mouse related operations
	}

	@Override
	public void visit(KeyBoard keyboard) {
		System.out.println("Keyboard is activating");
		// Keyboard related operations
	}

	@Override
	public void visit(Monitor monitor) {
		System.out.println("Monitor is activating");
		// Monitor related operations
	}	
}

public class VisitorsPatternClientSimple{
	public static void main(String...strings) {
		Computer computer = new Computer();
		computer.startUpTheComputer(new ComputerActivationVisitor());
	}
}