// Programa de creación del agente camera 

//javac -cp jade.jar;. CameraAgent.java  -- compilar


//java -cp jade.jar;. jade.Boot -agents camera1:CameraAgent -gui


import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;
import behaviours.CallForOffer;

public class CameraAgent extends Agent{
	private CallForOffer moveBehaviour;
	protected void setup () {
		System.out.println("I am an Agent "+getAID().getLocalName() );
		
		// Enviar un mensaje al agente robot1
		/*ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID("robot1",AID.ISLOCALNAME));
		msg.setContent("Hola amigo");
		send(msg);
		*/
		addBehaviour(new CallForOffer ());
	}
}