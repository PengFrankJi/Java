public class O extends Tetromino {
   public O(int base) {
      this.cells[0] = new Cell(0, base);
      this.cells[1] = new Cell(0, base + 1);
      this.cells[2] = new Cell(1, base);
      this.cells[3] = new Cell(1, base + 1);
   }
}