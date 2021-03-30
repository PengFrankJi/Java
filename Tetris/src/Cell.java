public class Cell {
   private int row;
   private int col;
   //private boolean status;

   public Cell(int row, int col) {
      this.row = row;
      this.col = col;
   }

   public int getRow() {
      return this.row;
   }

   public int getCol() {
      return this.col;
   }

   public void setRow(int row) {
      this.row = row;
   }

   public void setCol(int col) {
      this.col = col;
   }

   public void left(){
      col--;
   }

   public void right(){
      col++;
   }

   public void drop(){
      row++;
   }



}
