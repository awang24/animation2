import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.Shapes;

import static org.junit.Assert.assertEquals;

public class RectangleShapeTest {

  Posn p1;
  Posn p2;
  Shapes rect1;
  Shapes rect2;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    p1 = new Posn(0.0, 0.0);
    p2 = new Posn(102.112, 50.5);
    rect1 = new RectangleShape("rectangle 1", 0, 10,
            p1, Color.BLACK, 10.0, 10.0);
    rect2 = new RectangleShape("rectangle 2", 10, 15,
            p2, Color.BLUE, 20.0, 25.5);
  }

  // Test for creating an rectangle with negative width
  @Test(expected = IllegalArgumentException.class)
  public void createRectNegX() {
    Shapes rect3 = new RectangleShape("rectangle 3", 15, 20, p2,
            Color.DARK_GRAY, -10.0, 10.0);
  }

  // Test for creating an rectangle with negative height
  @Test(expected = IllegalArgumentException.class)
  public void createRectNegY() {
    Shapes rect3 = new RectangleShape("rectangle 3", 15, 20, p2,
            Color.DARK_GRAY, 10.0, -10.0);
  }

  // Test for method that prints out the posn of the rectangle
  @Test
  public void location() {
    assertEquals("Lower-left corner: (0.0, 0.0)", rect1.location());
    assertEquals("Lower-left corner: (102.112, 50.5)", rect2.location());
  }

  // Test for method that prints out the dimensions of the rectangle
  @Test
  public void dimensions() {
    assertEquals("Width: 10.0, Height: 10.0", rect1.getDimensions());
    assertEquals("Width: 20.0, Height: 25.5", rect2.getDimensions());
  }

  // Test for method that sets new dimensions
  @Test
  public void setDimensions() {
    assertEquals("Width: 10.0, Height: 10.0", rect1.getDimensions());
    assertEquals("Width: 20.0, Height: 25.5", rect2.getDimensions());

    this.rect1.setD1(100.1);
    this.rect1.setD2(100.1);
    this.rect2.setD1(3.4);
    this.rect2.setD2(5.4);

    assertEquals("Width: 100.1, Height: 100.1", rect1.getDimensions());
    assertEquals("Width: 3.4, Height: 5.4", rect2.getDimensions());
  }

  // Test for negative new dimensions width
  @Test(expected = IllegalArgumentException.class)
  public void setIllegalDimensionsX() {
    assertEquals("Width: 10.0, Height: 10.0", rect1.getDimensions());
    this.rect1.setD1(-1.0);
  }

  // Test for negative new dimensions height
  @Test(expected = IllegalArgumentException.class)
  public void setIllegalDimensionsY() {
    assertEquals("Width: 10.0, Height: 10.0", rect1.getDimensions());
    this.rect1.setD2(-15.5);
  }

  // Test for correct dimension tags
  @Test
  public void dimensionTags() {
    assertEquals("Width: ", this.rect1.d1TagString());
    assertEquals("Height: ", this.rect1.d2TagString());
  }

}