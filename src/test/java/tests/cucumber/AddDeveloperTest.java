package tests.cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import model.Activity;
import model.User;

public class AddDeveloperTest {
	@Test
	public void testAddDeveloper() {
		Activity activity = new Activity("Test", 10.0);

		User user1 = new User(false, "han", "Hassen Ali Nasrallah");

		//assert that the list of developers on the project is empty
		assertTrue(activity.getListOfDevelopers().isEmpty());

		// Add a developer to the project, and assert that the size is 1
		activity.addDeveloper(user1);
		assertEquals(1, activity.getListOfDevelopers().size());
		assertTrue(activity.getListOfDevelopers().contains(user1));

		// Add another developer to the project, and assert that the size is 2
		User user2 = new User(true, "aamo", "Abas Ali Mohamed Osman");
		activity.addDeveloper(user2);
		assertEquals(2, activity.getListOfDevelopers().size());
		assertTrue(activity.getListOfDevelopers().contains(user2));
	}
}
