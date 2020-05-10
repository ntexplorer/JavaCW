package weather.model;

import javafx.beans.property.*;

/**
 * The type Station overview.
 *
 * @author Tian Z
 */
public class StationOverview {
    private StringProperty stationName = new SimpleStringProperty(this, "Station Name", "");
    private final IntegerProperty highestYear = new SimpleIntegerProperty(this, "Highest Temperature Year", 0);
    private final IntegerProperty highestMonth = new SimpleIntegerProperty(this, "Highest Temperature Month", 0);
    private final IntegerProperty lowestYear = new SimpleIntegerProperty(this, "Lowest Temperature Year", 0);
    private final IntegerProperty lowestMonth = new SimpleIntegerProperty(this, "Lowest Temperature Month", 0);
    private final DoubleProperty highestTemperature = new SimpleDoubleProperty(this, "Highest Temperature Overall", 0);
    private final DoubleProperty lowestTemperature = new SimpleDoubleProperty(this, "Lowest Temperature Overall", 0);
    private final IntegerProperty averageAirFrostDay = new SimpleIntegerProperty(this, "Average Annual Air Frost Day", 0);
    private final DoubleProperty averageAnnualRainfall = new SimpleDoubleProperty(this, "Average Annual Rainfall", 0);

    /**
     * Instantiates a new Station overview.
     */
    public StationOverview() {
    }

    /**
     * Instantiates a new Station overview.
     *
     * @param stationName the station name
     */
    public StationOverview(String stationName) {
        this.stationName = new SimpleStringProperty(stationName);
    }

    /**
     * @return rewrite toString method for report generating
     */
    @Override
    public String toString() {
        return "Station: " + this.getStationName() + "\nHighest Temperature: " + this.getHighestTemperature()
                + " degrees on " + this.getHighestMonth() + "/" + this.getHighestYear() + "\nLowest Temperature: "
                + this.getLowestTemperature() + " degrees on " + this.getLowestMonth() + "/" + this.getLowestYear() +
                "\nAverage annual air frost days: " + this.getAverageAirFrostDay() + " days" + "\nAverage annual rainfall: "
                + this.getAverageAnnualRainfall() + " mm\n";
    }

    /**
     * Gets highest month.
     *
     * @return the highest month
     */
    public int getHighestMonth() {
        return highestMonth.get();
    }

    /**
     * Highest month property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty highestMonthProperty() {
        return highestMonth;
    }

    /**
     * Sets highest month.
     *
     * @param highestMonth the highest month
     */
    public void setHighestMonth(int highestMonth) {
        this.highestMonth.set(highestMonth);
    }

    /**
     * Gets lowest month.
     *
     * @return the lowest month
     */
    public int getLowestMonth() {
        return lowestMonth.get();
    }

    /**
     * Lowest month property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty lowestMonthProperty() {
        return lowestMonth;
    }

    /**
     * Sets lowest month.
     *
     * @param lowestMonth the lowest month
     */
    public void setLowestMonth(int lowestMonth) {
        this.lowestMonth.set(lowestMonth);
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
     * Gets highest year.
     *
     * @return the highest year
     */
    public int getHighestYear() {
        return highestYear.get();
    }

    /**
     * Highest year property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty highestYearProperty() {
        return highestYear;
    }

    /**
     * Sets highest year.
     *
     * @param highestYear the highest year
     */
    public void setHighestYear(int highestYear) {
        this.highestYear.set(highestYear);
    }

    /**
     * Gets lowest year.
     *
     * @return the lowest year
     */
    public int getLowestYear() {
        return lowestYear.get();
    }

    /**
     * Lowest year property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty lowestYearProperty() {
        return lowestYear;
    }

    /**
     * Sets lowest year.
     *
     * @param lowestYear the lowest year
     */
    public void setLowestYear(int lowestYear) {
        this.lowestYear.set(lowestYear);
    }

    /**
     * Gets highest temperature.
     *
     * @return the highest temperature
     */
    public double getHighestTemperature() {
        return highestTemperature.get();
    }

    /**
     * Highest temperature property double property.
     *
     * @return the double property
     */
    public DoubleProperty highestTemperatureProperty() {
        return highestTemperature;
    }

    /**
     * Sets highest temperature.
     *
     * @param highestTemperature the highest temperature
     */
    public void setHighestTemperature(double highestTemperature) {
        this.highestTemperature.set(highestTemperature);
    }

    /**
     * Gets lowest temperature.
     *
     * @return the lowest temperature
     */
    public double getLowestTemperature() {
        return lowestTemperature.get();
    }

    /**
     * Lowest temperature property double property.
     *
     * @return the double property
     */
    public DoubleProperty lowestTemperatureProperty() {
        return lowestTemperature;
    }

    /**
     * Sets lowest temperature.
     *
     * @param lowestTemperature the lowest temperature
     */
    public void setLowestTemperature(double lowestTemperature) {
        this.lowestTemperature.set(lowestTemperature);
    }

    /**
     * Gets average air frost day.
     *
     * @return the average air frost day
     */
    public int getAverageAirFrostDay() {
        return averageAirFrostDay.get();
    }

    /**
     * Average air frost day property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty averageAirFrostDayProperty() {
        return averageAirFrostDay;
    }

    /**
     * Sets average air frost day.
     *
     * @param averageAirFrostDay the average air frost day
     */
    public void setAverageAirFrostDay(int averageAirFrostDay) {
        this.averageAirFrostDay.set(averageAirFrostDay);
    }

    /**
     * Gets average annual rainfall.
     *
     * @return the average annual rainfall
     */
    public double getAverageAnnualRainfall() {
        return averageAnnualRainfall.get();
    }

    /**
     * Average annual rainfall property double property.
     *
     * @return the double property
     */
    public DoubleProperty averageAnnualRainfallProperty() {
        return averageAnnualRainfall;
    }

    /**
     * Sets average annual rainfall.
     *
     * @param averageAnnualRainfall the average annual rainfall
     */
    public void setAverageAnnualRainfall(double averageAnnualRainfall) {
        double rainfall = (double) Math.round(averageAnnualRainfall * 100) / 100;
        this.averageAnnualRainfall.set(rainfall);
    }
}
