package tests.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import application.viewSwitcher;
import application.viewSwitcher.View;
import model.Developers;
import model.User;

public class LoginSteps {
	
	@Given("{string} is registred")
	public void isRegistred(String string) {
		for(User user : Developers.developers) {
			assertEquals(user.getInitials(),string);
		}

	}

	@When("{string} clicks login after typing in his initials")
	public void clicksLoginAfterTypingInHisInitials(String string) {

	}

	@Then("the startpage is displayed")
	public void theStartpageIsDisplayed() {
	    viewSwitcher.switchTo(View.STARTPAGE);
	}

	@Given("{string} is not registred")
	public void isNotRegistred(String string) {
		for(User user : Developers.developers) {
			assertFalse(user.getInitials()!=string);
		}
	}

	@Then("the startpage is not displayed")
	public void theStartpageIsNotDisplayed() {
	    viewSwitcher.switchTo(View.LOGIN);

	
	}
}
