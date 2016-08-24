package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class CameraPlanner extends CyclicBehaviour{

	private String filePath = "/home/luismartin/Documentos/Maestria/Agentes/vision/proyectos/camera_agent/log/agents.txt";

	private int step = 0;
	private int time = 40;
	private String name;
	private String nameTag;

	public void onStart(){
		name = myAgent.getLocalName();
		nameTag = "("+name+")";
		System.out.println(nameTag+" Camera Planner started");
	}


	private void readAgentsData(){
        
        try{

        	InputStream fis = new FileInputStream(filePath);
	        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	        BufferedReader br= new BufferedReader(isr);

 			String line;
		 	while ((line = br.readLine()) != null) {
	             String data[] = line.split(",");
	             
	             System.out.println("id:"+data[0]);
	             System.out.println("x:"+data[1]);
	             System.out.println("y:"+data[2]);
         	}

         	br.close();

    	}catch(Exception ex){
	    	ex.printStackTrace();
    	}
	}

	public void action() {
	  	
	  	ACLMessage msg=myAgent.receive();

	  	if(msg !=null){
			
			
			// Si solicitan la posicion inicial
			if (msg.getPerformative() == ACLMessage.CFP){

				System.out.println(nameTag+" Haciendo propuesta");
				propose(msg);

			}else if(msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){

				System.out.println(nameTag+" Aceptando propuesta");
				inform(msg);

			}

		}else {
			block();
		}
	}


	//==================== Acciones individuales ====================


	private void propose(ACLMessage msg){
		
		ACLMessage proposal=msg.createReply();
		proposal.setPerformative(ACLMessage.PROPOSE);
					
		proposal.setContent(time+"");
		proposal.setConversationId("Plan-trade");
		myAgent.send(proposal);
	
	}

	private void inform(ACLMessage contract){

		System.out.println(nameTag+" Me contrataron");
		ACLMessage plan = new ACLMessage(ACLMessage.INFORM);
		plan.addReceiver(contract.getSender());
		plan.setContent("El siguiente punto es X Y");
		plan.setConversationId("Plan-trade");
		myAgent.send(plan);
	}


}
