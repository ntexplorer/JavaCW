package weather.model;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Tian Z
 */
public class ProcessedStation {
    private StringProperty stationName = new SimpleStringProperty(this, "Station Name", "");
    private IntegerProperty year = new SimpleIntegerProperty(this, "Year", 0);
    private List<DoubleProperty> monthlyMaxTemperature = new ArrayList<>();
    private List<DoubleProperty> monthlyMinTemperature = new ArrayList<>();
    private final DoubleProperty maximumTemperature = new SimpleDoubleProperty(this, "Maximum Temperature", 0);
    private final DoubleProperty minimumTemperature = new SimpleDoubleProperty(this, "Minimum Temperature", 0);
    private List<IntegerProperty> monthlyAirFrostDay = new ArrayList<>();
    private List<DoubleProperty> monthlyRainfall = new ArrayList<>();
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

    public List<DoubleProperty> getMonthlyMaxTemperature() {
        return monthlyMaxTemperature;
    }

    public void setMonthlyMaxTemperature(List<DoubleProperty> monthlyMaxTemperature) {
        this.monthlyMaxTemperature = monthlyMaxTemperature;
    }

    public List<DoubleProperty> getMonthlyMinTemperature() {
        return monthlyMinTemperature;
    }

    public void setMonthlyMinTemperature(List<DoubleProperty> monthlyMinTemperature) {
        this.monthlyMinTemperature = monthlyMinTemperature;
    }

    public double getMaximumTemperature() {
        return maximumTemperature.get();
    }

    public DoubleProperty maximumTemperatureProperty() {
        return maximumTemperature;
    }

    public void setMaximumTemperature() {
        List<Double> maxArr = new ArrayList<>();
        for (DoubleProperty maxTemp : monthlyMaxTemperature) {
            maxArr.add(maxTemp.get());
        }
        this.maximumTemperature.set(Collections.max(maxArr));
    }

    public double getMinimumTemperature() {
        return minimumTemperature.get();
    }

    public DoubleProperty minimumTemperatureProperty() {
        return minimumTemperature;
    }

    public void setMinimumTemperature() {
        List<Double> minArr = new ArrayList<>();
        for (DoubleProperty minTemp : monthlyMinTemperature) {
            minArr.add(minTemp.get());
        }
        this.minimumTemperature.set(Collections.min(minArr));
    }

    public List<IntegerProperty> getMonthlyAirFrostDay() {
        return monthlyAirFrostDay;
    }

    public void setMonthlyAirFrostDay(List<IntegerProperty> monthlyAirFrostDay) {
        this.monthlyAirFrostDay = monthlyAirFrostDay;
    }

    public List<DoubleProperty> getMonthlyRainfall() {
        return monthlyRainfall;
    }

    public void setMonthlyRainfall(List<DoubleProperty> monthlyRainfall) {
        this.monthlyRainfall = monthlyRainfall;
    }

    public int getAirFrostDaySum() {
        return airFrostDaySum.get();
    }

    public IntegerProperty airFrostDaySumProperty() {
        return airFrostDaySum;
    }

    public void setAirFrostDaySum() {
        int totalAirFrostDay = 0;
        for (IntegerProperty day : monthlyAirFrostDay) {
            totalAirFrostDay += day.get();
        }
        this.airFrostDaySum.set(totalAirFrostDay);
    }

    public double getRainfallSum() {
        return rainfallSum.get();
    }

    public DoubleProperty rainfallSumProperty() {
        return rainfallSum;
    }

    public void setRainfallSum() {
        double totalRainfall = 0.0;
        for (DoubleProperty rainfall : monthlyRainfall) {
            totalRainfall += rainfall.get();
        }
        totalRainfall = (double) Math.round(totalRainfall * 100) / 100;
        this.rainfallSum.set(totalRainfall);
    }

    public void addMaxTemp(DoubleProperty maxTemp) {
        this.monthlyMaxTemperature.add(maxTemp);
    }

    public void addMinTemp(DoubleProperty minTemp) {
        this.monthlyMinTemperature.add(minTemp);
    }

    public void addAirFrostDay(IntegerProperty airFrostDay) {
        this.monthlyAirFrostDay.add(airFrostDay);
    }

    public void addRainfall(DoubleProperty rainfall) {
        this.monthlyRainfall.add(rainfall);
    }

}
