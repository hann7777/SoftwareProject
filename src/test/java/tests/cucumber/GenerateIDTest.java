package tests.cucumber;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Year;

import org.junit.Test;

import model.Project;

public class GenerateIDTest {

    @Test
    public void testGenerateID() {
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 5, 31);
        Project project = new Project("Project A", startDate, endDate, "Description"); // create an instance of the                                                                                    // class being tested
        Project.setCounter(42); // set the value of counter to a known value
        project.generateID(); // call the method being tested
        int expectedYear = Year.now().getValue() - 2000; // calculate the expected value for year
        String expectedID = expectedYear + String.format("%03d", 42); // calculate the expected ID
        assertEquals(expectedID, project.getId()); // check that the generated ID matches the expected value
    }

}