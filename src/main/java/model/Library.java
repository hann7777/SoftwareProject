//Hassen
package model;

import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<User> developers;
	private List<Project> projects;
	public Library() {
		developers = new ArrayList<>();
		projects = new ArrayList<>();
	}
	public List<User> getDevelopers() {
		return developers;
	}
	public void setDevelopers(List<User> developers) {
		this.developers = developers;
	}
	public List<Project> getProjects() {
		return projects;
	}
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	
}
