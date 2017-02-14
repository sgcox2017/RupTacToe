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
        System.out.println("Starting an Easy Mode Game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        bc.startEasyMode();
    }
    
    /**
     * Starts a medium difficulty game against AI
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnMediumMode(ActionEvent event) throws IOException {
        System.out.println("Starting a Medium Mode Game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        bc.startMediumMode();
    }
    
    /**
     * Starts a hard difficulty game against AI
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnHardMode(ActionEvent event) throws IOException {
        System.out.println("Starting a Hard Mode Game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        bc.startHardMode();
    }
    
    /**
     * Starts a multiplayer game.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void handleBtnMultiplayerMode(ActionEvent event) throws IOException {
        System.out.println("Starting a Local Multiplayer Game!");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BoardScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);

        BoardController bc = loader.<BoardController>getController();
        bc.startMultiplayer();
    }
}
