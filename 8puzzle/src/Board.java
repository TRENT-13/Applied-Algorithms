import java.util.Stack;
import java.util.Arrays;

public class Board {
    private int[][] board;
    private int dimension;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        dimension = tiles.length;
        board = copyBoard(tiles, dimension);
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension).append("\n");

        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                s.append(board[x][y] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                if((board[i][j] != 0) && board[i][j] != index(i,j) ) count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum =0;
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                if((board[x][y] != 0) && (board[x][y] != index(x,y)))  {
                    int curr_row = (board[x][y] -1 ) / dimension;
                    int curr_col = (board[x][y] -1 ) % dimension;

                    sum += Math.abs((x-curr_row))+Math.abs(y-curr_col);
                }
            }
        }
        return sum;

    }


    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }

        if (y == this) {
            return true;
        }

        if (y.getClass() != this.getClass()) {
            return false;
        }

        Board that = (Board) y;

        return Arrays.deepEquals(this.board, that.board);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> boardStack = new Stack<>();
        int blankRow = -1, blankCol = -1;

        // Find the blank tile (0)
        outerLoop:
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (board[i][j] == 0) {
                    blankRow = i;
                    blankCol = j;
                    break outerLoop;
                }
            }
        }

        // Possible moves: up, down, left, right
        int[][] directions = {
                { -1, 0 }, // up
                { 1, 0 },  // down
                { 0, -1 }, // left
                { 0, 1 }   // right
        };

        for (int[] direction : directions) {
            int newRow = blankRow + direction[0];
            int newCol = blankCol + direction[1];

            if (isValidMove(newRow, newCol)) {
                int[][] blocksCopy = copyBoard(board, dimension);
                blocksCopy[blankRow][blankCol] = board[newRow][newCol];
                blocksCopy[newRow][newCol] = 0;
                boardStack.push(new Board(blocksCopy));
            }
        }

        return boardStack;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < dimension && col >= 0 && col < dimension;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] blocksCopy = copyBoard(board, dimension);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension - 1; j++) {

                if ((blocksCopy[i][j] != 0) && (blocksCopy[i][j + 1] != 0)) {
                    int toSwap = blocksCopy[i][j];
                    blocksCopy[i][j] =  blocksCopy[i][j + 1];
                    blocksCopy[i][j + 1] = toSwap;

                    return new Board(blocksCopy);
                }
            }
        }

        return null;
    }

     private int[][] copyBoard(int[][] board, int dimension) {
        int[][] res = new int[dimension][dimension];
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                res[i][j] = board[i][j];
            }
        }
        return res;
     }

     private int index(int x, int y) {
        return  (x*dimension) + y +1;
     }

     private int[] blanckFinder() {
         int blankRow = -1;
         int blankCol = -1;

         for (int row = 0; row < dimension; row++) {
             for (int col = 0; col < dimension; col++) {
                 if (board[row][col] == 0) {
                     blankRow = row;
                     blankCol = col;
                     break;  // Exit the loop once the blank space is found
                 }
             }
             if (blankRow != -1) {
                 break;  // Exit the outer loop if the blank space is found
             }
         }

         return new int[]{blankRow, blankCol};
     }

    private int[][] copyTiles() {
        int[][] newTiles = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                newTiles[i][j] = board[i][j];
            }
        }
        return newTiles;
    }

    // unit testing (not graded)
    public static void main(String[] args) {





    }

}