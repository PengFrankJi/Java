

public class Tetromino {
   protected Cell[] cells;
   protected int status = 0;
   private static final int NUMBER_OF_CELL = 4;

   public Tetromino() {
      this.cells = new Cell[NUMBER_OF_CELL];
   }

   public void moveLeft(){
      for (int i = 0; i < cells.length; i++) {
         cells[i].left();
      }
   }

   public void moveRight(){
      for (int i = 0; i < cells.length; i++) {
         cells[i].right();
      }
   }

   public void softDrop(){
      for (int i = 0; i < cells.length; i++) {
         cells[i].drop();
      }
   }

   public void rotateLeft() {

   }

   public void rotateRight() {

   }

   public Cell[] getCells() {
      return cells;
   }


}
