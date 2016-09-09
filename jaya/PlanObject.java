package jaya;

import java.util.ArrayList;


public class PlanObject{

	private int time;
	private ArrayList<int []> points;
	
	public PlanObject(){}
	
	public PlanObject(ArrayList<int[]> points, int time){
		this.time = time;
		this.points = points;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public void setPoints(ArrayList<int[]> points){
		this.points = points;
	}

	public int getTime(){
		return time;
	}
	
	public ArrayList<int[]> getPoints(){
		return this.points;
	}
        
        
    public static PlanObject getFromString(String protocol){
        
        PlanObject plan = new PlanObject();
        
        ArrayList<int[]> points = new ArrayList<>();
        String objects[] = protocol.split(";");
        
        for (int i = 0; i < objects.length; i++) {
            if(i==0){
                plan.setTime(Integer.parseInt(objects[i]));
            }else{
                String xy[] = objects[i].split(",");
                points.add(new int[]{Integer.parseInt(xy[0]),Integer.parseInt(xy[1])});
            }
        }
        
        plan.setPoints(points);
        
        return plan;
    }

    @Override
    public String toString() {
        
        StringBuilder str =  new StringBuilder();
        
        str.append(time);
        for (int i = 0; i < points.size(); i++) {
            int point[] = points.get(i);           
            str.append(";"+point[0]+","+point[1]);
        }
        
        return str.toString();
    }
        
        
}