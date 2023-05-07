package tests.cucumber;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import model.Library;
import model.Project;
import model.User;

public class CreateProjectSteps {
	private User user;
	private Library library = new Library();
	private Project project;
	private Boolean isProjectLeader;

//	public CreateProjectSteps(User user, Library library, Project project) {
//		this.user = user;
//		this.library = library;
//		this.project = project;
//	}

	@Given("there is a user with the initials {string}, and the name {string} who is a project leader")
	public void thereIsAUserWithTheInitialsAndTheNameWhoIsAProjectLeader(String string, String string2) {
		User user = new User(true, string, string2);
		this.user = user;
		library.getDevelopers().add(user);
		assertTrue(user.isProjectLeader());

	}

	@Given("the project leader creates a project with title {string}, with the description {string}, a start date {string}, and an end date {string}")
	public void theProjectLeaderCreatesAProjectWithTitleWithTheDescriptionAStartDateAndAnEndDate(String string,
			String string2, String string3, String string4) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate sDate = LocalDate.parse(string3, formatter);
		LocalDate eDate = LocalDate.parse(string4, formatter);
		project = new Project(string, sDate, eDate, string2);
		library.getProjects().add(project);
		this.project = project;

	}

	@Then("the project with name {string} is created, and has project ID {string}")
	public void theProjectWithNameIsCreatedAndHasProjectID(String string, String string2) {
		assertNotNull(project);
		assertEquals(string, project.getName());
		assertEquals(string2, project.getId());
	}

	@Given("the project leader creates a project with title {string}, with the description {string}, a start date {string} before the date it is created, and an end date {string}")
	public void theProjectLeaderCreatesAProjectWithTitleWithTheDescriptionAStartDateBeforeTheDateItIsCreatedAndAnEndDate(String string, String string2, String string3, String string4) {
		boolean dateIsValid=false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate sDate = LocalDate.parse(string3, formatter);
		LocalDate eDate = LocalDate.parse(string4, formatter);
		
		if(!sDate.isBefore(LocalDate.now())) {
			dateIsValid=true;
			library.getProjects().add(new Project(string, sDate, eDate, string2));
			
		}
	    assertFalse(dateIsValid);
	}

	@Then("the project with name {string} is not created.")
	public void theProjectWithNameIsNotCreated(String string) {
		boolean projectIsCreated=false;
	    for(Project project:library.getProjects()) {
	    	if(project.getName().contains(string)) {
	    		 projectIsCreated=true;	
	    		
	    	}
	    }
	    assertFalse(projectIsCreated);
	}
	@Given("the project leader creates a project with title {string}, with the description {string}, a start date {string}, and an end date {string} before a start date.")
	public void theProjectLeaderCreatesAProjectWithTitleWithTheDescriptionAStartDateAndAnEndDateBeforeAStartDate(String string, String string2, String string3, String string4) {
		boolean dateIsValid=false;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDate sDate = LocalDate.parse(string3, formatter);
		LocalDate eDate = LocalDate.parse(string4, formatter);
		
		if(!(eDate.compareTo(sDate) < 0)) {
			dateIsValid=true;
			library.getProjects().add(new Project(string, sDate, eDate, string2));
			
		}
	    assertFalse(dateIsValid);
	}
	@Given("the project leader creates a project with no title {string}, with the description {string}, a start date {string}, and an end date {string}.")
	public void theProjectLeaderCreatesAProjectWithNoTitleWithTheDescriptionAStartDateAndAnEndDate(String string, String string2, String string3, String string4) {
		boolean nameIsEmpty=false;
		if(string.isEmpty()) {
			nameIsEmpty=true;
			
		}
		assertTrue(nameIsEmpty);
	}
	
	@Given("the project leader creates a project with title {string}, with no description {string}, a start date {string}, and an end date {string}.")
	public void theProjectLeaderCreatesAProjectWithTitleWithNoDescriptionAStartDateAndAnEndDate(String string, String string2, String string3, String string4) {
		boolean descriptionIsEmpty=false;
		if(string2.isEmpty()) {
			descriptionIsEmpty=true;
			
		}
		assertTrue(descriptionIsEmpty);
	}


}
