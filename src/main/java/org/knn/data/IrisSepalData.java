package org.knn.data;

/**
 * IrisData-luokan aliluokka, joka sisältää vain sepal attribuutit
 */
public class IrisSepalData extends IrisData {

	public IrisSepalData(double[] data) {
		super(data, 2);
	}
	
	public IrisSepalData(double[] data, IrisType irisType) {
		super(data, irisType, 2);
	}
}
