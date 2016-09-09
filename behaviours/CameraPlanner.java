package behaviours;

import java.util.HashMap;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import jaya.Jaya;
import jaya.PlanObject;


class Point{
	private int x,y;

	Point(int x, int y){
		this.x = x;
		this.y = y;

	}

	int getX(){
		return x;
	}

	int getY(){
		return y;
	}
}

public class CameraPlanner extends CyclicBehaviour{

	private static final int GOAL_ID = 4;

	private String filePath = "/home/luismartin/Documentos/Maestria/Agentes/vision/proyectos/camera_agent/log/agents.txt";

	private int step = 0;
	private String name;
	private String nameTag;
	private Jaya algoritmo;
	private PlanObject plan;
	private HashMap<String, PlanObject> allPlans;

	public void onStart(){
		algoritmo = new Jaya();
		name = myAgent.getLocalName();
		nameTag = "("+name+")";
		allPlans = new HashMap<String, PlanObject>();
		System.out.println(nameTag+" Camera Planner started");
	}


	private Point[] readAgentData(int robotId){
        
        try{

        	InputStream fis = new FileInputStream(filePath);
	        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	        BufferedReader br= new BufferedReader(isr);

	        Point points[] = new Point[2];

 			String line;
		 	while ((line = br.readLine()) != null) {
	             String data[] = line.split(",");
	             
	             int currentId = Integer.parseInt(data[0]);

	             if(currentId == robotId){
		            
		            System.out.println("id:"+data[0]);
		            System.out.println("x:"+data[1]);
		            System.out.println("y:"+data[2]);
	             	
	             	points[1] = new Point(Integer.parseInt(data[1]),Integer.parseInt(data[2]));

	             }else if( currentId == GOAL_ID){

	             	System.out.println("Goal:"+data[0]);
		            System.out.println("x:"+data[1]);
		            System.out.println("y:"+data[2]);

		            points[0] = new Point(Integer.parseInt(data[1]),Integer.parseInt(data[2]));
	             }

         	}

         	br.close();

         	return points;

    	}catch(Exception ex){
	    	ex.printStackTrace();
	    	return null;
    	}
	}

	public void action() {
	  	
	  	ACLMessage msg=myAgent.receive();

	  	if(msg !=null){
			
			int robotId = Integer.parseInt(msg.getSender().getLocalName().replace("robot",""));

			// Si solicitan la posicion inicial
			if (msg.getPerformative() == ACLMessage.CFP){

				System.out.println(nameTag+": Solicitud del Robot "+robotId);

				Point []points = readAgentData(robotId);

				plan = algoritmo.optimizar(new int[]{5,20}, new int[]{3,20});

				allPlans.put("robot"+robotId,plan);

				int time = plan.getTime();

				System.out.println(nameTag+": Tiempo a proponer al robot "+robotId+":"+plan.getTime());
				System.out.println(nameTag+": Protocol string al robot "+robotId+":"+plan);

				propose(msg,time);

			}else if(msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){

				System.out.println(nameTag+" Aceptaron propuesta");
				inform(msg);

			}else if(msg.getPerformative() == ACLMessage.REQUEST){

				if(msg.getContent().equals("next")){
					
					//FALTA RECALCULAR LOS PUNTOS

					sendNext(msg);

				}

			}

		}else {
			block();
		}
	}


	//==================== Acciones individuales ====================


	private void propose(ACLMessage msg,int time){
		
		ACLMessage proposal=msg.createReply();
		proposal.setPerformative(ACLMessage.PROPOSE);
					
		proposal.setContent(time+"");
		proposal.setConversationId("Plan-trade");
		myAgent.send(proposal);
	
	}

	private void inform(ACLMessage contract){

		PlanObject planToSend = allPlans.get(contract.getSender().getLocalName());

		System.out.println(nameTag+" Me contrataron");
		System.out.println(nameTag+" El plan para "+contract.getSender().getLocalName()+" es "+planToSend);

		//CALCULAR EL SIGUIENTE PUNTO

		ACLMessage data = new ACLMessage(ACLMessage.INFORM);

		data.addReceiver(contract.getSender());
		data.setContent(planToSend.toString());
		data.setConversationId("Plan-trade");
		myAgent.send(data);
	}


	private void sendNext(ACLMessage contract){

		//CALCULAR EL SIGUIENTE PUNTO

		ACLMessage plan = new ACLMessage(ACLMessage.INFORM);

		plan.addReceiver(contract.getSender());
		plan.setContent("4,5");
		plan.setConversationId("Plan-trade");
		myAgent.send(plan);
	}

}
