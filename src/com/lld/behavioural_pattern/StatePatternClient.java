package com.lld.behavioural_pattern;

// This is a behavioural pattern, and it allows an object alter its behaviour when its internal behaviour changes.
//use Case:
// Lets assume there are 3 different states in Phone , Lock, Ready and OffState

// These 3 states will be attained at any of the state present

// using the clicking the home button and the power button on/off the state of the machine ( phone ),will be changed.

// So changing the state in the phone class onSpecific actions on the phone 

//Note : STATE VS STRATEGY : State can be considered as an extension of Strategy as both patterns are based on composition, 
//they change the behaviour of the context(Phone) by delegating some work to helper Objects

//STATE : 
//1) State can be dependent as you can easily jump from one state to another
//2) The State Pattern is about doing different things based on the state, hence the result may vary.
//   the OffState and ReadySate and LockSate will do the the same method mentioned in super class,
//   but varies from result based on the state where the context currently is.

//STRATEGIES : 
//1) Strategies are completely independent and unaware of each other
//2) the Strategy pattern is really about having different implementations(different process by subclass, 
// but has same steps mentioned in superclass or interface) that accomplish the same thing


abstract class State {

    protected Phone phone;

    public State(Phone phone) {
        this.phone = phone;
    }

    // these action will change the state of the machine object.
    public abstract String onHome();

    public abstract String onOffOn();

}

//Concrete state
class ReadyState extends State {

    public ReadyState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        return phone.home();
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }

}

//Concrete state
class OffState extends State {

    public OffState(Phone phone) {  // initialy geting the machine instance here.
        super(phone);
    }

    @Override
    public String onHome() {
        phone.setState(new LockedState(phone));
        return phone.turnOn();
    }

    @Override
    public String onOffOn() {
        phone.setState(new LockedState(phone));   //when we press power button in offstate it will go to lock state and will turn on the phone
        return phone.turnOn();
    }

}

//Concrete state
class LockedState extends State {

    public LockedState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        phone.setState(new ReadyState(phone));
        return phone.unlock();
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return phone.lock();
    }

}


//State Machine
class Phone {

    private State state;

    public Phone() {
        state = new OffState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public String lock() {
        return "Locking phone and turning off the screen";
    }

    public String home() {
        return "Going to home-screen";
    }

    public String unlock() {
        return "Unlocking the phone to home";
    }

    public String turnOn() {
        return "Turning screen on, device still locked";
    }

    public String clickHome() {
        return state.onHome();
    }

    public String clickPower() {
        return state.onOffOn();
    }

}

public class StatePatternClient{
	public static void main(String... strings) {
		Phone phone = new Phone();
        simulatePhoneClicks(phone);
	}
	private static void simulatePhoneClicks(Phone phone) {
        System.out.println(phone.clickPower());
        System.out.println(phone.clickPower());
        System.out.println(phone.clickHome());
        System.out.println(phone.clickHome());
        System.out.println(phone.clickHome());
        System.out.println(phone.clickPower());
        System.out.println(phone.clickPower());
        System.out.println(phone.clickHome());
    }
}