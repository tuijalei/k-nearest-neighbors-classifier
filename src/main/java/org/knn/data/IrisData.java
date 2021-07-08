package org.knn.data;

/**
 * IrisData-luokka, jonka avulla voidaan luoda valmis Iris-olio
 * Yksilöityy double[]:n sisältämien sepal ja petal pituuksien ja leveyksien
 * sekä lajin (enum IrisType) perusteella
 */
public class IrisData {

    private double[] data;
    private IrisType irisType;
    private final int numberOfAttributes; //4
    private final String dataSeparator = ",";

    public IrisData(final double[] data, final int numberOfAttributes){
        this.data = data;
        this.irisType = null;
        this.numberOfAttributes = numberOfAttributes;
    }
    
    public IrisData(double[] data, final IrisType irisClass, final int numberOfAttributes){
        this.data = data;
        this.irisType = irisClass;
        this.numberOfAttributes = numberOfAttributes;
    }

    public double[] getData(){ return this.data; }
    
    public IrisType getIrisType(){ return this.irisType; }
    public void setIrisType(IrisType irisType) { this.irisType = irisType; }
    public int getNumberOfAttributes(){ return this.numberOfAttributes; }
    public String getDataSeparator(){ return this.dataSeparator; }
    
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0 ; i < this.data.length; i++)
    		sb.append(this.data[i]+", ");
    	sb.append(irisType);
    	return sb.toString();
    }
}
