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
    
    String Marker;
    Boolean Turn;
    
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
}
