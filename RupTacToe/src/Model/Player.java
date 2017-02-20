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
        
        char tl = board[0][0];     // index 0
        char tc = board[0][1];     // index 1
        char tr = board[0][2];     // index 2
        char cl = board[1][0];     // index 3
        char cc = board[1][1];     // index 4
        char cr = board[1][2];     // index 5
        char bl = board[2][0];     // index 6
        char bc = board[2][1];     // index 7
        char br = board[2][2];     // index 8
        
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
        
        if("Hard".equals(Dificulty)){
            
            if(cc == 0 || cc == 'O'){                                           //First Move    4
                if(cc == 0){
                    index=4;
                }
                else{
                    if(bl == 0 || bl == 'O'){                                   //Second Move   6
                        if(bl == 0){
                            index=6;
                        }
                        else{
                            if(tr == 0){                                        //Third Move    2
                                index=2;
                            }
                            else if(br == 0 || br == 'O'){                      //Third Move    8
                                if(br == 0){
                                    index=8;
                                }
                                else{
                                    if(bc == 0){                                //Fourth Move   7
                                        index=7;
                                    }
                                    else if(tl == 0){                           //Fourth Move   0
                                        index=0;
                                    }
                                    else if(tc == 0 || tc == 'O'){              //Fourth Move   1 
                                        if(tc==0){
                                            index=1;
                                        }
                                        else{
                                            if(cr == 0){                        //Fifth Move    3 & 5
                                                index=5;
                                            }
                                            else{
                                                index=3;
                                            }
                                        }
                                    }
                                }
                            }
                            else if(tl == 0 || tl == 'O'){                      //Third Move    0
                                if(tl == 0){
                                    index=0;
                                }
                                else{
                                    if(cl == 0){                                //Fourth Move   3
                                        index=3;
                                    }
                                    else if(cr == 0 || cr == 'O'){              //Fourth Move   5
                                        if(cr == 0){
                                            index=5;
                                        }
                                        else{
                                            if(tc == 0){                        //Fifth Move    1 & 7
                                                index=1;
                                            }
                                            else{
                                                index=7;
                                            }
                                        }
                                    }
                                }
                            }
                            else if(tc == 0 || tc == 'O'){                      //Third Move    1
                                if(tc == 0){
                                    index=1;
                                }
                                else{
                                    if(bc == 0){                                //Fourth Move   7
                                        index=7;
                                    }
                                    else if(cr == 0){                           //Fourth Move   5 & 3
                                        index=5;
                                    }
                                    else{
                                        index=3;
                                    }
                                }
                            }
                        }
                    }
                    else if(tl == 0 || tl == 'O'){                              //Second Move   0
                        if(tl == 0){
                            index=0;
                        }
                        else{
                            if(br == 0){                                        //Third Move    8
                                index=8;
                            }
                            else if(bc == 0 || bc == 'O'){                      //Third Move    7
                                if(bc == 0){
                                    index=7;
                                }
                                else{
                                    if(tc == 0){                                //Fourth Move   1
                                        index=1;
                                    }
                                    else if(cr == 0 || cr == 'O'){              //Fourth Move   5
                                        if(cr == 0){
                                            index=5;
                                        }
                                        else{
                                            if(tr == 0){                        //Fifth Move    2 & 3
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
                    else if(cl == 0 || cl == 'O'){                              //Second Move   3
                        if(cl == 0){
                            index=3;
                        }
                        else{
                            if(cr == 0){                                        //Third Move   5
                                index=5;
                            }
                            else if(tc == 0 || tc =='O'){                       //Third Move   1
                                if(tc == 0){
                                    index=1;
                                }
                                else{
                                    if(bc ==0 ){                                //Fourth Move   7
                                        index=7;
                                    }
                                    else if(br == 0 || br == 'O'){              //Fourth Move   8
                                        if(br == 0){
                                            index=8;
                                        }
                                        else{
                                            if(tr == 0){                        //Fifth Move    2 & 6
                                                index=2;
                                            }
                                            else{
                                                index=6;
                                            }
                                        }
                                    }
                                }
                            }
                            else if(tr == 0 || tr == 'O'){                      //Third Move    2
                                if(tr == 0){
                                    index=2;
                                }
                                else{
                                    if(bc == 0){                                //Fourth Move   7 & 8
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
            
            else{                        
                if(tl == 0 || tl == 'O'){                                       //First Move    0
                    index=0;
                }
                else{
                    
                }
            }
        }
        
    return index;
    }
}
