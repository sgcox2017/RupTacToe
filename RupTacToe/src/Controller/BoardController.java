package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import Model.Board;
import Model.Player;
import View.RupTacToe;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

public class BoardController implements Initializable {
    
    /**
     * The board holds the instance of a board
     * The gameBoard holds the board being played on
     */
    private static Board    board;
    private static char[][] gameBoard;
    
    /**
     * Player 1 is the user and player 2 is another local user or the CPU
     * P1 represents player 1 (X) and p2 represents player 2 (O)
     */
    private static Player   player;
    private static Player   player2;
    private final int       p1 = 1;
    private final int       p2 = 2;
    
    /**
     * isSinglePlayer holds the value of whether or not player 1 is playing against the AI
     * cpuName and cpuNum hold the name and marker (O) for the CPU
     */
    private boolean         isSinglePlayer;
    private final String    cpuName = "The CPU";
    private final int       cpuNum = 2;
    
    /**
     * dialog is for text input dialogs
     * fontSize holds the fontSize of the X's and O's
     * buttonSize holds the size of the squares
     */
    private TextInputDialog dialog;
    private final int       fontSize = 80;
    private final int       buttonSize = 175;
    
    /**
     * Turn is used for adjust the value of who's turn it is on screen
     * moves keeps track of how many moves are played for recognizing a draw
     */
    private Label           turn;
    private int             moves;
    
    /**
     * isWon holds the value of whether or not a win has occurred
     * winner holds the name of the winner if one wins
     * isDraw holds the value of whether or not a draw has occurred
     * fullBoard holds the value of how many total moves there are before a full board
     */
    private static boolean  isWon;
    private static String   winner;
    private static boolean  isDraw;
    private final int       fullBoard = 9;
      
    @FXML
    private AnchorPane rootPane;
    @FXML
    private GridPane   gpBoard;

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    /**
     * The initialization logic for starting a single player game.
     * @param difficulty
     */
        public void startCPU(String difficulty) {
        try {
            player = setupPlayer(p1);
            player2 = setupCPU();
            
            isSinglePlayer = true;
            player2.setDifficulty(difficulty);
            
            board = new Board();
            board.createBoard();
            gameBoard = board.getBoard();
            
            isWon = false;
            isDraw = false;
            moves = 0;
            turnName();
            generateBoard();
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
    
    /**
     * The initialization logic for starting a multiplayer game.
     * @throws java.io.IOException
     */
    public void startMultiplayer() throws IOException {
        isSinglePlayer = false;
        
        player = setupPlayer(p1);
        player2 = setupPlayer(p2);
        checkNames(player, player2);
        
        board = new Board();
        board.createBoard();
        gameBoard = board.getBoard();
        
        isWon = false;
        isDraw = false;
        moves = 0;
        turnName();
        generateBoard();
    }
    
    /**
     * Creates an instance of a player and acquires their name
     * @param typePlayer
     * @return 
     */
    private Player setupPlayer(int typePlayer) {        
        Player tempPlayer = new Player(typePlayer);
        tempPlayer.setPlayerName(inputPlayerName());
        
        return tempPlayer;
    }
    
    /**
     * Creates an instance of a CPU player and sets their name
     * @return 
     */
    private Player setupCPU() {
        Player cpu = new Player(cpuNum);
        cpu.setPlayerName(cpuName);
        
        return cpu;
    }
    
    /**
     * Gets the input of the players name and does not accept an empty string
     * @return 
     */
    private String inputPlayerName() {
        dialog = new TextInputDialog(null);
        dialog.setTitle("Names");
        dialog.setHeaderText("Enter Player's Name");
        String playerName = "";
        while(String.valueOf(playerName).equals("")) {
            Optional<String> result = dialog.showAndWait();
            String entered = null;
            if (result.isPresent()) {
                entered = result.get();
                playerName = entered;
            }
        }
        return playerName;
    }
    
    /**
     * Checks to see if player 1 and player 2 have the same name. If so, player 2 must change their name
     * @param p1
     * @param p2 
     */
    private void checkNames(Player p1, Player p2) {
        while(p1.getPlayerName().equals(p2.getPlayerName())){
            nameAlert();
            p2.setPlayerName(inputPlayerName());
        }
    }
    
    /**
     * Alerts the players they have entered the same name
     */
    private void nameAlert() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Problem!");
        alert.setHeaderText("You've entered the same name as Player 1.");
        alert.setContentText("Please enter a different name.");
        alert.showAndWait();
    }
    
    /**
     * Generates the board.
     */
    @FXML
    private void generateBoard() throws IOException {
        int id = 0;
        for ( int r = 0; r < 3; r++ ) {
            for ( int c = 0; c < 3; c++ ) {
                String buttonText = String.valueOf(gameBoard[r][c]);
                Button buttonHolder = new Button(buttonText);
                this.gpBoard.add(buttonHolder, r, c);
                setupButton(buttonHolder, id);
                id++;
            }
        }      
    }
    
    /**
     * Sets up the styling of the buttons
     * @param setup
     * @param id 
     */
    private void setupButton(Button setup, int id) {
        setup.setFont(Font.font(fontSize));
        setup.setTextFill(Paint.valueOf("WHITE"));
        setup.setAlignment(Pos.CENTER);
        setup.setMinSize(buttonSize, buttonSize);
        setup.setMaxSize(buttonSize, buttonSize);
        setup.setBorder(new Border(new BorderStroke(Paint.valueOf("#87CEEB"),
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setup.setOnAction(this::handleSelected);
        setup.setDisable(false);
        setup.setId(Integer.toString(id));
    }
    
    /**
     * Method that handles what occurs when a button is selected
     * @param event 
     */
    @FXML
    private void handleSelected(ActionEvent event) {
        Button selectedButton = (Button)event.getSource();
        if( !selectedButton.isDisable()) {
            if(isWon == false && isDraw == false) {
                selectedButton.setDisable(true);
                markViewBoard(selectedButton);
            }
        }
    }

    /**
     * Method for marking when a button has been selected
     * @param b 
     */
    private void markViewBoard(Node b) {
        Button selected = (Button) b;
        if(player.isTurn()){
            selected.setText(String.valueOf(player.getMarker()));
            selected.setStyle("-fx-background-color: #87CEEB");
            markGameBoard(selected.getId(), player.getMarker());
            ++moves;
            player.switchTurn();
            player2.switchTurn();
            turnName();
            
            if(isSinglePlayer == true) {
                if(checkDraw() == true || checkWin() == true) {
                    
                } else {
                    int place = player2.pickSpace(gameBoard);
                    Button here = (Button) this.gpBoard.getChildren().get(place);
                    here.fire();
                }
            }
        }
        else{
            selected.setText(String.valueOf(player2.getMarker()));
            selected.setStyle("-fx-background-color: pink"); 
            markGameBoard(selected.getId(), player2.getMarker());
            ++moves;
            player2.switchTurn();
            player.switchTurn();
            turnName();
        }
       if(checkDraw() == true || checkWin() == true) {
            gameOver();
        }
    }
    
    /**
     * Marks the hidden gameBoard based on the mark of the visual board
     * @param id
     * @param mark 
     */
    private void markGameBoard(String id, char mark){
        int row = Integer.valueOf(id);
        int col = row%3;
        if(row<3){
            gameBoard[0][col] = mark;
        }
        else if(row<6){
            gameBoard[1][col] = mark;
        }
        else{
            gameBoard[2][col] = mark;
        }
    }
    
    /**
     * Returns the hidden gameBoard
     * @return 
     */
    public static char[][] getGameBoard() {
        return gameBoard;
    }
    
    /**
     * Checks whose turn it is and adjusts the label on accordingly
     */
    @FXML
    private void turnName() {
        turn = (Label) RupTacToe.getScene().lookup("#playersTurn");
        if(isSinglePlayer == false) {
            turn.setVisible(true);
            if(player.isTurn().equals(true)) {
                turn.setText(player.getPlayerName() + "'s Turn");
            } else {
                turn.setText(player2.getPlayerName() + "'s Turn");
            }
        }
    }
    
    /**
     * Checks for a three in a row and ends the game if one is found
     */
    private boolean checkWin(){
        char tl = gameBoard[0][0];
        char tc = gameBoard[0][1];
        char tr = gameBoard[0][2];
        char ml = gameBoard[1][0];
        char mc = gameBoard[1][1];
        char mr = gameBoard[1][2];
        char bl = gameBoard[2][0];
        char bc = gameBoard[2][1];
        char br = gameBoard[2][2];
        
        isWon = false;
        
        if (tl == tc && tc == tr && tl == tr && tl != ' ') {
            isWon = true;
            determineWinner();
        }
        else if (ml == mc && mc == mr && ml == mr && ml != ' ') {
            isWon = true;
            determineWinner();
        }
        else if (bl == bc && bc == br && bl == br && bl != ' ') {
            isWon = true;
            determineWinner();
        }
        else if (tl == ml && ml == bl && tl == bl && tl != ' ') {
            isWon = true;
            determineWinner();
        }
        else if (tc == mc && mc == bc && tc == bc && tc != ' ') {
            isWon = true;
            determineWinner();
        }
        else if (tr == mr && mr == br && tr == br && tr != ' ') {
            isWon = true;
            determineWinner();
        }
        else if (tl == mc && mc == br && tl == br && tl != ' ') {
            isWon = true;
            determineWinner();
        }
        else if (tr == mc && mc == bl && tr == bl && tr != ' ') {
            isWon = true;
            determineWinner();
        }
        
        return isWon;
    }
    
    /**
     * Checks to see if a draw has occurred and if so ends the game
     */
    private boolean checkDraw() {
        if(moves == fullBoard) {
            if(checkWin() == false) {
                isDraw = true;
            }
        }
        
        return isDraw;
    }
    
    /**
     * Logic executed when the game is over.
     */
    public void gameOver() {
        try {
            this.showGameoverScene(null);
        } catch (IOException ex) {
            System.out.println("Unexpected Exception: " + ex.getMessage());
        }
    }
    
    /**
     * Returns the value of the isDraw variable
     * @return 
     */
    public static boolean getDraw() {
        return isDraw;
    }
    
    /**
     * In the event of a win, checks to see who the winner was and assigns it to the winner variable
     */
    private void determineWinner() {
        if(!player.isTurn()) {
            setWinner(player.getPlayerName());
        } else {
            setWinner(player2.getPlayerName());
        }
    }
    
    /**
     * Returns the name of the winner of the match
     * @return 
     */
    public static String getWinner() {
        return winner;
    }

    /**
     * Sets the name of the winner of the match
     * @param w 
     */
    private void setWinner(String w) {
        winner = w;
    }
    
    /**
     * Shows the Game Over Scene.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void showGameoverScene(ActionEvent event) throws IOException { 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EndGameScene.fxml"));
        Parent pane = (Parent)loader.load();
        rootPane.getChildren().setAll(pane);
    }
    
}

