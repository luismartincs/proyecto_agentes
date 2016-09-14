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
}