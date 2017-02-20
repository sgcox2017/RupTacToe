/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Samuel
 */
public class Player {
    
    private final String Marker;
    private Boolean Turn;
    private static String Dificulty;
    
    public Player(int number){
        if(number == 1){
            Turn = true;
            Marker = "X";
        }
        else{
            Turn = false;
            Marker = "O";
        }
    }
    
    public void isSingleplayer(String dif){
        Dificulty = dif;
    }
    
    public void startTurn(){
        Turn = true;
    }
    public Boolean isTurn(){
        return Turn;
    }
    public void endTurn(){
        Turn = false;
    }
    
    public String getMarker(){
        return Marker;
    }
    
    public int pickSpace(char[][] board){ 
        
        int index = 0;
        
        char tl = board[0][0];
        char tc = board[0][1];
        char tr = board[0][2];
        char cl = board[1][0];
        char cc = board[1][1];
        char cr = board[1][2];
        char bl = board[2][0];
        char bc = board[2][1];
        char br = board[2][2];
        
        if("Easy".equals(Dificulty)){
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
        
        if("Medium".equals(Dificulty)){
            System.out.println();
        }
        
        return index;
    }
}
