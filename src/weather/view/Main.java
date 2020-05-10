package weather.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Main. Entrance of the GUI
 *
 * @author Tian Z
 */
public class Main extends Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
        primaryStage.setTitle("CMT205 Weather Statistics Viewer");
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(getClass().getResource("material-fx.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
