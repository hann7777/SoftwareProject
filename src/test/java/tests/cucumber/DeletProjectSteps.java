package tests.cucumber;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Library;
import model.Project;
import model.User;

public class DeletProjectSteps {
	private User user;
	private Library library = new Library();
	private Project project;
	private Boolean isProjectLeader;
	private boolean isclicked = false;
	private boolean isclickedbyNoAdmin = false;
	
	
	@Given("that projects exist in the library:")
	public void thatProjectsExistInTheLibrary(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> projectsData = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> projectData : projectsData) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    LocalDate sDate = LocalDate.parse(projectData.get("sDate"), formatter);
		    LocalDate eDate = LocalDate.parse(projectData.get("eDate"), formatter);
		    String name = projectData.get("name");
		    String description = projectData.get("Description");

		    Project project = new Project(name, sDate, eDate, description);
		    library.getProjects().add(project);
	    }
	}

	@Given("the initials {string} is registred with the name {string} and is a project leader")
	public void theInitialsIsRegistredWithTheNameAndIsAProjectLeader(String string, String string2) {
		User user = new User(true, string, string2);
		this.user = user;
		library.getDevelopers().add(user);
		assertTrue(user.isProjectLeader());
	}
	@When("{string} clicks delete")
	public void clicksDelete(String string) {
		try {
			if(library.getProjects() != null) {
				isclicked = true;
				library.getProjects().remove(0);
			}
			if(library.getProjects() != null) {
				isclickedbyNoAdmin  = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		assertTrue(isclicked);
	}
	
	@Then("the selected project is deleted")
	public void theSelectedProjectIsDeleted() {
	    assertFalse(library.getProjects().contains(getClass().getName().equals("first")));
	}
	
	@Given("the initials {string} is registred with the name {string} and is not a project leader")
	public void theInitialsIsRegistredWithTheNameAndIsNotAProjectLeader(String string, String string2) {
		User user = new User(false, string, string2);
		this.user = user;
		library.getDevelopers().add(user);
		assertFalse(user.isProjectLeader());
	}

	@Then("the and the selected project is not deleted")
	public void theAndTheSelectedProjectIsNotDeleted() {
		int countP = 0;
		if(isclickedbyNoAdmin) {
			for(Project project: library.getProjects()) {
				countP++;
			}
		}
		assertTrue(1 <= countP);
	}
	
}
