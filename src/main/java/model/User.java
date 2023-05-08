package model;

import application.Main;

public class User {
	
	private boolean isProjectLeader = false;
	private String initials;
	private String name;
	private boolean isLoggedIn = false;
	private double timeRegistred = 0;
	

	

	public User(boolean isProjectLeader, String initials, String name) {
		this.isProjectLeader = isProjectLeader;
		this.initials = initials;
		this.name = name;
		Main.library.getDevelopers().add(this);
	} 
 


	
	public void registerTime(double time) {
		timeRegistred += time;
	}
	public double getTimeRegistred() {
		return timeRegistred;
	}
	
	 
	public boolean isProjectLeader() {
		return isProjectLeader;
	}

	public void setProjectLeader(boolean isProjectLeader) {
		this.isProjectLeader = isProjectLeader;
	}

	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
						