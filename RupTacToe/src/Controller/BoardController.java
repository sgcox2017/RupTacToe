package Controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import Model.Board;
import Model.Player;
import java.awt.Color;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

public class BoardController implements Initializable, Serializable {
    
    private Board        board;
    private static Player player;
    private static Player player2;
    private Object[][]   boardGrid;
    private List<Node>   selected;
    private Object[][][] wordKey;
    private Object[]     targetKeys;
    private int          btnKey;
    private TranslateTransition tt; 
    private final int    spriteDuration = 9;
    private static BoardController bc;
    private boolean isSingleplayer;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane   gpBoard;

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    /**
     * The initialization logic for starting a single player game.
     */
        public void startEasyMode() {
        try {
            isSingleplayer = true;
            player = new Player(1);
            board = new Board(player);
            board.setup();
            setupGame();
            generateBoard();
            startGame();
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
        
        /**
     * The initialization logic for starting a single player game.
     */
        public void startMediumMode() {
        try {
            isSingleplayer = true;
            player = new Player(1);
            board = new Board(player);
            board.setup();
            setupGame();
            generateBoard();
            startGame();
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
        
       /**
     * The initialization logic for starting a single player game.
     */
        public void startHardMode() {
        try {
            isSingleplayer = true;
            player = new Player(1);
            board = new Board(player);
            board.setup();
            setupGame();
            generateBoard();
            startGame();
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
    
    /**
     * The initialization logic for starting a multiplayer game.
     * @throws java.io.IOException
     */
    public void startMultiplayer() throws IOException {
        isSingleplayer = false;
        player = new Player(1);
        player2 = new Player(2);
        board = new Board(player);
        board.setup();
        setupGame();
        generateBoard();
        startGame();
    }
    
    /**
     * Helper method to set up a new game.
     */
    private void setupGame() {
        bc = this;
    }
    
    /**
     * Generates the board.
     */
    @FXML
    private void generateBoard() throws IOException {
        // Load a 2D array to the grid
        boardGrid = board.getBoard();
        selected = new ArrayList<>();
        
        // Load the board into the grid pane as buttons
        for ( int r = 0; r < 3; r++ ) {
            for ( int c = 0; c < 3; c++ ) {
                String text = boardGrid[r][c].toString();
                Button temp = new Button(text);
                this.gpBoard.add(temp, c, r);
                temp.setBackground(Background.EMPTY);
                temp.setFont(Font.font(130.0));
                temp.setAlignment(Pos.CENTER);
                temp.setMinWidth(55);
                temp.setBorder(new Border(new BorderStroke(Paint.valueOf("#87CEEB"), 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                temp.setOnAction( this::handleSelected );
            }
        }      
    }
    
    /**
     * Starts the game.
     */
    @FXML
    private void startGame() throws IOException {
        
    }
    
    /**
     * This method handles when a letter button is selected.
     */
    @FXML
    private void handleSelected(ActionEvent event) {
        Button selectedButton = (Button)event.getSource();
        if( !selectedButton.isDisable()) {
            mark(selectedButton);
            selectedButton.setDisable(true);
        }
    }
    
    /**
     * This method implements the logic for selecting a word from the board.
     * @param button
     * @return 
     */
    private boolean validateClick(Node b) {
        return true;
    }
    
    /**
     * Gets the node located at the given coordinates.
     * @param row
     * @param col
     * @return
     * @throws IOException 
     */
    @FXML
    private Node getGridNode(int row, int col) throws IOException {
        Node returnNode = null;
        ObservableList<Node> children = this.gpBoard.getChildren();

        for ( Node node : children ) {
            Integer r = GridPane.getRowIndex(node);
            Integer c = GridPane.getColumnIndex(node);
            if ( r != null && c != null ) {
                if( r == row && c == col ) {
                    returnNode = node;
                    break;
                }
            }
        }

        return returnNode;
    }
    
    /**
     * Gets the row coordinate of the button.
     * @param button
     * @return 
     */
    private int getR(Node b) {
        return GridPane.getRowIndex(b);
    }
    
    /**
     * Gets the column coordinate of the button.
     * @param button
     * @return 
     */
    private int getC(Node b) {
        return GridPane.getColumnIndex(b);
    }
    
    /**
     * Logic executed when the game is over.
     */
    public void gameOver() {
        try {
            System.out.println("Game over!");
            this.showGameoverScene(null);
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
    
    /**
     * Resets the buttons and associated attributes after a selection.
     */
    private void resetSelection() {
        for ( Node b : selected ) {
            b.setStyle("X");
        }
        selected.clear();
    }
    
    /**
     * Method for changing the look of a button.
     */
    private void mark(Node b) {
        Button temp = (Button) b;
        if(player.isTurn()){
        temp.setText(player.getMarker());
        player.endTurn();
        player2.startTurn();
        }
        else{
            temp.setText(player2.getMarker());
            player2.endTurn();
            player.startTurn();
        }
        System.out.println("Marked");
        //temp.setDisable(true);
        temp.setStyle("-fx-background-color: magenta");
        CheckGameOver();
    }
    
    private void CheckGameOver(){
        
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void showGameoverScene(ActionEvent event) throws IOException { 
        Parent pane = FXMLLoader.load(getClass().getResource("/view/GameoverScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void showStartScene(ActionEvent event) throws IOException { 
        Parent pane = FXMLLoader.load(getClass().getResource("/view/StartScene.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}

