package Model;

import java.io.IOException;

public class Board {
    
    /**
     * The boardSize is how big the board will be
     * The rowSize is how many rows
     * The colSize is how many columns
     * The board represents the board
     * The empty represents an empty board space
     */
    private static final int boardSize = 3;
    private final int        rowSize;
    private final int        colSize;
    private char[][]         board;
    private final char       empty = ' ';
    
    /**
     * Construcotr which initializes the row size, column size and board
     * @throws IOException 
     */
    public Board() throws IOException {
        this.rowSize = Board.getBoardSize();
        this.colSize = Board.getBoardSize();
        this.board = new char[getRowSize()][getColSize()];
    }
    
    /**
     * Returns the board size
     * @return 
     */
    private static int getBoardSize() {
        return boardSize;
    }
    
    /**
     * Creates an empty board
     */
    public void createBoard() {
        for(int r = 0; r < getRowSize(); ++r) {
            for(int c = 0; c < getColSize(); ++c) {
                board[r][c] = empty;
            }
        }
    }
    
    /**
     * Returns the row size
     * @return 
     */
    public int getRowSize() {
        return rowSize;
    }
    
    /**
     * Returns the column size
     * @return 
     */
    public int getColSize() {
        return colSize;
    }
    
    /**
     * Returns the board
     * @return 
     */
    public char[][] getBoard() {
        return board;
    }
    
}
