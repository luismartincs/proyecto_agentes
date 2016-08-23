//javac -cp jade.jar behaviours\PlanNegotiator.java


package behaviours;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;


public class PlanNegotiator extends Behaviour{
	//public MessageTemplate mt=MessageTemplate.MatchConversationID("Plan-trade");
	public int repliesCnt=0;
	public int besttimeplan=100;
	private int step=0;
	private AID bestCamera;
	public void action(){
			switch (step){
			case 0:
				// Pedir el plan a todas las camaras 
				ACLMessage cfp=new ACLMessage(ACLMessage.CFP);
				cfp.addReceiver(new AID("camera1",AID.ISLOCALNAME));
				cfp.setContent("Give me a plan");
				cfp.setConversationId("Plan-trade");
				myAgent.send(cfp);
				step=1;
				
				break;
			case 1:
				//Recibir las respuestas de las camaras
				ACLMessage reply=myAgent.receive();
				if (reply !=null){
					if (reply.getPerformative()==ACLMessage.PROPOSE){
						int timeplan=Integer.parseInt(reply.getContent());
						if  (timeplan<besttimeplan){
							besttimeplan=timeplan;
							bestCamera=reply.getSender();
						}
						System.out.println("El nuevo mejor tiempo es "+besttimeplan);
					}
					repliesCnt++;
					//if (repliesCnt>=cameraAgents.length){
					//	step=2;
					//}
					step=2;
				}
				else{
					block();
				}
				break;
			case 2:
				// responder a la mejor camara
				if (bestCamera !=null){
					ACLMessage order=new ACLMessage(ACLMessage.ACCEPT_PROPOSAL);
					order.addReceiver(bestCamera);
					order.setContent("You won");
					order.setConversationId("Plan-trade");
					myAgent.send(order);
					step=3;
					break;
				}
			
			}
	}
	public boolean done(){
		return step==3;
	}
}