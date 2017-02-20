package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller
 * The initial scene for the user. The user decided which of the game modes to play
 */
public class StartController implements Initializable {
    
    //Holds the value of the difficulty of the CPU
    private String difficulty;
    
    @FXML
    private AnchorPane rootPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
    
    /**
     * Starts an easy difficulty game against AI
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnEasyMode(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        difficulty = "Easy";
        bc.startCPU(difficulty);
    }
    
    /**
     * Starts a medium difficulty game against AI
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnMediumMode(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        difficulty = "Medium";
        bc.startCPU(difficulty);
    }
    
    /**
     * Starts a hard difficulty game against AI
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnHardMode(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        difficulty = "Hard";
        bc.startCPU(difficulty);
    }
    
    /**
     * Starts a multiplayer game.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnMultiplayerMode(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);
        BoardController bc = loader.<BoardController>getController();
        bc.startMultiplayer();
    }
}
