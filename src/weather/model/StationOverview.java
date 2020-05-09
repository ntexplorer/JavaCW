package weather.model;

import javafx.beans.property.*;

/**
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

    public StationOverview() {
    }

    public StationOverview(String stationName) {
        this.stationName = new SimpleStringProperty(stationName);
    }

    @Override
    public String toString() {
        return "Station: " + this.getStationName() + "\nHighest Temperature: " + this.getHighestTemperature()
                + " degrees on " + this.getHighestMonth() + "/" + this.getHighestYear() + "\nLowest Temperature: "
                + this.getLowestTemperature() + " degrees on " + this.getLowestMonth() + "/" + this.getLowestYear() +
                "\nAverage annual air frost days: " + this.getAverageAirFrostDay() + " days" + "\nAverage annual rainfall: "
                + this.getAverageAnnualRainfall() + " mm\n";
    }

    public int getHighestMonth() {
        return highestMonth.get();
    }

    public IntegerProperty highestMonthProperty() {
        return highestMonth;
    }

    public void setHighestMonth(int highestMonth) {
        this.highestMonth.set(highestMonth);
    }

    public int getLowestMonth() {
        return lowestMonth.get();
    }

    public IntegerProperty lowestMonthProperty() {
        return lowestMonth;
    }

    public void setLowestMonth(int lowestMonth) {
        this.lowestMonth.set(lowestMonth);
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

    public int getHighestYear() {
        return highestYear.get();
    }

    public IntegerProperty highestYearProperty() {
        return highestYear;
    }

    public void setHighestYear(int highestYear) {
        this.highestYear.set(highestYear);
    }

    public int getLowestYear() {
        return lowestYear.get();
    }

    public IntegerProperty lowestYearProperty() {
        return lowestYear;
    }

    public void setLowestYear(int lowestYear) {
        this.lowestYear.set(lowestYear);
    }

    public double getHighestTemperature() {
        return highestTemperature.get();
    }

    public DoubleProperty highestTemperatureProperty() {
        return highestTemperature;
    }

    public void setHighestTemperature(double highestTemperature) {
        this.highestTemperature.set(highestTemperature);
    }

    public double getLowestTemperature() {
        return lowestTemperature.get();
    }

    public DoubleProperty lowestTemperatureProperty() {
        return lowestTemperature;
    }

    public void setLowestTemperature(double lowestTemperature) {
        this.lowestTemperature.set(lowestTemperature);
    }

    public int getAverageAirFrostDay() {
        return averageAirFrostDay.get();
    }

    public IntegerProperty averageAirFrostDayProperty() {
        return averageAirFrostDay;
    }

    public void setAverageAirFrostDay(int averageAirFrostDay) {
        this.averageAirFrostDay.set(averageAirFrostDay);
    }

    public double getAverageAnnualRainfall() {
        return averageAnnualRainfall.get();
    }

    public DoubleProperty averageAnnualRainfallProperty() {
        return averageAnnualRainfall;
    }

    public void setAverageAnnualRainfall(double averageAnnualRainfall) {
        double rainfall = (double) Math.round(averageAnnualRainfall * 100) / 100;
        this.averageAnnualRainfall.set(rainfall);
    }
}
