package SWProject;

import SWProject.View.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        ViewController viewer = new ViewController();
        viewer.getLoginView(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
