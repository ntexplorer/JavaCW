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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    private File selectedDirectory;

    @FXML
    private Button selectFolderBtn;

    @FXML
    private Label directoryChosen;

    @FXML
    private Button importDataBtn;

    @FXML
    private Button testBtn;

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
//        testBtn.setOnAction(e -> dataProcess());
    }

    private void importData() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose the resource directory...");
        directoryChooser.setInitialDirectory(new File("resources/"));
        selectedDirectory = directoryChooser.showDialog(selectFolderBtn.getScene().getWindow());
        directoryChosen.setText("Directory Chosen: " + selectedDirectory.getAbsolutePath());
    }

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
        if (fileArr.length > 0) {
            for (int i = 0; i < Objects.requireNonNull(fileArr).length; i++) {
                File file = fileArr[i];
                if (file.getAbsolutePath().endsWith(".csv")) {
                    dataFiles.add(file);
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Fail To Import Data");
                    alert.setContentText("Invalid file imported, please choose the correct directory!");
                    alert.showAndWait();
                    return null;
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Data File Found");
            alert.setContentText("Empty directory selected, please try again.");
            alert.showAndWait();
            return null;
        }
        return dataFiles;
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
            ProcessedStation tempProcessedStation = new ProcessedStation(tempName, tempYear);
            for (int i = 0; i < stationData.size(); i++) {
                if ((stationData.get(i).getStationName().equals(tempName)) && (stationData.get(i).getYear() == tempYear)) {
                    tempProcessedStation.addMaxTemp(stationData.get(i).maximumTemperatureProperty());
                    tempProcessedStation.addMinTemp(stationData.get(i).minimumTemperatureProperty());
                    tempProcessedStation.addAirFrostDay(stationData.get(i).airFrostDayNumProperty());
                    tempProcessedStation.addRainfall(stationData.get(i).rainfallProperty());
                } else {
                    tempProcessedStation.setMaximumTemperature();
                    tempProcessedStation.setMinimumTemperature();
                    tempProcessedStation.setAirFrostDaySum();
                    tempProcessedStation.setRainfallSum();
                    processedStationData.add(tempProcessedStation);
                    tempName = stationData.get(i).getStationName();
                    tempYear = stationData.get(i).getYear();
                    tempProcessedStation = new ProcessedStation(tempName, tempYear);
                    tempProcessedStation.setStationName(stationData.get(i).getStationName());
                    tempProcessedStation.setYear(stationData.get(i).getYear());
                    tempProcessedStation.addMaxTemp(stationData.get(i).maximumTemperatureProperty());
                    tempProcessedStation.addMinTemp(stationData.get(i).minimumTemperatureProperty());
                    tempProcessedStation.addAirFrostDay(stationData.get(i).airFrostDayNumProperty());
                    tempProcessedStation.addRainfall(stationData.get(i).rainfallProperty());
                }
            }
//            for (ProcessedStation station : processedStationData) {
//                System.out.println(station);
//            }
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


}
