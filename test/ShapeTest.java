import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.Utils;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.ShapeType;
import cs3500.animator.model.shape.Shapes;

import static org.junit.Assert.assertEquals;

public class ShapeTest {
  Posn p1;
  Posn p2;
  Shapes oval1;
  Shapes oval2;
  Shapes rect1;
  Shapes rect2;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    p1 = new Posn(0.0, 0.0);
    p2 = new Posn(102.112, 50.5);
    oval1 = new Oval("oval 1", 0, 10, p1, Color.BLACK, 10.0, 10.0);
    oval2 = new Oval("oval 2", 10, 15, p2, Color.BLUE, 20.0, 25.5);
    rect1 = new RectangleShape("rectangle 1", 0, 10,
            p1, Color.BLACK, 10.0, 10.0);
    rect2 = new RectangleShape("rectangle 2", 10, 15,
            p2, Color.BLUE, 20.0, 25.5);

  }

  // Test for string representation of a shape
  @Test
  public void getState() {
    assertEquals("Name: oval 1\n"
            + "Type: oval\n"
            + "Center: (0.0, 0.0), X radius: 10.0, Y radius: 10.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n", oval1.getState());
    assertEquals("Name: rectangle 1\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (0.0, 0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n", rect1.getState());
  }

  // Test for getting name of shape
  @Test
  public void getName() {
    assertEquals("oval 1", this.oval1.getName());
    assertEquals("rectangle 1", this.rect1.getName());
  }

  // Test for getting the shape type
  @Test
  public void getShapeType() {
    assertEquals(ShapeType.OVAL, this.oval1.getShapeType());
    assertEquals(ShapeType.RECTANGLE, this.rect1.getShapeType());
  }

  // Test for getting the appear time
  @Test
  public void getAppear() {
    assertEquals(0, this.oval1.getAppear());
    assertEquals(10, this.oval2.getAppear());
    assertEquals(0, this.rect1.getAppear());
    assertEquals(10, this.rect2.getAppear());
  }

  // Test for getting the disappear time
  @Test
  public void getDisappear() {
    assertEquals(10, this.oval1.getDisappear());
    assertEquals(15, this.oval2.getDisappear());
    assertEquals(10, this.rect1.getDisappear());
    assertEquals(15, this.rect2.getDisappear());
  }

  // Test for getting the posn of the shape
  @Test
  public void getPosn() {
    assertEquals("(0.0, 0.0)", Utils.getPosnString(this.oval1.getPosn()));
    assertEquals("(102.112, 50.5)", Utils.getPosnString(this.oval2.getPosn()));
  }

  // Test for getting the color of the shape
  @Test
  public void getColor() {
    assertEquals(Color.BLACK, this.oval1.getColor());
    assertEquals(Color.BLUE, this.rect2.getColor());
  }

  // Test for getting the first dimension
  @Test
  public void getD1() {
    assertEquals(true, Utils.checkDoubles(this.oval1.getD1(), 10.0));
    assertEquals(true, Utils.checkDoubles(this.oval2.getD1(), 20.0));
    assertEquals(true, Utils.checkDoubles(this.rect1.getD1(), 10.0));
    assertEquals(true, Utils.checkDoubles(this.rect2.getD1(), 20.0));
  }

  // Test for getting the second dimension
  @Test
  public void getD2() {
    assertEquals(true, Utils.checkDoubles(this.oval1.getD2(), 10.0));
    assertEquals(true, Utils.checkDoubles(this.oval2.getD2(), 25.5));
    assertEquals(true, Utils.checkDoubles(this.rect1.getD2(), 10.0));
    assertEquals(true, Utils.checkDoubles(this.rect2.getD2(), 25.5));
  }

  // Test for setting the type of the shape
  @Test
  public void setType() {
    assertEquals(ShapeType.OVAL, this.oval1.getShapeType());
    assertEquals(ShapeType.RECTANGLE, this.rect1.getShapeType());

    this.oval1.setType(ShapeType.RECTANGLE);
    this.rect1.setType(ShapeType.OVAL);

    assertEquals(ShapeType.RECTANGLE, this.oval1.getShapeType());
    assertEquals(ShapeType.OVAL, this.rect1.getShapeType());
  }

  // Test for setting the appear time
  @Test
  public void setAppear() {
    assertEquals(10, this.oval2.getAppear());
    assertEquals(0, this.rect1.getAppear());

    this.oval2.setAppear(11);
    this.rect1.setAppear(9);

    assertEquals(11, this.oval2.getAppear());
    assertEquals(9, this.rect1.getAppear());
  }

  // Test for setting the invalid appear times
  @Test(expected = IllegalArgumentException.class)
  public void setAppearInvalid() {
    assertEquals(10, this.oval2.getAppear());

    this.oval2.setAppear(20);
  }

  // Test for setting the disappear time
  @Test
  public void setDisappear() {
    assertEquals(15, this.oval2.getDisappear());
    assertEquals(10, this.rect1.getDisappear());

    this.oval2.setDisappear(20);
    this.rect1.setDisappear(15);

    assertEquals(20, this.oval2.getAppear());
    assertEquals(15, this.rect1.getAppear());
  }

  // Test for setting invalid disappear times
  @Test(expected = IllegalArgumentException.class)
  public void setDisappearInvalid() {
    assertEquals(15, this.oval2.getDisappear());

    this.oval2.setDisappear(5);
  }

  // Test for setting posn
  @Test
  public void setPosn() {
    assertEquals("(0.0, 0.0)", Utils.getPosnString(this.oval1.getPosn()));
    assertEquals("(102.112, 50.5)", Utils.getPosnString(this.oval2.getPosn()));

    this.oval1.setPosn(this.p2);
    this.oval2.setPosn(this.p1);

    assertEquals("(102.112, 50.5)", Utils.getPosnString(this.oval1.getPosn()));
    assertEquals("(0.0, 0.0)", Utils.getPosnString(this.oval2.getPosn()));
  }

  // Test for setting color
  @Test
  public void setColor() {
    assertEquals(Color.BLACK, this.oval1.getColor());
    assertEquals(Color.BLUE, this.rect2.getColor());

    this.oval1.setColor(Color.RED);
    this.rect2.setColor(Color.RED);

    assertEquals(Color.RED, this.oval1.getColor());
    assertEquals(Color.RED, this.rect2.getColor());
  }

  // Test for setting first dimension
  @Test
  public void setD1() {
    assertEquals(true, Utils.checkDoubles(this.oval1.getD1(), 10.0));
    assertEquals(true, Utils.checkDoubles(this.rect1.getD1(), 10.0));

    this.oval1.setD1(20.0);
    this.rect1.setD1(20.0);

    assertEquals(true, Utils.checkDoubles(this.oval1.getD1(), 20.0));
    assertEquals(true, Utils.checkDoubles(this.rect1.getD1(), 20.0));
  }

  // Test for setting first dimension invalid
  @Test(expected = IllegalArgumentException.class)
  public void setInvalidD1() {
    assertEquals(true, Utils.checkDoubles(this.oval1.getD1(), 10.0));

    this.oval1.setD1(-20.0);
  }

  // Test for setting second dimension
  @Test
  public void setD2() {
    assertEquals(true, Utils.checkDoubles(this.oval1.getD2(), 10.0));
    assertEquals(true, Utils.checkDoubles(this.rect2.getD2(), 25.5));

    this.oval1.setD2(15.5);
    this.rect2.setD2(15.5);

    assertEquals(true, Utils.checkDoubles(this.oval1.getD2(), 15.5));
    assertEquals(true, Utils.checkDoubles(this.rect2.getD2(), 15.5));
  }

  // Test for setting second dimension invalid
  @Test(expected = IllegalArgumentException.class)
  public void setInvalidD2() {
    assertEquals(true, Utils.checkDoubles(this.rect2.getD2(), 25.5));

    this.rect2.setD2(-20.0);
  }

  // Test for getting dimensions
  @Test
  public void getDimensions() {
    assertEquals("Width: 10.0, Height: 10.0", rect1.getDimensions());
    assertEquals("Width: 20.0, Height: 25.5", rect2.getDimensions());
    assertEquals("X radius: 10.0, Y radius: 10.0", oval1.getDimensions());
    assertEquals("X radius: 20.0, Y radius: 25.5", oval2.getDimensions());
  }

  // Test for overriding equals
  @Test
  public void testEquals() {
    assertEquals(true, this.oval1.equals(this.oval1));
    assertEquals(false, this.oval1.equals(this.oval2));
    assertEquals(true, this.rect1.equals(this.rect1));
    assertEquals(false, this.rect1.equals(this.rect2));
  }

  // Test for creating invalid shape
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeAppear() {
    Shapes shape = new RectangleShape("rectangle 1", -1, 10,
            p1, Color.BLACK, 10.0, 10.0);
  }

  // Test for creating invalid shape
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDisppear() {
    Shapes shape = new RectangleShape("rectangle 1", 1, -10,
            p1, Color.BLACK, 10.0, 10.0);
  }

  // Test for creating invalid shape
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeD1() {
    Shapes shape = new RectangleShape("rectangle 1", 1, 10,
            p1, Color.BLACK, -10.0, 10.0);
  }

  // Test for creating invalid shape
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeD2() {
    Shapes shape = new RectangleShape("rectangle 1", 1, 10,
            p1, Color.BLACK, 10.0, -10.0);
  }

  // Test for creating invalid shape
  @Test(expected = IllegalArgumentException.class)
  public void testDisappearBeforeAppear() {
    Shapes shape = new RectangleShape("rectangle 1", 10, 1,
            p1, Color.BLACK, 10.0, 10.0);
  }

}