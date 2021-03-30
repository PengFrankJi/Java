public class I extends Tetromino {
   public I(int base) {
      super();
      this.cells[0] = new Cell(0, base);
      this.cells[1] = new Cell(1, base);
      this.cells[2] = new Cell(2, base);
      this.cells[3] = new Cell(3, base);
      // 0 0
      // 1 1
      // 0 2
      // 0 3
   }

}
