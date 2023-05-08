package tests.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;

import model.Activity;

public class UpdateRemainingTimeTest {
	private Activity activity = new Activity("act", 20);

	private double estimatedTime = activity.getEstimatedTime();
	
	@Test
	public void testUpdateRemainingTime() {
		double input = 10.0; 
		activity.updateRemainingTime(input);
		assertEquals(estimatedTime - input, activity.getRemainingTime());
	}

}
