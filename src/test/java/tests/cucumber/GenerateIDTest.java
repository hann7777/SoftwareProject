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
        Project project = new Project("Project A", startDate, endDate, "Description"); 
        Project.setCounter(7); 
        project.generateID(); 
        int expectedYear = Year.now().getValue() - 2000;
        String expectedID = expectedYear + String.format("%03d", 7); 
        assertEquals(expectedID, project.getId()); 
    }

}