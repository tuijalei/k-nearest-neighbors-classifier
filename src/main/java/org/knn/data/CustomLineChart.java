package org.knn.data;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
/**
 * Luodaan diagrammit Sepal chart ja Petal chart LineChart-luokan aliluokkana
 *
 */
public class CustomLineChart extends LineChart<Number, Number> {
	
	private final int circleRadius = 4;
	private final ArrayList<IrisData> irisData;
	private NumberAxis yAxis, xAxis;
    private List<Shape> shapes = new ArrayList<>();
    
	public CustomLineChart(NumberAxis yAxis, NumberAxis xAxis, ArrayList<IrisData> irisData){
		super(yAxis, xAxis);
		this.irisData = irisData;
		this.yAxis = yAxis;
		this.xAxis = xAxis;
		
		this.yAxis.setLabel("Length (cm)");
		this.xAxis.setLabel("Width (cm)");
		setAxisRange();
	}

	public NumberAxis getYaxis() { return this.yAxis;}
	public NumberAxis getXaxis() { return this.xAxis;}
	
	/**
	 * Asetetaan xy-axiksien max ja min arvot
	 * setTickUnit(0.25) && autoRanging=false
	 */
	public void setAxisRange(){
		double yMin = (getYMinLength()-1) < 0 ? 0 : getYMinLength()-0.1;
		double yMax = (getYMaxLength()+1) > 10 ? 10 : getYMaxLength()+0.1;
		double xMin = (getXMinWidth()-1) < 0 ? 0 : getXMinWidth()-0.1;
		double xMax = (getXMaxWidth()+1) > 10 ? 10 : getXMaxWidth()+0.1;
		
		this.yAxis.setAutoRanging(false);
		this.yAxis.setLowerBound(yMin);
		this.yAxis.setUpperBound(yMax);
		this.yAxis.setTickUnit(0.25);
		
		this.xAxis.setAutoRanging(false);
		this.xAxis.setLowerBound(xMin);
		this.xAxis.setUpperBound(xMax);
		this.xAxis.setTickUnit(0.25);
	}
	
	/**
	 * Luodaan ArrayLististä stream, josta max arvo luetaan
	 * @return max arvo
	 */
	private double getYMaxLength() {
		return this.irisData.stream().mapToDouble(data -> data.getData()[0]).max().getAsDouble();
	}
	
	/**
	 * Luodaan ArrayLististä stream, josta min arvo luetaan
	 * @return min arvo
	 */
	private double getYMinLength() {
		return this.irisData.stream().mapToDouble(data -> data.getData()[0]).min().getAsDouble();
	}
	
	/**
	 * Luodaan ArrayLististä stream, josta max arvo luetaan
	 * @return max arvo
	 */
	private double getXMaxWidth() {
		return this.irisData.stream().mapToDouble(data -> data.getData()[1]).max().getAsDouble();
	}
	
	/**
	 * Luodaan ArrayLististä stream, josta min arvo luetaan
	 * @return min arvo
	 */	
	private double getXMinWidth() {
		return this.irisData.stream().mapToDouble(data -> data.getData()[1]).min().getAsDouble();
	}
	
	/**
	 * Petal ja sepalien piirtäminen chartteihin piirteiden length ja width mukaan
	 */
	@Override
	protected void layoutPlotChildren() {
		super.layoutPlotChildren();
		super.getPlotChildren().removeAll(shapes);
        shapes.clear();

        for (IrisData data : this.irisData) {
        	double x = getXAxis().getDisplayPosition(data.getData()[0]);
	        double y = getYAxis().getDisplayPosition(data.getData()[1]);
	        Circle circle = new Circle(x , y, circleRadius, data.getIrisType().getCircleColor());
            shapes.add(circle);
        }
        super.getPlotChildren().addAll(shapes);
	}
	
	/**
	 * Lisätään uusi IrisData-tyypin olio CustomLineChartiin
	 * @param data - IrisData, joka halutaan lisätä
	 */
	public void addDataPoint(IrisData data) {
    	double x = getXAxis().getDisplayPosition(data.getData()[0]);
        double y = getYAxis().getDisplayPosition(data.getData()[1]);
        //System.out.println("(x,y) - "+x+", "+y);
        Circle circle = new Circle(x , y, circleRadius, data.getIrisType().getCircleColor());
        shapes.add(circle);		
        super.getPlotChildren().add(circle);
        setAxisRange();
	}
}
