package weather.model;

import javafx.beans.property.*;

/**
 * The type Station.
 *
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

    /**
     * Instantiates a new Station.
     */
    public Station() {
    }

    /**
     * Instantiates a new Station.
     * Original data class
     *
     * @param stationName        the station name
     * @param year               the year
     * @param month              the month
     * @param maximumTemperature the maximum temperature
     * @param minimumTemperature the minimum temperature
     * @param airFrostDayNum     the air frost day number
     * @param rainfall           the rainfall
     */
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

    /**
     * Gets station name.
     *
     * @return the station name
     */
    public String getStationName() {
        return stationName.get();
    }

    /**
     * Station name property string property.
     *
     * @return the string property
     */
    public StringProperty stationNameProperty() {
        return stationName;
    }

    /**
     * Sets station name.
     *
     * @param stationName the station name
     */
    public void setStationName(String stationName) {
        this.stationName.set(stationName);
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public int getYear() {
        return year.get();
    }

    /**
     * Year property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty yearProperty() {
        return year;
    }

    /**
     * Sets year.
     *
     * @param year the year
     */
    public void setYear(int year) {
        this.year.set(year);
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public int getMonth() {
        return month.get();
    }

    /**
     * Month property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty monthProperty() {
        return month;
    }

    /**
     * Sets month.
     *
     * @param month the month
     */
    public void setMonth(int month) {
        this.month.set(month);
    }

    /**
     * Gets maximum temperature.
     *
     * @return the maximum temperature
     */
    public double getMaximumTemperature() {
        return maximumTemperature.get();
    }

    /**
     * Maximum temperature property double property.
     *
     * @return the double property
     */
    public DoubleProperty maximumTemperatureProperty() {
        return maximumTemperature;
    }

    /**
     * Sets maximum temperature.
     *
     * @param maximumTemperature the maximum temperature
     */
    public void setMaximumTemperature(double maximumTemperature) {
        this.maximumTemperature.set(maximumTemperature);
    }

    /**
     * Gets minimum temperature.
     *
     * @return the minimum temperature
     */
    public double getMinimumTemperature() {
        return minimumTemperature.get();
    }

    /**
     * Minimum temperature property double property.
     *
     * @return the double property
     */
    public DoubleProperty minimumTemperatureProperty() {
        return minimumTemperature;
    }

    /**
     * Sets minimum temperature.
     *
     * @param minimumTemperature the minimum temperature
     */
    public void setMinimumTemperature(double minimumTemperature) {
        this.minimumTemperature.set(minimumTemperature);
    }

    /**
     * Gets air frost day num.
     *
     * @return the air frost day num
     */
    public int getAirFrostDayNum() {
        return airFrostDayNum.get();
    }

    /**
     * Air frost day num property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty airFrostDayNumProperty() {
        return airFrostDayNum;
    }

    /**
     * Sets air frost day num.
     *
     * @param airFrostDayNum the air frost day num
     */
    public void setAirFrostDayNum(int airFrostDayNum) {
        this.airFrostDayNum.set(airFrostDayNum);
    }

    /**
     * Gets rainfall.
     *
     * @return the rainfall
     */
    public double getRainfall() {
        return rainfall.get();
    }

    /**
     * Rainfall property double property.
     *
     * @return the double property
     */
    public DoubleProperty rainfallProperty() {
        return rainfall;
    }

    /**
     * Sets rainfall.
     *
     * @param rainfall the rainfall
     */
    public void setRainfall(double rainfall) {
        this.rainfall.set(rainfall);
    }
}
