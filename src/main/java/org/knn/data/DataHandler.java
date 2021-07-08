package org.knn.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.knn.App;

/**
 * Käsittelee opetus datan lataamisesta, sen opettamiseen ja lisämiseen.
 * @author JUKU
 *
 */
public class DataHandler {

    private final String trainingDataFileName = "org/knn/data/iris.data";
    private ArrayList<IrisData> sepalData;
    private ArrayList<IrisData> petalData;
    
    public DataHandler() {
    	this.sepalData = new ArrayList<>();
    	this.petalData = new ArrayList<>();
    }
    
    public ArrayList<IrisData> getSepalData(){ return this.sepalData; }
   
    public ArrayList<IrisData> getPetalData(){ return this.petalData; }

    /**
     * Lataa tekstitiedostoon kirjotetun datan ArrayList<IrisData> mutoon. (ns. opetetaan konetta annenetulla datalla)
     */
    public void learnIrisData() {
    	try {
    		ArrayList<ArrayList<IrisData>> trainingData = DataLoader.loadIrisDataFromFile(trainingDataFileName);
			this.sepalData = trainingData.get(0);
			this.petalData = trainingData.get(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("data loaded!");
    }
    
    /**
     * Otetaan double[4] suurunen data, joka luotitellaan erikseen sepal ja petal dataksi.
     * @param data
     * @return
     */
    public IrisData[] addIrisData(final double[] data) {
    	System.out.println("--------------------");
    	
    	IrisSepalData tempIrisSepalData = new IrisSepalData(new double[] {data[0], data[1]});
    	IrisPetalData tempIrisPetalData = new IrisPetalData(new double[] {data[2], data[3]});
    	
    	tempIrisSepalData.setIrisType(getIrisType(tempIrisSepalData));
    	tempIrisPetalData.setIrisType(getIrisType(tempIrisPetalData));
    	this.sepalData.add(tempIrisSepalData);
    	this.petalData.add(tempIrisPetalData);
    	
    	return new IrisData[] {tempIrisSepalData, tempIrisPetalData};
    }
    
    /**
     * Palauttaa IrisType:n opetus datan ja K arvon pohjalla.
     * @param data, jolle tarvii ennustaa IrisType-tyyppi
     * @return IrisType, joka ennustettu datalle
     */
    IrisType getIrisType(IrisData data) {
    	ArrayList<Double> distances = new ArrayList<>();
    	HashMap<Double, IrisData> hMap = new HashMap<>();

    	String type = "";
    	if(data instanceof IrisSepalData) {
    		distances = calcDistanceToEverPoint(data, this.sepalData);
    		hMap = connectListsToMap(distances, this.sepalData);
    		type = "sepal";
    	}
    	
    	if(data instanceof IrisPetalData) {
    		distances = calcDistanceToEverPoint(data, this.petalData);
    		hMap = connectListsToMap(distances, this.petalData);
    		type = "petal";
    	}
    	Collections.sort(distances);
    	
    	int virginicaCount = 0;
    	int setosaCount = 0;
    	int versicolorCount = 0;
    	
    	for(int i = 0; i < App.K; i++) {
    		if(distances.size() == i)
    			break;
    		
    		System.out.println(type+" "+distances.get(i)+" - "+hMap.get(distances.get(i)).toString());
    		switch(hMap.get(distances.get(i)).getIrisType()) {	
				case SETOSA:
				case SETOSA_NEW:
					setosaCount++;
					continue;
				case VERSICOLOR:
				case VERSICOLOR_NEW:
					versicolorCount++;
					continue;
				case VIRGINICA:
				case VIRGINICA_NEW:
					virginicaCount++;
					continue;
    		}
    	}
    	if( (setosaCount == virginicaCount && setosaCount == versicolorCount) || 
			(setosaCount == virginicaCount && setosaCount > versicolorCount) ||
			(setosaCount == versicolorCount && setosaCount > virginicaCount) ||
			(virginicaCount == versicolorCount && virginicaCount > setosaCount) ||
			(virginicaCount == setosaCount && virginicaCount > versicolorCount)||
			(versicolorCount == setosaCount && setosaCount > virginicaCount) ||
			(versicolorCount == virginicaCount && setosaCount > setosaCount)) {
    		return IrisType.toNew(hMap.get(distances.get(0)).getIrisType());
    	}
    	
    	int max = Math.max(setosaCount, virginicaCount);
    		max = Math.max(max, versicolorCount);
    	
    	if(setosaCount == max) {
    		return IrisType.SETOSA_NEW;
    	}else if(versicolorCount == max) {
    		return IrisType.VERSICOLOR_NEW;
    	}else {
    		return IrisType.VIRGINICA_NEW;
    	}
    }
    
    /**
     * Laskee ja palauttaa matkan jokaisen ArrayListissä olevan datan "välimatkan" annettuun pisteseen..
     * @param data
     * @param toData
     * @return
     */
    private ArrayList<Double> calcDistanceToEverPoint(IrisData data, ArrayList<IrisData> toData) {
    	ArrayList<Double> distanceList = new ArrayList<>();
    	
    	for(IrisData i : toData)
    		distanceList.add(DistanceCalculator.countEucDistance(data, i));
    	
    	return distanceList;
    }
    
    /**
     * Luo ja palauttaa HashMapin käyttäen lasketun matkan dataa ja IrisDatasta. 
     * @param distance
     * @param iris
     * @return
     */
    private HashMap<Double, IrisData> connectListsToMap(ArrayList<Double> distance, ArrayList<IrisData> iris){
    	HashMap<Double, IrisData> hMap = new HashMap<>();
    	for(int i = 0; i < iris.size(); i++) {
    		hMap.put(distance.get(i), iris.get(i));
    	}
		return hMap;
    }
    
}
