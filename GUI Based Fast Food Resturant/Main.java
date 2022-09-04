package project4;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * This is the driver class to run project 4.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage)  {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));
            Scene scene = new Scene(root,1250,900);
            primaryStage.setTitle("Order");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
