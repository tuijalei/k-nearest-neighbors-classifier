package org.knn.data;

public class DistanceCalculator {
	
	/**
	 * Laskee kahden eri IrisData-tyypin olion välisen euklidisen pituuden
	 * @param a - IrisData-tyypin olio
	 * @param b - IrisData-tyypin olio
	 * @return double, etäisyys a-b välillä
	 */
	public static double countEucDistance(IrisData a, IrisData b) {
		double[] dataA = a.getData();
		double[] dataB = b.getData();
		
		double sum = 0;
		
		for(int i = 0; i < dataA.length; i++) {
			sum += Math.pow((dataA[i]-dataB[i]), 2);
		}
		
		return Math.sqrt(sum);
	}
}
