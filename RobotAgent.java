
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import java.util.Iterator;
import behaviours.CallForPosition;

//javac -cp jade.jar behaviours/MoveRobotBehaviour.java
//java -cp jade.jar:. jade.Boot -agents Luis:HelloWorAgent -gui
//java -cp jade.jar:. jade.Boot -agents "Luis2:HelloWldAgent(1 k arg3)"

public class RobotAgent extends Agent {

	private CallForPosition moveBehaviour;

	protected void setup() { 
		System.out.println("I’m a robot agent!"); 
		addBehaviour(new CallForPosition());
    }

}

