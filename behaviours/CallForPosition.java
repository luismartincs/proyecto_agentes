package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import serial.RobotInterface;
import jade.core.AID;

public class CallForPosition extends CyclicBehaviour{

	private int repliesCnt=0;
	private int besttimeplan=10000;
	private AID bestCamera;
	private String name;
	private String nameTag;
	private RobotInterface serialComm;
	private static boolean DEBUG = true;


	public void onStart(){
		
		name = myAgent.getLocalName();
		nameTag = "("+name+")";

		if(!DEBUG){
			System.out.println("Opening the port");
			serialComm = new RobotInterface();
		}
	}

	public void action() {
	  	
	  	ACLMessage msg=myAgent.receive();

	  	if(msg !=null){
			
			
			// Si solicitan la posicion inicial
			if (msg.getPerformative() == ACLMessage.REQUEST){

				if(msg.getContent().equals("start")){
					start();
				}

			}else if(msg.getPerformative() == ACLMessage.PROPOSE){

				receiveProposal(msg);
				
			}else if(msg.getPerformative() == ACLMessage.INFORM){
				
				//move(msg);

			}

		}else {
			block();
		}

	}

	//==================== Acciones individuales ====================

	private void start(){
		System.out.println(nameTag + " Iniciando agente");
		
		ACLMessage cfp=new ACLMessage(ACLMessage.CFP);
		cfp.addReceiver(new AID("camera1",AID.ISLOCALNAME));
		cfp.addReceiver(new AID("camera2",AID.ISLOCALNAME));
		cfp.setContent("get-plan");
		cfp.setConversationId("Plan-trade");
		myAgent.send(cfp);

	}

	private void receiveProposal(ACLMessage msg){
		
		System.out.println(nameTag + " Reply num: "+repliesCnt);
		System.out.println(nameTag + " Proposal: "+msg.getContent());

		int timeplan=Integer.parseInt(msg.getContent());
				
		if  (timeplan < besttimeplan){
			besttimeplan=timeplan;
			bestCamera=msg.getSender();
		}
				
		System.out.println(nameTag + " El nuevo mejor tiempo es "+besttimeplan);

		repliesCnt++;

		if(repliesCnt >= 2){
			accept(msg);
		}

	}

	private void accept(ACLMessage msg){

		if (bestCamera !=null){
			ACLMessage order = new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
			order.addReceiver(bestCamera);
			order.setContent("You won");
			order.setConversationId("Plan-trade");
			myAgent.send(order);
		}	

	}

	private void move(ACLMessage msg){

		String data[] = msg.getContent().split(",");

		System.out.println(nameTag + " Move to "+data[0]+","+data[1]);

		//INTEGRAR CON ARDUINO
		
		next();

	}


	private void next(){

		if (bestCamera !=null){
			ACLMessage order = new ACLMessage(ACLMessage.REQUEST);
			order.addReceiver(bestCamera);
			order.setContent("next");
			order.setConversationId("Plan-trade");
			myAgent.send(order);
		}	

	}

}
