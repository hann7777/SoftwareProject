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
		this.remainingTime -= remainingTime;
	}

	public boolean isCompleted() {
		registeredHours.forEach((key, value) -> {
			double sum = 0;
			sum += value;
			if (sum == estimatedTime) {
				setCompleted(true);
			} else {
				setCompleted(false);
			}

		});
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

	public Map<String, Double> getRegisteredHours() {
		return registeredHours;
	}

	public void setRegisteredHours(String initials, double time) {
		this.registeredHours.put(initials, time);
	}

}
