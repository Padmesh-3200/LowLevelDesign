package com.lld.behavioural_pattern;

//The Command pattern turns the request itself into an object. 
//This object can be stored and passed around like other objects

// interface for command 
interface Command {
    void execute();
}

//actions on a component and these present in the Invoker Room
class OpenCloseCurtainsCommand implements Command {
    private final Curtains curtains;

    public OpenCloseCurtainsCommand(Curtains curtains) {
        this.curtains = curtains;
    }

    public Curtains getCurtains() {
        return curtains;
    }

    @Override
    public void execute() {
        curtains.openClose();
    }
}


//actions on a component and these present in the Invoker Light 
class SwitchLightsCommand implements Command {
    private final Light light;

    public SwitchLightsCommand(Light light) {
        this.light = light;
    }

    public Light getLight() {
        return light;
    }

    @Override
    public void execute() {
        light.switchLights();
    }
}

//reason for this class is ,
// avoiding code repetation in all invoker so placed in an abstract class and 
// called from subclass invokers
abstract class Invoker {

    private Command command;

    public void setCommand(Command command) { 
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

}

//Reciever
class Light {

    private boolean switchedOn = false;

    public void switchLights() {
        switchedOn = !switchedOn;
    }

    public boolean isSwitchedOn() {
        return switchedOn;
    }

}

//Reciever
class Curtains {

    private boolean open = false;

    public void openClose() {
        open = !open;
    }

    public boolean isOpen() {
        return open;
    }

}

//Invoker
class FloorLamp extends Invoker {

    private final Light light;

    public FloorLamp() {
        this.light = new Light();
    }

    public Light getLight() {
        return light;
    }

    public boolean isLightOn() {
        return light.isSwitchedOn();
    }

}

//Invoker
class Room extends Invoker {

    private final Curtains curtains;

    public Room() {
        this.curtains = new Curtains();
    }

    public Curtains getCurtains() {
        return curtains;
    }

    public boolean curtainsOpen() {
        return curtains.isOpen();
    }

}

public class CommandPatternClient{
	public static void main(String...strings) {
		Room room = new Room();
        room.setCommand(new OpenCloseCurtainsCommand(room.getCurtains()));
        room.executeCommand();
        System.out.println(room.curtainsOpen());

        System.out.println("==========================================");

        FloorLamp lamp = new FloorLamp();
        lamp.setCommand(new SwitchLightsCommand(lamp.getLight()));
        lamp.executeCommand();
        System.out.println(lamp.isLightOn());
	}
}