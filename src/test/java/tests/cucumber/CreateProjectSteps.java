package tests.cucumber;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
	private Library library;
	private Project project;
	private Boolean isProjectLeader;
	public CreateProjectSteps(User user, Library library, Project project) {
		this.user = user;
		this.library = library;
		this.project = project;
	}

	@Given("there is a user with the initials {string}, and the name {string} who is a project leader")
	public void thereIsAUserWithTheInitialsAndTheNameWhoIsAProjectLeader(String string, String string2) {
		isProjectLeader = true;
	    user = new User(isProjectLeader, string, string2);
	    assertTrue(isProjectLeader);
	}

	@Given("the project leader creates a project with title {string}, with the description {string}, a start date {string}, and an end date {string}")
	public void theProjectLeaderCreatesAProjectWithTitleWithTheDescriptionAStartDateAndAnEndDate(String string,
			String string2, String string3, String string4) {
		
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate sdate = LocalDate.parse(string3, formatter);
        LocalDate edate = LocalDate.parse(string4, formatter);
		 project = new Project(string, sdate, edate, string2);
	}

	@Then("the project with name {string} is created, and has project ID {string}")
	public void theProjectWithNameIsCreatedAndHasProjectID(String string, String string2) {
	    assertNotNull(project);
	    assertEquals(string, project.getName());
	    assertEquals(string2, project.getId());
	}
//
//	@Given("the project leader creates a project with title {string}, with the description {string}, a start date \"\"{double}.{int}\"\", and an end date \"\"{double}.{int}\"\".")
//	public void theProjectLeaderCreatesAProjectWithTitleWithTheDescriptionAStartDateAndAnEndDate(String string,
//			String string2, Double double1, Integer int1, Double double2, Integer int2) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("the project with name {string} is not created.")
//	public void theProjectWithNameIsNotCreated(String string) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@Then("errormessage {string};")
//	public void errormessage(String string) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("there is a user with the initials {string}, and the name {string} who is a project leader true")
//	public void thereIsAUserWithTheInitialsAndTheNameWhoIsAProjectLeaderTrue(String string, String string2) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("the project leader creates a project with no title {string}, with the description {string}, a start date \"\"{double}.{int}\"\", and an end date \"\"{double}.{int}\"\".")
//	public void theProjectLeaderCreatesAProjectWithNoTitleWithTheDescriptionAStartDateAndAnEndDate(String string,
//			String string2, Double double1, Integer int1, Double double2, Integer int2) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}
//
//	@Given("the project leader creates a project with title {string}, with no description {string}, a start date \"\"{double}.{int}\"\", and an end date \"\"{double}.{int}\"\".")
//	public void theProjectLeaderCreatesAProjectWithTitleWithNoDescriptionAStartDateAndAnEndDate(String string,
//			String string2, Double double1, Integer int1, Double double2, Integer int2) {
//		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
//	}

}
