package Model;

import java.io.IOException;

public class Board {
    
    private static final int boardSize = 3;
    private final int rSize;
    private final int cSize;
    private final Player player;
    private Object[][] board;
    
    public Board(Player p) throws IOException {
        this.rSize      = Board.getBoardSize();
        this.cSize      = this.getBoardSize();
        this.player     = p;
    }
    
    private static int getBoardSize() {
        return boardSize;
    }
    
    public void createBoard() {
        for(int r = 0; r < Board.getBoardSize(); ++r) {
            for(int c = 0; c < cSize; ++c) {
                board[r][c] = 'O';
            }
        }
    }
    
    public void setup() throws IOException {
        this.board      = new Character[getrSize()][getcSize()];
        createBoard();
    }
    
    public int getrSize() {
        return rSize;
    }
    
    public int getcSize() {
        return cSize;
    }
    
    public Object[][] getBoard() {
        return board;
    }
    
}
