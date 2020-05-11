package weather.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import weather.model.ProcessedStation;
import weather.model.Station;
import weather.model.StationOverview;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The Controller of the GUI
 *
 * @author Tian Z
 */
public class Controller implements Initializable {
    private final ObservableList<Station> stationData = FXCollections.observableArrayList();
    private final ObservableList<ProcessedStation> processedStationData = FXCollections.observableArrayList();
    private final ObservableList<StationOverview> overviewStationData = FXCollections.observableArrayList();

    private File selectedDirectory;

    @FXML
    private ImageView titleImage;

    @FXML
    private MenuItem selectDirMenu;

    @FXML
    private MenuItem importDataMenu;

    @FXML
    private MenuItem genReportMenu;

    @FXML
    private MenuItem exitMenu;

    @FXML
    private MenuItem aboutMenu;

    @FXML
    private Button selectFolderBtn;

    @FXML
    private Label directoryChosen;

    @FXML
    private Button importDataBtn;

    @FXML
    private Button generateReportBtn;

    @FXML
    private BarChart<String, Number> tempBarChart;

    @FXML
    private CategoryAxis xAxis1;

    @FXML
    private NumberAxis yAxis1;

    @FXML
    private BarChart<String, Number> airFrostBarChart;

    @FXML
    private CategoryAxis xAxis2;

    @FXML
    private NumberAxis yAxis2;

    @FXML
    private BarChart<String, Number> rainfallBarChart;

    @FXML
    private CategoryAxis xAxis3;

    @FXML
    private NumberAxis yAxis3;

    @FXML
    private LineChart<String, Number> tempLineChart;

    @FXML
    private CategoryAxis xAxis4;

    @FXML
    private NumberAxis yAxis4;

    @FXML
    private LineChart<String, Number> airFrostLineChart;

    @FXML
    private CategoryAxis xAxis5;

    @FXML
    private NumberAxis yAxis5;

    @FXML
    private LineChart<String, Number> rainfallLineChart;

    @FXML
    private CategoryAxis xAxis6;

    @FXML
    private NumberAxis yAxis6;

    @FXML
    private TreeView<String> dataTreeView;

    @FXML
    private TreeView<String> overallTreeView;

    @FXML
    private TableView<ProcessedStation> dataTable;

    @FXML
    private TableColumn<ProcessedStation, String> nameColumn;

    @FXML
    private TableColumn<ProcessedStation, Integer> yearColumn;

    @FXML
    private TableColumn<ProcessedStation, Double> maxTempColumn;

    @FXML
    private TableColumn<ProcessedStation, Double> minTempColumn;

    @FXML
    private TableColumn<ProcessedStation, Integer> totalAirFrostColumn;

    @FXML
    private TableColumn<ProcessedStation, Double> totalRainfallColumn;

    @FXML
    private TableColumn<ProcessedStation, Integer> maxTempMonthCol;

    @FXML
    private TableColumn<ProcessedStation, Integer> minTempMonthCol;

    /**
     * initializing method, connecting all the widgets
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Add the title image
        try {
            FileInputStream input = new FileInputStream("img/title.png");
            Image image = new Image(input);
            titleImage.setImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        selectFolderBtn.setOnAction(e -> importData());
        selectDirMenu.setOnAction(e -> importData());

        importDataBtn.setOnAction(e -> {
            readAllData();
            dataProcess();
            displayData();
            showTreeData();
            showAllTreeData();
        });

        importDataMenu.setOnAction(e -> {
            readAllData();
            dataProcess();
            displayData();
            showTreeData();
            showAllTreeData();
        });

        generateReportBtn.setOnAction(e -> {
            getStatistics();
            generateReport();
        });

        genReportMenu.setOnAction(e -> {
            getStatistics();
            generateReport();
        });

        exitMenu.setOnAction(e -> {
            Stage stage = (Stage) importDataBtn.getScene().getWindow();
            stage.close();
        });

        aboutMenu.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Author Information");
            alert.setHeaderText("CMT205 - Coursework");
            alert.setContentText("Author Name: Tian ZHANG\nStudent Number: c1912705");
            alert.showAndWait();
        });

//        add listener for the tree view to connect the bar charts
        dataTreeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            displayTempChart(newValue);
            displayAirFrostChart(newValue);
            displayRainfallChart(newValue);
        });

//        add listener for the tree view to connect the line charts
        overallTreeView.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            displayTempLineChart(newValue);
            displayAirFrostLineChart(newValue);
            displayRainfallLineChart(newValue);
        });
    }


//    **************** Import Data File ************************

    /**
     * Open a directoryChooser to select the data folder
     */
    private void importData() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Choose the resource directory...");
//        default folder set to resources folder so the user won't need to find the folder manually
            directoryChooser.setInitialDirectory(new File("resources/"));
            selectedDirectory = directoryChooser.showDialog(selectFolderBtn.getScene().getWindow());
//        set the label to the folder chosen for reference
            directoryChosen.setText("Directory Chosen: " + selectedDirectory.getAbsolutePath());
        } catch (NullPointerException e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Directory Required");
            alert.setHeaderText("No Directory Selected");
            alert.setContentText("You need to choose a directory to proceed.");
            alert.showAndWait();
        }
    }

//    **************** End Import Data File ************************

//    **************** Process & Display Data ************************

    /**
     * validate the files in the chosen folder for exception handling
     *
     * @return The list of file
     */
    private ArrayList<File> validateData() {
//        if clicked import btn without choosing a directory
        if (selectedDirectory == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Operation");
            alert.setContentText("Please choose a directory before import.");
            alert.showAndWait();
            return null;
        }
        ArrayList<File> dataFiles = new ArrayList<>();
        File[] fileArr = selectedDirectory.listFiles();
        assert fileArr != null;
        System.out.println("fileArr : " + fileArr.length);
//        if the file array is not empty
        if (fileArr.length > 0) {
            for (int i = 0; i < Objects.requireNonNull(fileArr).length; i++) {
                File file = fileArr[i];
//                if the file type is csv then add it to the List dataFiles
                if (file.getAbsolutePath().endsWith(".csv")) {
                    dataFiles.add(file);
                }
            }
        } else {
//            exception for choosing an empty directory
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty directory");
            alert.setContentText("Empty directory selected, please try again.");
            alert.showAndWait();
            directoryChosen.setText("Directory Chosen: ");
            return null;
        }
//        show message if any csv files imported
        if (dataFiles.size() > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Data process done");
            alert.setContentText("Data imported and displayed successfully!");
            alert.showAndWait();
//            clear the path label
            directoryChosen.setText("Directory Chosen: ");
            return dataFiles;
        } else {
//            if the chosen directory is not empty but without any csv file, then nothing imported into dataFiles
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Data File Found");
            alert.setContentText("No csv file in the selected directory, please try again.");
            alert.showAndWait();
            //            clear the path label
            directoryChosen.setText("Directory Chosen: ");
            return null;
        }
    }

    /**
     * read single piece of data and create a instance of Station
     *
     * @param file the data file
     */
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

    /**
     * call the validation and read singe data method
     */
    private void readAllData() {
        ArrayList<File> dataFileArray = validateData();
        if (dataFileArray != null) {
            for (File file : dataFileArray) {
                readData(file);
            }
        }
    }

    /**
     * Create a processed data instance using Station instance, which contains the annually data
     */
    private void dataProcess() {
        if (!stationData.isEmpty()) {
            String tempName = stationData.get(0).getStationName();
            int tempYear = stationData.get(0).getYear();
            int tempHighestMonth = stationData.get(0).getMonth();
            int tempLowestMonth = stationData.get(0).getMonth();
            double tempHighest = stationData.get(0).getMaximumTemperature();
            double tempLowest = stationData.get(0).getMinimumTemperature();
            int airFrostDay = 0;
            double rainfall = 0;
            int monthCount = 0;
            ProcessedStation tempProcessedStation = new ProcessedStation(tempName, tempYear);
            for (Station station : stationData) {
                if ((station.getStationName().equals(tempName)) && (station.getYear() == tempYear)) {
                    if (station.getMaximumTemperature() > tempHighest) {
                        tempHighest = station.getMaximumTemperature();
                        tempHighestMonth = station.getMonth();
                    }
                    if (station.getMinimumTemperature() < tempLowest) {
                        tempLowest = station.getMinimumTemperature();
                        tempLowestMonth = station.getMonth();
                    }
                    airFrostDay += station.getAirFrostDayNum();
                    rainfall += station.getRainfall();
                    monthCount += 1;
                } else {
                    tempProcessedStation.setStationName(tempName);
                    tempProcessedStation.setYear(tempYear);
                    tempProcessedStation.setMaximumTemperature(tempHighest);
                    tempProcessedStation.setMaxTempMonth(tempHighestMonth);
                    tempProcessedStation.setMinimumTemperature(tempLowest);
                    tempProcessedStation.setMinTempMonth(tempLowestMonth);
                    tempProcessedStation.setAirFrostDaySum(airFrostDay);
                    tempProcessedStation.setRainfallSum(rainfall);
                    tempProcessedStation.setMonthCount(monthCount);
                    processedStationData.add(tempProcessedStation);
                    tempProcessedStation = new ProcessedStation();
                    tempName = station.getStationName();
                    tempYear = station.getYear();
                    tempHighest = station.getMaximumTemperature();
                    tempHighestMonth = station.getMonth();
                    tempLowest = station.getMinimumTemperature();
                    tempLowestMonth = station.getMonth();
                    airFrostDay = station.getAirFrostDayNum();
                    rainfall = station.getRainfall();
                    monthCount = 1;
                }
            }
            tempProcessedStation.setStationName(tempName);
            tempProcessedStation.setYear(tempYear);
            tempProcessedStation.setMaximumTemperature(tempHighest);
            tempProcessedStation.setMaxTempMonth(tempHighestMonth);
            tempProcessedStation.setMinimumTemperature(tempLowest);
            tempProcessedStation.setMinTempMonth(tempLowestMonth);
            tempProcessedStation.setAirFrostDaySum(airFrostDay);
            tempProcessedStation.setRainfallSum(rainfall);
            tempProcessedStation.setMonthCount(monthCount);
            processedStationData.add(tempProcessedStation);

            System.out.println(processedStationData.size());
        }
    }

    /**
     * Display all the data in the TableView
     */
    private void displayData() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("stationName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        maxTempColumn.setCellValueFactory(new PropertyValueFactory<>("maximumTemperature"));
        maxTempMonthCol.setCellValueFactory(new PropertyValueFactory<>("maxTempMonth"));
        minTempColumn.setCellValueFactory(new PropertyValueFactory<>("minimumTemperature"));
        minTempMonthCol.setCellValueFactory(new PropertyValueFactory<>("minTempMonth"));
        totalAirFrostColumn.setCellValueFactory(new PropertyValueFactory<>("airFrostDaySum"));
        totalRainfallColumn.setCellValueFactory(new PropertyValueFactory<>("rainfallSum"));
        dataTable.setItems(processedStationData);
    }

    /**
     * @param filename file with extended name
     * @return filename without extended name
     */
    private static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

//    **************** End of Process & Display Data ************************

//    **************** Report Generator  ************************

    /**
     * Gets statistics.
     * Using processed station instance to create a overview station instance
     */
    private void getStatistics() {
        if (!processedStationData.isEmpty()) {
            String tempName = processedStationData.get(0).getStationName();
            int tempHighestYear = processedStationData.get(0).getYear();
            int tempLowestYear = processedStationData.get(0).getYear();
            int tempHighestMonth = processedStationData.get(0).getMaxTempMonth();
            int tempLowestMonth = processedStationData.get(0).getMinTempMonth();
            double tempHighest = processedStationData.get(0).getMaximumTemperature();
            double tempLowest = processedStationData.get(0).getMinimumTemperature();
            int airFrostDay = 0;
            double rainfall = 0;
            int monthCount = 0;
            StationOverview tempStationOverview = new StationOverview(tempName);
            for (ProcessedStation processedStation : processedStationData) {
                if (processedStation.getStationName().equals(tempName)) {
                    if (processedStation.getMaximumTemperature() > tempHighest) {
                        tempHighest = processedStation.getMaximumTemperature();
                        tempHighestYear = processedStation.getYear();
                        tempHighestMonth = processedStation.getMaxTempMonth();
                    }
                    if (processedStation.getMinimumTemperature() < tempLowest) {
                        tempLowest = processedStation.getMinimumTemperature();
                        tempLowestYear = processedStation.getYear();
                        tempLowestMonth = processedStation.getMinTempMonth();
                    }
                    airFrostDay += processedStation.getAirFrostDaySum();
                    rainfall += processedStation.getRainfallSum();
                    monthCount += processedStation.getMonthCount();
                } else {
                    tempStationOverview.setStationName(tempName);
                    tempStationOverview.setHighestTemperature(tempHighest);
                    tempStationOverview.setHighestYear(tempHighestYear);
                    tempStationOverview.setHighestMonth(tempHighestMonth);
                    tempStationOverview.setLowestTemperature(tempLowest);
                    tempStationOverview.setLowestYear(tempLowestYear);
                    tempStationOverview.setLowestMonth(tempLowestMonth);
//                  For there is missing data in some files, use month number to calculate the average air frost and rainfall
                    tempStationOverview.setAverageAirFrostDay(airFrostDay * 12 / monthCount);
                    tempStationOverview.setAverageAnnualRainfall(rainfall * 12 / monthCount);
                    overviewStationData.add(tempStationOverview);
                    tempStationOverview = new StationOverview();
                    tempName = processedStation.getStationName();
                    tempHighestYear = processedStation.getYear();
                    tempLowestYear = processedStation.getYear();
                    tempHighestMonth = processedStation.getMaxTempMonth();
                    tempLowestMonth = processedStation.getMinTempMonth();
                    tempHighest = processedStation.getMaximumTemperature();
                    tempLowest = processedStation.getMinimumTemperature();
                    airFrostDay = processedStation.getAirFrostDaySum();
                    rainfall = processedStation.getRainfallSum();
                    monthCount = processedStation.getMonthCount();
                }
            }
//           The last set of data not processed in the for loop above
            tempStationOverview.setStationName(tempName);
            tempStationOverview.setHighestTemperature(tempHighest);
            tempStationOverview.setHighestYear(tempHighestYear);
            tempStationOverview.setHighestMonth(tempHighestMonth);
            tempStationOverview.setLowestTemperature(tempLowest);
            tempStationOverview.setLowestYear(tempLowestYear);
            tempStationOverview.setLowestMonth(tempLowestMonth);
            tempStationOverview.setAverageAirFrostDay(airFrostDay * 12 / monthCount);
            tempStationOverview.setAverageAnnualRainfall(rainfall * 12 / monthCount);
            overviewStationData.add(tempStationOverview);
//            System.out.println(overviewStationData.size());
        }
    }

    /**
     * Generate report.
     */
    private void generateReport() {
        if (!overviewStationData.isEmpty()) {
            try {
                File report = new File("report.txt");
                if (!report.exists()) {
                    report.createNewFile();
                }
                FileWriter fw = new FileWriter(report.getAbsolutePath());
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < overviewStationData.size(); i++) {
                    bw.write("***************************\n");
                    bw.write("Number: " + (i + 1) + "\n");
                    bw.write(overviewStationData.get(i).toString());
                    bw.write("***************************\n\n");
                }
                bw.flush();
                bw.close();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Report Generated");
                alert.setContentText("Report generated successfully!");
                alert.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Data Found");
            alert.setContentText("Please import the data and try again!");
            alert.showAndWait();
        }


    }

//    **************** End of Report Generator  ************************


//    **************** Tree View  ************************

    /**
     * Show tree data in the TreeView
     */
    private void showTreeData() {
        if (!processedStationData.isEmpty()) {
            TreeItem<String> root = new TreeItem<>("Station");
            root.setExpanded(true);
            String tempName = processedStationData.get(0).getStationName();
            TreeItem<String> stationItem = new TreeItem<>(tempName);
            stationItem.setExpanded(false);
            root.getChildren().add(stationItem);
            for (ProcessedStation station : processedStationData) {
                if (!tempName.equals(station.getStationName())) {
                    tempName = station.getStationName();
                    stationItem = makeBranch(tempName, root);
                }
//                set year to the branch of stationItem
                makeBranch(Integer.toString(station.getYear()), stationItem);
            }
            dataTreeView.setRoot(root);
            dataTreeView.setShowRoot(false);
        }
    }

    /**
     * Show the overview data in the TreeView in the 3rd tab
     */
    private void showAllTreeData() {
        if (!processedStationData.isEmpty()) {
            TreeItem<String> root = new TreeItem<>("Station");
            root.setExpanded(true);
            String tempName = processedStationData.get(0).getStationName();
            TreeItem<String> stationItem = new TreeItem<>(tempName);
            stationItem.setExpanded(false);
            root.getChildren().add(stationItem);
            for (ProcessedStation station : processedStationData) {
                if (!tempName.equals(station.getStationName())) {
                    tempName = station.getStationName();
                    stationItem = new TreeItem<>(tempName);
                    root.getChildren().add(stationItem);
                }
            }
            overallTreeView.setRoot(root);
            overallTreeView.setShowRoot(false);
        }
    }

    /**
     * Make branch tree item.
     *
     * @param itemName the item name
     * @param parent   the parent
     * @return the tree item
     */
    private TreeItem<String> makeBranch(String itemName, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(itemName);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }


//    **************** End of Tree View  ************************

//    **************** Charts  ************************

    /**
     * Display temperature bar chart.
     *
     * @param treeItem the tree item
     */
    private void displayTempChart(TreeItem<String> treeItem) {
        if (treeItem.isLeaf()) {
            clearBarChart(tempBarChart);
            xAxis1.setLabel("Month");
            yAxis1.setLabel("Temperature");
            String stationName = treeItem.getParent().getValue();
            String year = treeItem.getValue();
            tempBarChart.setTitle("Temperature of " + stationName + " Station in " + year);
            List<String> monthList = new ArrayList<>();
            List<Double> maxTempList = new ArrayList<>();
            List<Double> minTempList = new ArrayList<>();
            for (Station station : stationData) {
                if ((station.getStationName().equals(stationName)) && (station.getYear() == Integer.parseInt(year))) {
                    monthList.add(String.valueOf(station.getMonth()));
                    maxTempList.add(station.getMaximumTemperature());
                    minTempList.add(station.getMinimumTemperature());
                }
            }

            XYChart.Series series1 = new XYChart.Series();
            XYChart.Series series2 = new XYChart.Series();
            series1.setName("Maximum Temperature");
            series2.setName("Minimum Temperature");

            for (int i = 0; i < monthList.size(); i++) {
                series1.getData().add(new XYChart.Data(monthList.get(i), maxTempList.get(i)));
                series2.getData().add(new XYChart.Data(monthList.get(i), minTempList.get(i)));
            }
            tempBarChart.getData().addAll(series1, series2);
        }
    }

    /**
     * Display air frost chart.
     *
     * @param treeItem the tree item
     */
    private void displayAirFrostChart(TreeItem<String> treeItem) {
        if (treeItem.isLeaf()) {
            clearBarChart(airFrostBarChart);
            xAxis2.setLabel("Month");
            yAxis2.setLabel("Air Frost Day");
            String stationName = treeItem.getParent().getValue();
            String year = treeItem.getValue();
            airFrostBarChart.setTitle("Air Frost Days of " + stationName + " Station in " + year);
            List<String> monthList = new ArrayList<>();
            List<Integer> airFrostList = new ArrayList<>();
            for (Station station : stationData) {
                if ((station.getStationName().equals(stationName)) && (station.getYear() == Integer.parseInt(year))) {
                    monthList.add(String.valueOf(station.getMonth()));
                    airFrostList.add(station.getAirFrostDayNum());
                }
            }

            XYChart.Series series3 = new XYChart.Series();
            series3.setName("Air Frost Days");

            for (int i = 0; i < monthList.size(); i++) {
                series3.getData().add(new XYChart.Data(monthList.get(i), airFrostList.get(i)));
            }
            airFrostBarChart.getData().addAll(series3);
        }
    }

    /**
     * Display rainfall chart.
     *
     * @param treeItem the tree item
     */
    private void displayRainfallChart(TreeItem<String> treeItem) {
        if (treeItem.isLeaf()) {
            clearBarChart(rainfallBarChart);
            xAxis3.setLabel("Month");
            yAxis3.setLabel("Rainfall");
            String stationName = treeItem.getParent().getValue();
            String year = treeItem.getValue();
            rainfallBarChart.setTitle("Rainfall of " + stationName + " Station in " + year);
            List<String> monthList = new ArrayList<>();
            List<Double> rainfallList = new ArrayList<>();
            for (Station station : stationData) {
                if ((station.getStationName().equals(stationName)) && (station.getYear() == Integer.parseInt(year))) {
                    monthList.add(String.valueOf(station.getMonth()));
                    rainfallList.add(station.getRainfall());
                }
            }

            XYChart.Series series4 = new XYChart.Series();
            series4.setName("Rainfall");

            for (int i = 0; i < monthList.size(); i++) {
                series4.getData().add(new XYChart.Data(monthList.get(i), rainfallList.get(i)));
            }
            rainfallBarChart.getData().addAll(series4);
        }
    }

    /**
     * Display temp line chart.
     *
     * @param treeItem the tree item
     */
    private void displayTempLineChart(TreeItem<String> treeItem) {
        clearLineChart(tempLineChart);

        xAxis4.setLabel("Year");
        yAxis4.setLabel("Temperature");
        String stationName = treeItem.getValue();
        tempLineChart.setTitle("Temperature of " + stationName + " Station");
        List<String> yearList = new ArrayList<>();
        List<Double> maxTempList = new ArrayList<>();
        List<Double> minTempList = new ArrayList<>();
        for (ProcessedStation station : processedStationData) {
            if (station.getStationName().equals(stationName)) {
                yearList.add(String.valueOf(station.getYear()));
                maxTempList.add(station.getMaximumTemperature());
                minTempList.add(station.getMinimumTemperature());
            }
        }

        XYChart.Series series5 = new XYChart.Series();
        XYChart.Series series6 = new XYChart.Series();
        series5.setName("Maximum Temperature");
        series6.setName("Minimum Temperature");


        for (int i = 0; i < yearList.size(); i++) {
            series5.getData().add(new XYChart.Data(yearList.get(i), maxTempList.get(i)));
            series6.getData().add(new XYChart.Data(yearList.get(i), minTempList.get(i)));
        }
        tempLineChart.getData().addAll(series5, series6);
    }

    /**
     * Display air frost line chart.
     *
     * @param treeItem the tree item
     */
    private void displayAirFrostLineChart(TreeItem<String> treeItem) {
        clearLineChart(airFrostLineChart);

        xAxis5.setLabel("Year");
        yAxis5.setLabel("Air Frost Day");
        String stationName = treeItem.getValue();
        airFrostLineChart.setTitle("Average Monthly Air Frost Days of " + stationName + " Station");
        List<String> yearList = new ArrayList<>();
        List<Integer> airFrostList = new ArrayList<>();
        for (ProcessedStation station : processedStationData) {
            if (station.getStationName().equals(stationName)) {
                yearList.add(String.valueOf(station.getYear()));
                airFrostList.add(station.getAirFrostDaySum() / station.getMonthCount());
            }
        }

        XYChart.Series series7 = new XYChart.Series();
        series7.setName("Air Frost Day");


        for (int i = 0; i < yearList.size(); i++) {
            series7.getData().add(new XYChart.Data(yearList.get(i), airFrostList.get(i)));
        }
        airFrostLineChart.getData().addAll(series7);
    }

    /**
     * Display rainfall line chart.
     *
     * @param treeItem the tree item
     */
    private void displayRainfallLineChart(TreeItem<String> treeItem) {
        clearLineChart(rainfallLineChart);
        xAxis6.setLabel("Year");
        yAxis6.setLabel("Rainfall");
        String stationName = treeItem.getValue();
        rainfallLineChart.setTitle("Average Monthly Rainfall of " + stationName + " Station");
        List<String> yearList = new ArrayList<>();
        List<Double> rainfallList = new ArrayList<>();
        for (ProcessedStation station : processedStationData) {
            if (station.getStationName().equals(stationName)) {
                yearList.add(String.valueOf(station.getYear()));
                rainfallList.add(station.getRainfallSum() / station.getMonthCount());
            }
        }

        XYChart.Series series8 = new XYChart.Series();
        series8.setName("Rainfall");

        for (int i = 0; i < yearList.size(); i++) {
            series8.getData().add(new XYChart.Data(yearList.get(i), rainfallList.get(i)));
        }
        rainfallLineChart.getData().addAll(series8);
    }

    /**
     * Clear bar chart.
     *
     * @param chart the chart
     */
    private void clearBarChart(BarChart<String, Number> chart) {
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

    /**
     * Clear line chart.
     *
     * @param chart the chart
     */
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
}