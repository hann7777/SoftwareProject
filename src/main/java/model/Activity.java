package model;

import java.util.ArrayList;

public class Activity {
	private String name;
	private double estimatedTime;
	private boolean isCompleted;
	private ArrayList<User> listOfDevelopers= new ArrayList<User>();
 
	public Activity(String name, double estimatedTime) {
		super();
		this.name = name;
		this.estimatedTime = estimatedTime;

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

	public ArrayList<User> getListOfDevelopers() {
		return listOfDevelopers;
	}

	public void setListOfDevelopers(ArrayList<User> listOfDevelopers) {
		this.listOfDevelopers = listOfDevelopers;
	}
	
	public void addDeveloper(User user) {
		listOfDevelopers.add(user);
	}

	public void removeDeveloper(User user) {
		listOfDevelopers.remove(user);
	}

}
