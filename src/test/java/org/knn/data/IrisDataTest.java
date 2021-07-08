package org.knn.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IrisDataTest {

	/**
	 * Testataan IrisData-luokan mukaisen olion luonti
	 */
	@Test
	void creatingIrisDataTest() {
		IrisData testData_1 = new IrisData(new double[] {2.0, 3.0}, IrisType.SETOSA, 2);
		IrisData testData_2 = new IrisData(new double[] {1.0, 1.0}, 4);
		
		// double[] toimii
		assertArrayEquals(new double[] {2.0, 3.0}, testData_1.getData());
		assertArrayEquals(new double[] {1.0, 1.0}, testData_2.getData());
		
		// IrisType toimii
		assertEquals(IrisType.SETOSA, testData_1.getIrisType());
		
		// NumOfAttributes toimii
		assertEquals(2, testData_1.getNumberOfAttributes());
		assertEquals(4, testData_2.getNumberOfAttributes());
	}

}
