package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;

/**
 * Displays the fxml view.
 */
public class MyApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(MyApp.class.getResource("/exchange_scene.fxml"));
        stage.setTitle("Exchanger");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
        Logger.info("A javafx elindult");


    }


}
