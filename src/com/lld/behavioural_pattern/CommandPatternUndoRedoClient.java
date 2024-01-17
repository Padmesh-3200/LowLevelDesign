package com.lld.behavioural_pattern;

import java.util.Stack;

// main parts of this pattern 

// Reciever
// Commander
// Invoker

//Commader
interface CommandA {
	public void execute();

	public void undo();
}

// Command Impl
class TurnACOnCommand implements CommandA {
	AirConditioner ac;

	TurnACOnCommand(AirConditioner ac) {
		this.ac = ac;
	}

	@Override
	public void execute() {
		ac.swithAcOn();
	}

	@Override
	public void undo() {
		ac.swithAcOff();
	}

}

// Command Impl
class TurnACOffCommand implements CommandA {
	AirConditioner ac;

	TurnACOffCommand(AirConditioner ac) {
		this.ac = ac;
	}

	@Override
	public void execute() {
		ac.swithAcOff();
	}

	@Override
	public void undo() {
		ac.swithAcOn();
	}

}

// Reciever
class AirConditioner {
	boolean ison;
	int temperature;

	public void swithAcOn() {
		ison = true;
		System.out.println("AC is ON");

	}

	public void swithAcOff() {
		ison = false;
		System.out.println("AC is OFF");

	}

	public void setTemperature(int temp) {
		this.temperature = temp;
		System.out.println("Temperature changed to: temperature " + temp + " Celsius");
	}

}

// Invoker
class RemoteControl {
	Stack<CommandA> acCommandHistory = new Stack<>();
	CommandA command;

	RemoteControl() {
	}

	public void setCommand(CommandA command) {
		this.command = command;
	}

	public void pressButton() {
		command.execute();
		acCommandHistory.add(command);
	}

	public void undo() {
		if (!acCommandHistory.isEmpty()) {
			CommandA lastCommand = acCommandHistory.pop();
			lastCommand.undo();
		}
	}
}

class CommandPatternUndoRedoClient {
	public static void main(String[] args) {
		// AC object
		AirConditioner airConditioner = new AirConditioner();
		// remote
		RemoteControl remoteObj = new RemoteControl();
		// create the command and press the button
		remoteObj.setCommand(new TurnACOnCommand(airConditioner));
		remoteObj.pressButton();
		remoteObj.setCommand(new TurnACOffCommand(airConditioner));
		remoteObj.pressButton();
		remoteObj.setCommand(new TurnACOnCommand(airConditioner));
		remoteObj.pressButton();
		remoteObj.setCommand(new TurnACOffCommand(airConditioner));
		remoteObj.pressButton();
		remoteObj.setCommand(new TurnACOnCommand(airConditioner));
		remoteObj.pressButton();
		
		// undo the last operation
		System.out.print("undo operation : ");remoteObj.undo(); 
	}
}
