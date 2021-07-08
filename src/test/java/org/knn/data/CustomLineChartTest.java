package org.knn.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;

class CustomLineChartTest {

	/**
	 * JUnit testi jostain syystä ei hyväksy NumberAxista.
	 */
	@Test
	void testSetAxisRangeLowerBoundMax() {
		ArrayList<IrisData> data = new ArrayList<>();
		data.add(new IrisPetalData(new double[] {3.2, 5.4}));
		data.add(new IrisPetalData(new double[] {1.3, 3}));
		data.add(new IrisPetalData(new double[] {0.4, 9.7}));
		data.add(new IrisPetalData(new double[] {4.3, 8.3}));

	    //Axis<Number> yAxis = new NumberAxis();
		//NumberAxis na = new NumberAxis();
		
		CustomLineChart clc = new CustomLineChart(new NumberAxis(), new NumberAxis(), data);
		double result = clc.getXaxis().getLowerBound();
		assertEquals(9.7, result);
	}

}

