import org.junit.Test;


import cs3500.animator.model.Utils;
import cs3500.animator.model.shape.Posn;

import static org.junit.Assert.assertEquals;

public class PosnTest {
  Posn p1 = new Posn(0.0, 0.0);
  Posn p2 = new Posn(102.112, 50.5);

  // Test for getting the x coordinate
  @Test
  public void getX() {
    assertEquals(true, Utils.checkDoubles(0.0, this.p1.getX()));
    assertEquals(true, Utils.checkDoubles(102.112, this.p2.getX()));
    assertEquals(false, Utils.checkDoubles(105.0, this.p1.getX()));
    assertEquals(false, Utils.checkDoubles(105.0, this.p2.getX()));
  }

  // Test for getting the y coordinate
  @Test
  public void getY() {
    assertEquals(true, Utils.checkDoubles(0.0, this.p1.getY()));
    assertEquals(true, Utils.checkDoubles(50.5, this.p2.getY()));
    assertEquals(false, Utils.checkDoubles(105.0, this.p1.getY()));
    assertEquals(false, Utils.checkDoubles(105.0, this.p2.getY()));
  }

  // Test for comparing two doubles
  @Test
  public void checkDoubles() {
    assertEquals(true, Utils.checkDoubles(0.0, 0.0));
    assertEquals(true, Utils.checkDoubles(102.0, 102.0));
    assertEquals(false, Utils.checkDoubles(90.0, 89.0));
    assertEquals(false, Utils.checkDoubles(2.0, 4.0));
  }

}