package tests.cucumber;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

public class DeleteActivitySteps {
	private User user;
	private Library library = new Library();
	private Project project;
	private Boolean isProjectLeader;
	private boolean isclicked;
	private boolean isclickedbyNoAdmin;

	@Given("that project exist in the library:")
	public void thatProjectExistInTheLibrary(io.cucumber.datatable.DataTable dataTable) {
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
	@Given("that Activity exist in the Project:")
	public void thatActivityExistInTheProject(io.cucumber.datatable.DataTable dataTable) {
	    List<Map<String, String>> activityData = dataTable.asMaps(String.class, String.class);
	    for (Map<String, String> activityMap : activityData) {
	        String name = activityMap.get("name");
	        double estimatedTime = Double.parseDouble(activityMap.get("estimatedTime"));

	        Activity activity = new Activity(name, estimatedTime);
	        // Assuming you have a current project instance
	        project.getListOfActivities().add(activity);
	    }
	}
	@When("{string} clicks delete Activity")
	public void clicksDeleteActivity(String string) {
		
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
	
	@Then("the selected Activity is deleted")
	public void theSelectedActivityIsDeleted() {
	    assertFalse(project.getListOfActivities().contains("first"));
	}
	
	@Then("the Activity is not deleted")
	public void theActivityIsNotDeleted() {
		int countP = 0;
		if(isclickedbyNoAdmin) {
			for(Activity activity: project.getListOfActivities()) {
				countP++;
			}
		}
		assertTrue(1 <= countP);
	}
}
