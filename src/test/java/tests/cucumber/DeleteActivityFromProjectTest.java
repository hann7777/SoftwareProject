package tests.cucumber;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import model.Activity;
import model.Project;

public class DeleteActivityFromProjectTest {

	  @Test
	  public void testDeleteActivity() {
	    Activity activity1 = new Activity("Report 1", 20.0);
	    Activity activity2 = new Activity("Report 2", 30.0);
	    LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);
        Project project = new Project("Project Management System", startDate, endDate, "Description");
        
        //add the activities to the project
        project.addActivity(activity1);
        project.addActivity(activity2);
	   
	    // Assert that there are two elements in the list
	    assertEquals(2, project.getListOfActivities().size());
	    assertTrue(project.getListOfActivities().contains(activity1));
	    assertTrue(project.getListOfActivities().contains(activity2));

	    // Delete one of the activities
	    project.deleteActivity(activity2);
	    assertEquals(1, project.getListOfActivities().size());
	    assertTrue(project.getListOfActivities().contains(activity1));
	    assertFalse(project.getListOfActivities().contains(activity2));

	    // Delete the last Activity
	    project.deleteActivity(activity1);
	    assertTrue(project.getListOfActivities().isEmpty());
	  
	  }
	}
