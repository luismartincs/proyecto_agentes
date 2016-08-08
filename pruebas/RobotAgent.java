// Programa de creación del agente robot 

//javac -cp jade.jar;. RobotAgent.java  -- compilar


//java -cp jade.jar;. jade.Boot -agents robot1:RobotAgent -gui


import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;
import behaviours.PlanNegotiator;

public class RobotAgent extends Agent{
	private PlanNegotiator moveBehaviour;
	protected void setup () {
		System.out.println("I am an Agent "+getAID().getLocalName() );
		
		//Empezando comportamientos
		addBehaviour(new PlanNegotiator ());
		//addBehaviour(new MessageReceived ());
		
		
	}
}