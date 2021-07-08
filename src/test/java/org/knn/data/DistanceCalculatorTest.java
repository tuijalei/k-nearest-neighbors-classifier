package org.knn.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DistanceCalculatorTest {

	@Test @DisplayName("sqrt((1-4)^2+(1-3)^2)=sqrt(13)")
	void countEucDistanceTest() {
			double[] dataA = new double[] {1,1};
			double[] dataB = new double[] {4,3};
			
			double result = DistanceCalculator.countEucDistance(new IrisSepalData(dataA, IrisType.SETOSA), new IrisSepalData(dataB, IrisType.SETOSA));
			assertEquals(Math.sqrt(13), result, "tulisi olla sqrt((1-4)^2+(1-3)^2)=sqrt(13)");
	}

}
