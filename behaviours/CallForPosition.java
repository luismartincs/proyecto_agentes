package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


public class CallForPosition extends CyclicBehaviour{

	public void action() {
	  	
	  	ACLMessage msg = myAgent.receive();

	  	if (msg != null) {
	  		System.out.println("Message received!");
			System.out.println(msg.getContent());
		}else {
		    block();
		} 
	}

}