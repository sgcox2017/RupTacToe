package Controller;

import View.RupTacToe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * FXML Controller
 * The end scene for the user. The user sees who wins and decides to exit or 
 * play a new game
 */
public class EndGameController implements Initializable {
    
    /**
     * The fontSize holds the size of the font for the buttons
     * The buttonSize holds the value of how large the buttons are
     * The result alerts who was the winner/loser or if there was a draw
     * The endBoard holds the value of the board just played
     */
    private final int fontSize = 55;
    private final int buttonSize = 125;
    private Label result;
    private char[][] endBoard;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane finalBoard;
    
    /**
     * Assigns the value of the endBoard then generates the final board and 
     * shows the results of the game
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        endBoard = BoardController.getGameBoard();
        generateFinalBoard();
        showResult();
    }    
    
    /**
     * Recreates the board from the game just played
     */
    public void generateFinalBoard() {;
        for ( int r = 0; r < 3; r++ ) {
            for ( int c = 0; c < 3; c++ ) {
                String text = String.valueOf(endBoard[r][c]);
                Button buttonHolder = new Button(text);
                this.finalBoard.add(buttonHolder, r, c);
                setupButton(buttonHolder);
            }
        }  
    }
    
    /**
     * Sets up the styling of the button and disables them
     * @param setup 
     */
    private void setupButton(Button setup) {
        setup.setFont(Font.font(fontSize));
        setup.setTextFill(Paint.valueOf("BLACK"));
        setup.setAlignment(Pos.CENTER);
        setup.setMinSize(buttonSize, buttonSize);
        setup.setMaxSize(buttonSize, buttonSize);
        setup.setBorder(new Border(new BorderStroke(Paint.valueOf("#87CEEB"),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setup.setDisable(true);
    }
    
    /**
     * Creates an alert box and announces the results of the game and what to do next
     */
    private void showResult() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Results");
        if(BoardController.getDraw() == true){
            alert.setHeaderText("Draw!");
            alert.setContentText("Please start a new game or quit the application");
        } else {
            alert.setHeaderText(BoardController.getWinner() + " has won!");
            alert.setContentText("Please start a new game or quit the application");
        }
        alert.showAndWait();
    }
    
    /**
     * Upon clicking, the user is taken back to the start menu to start up a new game
     * @param event
     * @throws IOException 
     */
    @FXML
    public void handleNewGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TitleScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);
    }
    
    /**
     * Upon clicking, the user exits the application
     * @param event 
     */
    @FXML
    public void handleQuit(ActionEvent event) {
        System.exit(0);
    }
}
