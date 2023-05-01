package model;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class Project {
	private String name;
	private String id;
	private LocalDate startDate;
	private LocalDate endDate;
	private ArrayList<Activity> listOfActivities;
	private ArrayList<User> listOfDevelopers;
	private String description;
	private int counter = 000;
	private String projectLeader;


	public Project(String name, LocalDate startDate, LocalDate endDate, String description) {
		super();
		
		for(User user : Library.developers) {
			if(user.isLoggedIn()) {
				if(user.isProjectLeader()) {
					projectLeader = user.getName();
				}
			}
		}
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		listOfDevelopers = new ArrayList<>();
		listOfActivities = new ArrayList<>();
		counter++;
		generateID();
		Library.projects.add(this);

	}

	public void generateID() {
		int year = Year.now().getValue() - 2000;
		id = year + String.format("%03d", counter);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addDeveloper(User user) {
		listOfDevelopers.add(user);
	}

	public void removeDeveloper(User user) {
		listOfDevelopers.remove(user);
	}
	
	public void addActivity(Activity activity) {
		listOfActivities.add(activity);
	}

	public void removoeActivity(Activity activity) {
		listOfActivities.remove(activity);
	}
	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}
	public ArrayList<Activity> getListOfActivities() {
		return listOfActivities;
	}

	public void setListOfActivities(ArrayList<Activity> listOfActivities) {
		this.listOfActivities = listOfActivities;
	}

	public ArrayList<User> getListOfDevelopers() {
		return listOfDevelopers;
	}

	public void setListOfDevelopers(ArrayList<User> listOfDevelopers) {
		this.listOfDevelopers = listOfDevelopers;
	}

}
