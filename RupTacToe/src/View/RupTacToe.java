package View;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//The main class which creates the scene and starts the program
public class RupTacToe extends Application {
    
    //The width and height of the scene and the scene itself
    private final int width  = 1000;
    private final int height = 700;
    private static Scene scene;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       
        //Creates the scene
        Parent startLayout = FXMLLoader.load(getClass().getResource("TitleScene.fxml"));
        StackPane startScene = new StackPane(startLayout);
        scene = new Scene(startScene);
        
        //Sets the sizing and title of the scene and shows it
        primaryStage.setScene(scene);  
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setResizable(false);
        primaryStage.setTitle("SCRAM Tic-Tac-Toe");
        primaryStage.show();
        
    } 

    public static void main(String[] args) {
        launch(args);
    }
    
    public static Scene getScene() {
        return scene;
    }
}
