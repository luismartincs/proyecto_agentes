package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import serial.RobotInterface;


public class CallForPosition extends CyclicBehaviour{

	private RobotInterface serialComm;


	public void onStart(){
		System.out.println("Opening the port");
		serialComm = new RobotInterface();
	}

	public void action() {
	  	
	  	ACLMessage msg = myAgent.receive();

	  	if (msg != null) {
	  		System.out.println("Message received!");
			System.out.println(msg.getContent());
			serialComm.write(msg.getContent().charAt(0));
		}else {
		    block();
		} 
	}

}
