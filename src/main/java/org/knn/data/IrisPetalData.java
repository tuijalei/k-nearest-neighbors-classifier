package org.knn.data;

/**
 * IrisData-luokan aliluokka, joka sisältää vain petal attribuutit
 */
public class IrisPetalData extends IrisData {
	
	public IrisPetalData(double[] data) {
		super(data, 2);
	}
	
	public IrisPetalData(double[] data, IrisType irisType) {
		super(data, irisType, 2);
	}

}
