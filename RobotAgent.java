//C:\Users\alejandra\Downloads\JADE-all-4.4.0\JADE-bin-4.4.0\jade>javac    ----   este es el directoria donde tienes jadex!!

//java -cp jade.jar;. jade.Boot -agents agent2:RobotAgent -gui ----
 //javac -cp jade.jar;. RobotAgent.java
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.util.Iterator;
import behaviours.CallForPosition;

//javac -cp jade.jar behaviours/CallForPosition.java
//java -cp jade.jar:. jade.Boot -agents Luis:HelloWorAgent -gui
//java -cp jade.jar:. jade.Boot -agents "Luis2:HelloWldAgent(1 k arg3)"

public class RobotAgent extends Agent {

	private CallForPosition moveBehaviour;

	protected void setup() { 
		System.out.println("Im a robot agent!"); 
		addBehaviour(new CallForPosition());
    }

}

