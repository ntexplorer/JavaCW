package weather.model;

import javafx.beans.property.*;

/**
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

    public ProcessedStation() {
    }

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

    public int getMonthCount() {
        return monthCount.get();
    }

    public IntegerProperty monthCountProperty() {
        return monthCount;
    }

    public void setMonthCount(int monthCount) {
        this.monthCount.set(monthCount);
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

    public int getMaxTempMonth() {
        return maxTempMonth.get();
    }

    public IntegerProperty maxTempMonthProperty() {
        return maxTempMonth;
    }

    public void setMaxTempMonth(int maxTempMonth) {
        this.maxTempMonth.set(maxTempMonth);
    }

    public int getMinTempMonth() {
        return minTempMonth.get();
    }

    public IntegerProperty minTempMonthProperty() {
        return minTempMonth;
    }

    public void setMinTempMonth(int minTempMonth) {
        this.minTempMonth.set(minTempMonth);
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

    public int getAirFrostDaySum() {
        return airFrostDaySum.get();
    }

    public IntegerProperty airFrostDaySumProperty() {
        return airFrostDaySum;
    }

    public void setAirFrostDaySum(int airFrostDaySum) {
        this.airFrostDaySum.set(airFrostDaySum);
    }

    public double getRainfallSum() {
        return rainfallSum.get();
    }

    public DoubleProperty rainfallSumProperty() {
        return rainfallSum;
    }

    public void setRainfallSum(double rainfallSum) {
        double rainfall = (double) Math.round(rainfallSum * 100) / 100;
        this.rainfallSum.set(rainfall);
    }
}
