package model;

import java.util.ArrayList;

public class Activity {
	private String name;
	private double estimatedTime;
	private boolean isCompleted;
	public static ArrayList<User> listOfDevelopers;
	
	
	
	public Activity(String name, double estimatedTime) {
		super();
		this.name = name;
		this.estimatedTime = estimatedTime;
		listOfDevelopers = new ArrayList<User>();
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getEstimatedTime() {
		return estimatedTime;
	}
	public void setEstimatedTime(double estimatedTime) {
		this.estimatedTime = estimatedTime;
	}
	public boolean isCompleted() {
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	

}
