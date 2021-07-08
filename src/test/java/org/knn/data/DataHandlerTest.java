package org.knn.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataHandlerTest {

	@Test
	void testLoadData() {
		DataHandler dh = new DataHandler();
		dh.learnIrisData();
		assertEquals(150, dh.getPetalData().size(), dh.getPetalData().size()+"/150");
	}
	
	@Test
	void testAddIrisData() {
		DataHandler dh = new DataHandler();
		dh.learnIrisData();
		double[] data = new double[] {3.2, 1.2, 4.3, 3.3};
		dh.addIrisData(data);
		System.out.println(dh.getPetalData());
		assertEquals(data , data);
	}

	@Test
	void testGetIrisType() {
		DataHandler dh = new DataHandler();
		dh.learnIrisData();
		double[] data = new double[] {3.2, 1.2, 4.3, 3.3};
		IrisData pedalData = new IrisPetalData(new double[] {4.3, 3.3});
		assertEquals(IrisType.VIRGINICA_NEW, dh.getIrisType(pedalData));
	}
}
