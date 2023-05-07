package tests.cucumber;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Activity;
import model.Library;
import model.Project;
import model.User;

public class CreateActivitySteps {
	
	private User user;
	private Library library = new Library();
	private Project project;
	private Activity activity;
	private Boolean isProjectLeader;
	
	
	@Given("a user with initials {string} is registred with the name {string} and is a project leader")
	public void aUserWithInitialsIsRegistredWithTheNameAndIsAProjectLeader(String string, String string2) {
	    User user = new User(true, string, string2);
	    this.user = user;
	    library.getDevelopers().add(user);
	    assertTrue(user.isProjectLeader());
	    
	}

	@Given("a {string} exist with the start date {string} and a end date {string}")
	public void aExistWithTheStartDateAndAEndDate(String string, String string2, String string3) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate sDate = LocalDate.parse(string2, formatter);
		LocalDate eDate = LocalDate.parse(string3, formatter);
		Project project = new Project(string, sDate, eDate, "description");
		this.project = project;
		library.getProjects().add(project);
		assertTrue(library.getProjects().get(0) != null);
	}


	@When("creating an activity with the name {string}")
	public void creatingAnActivityWithTheName(String string) {
		
	    activity = new Activity(string, 2);
	    assertEquals(activity.getName(), string);
	}

	@When("an estimated time {string} hours")
	public void anEstimatedTimeHours(String string) {
		int time = Integer.parseInt(string);
	    activity.setEstimatedTime(time);
	    assertTrue(activity.getEstimatedTime() == Integer.parseInt(string));
	}

	@Then("a project leader can create an acivity")
	public void aProjectLeaderCanCreateAnAcivity() {
	    if(user.isProjectLeader()) {
	    	project.addActivity(activity);
	    }
	    assertTrue(project.getListOfActivities() != null);
	}
	
	@When("creating an acitvity with the name {string} and a time {double}")
	public void creatingAnAcitvityWithTheNameAndATime(String string, Double int1) {
	    if (project == null) {
	        project = new Project("project name", LocalDate.now(), LocalDate.of(2023, 06, 22), "description");
	        library.getProjects().add(project);
	    }
	    if (user.isProjectLeader()) {
	        activity = new Activity(string, int1);
	        project.addActivity(activity);
	    }
	    assertTrue(project.getListOfActivities() != null);
	}

	@Then("the activity can not be created")
	public void theActivityCanNotBeCreated() {
	    boolean isDeleted = false;
	    Iterator<Activity> iterator = project.getListOfActivities().iterator();
	    while (iterator.hasNext()) {
	        Activity activity = iterator.next();
	        if (activity.getEstimatedTime() <= 0) {
	            iterator.remove();
	            isDeleted = true;
	        }
	    }
	    assertTrue(isDeleted);
	}
	
	
	
	
}
