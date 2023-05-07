package tests.cucumber;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.When;
import io.cucumber.java.en.Given;
import model.Project;
import model.User;
import model.Library;

public class DeleteProject {
	private User user;
	private Library library = new Library();
	private Project project;
	private Boolean isProjectLeader;
	
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
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	 
	
}
