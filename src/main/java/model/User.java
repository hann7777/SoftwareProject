package model;

public class User {
	
	private boolean isProjectLeader = false;
	private String initials;
	private String name;
	


	public User(boolean isProjectLeader, String initials, String name) {
		this.isProjectLeader = isProjectLeader;
		this.initials = initials;
		this.name = name;
		Library.developers.add(this);
	}

	public void createProject() {
		
	}
	public void markAnActivityAsCompleted() {
		
	}
	
	public void registerTime() {
		
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

}
						