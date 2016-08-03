//C:\Users\alejandra\Downloads\JADE-all-4.4.0\JADE-bin-4.4.0\jade>javac    ----   este es el directoria donde tienes jadex!!
// javac -cp jade.jar behaviours/CallForPosition.java --- compilar
 //java -cp jade.jar;. jade.Boot -agents agent1:CameraAgent  -- crear el agente "agent1" de clase "Camera Agent"
 //javac -cp jade.jar;. CameraAgent.java
//java -cp jade.jar:. jade.Boot -container -host 192.168.15.190 -port 1099 -agents agent2:RobotAgent
import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import behaviours.CallForPosition;

public class CameraAgent extends Agent{

	protected void setup() { 
		
		System.out.println("Im a camera agent!"); 

		ACLMessage msg = new ACLMessage(ACLMessage.INFORM); 
		
		msg.addReceiver(new AID("agent2", AID.ISLOCALNAME));    // el nombre debe ser el del agente que quiera recibir el mensaje
		msg.setLanguage("English"); 
		msg.setOntology("Weather-forecast-ontology"); 
		msg.setContent("Today its raining");
		send(msg);
    }

} 
