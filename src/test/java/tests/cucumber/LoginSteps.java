package tests.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import application.viewSwitcher;
import application.viewSwitcher.View;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Library;
import model.Project;
import model.User;

public class LoginSteps {
	private User user;
	private Library library;
	private Project project;
	private Boolean isProjectLeader;

	@Given("that some people are registered to the system:")
	public void thatSomePeopleAreRegisteredToTheSystem(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> developersData = dataTable.asMaps(String.class, String.class);
		library = new Library();
		for (Map<String, String> developerData : developersData) {
			boolean isProjectLeader = Boolean.parseBoolean(developerData.get("isP"));
			String name = developerData.get("name");
			String initials = developerData.get("initials");

			library.getDevelopers().add(new User(isProjectLeader, initials, name));

		}
	}

	@Given("the initials {string} is registred with the name {string}")
	public void theInitialsIsRegistred(String string, String string2) {
		for (User user : library.getDevelopers()) {
			if (user.getInitials().equals(string)) {
				assertEquals("abas", user.getInitials());
			}

		}

	}

	@When("{string} clicks login after typing in his initials")
	public void clicksLoginAfterTypingInHisInitials(String string) {
		for (User user : library.getDevelopers()) {
			if (user.getInitials().equals(string)) {
				user.setLoggedIn(true);
				assertEquals(true, user.isLoggedIn());
			}
			if (!user.getInitials().equals(string)) {
				user.setLoggedIn(false);
				assertEquals(false, user.isLoggedIn());
			}
		}

	}

	@Then("the startpage is displayed")
	public void theStartpageIsDisplayed() {
		for (User user : library.getDevelopers()) {
			if (user.isLoggedIn()) {
				viewSwitcher.switchTo(View.STARTPAGE);
			}
		}

	}

	@Given("{string} is not registred")
	public void isNotRegistred(String string) {
		boolean isNotRegistrered = false;
		for (User user : library.getDevelopers()) {
			if (user.getInitials().contains(string)) {
				isNotRegistrered = true;
				break;
			}

		}
		assertFalse(isNotRegistrered);

	}

	@Then("the startpage is not displayed")
	public void theStartpageIsNotDisplayed() {
		viewSwitcher.switchTo(View.LOGIN);
	}
}
