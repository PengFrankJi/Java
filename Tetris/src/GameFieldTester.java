import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameFieldTester {
   private GameField gameField1;

   @Before
   public void setUp() {
      gameField1 = new GameField(5, 6);
   }

   @Test
   public void test() {
      assertEquals(5, gameField1.getRows());
      assertEquals(6, gameField1.getCols());
      assertFalse(gameField1.isGameOver());

      System.out.println("new gameField1:");
      System.out.println(gameField1.toString());

      System.out.println("gameField1 after 1");
      gameField1.moveRight();
      gameField1.moveRight();
      gameField1.softDrop();
      gameField1.softDrop();
      System.out.println(gameField1.toString());

      System.out.println("gameField1 after 1");
      gameField1.moveRight();
      gameField1.softDrop();
      gameField1.softDrop();
      System.out.println(gameField1.toString());

      System.out.println("gameField1 after 1");
      gameField1.moveLeft();
      gameField1.moveLeft();
      gameField1.moveLeft();
      gameField1.softDrop();
      gameField1.softDrop();
      System.out.println(gameField1.toString());

      System.out.println("gameField1 after 1");
      gameField1.moveLeft();
      gameField1.moveLeft();
      gameField1.softDrop();
      gameField1.softDrop();
      System.out.println(gameField1.toString());

      System.out.println("gameField1 after 1");
      gameField1.moveLeft();
      gameField1.softDrop();
      gameField1.softDrop();
      System.out.println(gameField1.toString());

      System.out.println("gameField1 after 1");
      gameField1.softDrop();
      gameField1.softDrop();
      System.out.println(gameField1.toString());

   }




}
