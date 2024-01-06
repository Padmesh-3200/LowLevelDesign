package com.lld.creational_pattern;


public class AbstractFactoryPatternClient{
	
	public static void main(String... strings) {
		AbstractFactoryProducer carType = new AbstractFactoryProducer();
		AbstractFactory economyCar = carType.getFactoryInstance("Economy");
		Car economyA = economyCar.getInstance(3000);
		Car economyB = economyCar.getInstance(6002);
		System.out.println("economyA speed "+economyA.getTopSpeed());
		System.out.println("economyB speed "+economyB.getTopSpeed());
		AbstractFactory premiumCar = carType.getFactoryInstance("Premium");
		Car premiumA = premiumCar.getInstance(30000);
		Car premiumB = premiumCar.getInstance(60040);
		System.out.println("premiumA speed "+premiumA.getTopSpeed());
		System.out.println("premiumB speed "+premiumB.getTopSpeed());
		
		
	}
}

class AbstractFactoryProducer{
	
	public AbstractFactory getFactoryInstance(String name) {
		if(name.equals("Premium")) {
		     return new PremiumCarFactory();
		}else if(name.equals("Economy")) {
			return new EconomyCarFactory();
	    }
//      else if(name.equals("Many ")) {  ///add any type if extensions are there
//	     	
//	    }
		return null;
	}
}

interface AbstractFactory{
	public Car getInstance(int price);
}


class EconomyCarFactory implements AbstractFactory{

	@Override
	public Car getInstance(int price) {
		if(price>=3000 && price<=3500) {
			return new EconomyCarA();
		}else if(price>=6001 && price<=6500) {
			return new EconomyCarB();
		}
		return null;
	}
}

class PremiumCarFactory implements AbstractFactory{

	@Override
	public Car getInstance(int price) {
		if(price>=30000 && price<=35000) {
			return new PremiumCarA();
		}else if(price>=60000 && price<=65000) {
			return new PremiumCarB();
		}
		return null;
	}
}

interface Car{
	public int getTopSpeed();
}

class EconomyCarA implements Car{

	@Override
	public int getTopSpeed() {
		// TODO Auto-generated method stub
		return 70;
	}
	
}

class EconomyCarB implements Car{

	@Override
	public int getTopSpeed() {
		// TODO Auto-generated method stub
		return 80;
	}
	
}

class PremiumCarA implements Car{

	@Override
	public int getTopSpeed() {
		// TODO Auto-generated method stub
		return 200;
	}
	
}

class PremiumCarB implements Car{

	@Override
	public int getTopSpeed() {
		// TODO Auto-generated method stub
		return 230;
	}
	
}


