package tests.cucumber;

import static org.junit.Assert.assertFalse;
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

public class TimeRegisteredSteps {

	private User user;
	private Library library = new Library();
	private Project project;
	private Activity activity;
	private boolean isclicked;

	@Given("that this project already exist in the library:")
	public void thatThisProjectAlreadyExistInTheLibrary(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> projectsData = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> projectData : projectsData) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate sDate = LocalDate.parse(projectData.get("sDate"), formatter);
			LocalDate eDate = LocalDate.parse(projectData.get("eDate"), formatter);
			String name = projectData.get("name");
			String description = projectData.get("Description");

			Project project = new Project(name, sDate, eDate, description);
			this.project = project;
			library.getProjects().add(project);
		}
	}

	@Given("that one developer is registered on the project:")
	public void thatOneDeveloperIsRegisteredOnTheProject(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> activityData = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> activityMap : activityData) {
			String name = activityMap.get("name");
			double estimatedTime = Double.parseDouble(activityMap.get("estimatedTime"));

			Activity activity = new Activity(name, estimatedTime);
			this.activity = activity;
			// Assuming you have a current project instance
			project.getListOfActivities().add(activity);
		}
	}

	@Given("the initials {string} is registred with the name {string} and is a developer")
	public void theInitialsIsRegistredWithTheNameAndIsADeveloper(String string, String string2) {
		User user = new User(false, string, string2);

		this.user = user;

		assertFalse(user.isProjectLeader());

	}

	@When("{string} clicks add time")
	public void clicksAddTime(String string) {
		try {
			if (library.getProjects() != null) {
				isclicked = true;
			}
		} catch (Exception e) {
		}

		assertTrue(isclicked);
	}

	@Then("he registers {double} hours to the activity")
	public void heRegistersHoursToTheActivity(Double double1) {
		activity.updateRemainingTime(double1);
		assertEquals(0.5, activity.getRemainingTime());
	}

	@Then("the remainingtime updates to {double}")
	public void theEstimatedTimeUpdatesTo(Double double1) {
		assertEquals(double1, activity.getRemainingTime());
	}

	@Then("he registers more time than estimated {double} hours to the activity")
	public void heRegistersMoreTimeThanEstimatedHoursToTheActivity(Double double1) {
		boolean isPossible = false;
		if (double1 > activity.getRemainingTime()) {
			isPossible = false;
		} else {
			isPossible = true;
		}

		assertFalse(isPossible);
	} 

	@Then("the remainingtime stays at {double};")
	public void theRemainingtimeStaysAt(Double double1) {
		assertEquals(double1, activity.getRemainingTime());
	}
	
	
}
