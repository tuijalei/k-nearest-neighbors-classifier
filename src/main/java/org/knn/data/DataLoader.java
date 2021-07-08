package org.knn.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * DataLoaderi lataamaan lataa tiedostoista
 */
public class DataLoader {

	public static int dataSetSize;
	
    /**
     * Muunnetaan iris.datan rivi IrisDataksi
     * @param line - luettu rivi iris.data filesta
     */
    private static IrisData[] convertLineToIrishData(String line){
        String[] splittedData = line.split(",", 5);
        double[] data = new double[]{Double.parseDouble(splittedData[0]),
                                                Double.parseDouble(splittedData[1]),
                                                Double.parseDouble(splittedData[2]),
                                                Double.parseDouble(splittedData[3])};

        IrisType irisType = IrisType.stringToIrishClass(splittedData[4]);
        IrisSepalData sepalData = new IrisSepalData(new double[] {data[0], data[1]}, irisType);
        IrisPetalData petalData = new IrisPetalData(new double[] {data[2], data[3]}, irisType);
        
        return new IrisData[] {sepalData, petalData};
    }

    /**
     * Ladataan Resourcesta file ja muunnetaan ArrayList<IrisData>
     * @param fileName tiedoston nimi, joka halutaan ladata
     * @return ArrayList, joka sisältää IrisData-tyypin olioita
     * @throws IOException
     */
    public static ArrayList<ArrayList<IrisData>> loadIrisDataFromFile(String fileName) throws IOException {
	//public static ArrayList<IrisData> loadIrisDataFromFile(String fileName) throws IOException {
    	InputStream inputStream = DataLoader.class.getClassLoader().getResourceAsStream(fileName);
        ArrayList<IrisData> sepalData = new ArrayList<>();
        ArrayList<IrisData> petalData = new ArrayList<>();
        try(BufferedReader br = new BufferedReader((new InputStreamReader(inputStream)))) {
            String line;
            while((line = br.readLine()) != null){
            	IrisData[] data = convertLineToIrishData(line);
            	sepalData.add((IrisSepalData) data[0]);
                petalData.add((IrisPetalData) data[1]);
                dataSetSize++;
            }
        }
        return new ArrayList<ArrayList<IrisData>>(Arrays.asList(sepalData, petalData));
    }
    
}
