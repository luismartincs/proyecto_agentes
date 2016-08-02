
import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import behaviours.CallForPosition;

public class CameraAgent extends Agent{

	protected void setup() { 
		
		System.out.println("I’m a camera agent!"); 

		ACLMessage msg = new ACLMessage(ACLMessage.INFORM); 
		
		msg.addReceiver(new AID("Robot1", AID.ISLOCALNAME)); 
		msg.setLanguage("English"); 
		msg.setOntology("Weather-forecast-ontology"); 
		msg.setContent("Today it’s raining");
		send(msg);
    }

} 