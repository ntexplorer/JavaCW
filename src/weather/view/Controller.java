package weather.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import weather.model.ProcessedStation;
import weather.model.Station;
import weather.model.StationOverview;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * @author Tian Z
 */
public class Controller implements Initializable {
    private final ObservableList<Station> stationData = FXCollections.observableArrayList();
    private final ObservableList<ProcessedStation> processedStationData = FXCollections.observableArrayList();
    private final ObservableList<StationOverview> overviewStationData = FXCollections.observableArrayList();

    private File selectedDirectory;

    @FXML
    private Button selectFolderBtn;

    @FXML
    private Label directoryChosen;

    @FXML
    private Button importDataBtn;

    @FXML
    private Button generateReportBtn;

    @FXML
    private Button bottomLabel;

    @FXML
    private Button tMaxBtn;

    @FXML
    private Button tMinBtn;

    @FXML
    private Button airFrostBtn;

    @FXML
    private Button rainfallBtn;

    @FXML
    private Button testBtn;

    @FXML
    private TreeView<String> dataTreeView;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectFolderBtn.setOnAction(e -> importData());
        importDataBtn.setOnAction(e -> {
            readAllData();
            dataProcess();
            displayData();
        });
        generateReportBtn.setOnAction(e -> {
            getStatistics();
            generateReport();
        });

        testBtn.setOnAction(e -> {
            showTreeData();
        });
    }


//    **************** Import Data File ************************

    private void importData() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose the resource directory...");
        directoryChooser.setInitialDirectory(new File("resources/"));
        selectedDirectory = directoryChooser.showDialog(selectFolderBtn.getScene().getWindow());
        directoryChosen.setText("Directory Chosen: " + selectedDirectory.getAbsolutePath());
    }

//    **************** End Import Data File ************************

//    **************** Process & Display Data ************************

    private ArrayList<File> validateData() {
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
        if (fileArr.length > 0) {
            for (int i = 0; i < Objects.requireNonNull(fileArr).length; i++) {
                File file = fileArr[i];
                if (file.getAbsolutePath().endsWith(".csv")) {
                    dataFiles.add(file);
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty directory");
            alert.setContentText("Empty directory selected, please try again.");
            alert.showAndWait();
            directoryChosen.setText("Directory Chosen: ");
            return null;
        }
        if (dataFiles.size() > 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Data process done");
            alert.setContentText("Data imported and displayed successfully!");
            alert.showAndWait();
            directoryChosen.setText("Directory Chosen: ");
            return dataFiles;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Data File Found");
            alert.setContentText("No csv file in the selected directory, please try again.");
            alert.showAndWait();
            directoryChosen.setText("Directory Chosen: ");
            return null;
        }
    }

    private void readData(File file) {
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String lineData;
            while ((lineData = fileReader.readLine()) != null) {
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
//        for (Station station : stationData) {
//            System.out.println(station);
//        }
    }

    private void readAllData() {
        ArrayList<File> dataFileArray = validateData();
        if (dataFileArray != null) {
            for (File file : dataFileArray) {
                readData(file);
            }
        }
    }

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
//            for (ProcessedStation station : processedStationData) {
//                System.out.println(station);
//            }
            System.out.println(processedStationData.size());
        }
    }

    private void displayData() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("stationName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        maxTempColumn.setCellValueFactory(new PropertyValueFactory<>("maximumTemperature"));
        minTempColumn.setCellValueFactory(new PropertyValueFactory<>("minimumTemperature"));
        totalAirFrostColumn.setCellValueFactory(new PropertyValueFactory<>("airFrostDaySum"));
        totalRainfallColumn.setCellValueFactory(new PropertyValueFactory<>("rainfallSum"));
        dataTable.setItems(processedStationData);
    }

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

    public void getStatistics() {
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

    public void generateReport() {
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

    }

//    **************** End of Report Generator  ************************


//    **************** Tree View  ************************

    public void showTreeData() {
        if (!processedStationData.isEmpty()) {
            TreeItem<String> root = new TreeItem<>("Station");
            root.setExpanded(true);
            String tempName = processedStationData.get(0).getStationName();
            TreeItem<String> stationItem = new TreeItem<>(tempName);
            stationItem.setExpanded(true);
            root.getChildren().add(stationItem);
            for (ProcessedStation station : processedStationData) {
                if (!tempName.equals(station.getStationName())) {
                    tempName = station.getStationName();
                    stationItem = makeBranch(tempName, root);
                }
                makeBranch(Integer.toString(station.getYear()), stationItem);
            }
            dataTreeView = new TreeView<>(root);
//            dataTreeView.setShowRoot(false);
        }
    }

    public TreeItem<String> makeBranch(String itemName, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(itemName);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }


//    TODO modified the bottom label
//    TODO add content to the second tab
//    TODO layout
}
