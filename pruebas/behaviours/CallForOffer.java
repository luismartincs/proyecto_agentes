//javac -cp jade.jar behaviours\CallForOffer.java
package behaviours;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;

public class CallForOffer extends CyclicBehaviour {
	//private MessageTemplate mt=MessageTemplate.MatchConversationId("Plan-trade");
	private int step=0;
	public int t=40;
	public void action (){
		switch (step){
		case 0:
		// Para cuando la cámara reciba un petición de plan 
			ACLMessage msg=myAgent.receive();
			if(msg !=null){
				
				System.out.println("Got a message ");
				System.out.println(msg.getContent());
				if (msg.getPerformative()==ACLMessage.CFP){
					//creando la respuesta al call for proposal del robot1
					ACLMessage proposal=msg.createReply();
					proposal.setPerformative(ACLMessage.PROPOSE);
					
					proposal.setContent(t+"");
					proposal.setConversationId("Plan-trade");
					myAgent.send(proposal);
				}
				step=1;
				break;
			}
			else {
				block();
			}
		case 1:
		// Si la camara es contratada por el agente
			ACLMessage contract=myAgent.receive();
			if (contract !=null){
				if (contract.getPerformative()==ACLMessage.ACCEPT_PROPOSAL){
					System.out.println("Me contrataron");
					ACLMessage plan=new ACLMessage(ACLMessage.INFORM);
					plan.addReceiver(contract.getSender());
					plan.setContent("El siguiente punto es X Y");
					plan.setConversationId("Plan-trade");
					myAgent.send(plan);
					
				}
				step=4;
				break;
			}
			else{
				block();
			}
		}	
	}
	//public boolean done(){
	//	return step ==4;
	//}
}