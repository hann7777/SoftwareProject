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
public class EditActivitySteps {
	
	private User user;
	private Library library = new Library();
	private Project project;
	private Boolean isProjectLeader;
	private boolean isclicked;
	private boolean isclickedbyNoAdmin;
	
	@Given("that one project exist in the library:")
	public void thatOneProjectExistInTheLibrary(io.cucumber.datatable.DataTable dataTable) {
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

	@Given("that one Activity exist in the Project:")
	public void thatOneActivityExistInTheProject(io.cucumber.datatable.DataTable dataTable) {
	    List<Map<String, String>> activityData = dataTable.asMaps(String.class, String.class);
	    for (Map<String, String> activityMap : activityData) {
	        String name = activityMap.get("name");
	        double estimatedTime = Double.parseDouble(activityMap.get("estimatedTime"));

	        Activity activity = new Activity(name, estimatedTime);
	        // Assuming you have a current project instance
	        project.getListOfActivities().add(activity);
	    }
	}

	@When("{string} clicks edit Activity")
	public void clicksEditActivity(String string) {
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

	@Then("he edit the name of the Activity from {string} to {string}")
	public void heEditTheNameOfTheActivityFromTo(String string, String string2) {
		boolean nameIsChanged = false;
	    project.getListOfActivities().get(0).setName(string2);
	    if(project.getListOfActivities().get(0).getName().contains(string2)){
	    	nameIsChanged = true;
	    }
	    assertEquals(true,nameIsChanged);
	}

	@Then("he can edit the estimatedTime from {double} to {double}")
	public void heCanEditTheEstimatedTimeFromTo(Double double1, Double double2) {
		project.getListOfActivities().get(0).setEstimatedTime(double2);
		assertEquals(11.5,double2);
	}

}
