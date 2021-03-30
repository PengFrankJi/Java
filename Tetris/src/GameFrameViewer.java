import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrameViewer {
   private static final int FRAME_WIDTH = 500;
   private static final int FRAME_HEIGHT = 650;

   public static void main(String[] args) {
      JFrame frame = new GameFrame();

      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setTitle("Tetris");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}


