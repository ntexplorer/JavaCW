package weather.model;

import javafx.beans.property.*;

/**
 * The type Processed station.
 *
 * @author Tian Z
 */
public class ProcessedStation {
    private StringProperty stationName = new SimpleStringProperty(this, "Station Name", "");
    private IntegerProperty year = new SimpleIntegerProperty(this, "Year", 0);
    private final IntegerProperty monthCount = new SimpleIntegerProperty(this, "Count of Valid Month", 0);
    private final IntegerProperty maxTempMonth = new SimpleIntegerProperty(this, "Maximum Temperature Month", 1);
    private final IntegerProperty minTempMonth = new SimpleIntegerProperty(this, "Minimum Temperature Month", 1);
    private final DoubleProperty maximumTemperature = new SimpleDoubleProperty(this, "Maximum Temperature", 0);
    private final DoubleProperty minimumTemperature = new SimpleDoubleProperty(this, "Minimum Temperature", 0);
    private final IntegerProperty airFrostDaySum = new SimpleIntegerProperty(this, "Air Frost Day Sum", 0);
    private final DoubleProperty rainfallSum = new SimpleDoubleProperty(this, "Rainfall Sum", 0);

    /**
     * Instantiates a new Processed station.
     */
    public ProcessedStation() {
    }

    /**
     * Instantiates a new Processed station.
     *
     * @param stationName the station name
     * @param year        the year
     */
    public ProcessedStation(String stationName, Integer year) {
        this.stationName = new SimpleStringProperty(stationName);
        this.year = new SimpleIntegerProperty(year);
    }

    @Override
    public String toString() {
        return "ProcessedStation{" +
                "stationName=" + this.getStationName() +
                ", year=" + this.getYear() +
                ", maximumTemperature=" + this.getMaximumTemperature() +
                ", minimumTemperature=" + this.getMinimumTemperature() +
                ", airFrostDaySum=" + this.getAirFrostDaySum() +
                ", rainfallSum=" + this.getRainfallSum() +
                '}';
    }

    /**
     * Gets month count.
     *
     * @return the month count
     */
    public int getMonthCount() {
        return monthCount.get();
    }

    /**
     * Month count property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty monthCountProperty() {
        return monthCount;
    }

    /**
     * Sets month count.
     *
     * @param monthCount the month count
     */
    public void setMonthCount(int monthCount) {
        this.monthCount.set(monthCount);
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
     * Gets max temp month.
     *
     * @return the max temp month
     */
    public int getMaxTempMonth() {
        return maxTempMonth.get();
    }

    /**
     * Max temp month property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty maxTempMonthProperty() {
        return maxTempMonth;
    }

    /**
     * Sets max temp month.
     *
     * @param maxTempMonth the max temp month
     */
    public void setMaxTempMonth(int maxTempMonth) {
        this.maxTempMonth.set(maxTempMonth);
    }

    /**
     * Gets min temp month.
     *
     * @return the min temp month
     */
    public int getMinTempMonth() {
        return minTempMonth.get();
    }

    /**
     * Min temp month property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty minTempMonthProperty() {
        return minTempMonth;
    }

    /**
     * Sets min temp month.
     *
     * @param minTempMonth the min temp month
     */
    public void setMinTempMonth(int minTempMonth) {
        this.minTempMonth.set(minTempMonth);
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
     * Gets air frost day sum.
     *
     * @return the air frost day sum
     */
    public int getAirFrostDaySum() {
        return airFrostDaySum.get();
    }

    /**
     * Air frost day sum property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty airFrostDaySumProperty() {
        return airFrostDaySum;
    }

    /**
     * Sets air frost day sum.
     *
     * @param airFrostDaySum the air frost day sum
     */
    public void setAirFrostDaySum(int airFrostDaySum) {
        this.airFrostDaySum.set(airFrostDaySum);
    }

    /**
     * Gets rainfall sum.
     *
     * @return the rainfall sum
     */
    public double getRainfallSum() {
        return rainfallSum.get();
    }

    /**
     * Rainfall sum property double property.
     *
     * @return the double property
     */
    public DoubleProperty rainfallSumProperty() {
        return rainfallSum;
    }

    /**
     * Sets rainfall sum.
     *
     * @param rainfallSum the rainfall sum
     */
    public void setRainfallSum(double rainfallSum) {
        double rainfall = (double) Math.round(rainfallSum * 100) / 100;
        this.rainfallSum.set(rainfall);
    }
}
