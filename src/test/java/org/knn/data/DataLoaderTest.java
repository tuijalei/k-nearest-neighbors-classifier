package org.knn.data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * Datan tiedostosta lautauksen testaus
 */
class DataLoaderTest {

	 @Test
	 void testLoadFile() throws IOException{
		 ArrayList<IrisData> expectedList = new ArrayList();
		 ArrayList<IrisData> testList = DataLoader.loadIrisDataFromFile("org/knn/data/test.data").get(0);
		 expectedList.add(new IrisData(new double[] {1.0, 1.0}, IrisType.SETOSA, 2));
	     assertEquals(expectedList.get(0).toString(), testList.get(0).toString());
	    }

}
