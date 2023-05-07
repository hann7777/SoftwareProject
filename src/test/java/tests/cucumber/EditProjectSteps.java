package tests.cucumber;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Activity;
import model.Library;
import model.Project;
import model.User;

public class EditProjectSteps {
	
	private User user;
	private Library library = new Library();
	private Project project;
	private Boolean isProjectLeader;
	private boolean isclicked;
	private boolean isclickedbyNoAdmin;
	
	@Given("that one project already exist in the library:")
	public void thatOneProjectAlreadyExistInTheLibrary(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> projectsData = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> projectData : projectsData) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate sDate = LocalDate.parse(projectData.get("sDate"), formatter);
			LocalDate eDate = LocalDate.parse(projectData.get("eDate"), formatter);
			String name = projectData.get("name");
			String description = projectData.get("Description");

			Project project = new Project(name, sDate, eDate, description);
			this.project=project;
			library.getProjects().add(project);
		}
	}

	@When("{string} clicks edit Project")
	public void clicksEditProject(String string) {
		try {
			if(library.getProjects() != null) {
				isclicked = true;
				for(Activity activity:project.getListOfActivities()) {
					if(activity.getName().equals("first")) {
						project.deleteActivity(activity);
					}
				}
				
			}
			if(library.getProjects() != null) {
				isclickedbyNoAdmin  = true;
			}
		} catch (Exception e) {
		}
		
		assertTrue(isclicked);
	}

	@Then("he edits the name of the Activity from {string} to {string}")
	public void heEditsTheNameOfTheActivityFromTo(String string, String string2) {
	    project.setName(string2);
		assertEquals("newfirst",string2);
	}
	
	@Then("he edits the name of the descrtiption from {string} to {string}")
	public void heEditsTheNameOfTheDescrtiptionFromTo(String string, String string2) {
		project.setDescription(string2);
		assertEquals("new first description",string2);
	}
	
	@Then("he edits the start date from {string} to {string}")
	public void heEditsTheStartDateFromTo(String string, String string2) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate sDate = LocalDate.parse(string2, formatter);
	    project.setStartDate(sDate);
	    assertEquals(sDate,project.getStartDate());
	}
	
	@Then("he edits the end date from {string} to {string}")
	public void heEditsTheEndDateFromTo(String string, String string2) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate eDate = LocalDate.parse(string2, formatter);
	    project.setEndDate(eDate);
	    assertEquals(eDate,project.getEndDate());
	}

}
