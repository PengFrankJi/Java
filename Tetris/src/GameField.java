import java.util.Random;

public class GameField {
   private int rows;
   private int cols;
   private int[][] field;
   private Tetromino tetromino;
   private Tetromino nextTetromino;
   private Random random;
   private int score;
   private int line;
   private boolean gameOver;

   private static final int STATUS_EMPTY = 0;
   private static final int STATUS_PARTIAL_FULL = 1;
   private static final int STATUS_FULL = 2;
   private static final int NUMBER_OF_KIND = 7;
   private static final int CODE_OF_I = 0;
   private static final int CODE_OF_J = 1;
   private static final int CODE_OF_L = 2;
   private static final int CODE_OF_O = 3;
   private static final int CODE_OF_S = 4;
   private static final int CODE_OF_T = 5;
   private static final int CODE_OF_Z = 6;
   private static final int SCORE_ONE_LINE = 10;
   private static final int SCORE_TWO_LINE = 30;
   private static final int SCORE_THREE_LINE = 60;
   private static final int SCORE_FOUR_LINE = 100;

   public GameField(int rows, int cols) {
      this.rows = rows;
      this.cols = cols;
      this.field = new int[rows][cols];
      this.random = new Random();
      this.score = 0;
      this.line = 0;
      this.gameOver = false;

      initializeTrtrominos();
   }

   public int getRows() {
      return this.rows;
   }

   public int getCols() {
      return this.cols;
   }

   public int getStatus(int row, int col) { return this.field[row][col]; }

   public Tetromino getTetromino() {
      return tetromino;
   }

   public boolean isGameOver() {
      return gameOver;
   }

   private void initializeTrtrominos() {
      tetromino = generateTetromino();
      paintTetromino();
      nextTetromino = generateTetromino();
   }

   private Tetromino generateTetromino() {
      Tetromino new_tetromino = null;
      int kind = random.nextInt(NUMBER_OF_KIND);
      //int kind = 0;
      if (kind == CODE_OF_I) {
         new_tetromino = new I(cols / 2);
      } else if (kind == CODE_OF_J) {
         new_tetromino = new J(cols / 2);
      } else if (kind == CODE_OF_L) {
         new_tetromino = new L(cols / 2);
      } else if (kind == CODE_OF_O) {
         new_tetromino = new O(cols / 2);
      } else if (kind == CODE_OF_S) {
         new_tetromino = new S(cols / 2);
      } else if (kind == CODE_OF_T) {
         new_tetromino = new T(cols / 2);
      } else if (kind == CODE_OF_Z) {
         new_tetromino = new Z(cols / 2);
      }

      return new_tetromino;
   }

   private int distanceToLeft() {
      Cell[] cells = tetromino.getCells();
      int smallest = cols;
      int distance;
      for (Cell cell: cells) {
         int i = cell.getCol();
         int row = cell.getRow();
         distance = 0;
         while (i > 0 && field[row][i - 1] == 0) {
            distance++;
            i--;
         }
         smallest = Math.min(smallest, distance);
      }
      return smallest;
   }

   private int distanceToRight() {
      Cell[] cells = tetromino.getCells();
      int smallest = cols;
      int distance;
      for (Cell cell: cells) {
         int i = cell.getCol();
         int row = cell.getRow();
         distance = 0;
         while (i < cols - 1 && field[row][i + 1] == 0) {
            distance++;
            i++;
         }
         smallest = Math.min(smallest, distance);
      }
      return smallest;
   }

   private void removeTetromino() {
      for (Cell cell: tetromino.getCells()) {
         field[cell.getRow()][cell.getCol()] = 0;
      }
   }

   private void paintTetromino() {
      for (Cell cell: tetromino.getCells()) {
         field[cell.getRow()][cell.getCol()] = 2;
      }
   }

   public void moveLeft() {
      removeTetromino();
      if (distanceToLeft() > 0) {
         tetromino.moveLeft();
      }
      paintTetromino();
   }

   public void moveRight() {
      removeTetromino();
      if (distanceToRight() > 0) {
         tetromino.moveRight();;
      }
      paintTetromino();
   }

   private void addScoreAndLine(int line) {
      this.line += line;
      if (line == 1) {
         this.score += SCORE_ONE_LINE;
      } else if (line == 2) {
         this.score += SCORE_TWO_LINE;
      } else if (line == 3) {
         this.score += SCORE_THREE_LINE;
      } else if (line == 4) {
         this.score += SCORE_FOUR_LINE;
      }
   }

   private boolean checkTetromino() {
      for (Cell cell: tetromino.getCells()) {
         if (field[cell.getRow()][cell.getCol()] == 1) {
            return false;
         }
      }
      return true;
   }

   private void updateTetromino() {
      for (Cell cell: tetromino.getCells()) {
         field[cell.getRow()][cell.getCol()] = 1;
      }
      int lineDeleted = delete();
      addScoreAndLine(lineDeleted);

      tetromino = nextTetromino;
      if(checkTetromino()) {
         paintTetromino();
         nextTetromino = generateTetromino();
      } else {
         gameOver = true;
      }

   }

   public void softDrop() {
      int row;
      int col;
      boolean canDrop = true;
      for (Cell cell: tetromino.getCells()) {
         row = cell.getRow();
         col = cell.getCol();
         if (row == rows - 1 || field[row + 1][col] == 1) {
            canDrop = false;
         }
      }
      if (canDrop) {
         removeTetromino();
         tetromino.softDrop();
         paintTetromino();
      } else {
         updateTetromino();
      }
   }

   public void rotateLeft() {
      //TODO
   }

   public void rotateRight() {
      //TODO
   }

   public int delete() {
      int statusOfOneLine;
      int numOfFullLine = 0;
      for (int i = rows - 1; i > 0; i--) {
         statusOfOneLine = checkOneRowStatus(i);
         if (statusOfOneLine == STATUS_EMPTY) {
            break;
         } else if (statusOfOneLine == STATUS_PARTIAL_FULL) {
            if (numOfFullLine != 0) {
               copyOneLine(i, i + numOfFullLine);
               setOneLineEmpty(i);
            }
         } else {
            numOfFullLine++;
            setOneLineEmpty(i);
         }
      }
      return numOfFullLine;
   }

   public String toString() {
      String string = "";
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            string = string + field[i][j] + " ";
         }
         string += "\n";
      }
      return string;
   }

   /**
    * return the row number of first true in the given column. If there is no, return number of rows of the field.
    * @param col
    * @return
    */
   public int getHeight(int col) {
      for (int i = 0; i < rows; i++) {
         if (field[i][col] == 1) {
            return i;
         }
      }
      return rows;
   }

   private int checkOneRowStatus(int row) {
      int numTrue = 0;
      int numFalse = 0;
      for (int j = 0; j < cols; j++) {
         if (field[row][j] == 1) {
            numTrue++;
         } else {
            numFalse++;
         }
      }
      if (numTrue == cols) {
         return STATUS_FULL;
      } else if (numFalse == cols) {
         return STATUS_EMPTY;
      } else {
         return STATUS_PARTIAL_FULL;
      }
   }

   private void setOneLineEmpty(int row) {
      for (int j = 0; j < cols; j++) {
         field[row][j] = 0;
      }
   }

   private void copyOneLine(int rowCopy, int rowPaste) {
      for (int j = 0; j < cols; j++) {
         field[rowPaste][j] = field[rowCopy][j];
      }
   }


}
