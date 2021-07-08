package org.knn.data;

import javafx.scene.paint.Color;

public enum IrisType {
    SETOSA(Color.web("#fab246")),
    SETOSA_NEW(Color.web("#CC6600")),
    VERSICOLOR(Color.web("#acd13a")),
    VERSICOLOR_NEW(Color.web("#00CC66")),
    VIRGINICA(Color.web("#9e79e1")),
    VIRGINICA_NEW(Color.web("#6600CC"));

	private Color circleColor;
	
	public Color getCircleColor() { return this.circleColor; }
	
	IrisType(Color circleColor) {
		this.circleColor = circleColor;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static IrisType toNew(IrisType type) {
        switch(type){
        case SETOSA:
            return SETOSA_NEW;
        case VERSICOLOR:
            return VERSICOLOR_NEW;
        case VIRGINICA:
            return VIRGINICA_NEW;
        }
        return null;    
    }

	/**
	 * Palauttaa IrisType arvon annetun String perusteella.
	 * Käytetään esim. opetusdatan lataamisessa.
	 * @param str
	 * @return 
	 */
	public static IrisType stringToIrishClass(String str){
        String temp = str.split("-")[1].trim().toLowerCase();

        if(temp.length() < 5)
            return null;

        switch(temp){
            case "setosa":
                return SETOSA;
            case "versicolor":
                return VERSICOLOR;
            case "virginica":
                return VIRGINICA;
        }
        return null;
    }
}
