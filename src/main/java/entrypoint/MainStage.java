package entrypoint;

import appinstance.AppInstanceSaver;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main_view.fxml"));
        primaryStage.setTitle("KCALculator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void init() {
        AppInstanceSaver instance = AppInstanceSaver.instance();
        instance.loadInstance();
    }

    public void stop() {
        AppInstanceSaver instance = AppInstanceSaver.instance();
        instance.saveInstance();
    }
}
