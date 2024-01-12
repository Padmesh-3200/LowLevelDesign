package com.lld.structural_pattern;

interface Car {
	public void constructCar();

	public int getCost();

	public void setImportantParts();
}

class EVCar implements Car {

	@Override
	public void constructCar() {
		System.out.println("Contructing EVCar");
		setImportantParts();
	}

	@Override
	public int getCost() {
		return 120000;
	}

	@Override
	public void setImportantParts() {
		System.out.println("Setting up important parts for EVCar");
		System.out.println("Setting EVBattery for Car");
		System.out.println("Setiing Wheels for EVCar");
		System.out.println("Setting up all other important parts for EVCar");
	}

}

class PetrolCar implements Car {

	@Override
	public void constructCar() {
		System.out.println("Contructing PetrolCar");
		setImportantParts();
	}

	@Override
	public int getCost() {
		return 75000;
	}

	@Override
	public void setImportantParts() {
		System.out.println("Setting up important parts for EVCar");
		System.out.println("Setting PetrolEngine for Car");
		System.out.println("Setiing Wheels for PetrolCar");
		System.out.println("Setting up all other important parts for PetrolCar");
	}

}

class DieselCar implements Car {

	@Override
	public void constructCar() {
		System.out.println("Contructing PetrolCar");
		setImportantParts();
	}

	@Override
	public int getCost() {
		return 80000;
	}

	@Override
	public void setImportantParts() {
		System.out.println("Setting up important parts for DieselCar");
		System.out.println("Setting DieselEngine for Car");
		System.out.println("Setiing Wheels for DieselCar");
		System.out.println("Setting up all other important parts for DieselCar");
	}

}

abstract class CarManufacturar {

	protected Car car;

	public CarManufacturar(Car car) {
		this.car = car;
	}

	abstract void order();
	abstract int getCost();
	public void constructCar() {
		 order();
         car.constructCar(); 
	}

}

class Hyundai extends CarManufacturar {
	
	private String name = "Hyundai";
	private int cost = 120000;
	public Hyundai(Car car) {
		super(car);
	}

	public void order() {
         System.out.println("******* Hyundai Car construction in progress **********");
	}

	public int getCost() {
	       return cost + car.getCost();
	}
}

class Honda extends CarManufacturar {
	private String name = "Honda";
	private int cost = 100000;
	public Honda(Car car) {
		super(car);
	}

	public void order() {
		System.out.println("******* Honda Car construction in progress **********");
	}

	public int getCost() {
	       return cost + car.getCost();
	}
	public String getName() {
	 return name;
	}
}

public class BridgePatternClientCar {

	public static void main(String... strings) {

		CarManufacturar hyundaiCar = new Hyundai(new EVCar());
		hyundaiCar.constructCar();
		System.out.println(" The cost of Ev Car in Hyundai is "+hyundaiCar.getCost());
		
		System.out.println(" ------------------------------------ ");
		
		CarManufacturar hondaCar = new Honda(new EVCar());
		hondaCar.constructCar();
		System.out.println(" The cost of Ev Car in Honda is "+ hondaCar.getCost());
		
		System.out.println(" ------------------------------------ ");

		CarManufacturar hyundai1Car = new Hyundai(new PetrolCar());
		hyundai1Car.constructCar();
		System.out.println(" The cost of Petrol Car in Hyundai is "+ hyundai1Car.getCost());
		
		System.out.println(" ------------------------------------ ");

		CarManufacturar honda1Car = new Honda(new DieselCar());
		honda1Car.constructCar();
		System.out.println(" The cost of Diesel Car in Honda is "+ honda1Car.getCost());
		
		System.out.println(" ------------------------------------ ");

	}

}