# CMT205 Coursework

### 1. User Guide & Error Handling

#### 1.1 Weather Data Process Tab

##### 1.1.1 Common Flows

The GUI includes one window (scene) with three different tabs. The first tab contains three buttons, a label to show the path chosen and a TableView to display dataset. When started, the TableView has no content and the user has to import the .csv data file from a directory to proceed.

<img src="https://i.loli.net/2020/05/11/j1xMizlB7TKAs4U.png" alt="image-20200510185028421" style="zoom:50%;" />

If the user clicks the "Select Folder..." button, a directory chooser will display. The default path has been set to "resources/" in the project folder so it won't cause too much trouble for the user to find the right path. 

![image-20200510185238820](https://i.loli.net/2020/05/11/LGaBTIZ8wtDvhSP.png)

After clicking the "Select Folder" button, this directory will be selected by the viewer, and the label below the button will show the directory path for the user's reference.

![image-20200510185611943](https://i.loli.net/2020/05/11/hQxVHEgslGqWLI4.png)

Now the user could click the "Import & Display Data" button to import and display the data within the selected folder. A message box will pop up after the data is proceeded successfully.

![image-20200510200507191](https://i.loli.net/2020/05/12/eLutVNcFM7sSfAW.png)

After closing this message box, the label showing the directory will reset to default and the annual weather data will be displayed in the TableView below. And since nothing is hard-coded in the application, it can read any amount of data files within a folder as long as they are in the correct format.

![image-20200510220146502](https://i.loli.net/2020/05/12/QPM68ECAl7o4JOX.png)



After the data is imported, the user can click the "Generate Report" button to generate a .txt report (report.txt located in the project folder) with the statistics of all the weather stations. (The processing for the empty or incomplete data files will be explained later)

If the report is generated as expected, a message box will pop up as follows:

![image-20200510185636100](https://i.loli.net/2020/05/12/Vpo5RFdtczeguDO.png)



The format of the report is shown below:

![image-20200510201424686](https://i.loli.net/2020/05/12/132M8abx7XO4Dpn.png)



##### 1.1.2 Error Handling

If the user closes the directory chooser without choosing a directory, a message box will pop up as a reminder:

![image-20200510200800871](https://i.loli.net/2020/05/12/X5A8cMPJFuzg4RQ.png)



If the user clicks the "Import & Display Data" button without selecting a directory first, an error message box will pop up as follows:

![image-20200510201217670](https://i.loli.net/2020/05/12/gEZjAv2B58IuzyT.png)



Also, if the user clicks the "Generate Report" button without importing the data first, there would be an error message box popping up as follows:

![image-20200510201321232](https://i.loli.net/2020/05/12/5C2NHTQjmxhJyka.png)

If the user selects an empty folder and clicks the "Import & Display Data" button, an error message box will pop up as follows:

![image-20200510201548889](https://i.loli.net/2020/05/11/uonLfvTsayAh4iE.png)

If the user selects a directory which doesn't have any .csv file, an error message box will pop up as follows:

![image-20200510201735310](https://i.loli.net/2020/05/11/ZIzO1ytX2K7pNVU.png)

#### 1.2 Menu

The menu contains two options, the "File" menu and the "Help" menu.

![image-20200510201944187](https://i.loli.net/2020/05/11/nCGX4wPlbVOY3pK.png)

The "File" menu has four MenuItems which have the same function as the three buttons and plus an exit option to close the application.

![image-20200510202243606](https://i.loli.net/2020/05/11/iBIkYC2Gz1jsDVq.png)

The "Help" menu has one MenuItems which shows the author information after clicked.

![image-20200510202404865](https://i.loli.net/2020/05/11/7eiU4aEcg5XyMm6.png)

#### 1.3 Annual Data Visualization Tab

This tab contains a TreeView and three bar charts within a SplitPane. When no data imported, the GUI is empty as below:

![image-20200510202648968](https://i.loli.net/2020/05/11/vuGI1melKAkCr4w.png)

After importing the data files from the directory, all the stations will be displayed in the TreeView as below:

![image-20200510203032432](https://i.loli.net/2020/05/11/UIscbyOJPqLvEhF.png)

If the user clicks the station items, nothing will happen. But if the user clicks any year items, the relative bar charts will be displayed dynamically.

![image-20200510203228910](https://i.loli.net/2020/05/11/hmG3fADdtP8Qpsq.png)

If the user clicks another year item, the previous charts will disappear and the new charts will be displayed.

![image-20200510203515421](https://i.loli.net/2020/05/11/fTEtVqsPQ5pn6NX.png)

#### 1.4 <u>Station Overview Visualization Tab (Novel Feature)</u>

This tab looks kind of similar to the "Annual Data Visualization" tab. When no data imported it is also empty with one TreeView and three empty line charts on the right.

![image-20200510203758696](https://i.loli.net/2020/05/11/lCpr4VwZgL26HEJ.png)

After importing the data files, the TreeView will display all the name of the stations.

![image-20200510203905682](https://i.loli.net/2020/05/11/8GY4mcu5LXTWElZ.png)

If the user clicks a station item, the overall statistics of this station will be displayed in the line charts.

![image-20200511181601274](https://i.loli.net/2020/05/12/JbOVwHxGrzQ51NA.png)

If the user clicks another station item, the previous charts will disappear and the new ones shows up.

![image-20200511181622702](https://i.loli.net/2020/05/12/RGa4AxNdug5XEzh.png)



 

------

### 2. Code Structure

The project is developed under Java JDK 11.0.6 with JavaFX SDK 11.0.2. It is made of two packages as shown below. Package "weather.view" contains all the essential components to construct a JavaFX GUI, while package "weather.model" contains three classes used for different data instantiation.

![image-20200510182841830.png](https://i.loli.net/2020/05/13/7sUrnKfN3JiIcRB.png)

#### 2.1 Class Station

Class "Station" stands for the original data imported from the data file, which has the followsing attributes:

```java
	private StringProperty stationName;
    private IntegerProperty year;
    private IntegerProperty month;
    private DoubleProperty maximumTemperature;
    private DoubleProperty minimumTemperature;
    private IntegerProperty airFrostDayNum;
    private DoubleProperty rainfall;
```

In such case, an instance of Station equals to one single piece of weather data which stores the figures of one station in one certain month.

#### 2.2 Class ProcessedStation

Class "ProcessedStation" preserves weather data that has been dealt with. It has the attributes as follows:

```java
private StringProperty stationName;
private IntegerProperty year;
private final IntegerProperty monthCount;
private final IntegerProperty maxTempMonth;
private final IntegerProperty minTempMonth;
private final DoubleProperty maximumTemperature;
private final DoubleProperty minimumTemperature;
private final IntegerProperty airFrostDaySum;
private final DoubleProperty rainfallSum;
```

So basically it contains the annual weather data of a station, since there are stations with incomplete data, the monthCount property counts the number of Station instances recorded for further calculation of statistics.

To round the rainfallSum attribute, the setter has been re-written as follows:

```java
public void setRainfallSum(double rainfallSum) {
    double rainfall = (double) Math.round(rainfallSum * 100) / 100;
    this.rainfallSum.set(rainfall);
}
```

#### 2.3 Class StationOverview

Class "StationOverview" is for storing the data to generate the overall statistics report. The attributes is shown below:

```java
private StringProperty stationName;
private final IntegerProperty highestYear;
private final IntegerProperty highestMonth;
private final IntegerProperty lowestYear;
private final IntegerProperty lowestMonth;
private final DoubleProperty highestTemperature;
private final DoubleProperty lowestTemperature;
private final IntegerProperty averageAirFrostDay;
private final DoubleProperty averageAnnualRainfall;
```

It contains all the figures of one station from 2011 to 2019, except the one with missing data. 

```java
@Override
public String toString() {
    return "Station: " + this.getStationName() + "\nHighest Temperature: " + this.getHighestTemperature()
            + " degrees on " + this.getHighestMonth() + "/" + this.getHighestYear() + "\nLowest Temperature: "
            + this.getLowestTemperature() + " degrees on " + this.getLowestMonth() + "/" + this.getLowestYear() +
            "\nAverage annual air frost days: " + this.getAverageAirFrostDay() + " days" + "\nAverage annual rainfall: "
            + this.getAverageAnnualRainfall() + " mm\n";
}
```

The toString() method has been re-written for report generation.

Also, the averageAnnualRainfall has been rounded by the following setter:

```java
public void setAverageAnnualRainfall(double averageAnnualRainfall) {
    double rainfall = (double) Math.round(averageAnnualRainfall * 100) / 100;
    this.averageAnnualRainfall.set(rainfall);
}
```

#### 2.4 Controller

Class Controller contains all the method used by the widgets of the GUI. A few typical components will be explained below.

##### 2.4.1 <u>DirectoryChooser (Novel Feature)</u>

```java
DirectoryChooser directoryChooser = new DirectoryChooser();
directoryChooser.setTitle("Choose the resource directory...");
directoryChooser.setInitialDirectory(new File("resources/"));
```

Instead of importing all the data by hard-coding them into the program, a DirectoryChooser is used and all kinds of exceptions have been considered (see 1.1.2) so that the user could import any other data files which is in the correct format.

##### 2.4.2 readData()

```java
private void readData(File file) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String lineData;
            while ((lineData = fileReader.readLine()) != null) {
//                split the data with "," and put them in a string array, then add to Station instance
                String[] dataSet = lineData.split(",");
                Station tempStation = new Station(getFileNameNoEx(file.getName()),
                        Integer.parseInt(dataSet[0]), Integer.parseInt(dataSet[1]),
                        Double.parseDouble(dataSet[2]),
                        Double.parseDouble(dataSet[3]),
                        Integer.parseInt(dataSet[4]), Double.parseDouble(dataSet[5]));
                stationData.add(tempStation);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
```

If the method can't read any data from a .csv file, then there won't be any Station instance of this station, which means the empty .csv file will be skipped.

##### 2.4.2 getFileNameNoEx()

A method made to take a filename with extended file type and return the name without the extended type. By using this method the program can get the name of the file as the attribute "stationName".

```java
private static String getFileNameNoEx(String filename) {
    if ((filename != null) && (filename.length() > 0)) {
        int dot = filename.lastIndexOf('.');
        if ((dot > -1) && (dot < (filename.length()))) {
            return filename.substring(0, dot);
        }
    }
    return filename;
}
```

##### 2.4.3 monthCount

Since there could be files with incomplete data (Like Cwmystwyth with only data of three years and year 2013 only contains data from January to March), monthCount is used instead of simply dividing the airFrostDay and rainfall by 9 years (2011-2019) to get the correct average figure.

```java
tempStationOverview.setAverageAirFrostDay(airFrostDay * 12 / monthCount);
tempStationOverview.setAverageAnnualRainfall(rainfall * 12 / monthCount);
```

##### 2.4.4 makeBranch()

```java
private TreeItem<String> makeBranch(String itemName, TreeItem<String> parent) {
    TreeItem<String> item = new TreeItem<>(itemName);
    item.setExpanded(false);
    parent.getChildren().add(item);
    return item;
}
```

This method is packaged for making new branch to the selected TreeItem.

##### 2.4.5 addListener()

```java
dataTreeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
    displayTempChart(newValue);
    displayAirFrostChart(newValue);
    displayRainfallChart(newValue);
});
```

To dynamically display the charts according to the TreeItem selected, addListener() is used to pass the selected TreeItem with a lambda to the relative method.

##### 2.4.6 XYChart.Series

```java
XYChart.Series series1 = new XYChart.Series();
XYChart.Series series2 = new XYChart.Series();
series1.setName("Maximum Temperature");
series2.setName("Minimum Temperature");
```

Since the maximum and minimum temperature share the same measurement, they are put into the same chart with two different series.

##### 2.4.7 clearLineChart()

```java
private void clearLineChart(LineChart<String, Number> chart) {
    ObservableList<XYChart.Series<String, Number>> allSeries = chart.getData();
    for (XYChart.Series<String, Number> series : allSeries) {
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node node = data.getNode();
            Parent parent = node.parentProperty().get();
            if (parent instanceof Group) {
                Group group = (Group) parent;
                group.getChildren().clear();
            }
        }
    }
    allSeries.clear();
}
```

This method is made to clear the content of the chart first after the user selects another TreeItem.

#### 2.5 Class Main

```java
@Override
public void start(Stage primaryStage) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
    primaryStage.setTitle("CMT205 Weather Statistics Viewer");
    Scene scene = new Scene(root, 1200, 800);
    scene.getStylesheets().add(getClass().getResource("material-fx.css").toString());
    primaryStage.setScene(scene);
    primaryStage.show();
}
```

"MainLayout.fxml" is constructed by SceneBuilder to design the layout of the GUI.

material-fx.css is used for changing the style of the GUI widgets.