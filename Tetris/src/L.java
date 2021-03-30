public class L extends Tetromino {
   public L(int base) {
      this.cells[0] = new Cell(0, base);
      this.cells[1] = new Cell(1, base);
      this.cells[2] = new Cell(2, base);
      this.cells[3] = new Cell(2, base + 1);
      // 0
      // 0
      // 10
   }
}