package weather.model;

import javafx.beans.property.*;

/**
 * @author Tian Z
 */
public class Station {
    private StringProperty stationName = new SimpleStringProperty(this, "Station Name", "");
    private IntegerProperty year = new SimpleIntegerProperty(this, "Year", 0);
    private IntegerProperty month = new SimpleIntegerProperty(this, "Month", 0);
    private DoubleProperty maximumTemperature = new SimpleDoubleProperty(this, "Max Temperature", 0);
    private DoubleProperty minimumTemperature = new SimpleDoubleProperty(this, "Min Temperature", 0);
    private IntegerProperty airFrostDayNum = new SimpleIntegerProperty(this, "Air Frost Day", 0);
    private DoubleProperty rainfall = new SimpleDoubleProperty(this, "Rainfall", 0);

    public Station() {
    }

    public Station(String stationName, Integer year, Integer month, Double maximumTemperature, Double minimumTemperature,
                   Integer airFrostDayNum, Double rainfall) {
        this.stationName = new SimpleStringProperty(stationName);
        this.year = new SimpleIntegerProperty(year);
        this.month = new SimpleIntegerProperty(month);
        this.maximumTemperature = new SimpleDoubleProperty(maximumTemperature);
        this.minimumTemperature = new SimpleDoubleProperty(minimumTemperature);
        this.airFrostDayNum = new SimpleIntegerProperty(airFrostDayNum);
        this.rainfall = new SimpleDoubleProperty(rainfall);
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationName=" + stationName +
                ", year=" + year +
                ", month=" + month +
                ", maximumTemperature=" + maximumTemperature +
                ", minimumTemperature=" + minimumTemperature +
                ", airFrostDayNum=" + airFrostDayNum +
                ", rainfall=" + rainfall +
                '}';
    }

    public String getStationName() {
        return stationName.get();
    }

    public StringProperty stationNameProperty() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName.set(stationName);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public int getMonth() {
        return month.get();
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public double getMaximumTemperature() {
        return maximumTemperature.get();
    }

    public DoubleProperty maximumTemperatureProperty() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(double maximumTemperature) {
        this.maximumTemperature.set(maximumTemperature);
    }

    public double getMinimumTemperature() {
        return minimumTemperature.get();
    }

    public DoubleProperty minimumTemperatureProperty() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature.set(minimumTemperature);
    }

    public int getAirFrostDayNum() {
        return airFrostDayNum.get();
    }

    public IntegerProperty airFrostDayNumProperty() {
        return airFrostDayNum;
    }

    public void setAirFrostDayNum(int airFrostDayNum) {
        this.airFrostDayNum.set(airFrostDayNum);
    }

    public double getRainfall() {
        return rainfall.get();
    }

    public DoubleProperty rainfallProperty() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall.set(rainfall);
    }
}
