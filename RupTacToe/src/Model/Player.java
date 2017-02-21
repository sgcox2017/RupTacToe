
package Model;

import java.util.Random;


//The Player class holds the basic information of the two players in the game
//as well as the CPU logic
public class Player {
    
    /**
     * The name is the name of the player
     * The marker is either X or O
     * The turn is whether or not it is their turn
     * The difficulty is for the AI
     * The p1 is for whether or not it is the first player being created
     */
    private String        name;
    private final char    marker;
    private Boolean       turn;
    private static String difficulty;
    private final int     p1 = 1;
    private final char    empty = ' ';
    
    /**
     * Constructor for Player which sets the turn and marker for a player
     * @param number 
     */
    public Player(int number){
        if(number == p1){
            turn = true;
            marker = 'X';
        }
        else{
            turn = false;
            marker = 'O';
        }
    }
    
    /**
     * Sets the player's name
     * @param n 
     */
    public void setPlayerName(String n) {
        name = n;
    }
    
    /**
     * Retrieves the player's name
     * @return 
     */
    public String getPlayerName() {
        return name;
    }
    
    /**
     * Sets the difficulty of the CPU
     * @param diff 
     */
    public void setDifficulty(String diff){
        difficulty = diff;
    }
    
    /**
     * Switches the turn of the player
     */
    public void switchTurn(){
        if(turn.equals(true)) {
            turn = false;
        } else {
            turn = true;
        }
    }
    
    /**
     * Returns whether or not it is the player's turn
     * @return 
     */
    public Boolean isTurn(){
        return turn;
    }
    
    /**
     * Returns the X or O of the player
     * @return 
     */
    public char getMarker(){
        return marker;
    }
    
    //CPU logic to be addressed in the second iteration
    public int pickSpace(char[][] board){
        int index = 0;
        
        char tl = board[0][0];     // index 0
        char tc = board[0][1];     // index 1
        char tr = board[0][2];     // index 2
        char ml = board[1][0];     // index 3
        char mc = board[1][1];     // index 4
        char mr = board[1][2];     // index 5
        char bl = board[2][0];     // index 6
        char bc = board[2][1];     // index 7
        char br = board[2][2];     // index 8
        
        if("Easy".equals(difficulty)){
            int row = 0;
            int col = 0;
            while(board[row][col] == 'X' || board[row][col] == 'O'){
                index++;
                if(col!=2){
                    col++;
                }
                else if(row!=2){
                    row++;
                    col=0;
                }
            }
        }
        
        if("Medium".equals(difficulty)){
            int row = 1;
            int col = 1;
            index = 4;
            while(board[row][col] == 'X' || board[row][col] == 'O'){
                Random rand = new Random();
                index = rand.nextInt(8);
                col=index%3;
                if(index<3){
                    row = 0;
                }
                else if(index>5){
                    row = 2;
                }
                else{
                    row = 1;
                }
            }
        }
        
        if("Hard".equals(difficulty)){
            if(mc == empty || mc == 'O'){                                           //First Move    4
                if(mc == empty){
                    index=4;
                }
                else{
                    if(bl == empty || bl == 'O'){                                   //Second Move   6
                        if(bl == empty){
                            index=6;
                        }
                        else{
                            if(tr == empty){                                        //Third Move    2
                                index=2;
                            }
                            else if(br == empty || br == 'O'){                      //Third Move    8
                                if(br == empty){
                                    index=8;
                                }
                                else{
                                    if(bc == empty){                                //Fourth Move   7
                                        index=7;
                                    }
                                    else if(tl == empty){                           //Fourth Move   0
                                        index=0;
                                    }
                                    else if(tc == empty || tc == 'O'){              //Fourth Move   1 
                                        if(tc==empty){
                                            index=1;
                                        }
                                        else{
                                            if(mr == empty){                        //Fifth Move    3 & 5
                                                index=5;
                                            }
                                            else{
                                                index=3;
                                            }
                                        }
                                    }
                                }
                            }
                            else if(tl == empty || tl == 'O'){                      //Third Move    0
                                if(tl == empty){
                                    index=0;
                                }
                                else{
                                    if(ml == empty){                                //Fourth Move   3
                                        index=3;
                                    }
                                    else if(mr == empty || mr == 'O'){              //Fourth Move   5
                                        if(mr == empty){
                                            index=5;
                                        }
                                        else{
                                            if(tc == empty){                        //Fifth Move    1 & 7
                                                index=1;
                                            }
                                            else{
                                                index=7;
                                            }
                                        }
                                    }
                                }
                            }
                            else if(tc == empty || tc == 'O'){                      //Third Move    1
                                if(tc == empty){
                                    index=1;
                                }
                                else{
                                    if(bc == empty){                                //Fourth Move   7
                                        index=7;
                                    }
                                    else if(mr == empty){                           //Fourth Move   5 & 3
                                        index=5;
                                    }
                                    else{
                                        index=3;
                                    }
                                }
                            }
                        }
                    }
                    else if(tl == empty || tl == 'O'){                              //Second Move   0
                        if(tl == empty){
                            index=0;
                        }
                        else{
                            if(br == empty){                                        //Third Move    8
                                index=8;
                            }
                            else if(bc == empty || bc == 'O'){                      //Third Move    7
                                if(bc == empty){
                                    index=7;
                                }
                                else{
                                    if(tc == empty){                                //Fourth Move   1
                                        index=1;
                                    }
                                    else if(mr == empty || mr == 'O'){              //Fourth Move   5
                                        if(mr == empty){
                                            index=5;
                                        }
                                        else{
                                            if(tr == empty){                        //Fifth Move    2 & 3
                                                index=2;
                                            }
                                            else{
                                                index=3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if(ml == empty || ml == 'O'){                              //Second Move   3
                        if(ml == empty){
                            index=3;
                        }
                        else{
                            if(mr == empty){                                        //Third Move   5
                                index=5;
                            }
                            else if(tc == empty || tc =='O'){                       //Third Move   1
                                if(tc == empty){
                                    index=1;
                                }
                                else{
                                    if(bc ==empty ){                                //Fourth Move   7
                                        index=7;
                                    }
                                    else if(br == empty || br == 'O'){              //Fourth Move   8
                                        if(br == empty){
                                            index=8;
                                        }
                                        else{
                                            if(tr == empty){                        //Fifth Move    2 & 6
                                                index=2;
                                            }
                                            else{
                                                index=6;
                                            }
                                        }
                                    }
                                }
                            }
                            else if(tr == empty || tr == 'O'){                      //Third Move    2
                                if(tr == empty){
                                    index=2;
                                }
                                else{
                                    if(bc == empty){                                //Fourth Move   7 & 8
                                        index=7;
                                    }
                                    else{
                                        index=8;
                                    }
                                }
                            }
                        }
                    }
                }
            }            
            else {                        
                if(tl == empty){                                                    //First Move    0
                    index=0;
                }
                else{
                    if(br == empty || br == 'O'){                                   //Second Move   8  
                        if(br == empty){
                            index=8;
                        }
                        else if(tr == empty || tr == 'O'){                          //Third Move    2
                            if(tr == empty){
                                index=2;
                            }
                            else if(tc == empty){                                   //Fourth Move   1 & 5
                                index=1;
                            }
                            else if(mr == empty){
                                index=5;
                            }
                            else{
                                if(ml == empty || ml == 'O'){                       //Fourth Move   3
                                    if(ml == empty){
                                        index=3;
                                    }
                                    else if(bc == empty){                           //Fourth Move   7
                                        index=7;
                                    }
                                    else{
                                        index=6;                                    //Fourth Move   6
                                    }
                                }
                            }
                        }
                        else{
                            if(ml == empty || ml == 'O'){                           //Third Move   3
                                    if(ml == empty){
                                        index=3;
                                    }
                                    else if(bc == empty){                           //Fourth Move   7
                                        index=7;
                                    }
                                    else{
                                        index=6;                                    //Fourth Move   6
                                    }
                            }
                        }
                    }
                    else{
                        if(tr == empty || tr == 'O'){                               //Second Move   2
                            if(tr == empty){
                                index=2;
                            }
                            else{
                                if(tc == empty){                                    //Third Move    1
                                    index=1;
                                }
                                else if(bc == empty || bc == 'O'){                  //Third Move    7
                                    if(bc == empty){
                                        index=7;
                                    }
                                    else{
                                        if(bl == empty || br == empty){             //Fourth Move   6 & 8
                                            if(bl == empty){
                                                index=6;
                                            }
                                            else{                                   
                                                index=8;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else if(tl == empty || tl == 'O'){                          //Second Move   2
                            if(tl == empty){
                                index=0;
                            }
                            else{
                                if(tc == empty){                                    //Third Move    1
                                    index=1;
                                }
                                else if(bc == empty || bc == 'O'){                  //Third Move    7
                                    if(bc == empty){
                                        index=7;
                                    }
                                    else{
                                        if(br == empty || tr == empty){              //Fourth Move   6 & 8
                                            if(bl == empty){
                                                index=6;
                                            }
                                            else{                                   
                                                index=8;
                                            }
                                        }
                                    }
                                }
                            }
                        }    
                    }
                }
            }
        }
        
    return index;
    }
}
