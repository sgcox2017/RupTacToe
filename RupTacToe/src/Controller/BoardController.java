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
import java.util.Arrays;
import javafx.scene.AccessibleAction;
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
    
    private final char[][] psudoboard = new char[3][3];
    
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
            player.isSingleplayer("Easy");
            player2 = new Player(2);
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
            player.isSingleplayer("Medium");
            player2 = new Player(2);
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
            player.isSingleplayer("Hard");
            player2 = new Player(2);
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
        int id = 0;
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
                temp.setOnAction( this::handleSelected);
                temp.setId(Integer.toString(id));
                id++;
                selected.add(temp);
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
            selectedButton.setDisable(true);
            mark(selectedButton);
            CheckGameOver();
        }
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
     * Method for changing the look of a button.
     */
    private void mark(Node b) {
        Button temp = (Button) b;
        //System.out.println(player.getMarker());
        if(player.isTurn()){
            temp.setText(player.getMarker());
            psudoboardmark(temp.getId(), player.getMarker());
            player.endTurn();
            player2.startTurn();
            temp.setStyle("-fx-background-color: green");
            System.out.println("Marked p1 at " + temp.getId());
            if(isSingleplayer){
                //System.out.println("Got here");
                int place = player2.pickSpace(this.psudoboard);
                //System.out.println("Got here2");
                //System.out.println(this.gpBoard.getChildren().get(place).getId());
                Button here = (Button) this.gpBoard.getChildren().get(place);
                here.fire();
            }
        }
        else{
            temp.setText(player2.getMarker());
            psudoboardmark(temp.getId(), player2.getMarker());
            player2.endTurn();
            player.startTurn();
            temp.setStyle("-fx-background-color: magenta");
            System.out.println("Marked p2 at " + temp.getId());
        }
        //System.out.println("Marked");
    }
    
    private void psudoboardmark(String id, String Smark){
        int row = Integer.valueOf(id);
        int col = row%3;
        char mark = Smark.charAt(0);
        if(row<3){
            psudoboard[0][col] = mark;
            //System.out.println(row +" "+ col +" "+ mark);
        }
        else if(row<6){
            psudoboard[1][col] = mark;
            //System.out.println(row +" "+ col +" "+ mark);
        }
        else{
            psudoboard[2][col] = mark;
            //System.out.println(row +" "+ col +" "+ mark);
        }
    }
    
    private void CheckGameOver(){
        char a = psudoboard[0][0];
        char b = psudoboard[0][1];
        char c = psudoboard[0][2];
        char d = psudoboard[1][0];
        char e = psudoboard[1][1];
        char f = psudoboard[1][2];
        char g = psudoboard[2][0];
        char h = psudoboard[2][1];
        char i = psudoboard[2][2];
        char marker = 'X';
        int changes = 0;
        while(changes < 2){
            if(a == marker && b == marker && c == marker){
            gameOver();
            }
            if(d == marker && e == marker && f == marker){
            gameOver();
            }
            if(g == marker && h == marker && i == marker){
            gameOver();
            }
            if(a == marker && d == marker && g == marker){
            gameOver();
            }
            if(b == marker && e == marker && h == marker){
            gameOver();
            }
            if(c == marker && f == marker && i == marker){
            gameOver();
            }
            if(a == marker && e == marker && i == marker){
            gameOver();
            }
            if(c == marker && e == marker && g == marker){
            gameOver();
            }
            marker = 'O';
            changes++;
        }
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void showGameoverScene(ActionEvent event) throws IOException { 
        Parent pane = FXMLLoader.load(getClass().getResource("/view/EndGameScene.fxml"));
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

