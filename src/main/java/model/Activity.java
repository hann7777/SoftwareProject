//Abas
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Activity {
	private String name;
	private double estimatedTime;
	private boolean isCompleted;
	private double remainingTime;
	private ArrayList<User> listOfDevelopers = new ArrayList<User>();
	private Map<String, Double> registeredHours;

	public Activity(String name, double estimatedTime) {
		super();

		this.name = name;
		this.estimatedTime = estimatedTime;
		remainingTime = estimatedTime;
		registeredHours = new HashMap<>();
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

	public double getRemainingTime() {
		return remainingTime;
	}

	public void updateRemainingTime(double remainingTime) {
		assert remainingTime != 0;
		assert remainingTime > this.estimatedTime;

		if (remainingTime > estimatedTime) { // 1
			return; // 2
		} else {// 3
			this.remainingTime -= remainingTime;// 4
		}

		assert this.remainingTime == this.remainingTime - remainingTime;

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
		assert user != null;
		assert this.listOfDevelopers.contains(user) == false;
		
		this.listOfDevelopers.add(user); // 1
		
		assert this.listOfDevelopers.contains(user);

	}

	public void removeDeveloper(User user) {
		listOfDevelopers.remove(user);
	}

	public Map<String, Double> getRegisteredHours() {
		return registeredHours;
	}

	public void setRegisteredHours(String initials, double time) {
		this.registeredHours.put(initials, time);
	}

}
