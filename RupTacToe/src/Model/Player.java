/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.lang.reflect.Array;

/**
 *
 * @author Samuel
 */
public class Player {
    
    private String Marker;
    private Boolean Turn;
    private static String Dificulty;
    private Boolean SinglePlayer;
    
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
        SinglePlayer = true;
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
        int row = 0;
        int col = 0;
        //System.out.println(Dificulty);
        if(Dificulty == "Easy"){
            //System.out.println("check");
            //System.out.println(board[row][col]);
            while(board[row][col] == 'X' || board[row][col] == 'O'){
                System.out.println(board[row][col]);
                if(col!=2){
                    col++;
                }
                else{
                    row++;
                }
                index++;
            }
        }
        //System.out.println(index);
        return index;
    }
}
