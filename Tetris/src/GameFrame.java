import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
   //private JPanel infoPanel;
   //private JPanel gamePanel;
   private GameField gameField;
   private SquareView[][] mySquares;
   private JLabel[][] gamePanelLabels;

   private static final int GAMEPANEL_WIDTH = 500;
   private static final int GAMEPANEL_HEIGHT = 700;
   private static final int INFOPANEL_WIDTH = 150;
   private static final int INFOPANEL_HEIGHT = 700;

   private static final int BORDER_THICKNESS = 1;

   private static final Border PADDING = BorderFactory.createEmptyBorder(10, 10, 10, 10);
   private static final Color STATUS_TRUE_COLOR = Color.BLACK;
   private static final Color STATUS_FALSE_COLOR = Color.GRAY;

   public GameFrame() {

      gameField = new GameField(20, 10);

      add(setUpGUI());

      addKeyListener(new myKeyListener());
   }

   private JPanel setUpGUI() {
      JPanel all = new JPanel();

      //JPanel infoPanel = setUpInfoPanel();

      JPanel gamePanel = setUpGamePanel();

      //all.add(infoPanel, BorderLayout.WEST);
      all.add(gamePanel, BorderLayout.CENTER);

      return all;
   }

   private JPanel setUpInfoPanel() {
      JPanel infoPanel = new JPanel();

      return infoPanel;
   }

   private JPanel setUpGamePanel() {
      JPanel gamePanel = new JPanel();

      int rows = this.gameField.getRows();
      int cols = this.gameField.getCols();

      mySquares = new SquareView[rows][cols];

      GridLayout squareLayout = new GridLayout(rows, cols);
      squareLayout.setHgap(BORDER_THICKNESS);
      squareLayout.setVgap(BORDER_THICKNESS);

      gamePanel.setLayout(squareLayout);

      for (int i = 0; i < mySquares.length; i++) {
         for (int j = 0; j < mySquares[0].length; j++) {
            mySquares[i][j] = new SquareView(i, j);
            gamePanel.add(mySquares[i][j]);
         }
      }
      updateAllSquaresViews();
      repaint();
      return gamePanel;
   }

   private void updateAllSquaresViews() {
      for (int row = 0; row < mySquares.length; row++) {
         for (int col = 0; col < mySquares[0].length; col++) {
            mySquares[row][col].updateDisplay();
         }
      }
   }


   //-------------------------------------------------------------------------------------
   // INNER CLASS SquareListener
   // the Controller (in MVC) class for a square
   private class myKeyListener extends KeyAdapter {

      @Override
      public void keyPressed(KeyEvent event) {

         if (gameField.isGameOver()) {
            return;
         }

         if (event.getKeyCode() == KeyEvent.VK_UP) {
            gameField.rotateRight();
         } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            gameField.moveLeft();
         } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            gameField.moveRight();
         } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            gameField.softDrop();
         }

         updateAllSquaresViews();

         repaint();
      }

   }


   //-----------------------------------------
   // INNER CLASS
   // The view class for s square
   private class SquareView extends JLabel {
      public static final int PREF_SQUARE_WIDTH = 25;
      public static final int PREF_SQUARE_HEIGHT = 25;


      private int myRow;
      private int myCol;

      public SquareView(int row, int col) {
         myRow = row;
         myCol = col;
         setBorder(PADDING);
         setPreferredSize(new Dimension(PREF_SQUARE_WIDTH, PREF_SQUARE_HEIGHT));
         setHorizontalAlignment(SwingConstants.CENTER);
         //setFont(DEFAULT_FONT);
         updateDisplay();
         //addKeyListener(new Listener(this));
      }

      public void updateDisplay() {
         int status = gameField.getStatus(myRow, myCol);
         if (status > 0) {
            setOpaque(true);
            setBackground(STATUS_TRUE_COLOR);
         } else {
            setOpaque(true);
            setBackground(STATUS_FALSE_COLOR);
         }

      }
   }

}



