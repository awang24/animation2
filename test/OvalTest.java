import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.Shapes;

import static org.junit.Assert.assertEquals;


public class OvalTest {
  Posn p1;
  Posn p2;
  Shapes oval1;
  Shapes oval2;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    p1 = new Posn(0.0, 0.0);
    p2 = new Posn(102.112, 50.5);
    oval1 = new Oval("oval 1", 0, 10, p1, Color.BLACK, 10.0, 10.0);
    oval2 = new Oval("oval 2", 10, 15, p2, Color.BLUE, 20.0, 25.5);
  }

  // Test for creating an oval with negative x radius
  @Test(expected = IllegalArgumentException.class)
  public void createOvalNegX() {
    Shapes oval3 = new Oval("oval 3", 15, 20,
            p2, Color.DARK_GRAY, -10.0, 10.0);
  }

  // Test for creating an oval with negative x radius
  @Test(expected = IllegalArgumentException.class)
  public void createOvalNegY() {
    Shapes oval3 = new Oval("oval 3", 15, 20,
            p2, Color.DARK_GRAY, 10.0, -10.0);
  }

  // Test for method that prints out the posn of the oval
  @Test
  public void location() {
    assertEquals("Center: (0.0, 0.0)", oval1.location());
    assertEquals("Center: (102.112, 50.5)", oval2.location());
  }

  // Test for method that prints out the dimensions of the oval
  @Test
  public void dimensions() {
    assertEquals("X radius: 10.0, Y radius: 10.0", oval1.getDimensions());
    assertEquals("X radius: 20.0, Y radius: 25.5", oval2.getDimensions());
  }

  // Test for method that sets new dimensions
  @Test
  public void setDimensions() {
    assertEquals("X radius: 10.0, Y radius: 10.0", oval1.getDimensions());
    this.oval1.setD1(15.5);
    this.oval1.setD2(15.5);
    assertEquals("X radius: 15.5, Y radius: 15.5", oval1.getDimensions());

    assertEquals("X radius: 20.0, Y radius: 25.5", oval2.getDimensions());
    this.oval2.setD1(100.2);
    this.oval2.setD2(20);
    assertEquals("X radius: 100.2, Y radius: 20.0", oval2.getDimensions());
  }

  // Test for negative new dimensions X radius
  @Test(expected = IllegalArgumentException.class)
  public void setIllegalDimensionsX() {
    assertEquals("X radius: 10.0, Y radius: 10.0", oval1.getDimensions());
    this.oval1.setD1(-1.0);
  }

  // Test for negative new dimensions Y radius
  @Test(expected = IllegalArgumentException.class)
  public void setIllegalDimensionsY() {
    assertEquals("X radius: 10.0, Y radius: 10.0", oval1.getDimensions());
    this.oval1.setD2(-15.5);
  }

  // Test for correct dimension tags
  @Test
  public void dimensionTags() {
    assertEquals("X radius: ", this.oval1.d1TagString());
    assertEquals("Y radius: ", this.oval1.d2TagString());
  }

}