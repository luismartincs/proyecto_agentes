//javac -cp jade.jar MessageReceived.java
package behaviours;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

public class MessageReceived extends CyclicBehaviour {
	public void action (){
		
		ACLMessage msg=myAgent.receive();
		if(msg !=null){
			System.out.println("Got a message ");
			System.out.println(msg.getContent());
		}
		else{
			block();
		}
	}
}