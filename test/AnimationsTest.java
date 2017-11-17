import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs3500.animator.model.Utils;
import cs3500.animator.model.animation.AnimationType;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.animation.ChangeDimension;
import cs3500.animator.model.animation.MoveAnimation;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.Shapes;

import static org.junit.Assert.assertEquals;

public class AnimationsTest {
  Animations changeEclipseColor;
  Animations changeRectColor;
  Animations changeEclipseDimension;
  Animations changeRectDimension;
  Animations moveEclipse;
  Animations moveRect;

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

    changeEclipseColor = new ChangeColor(this.oval1, 5, 10, Color.BLACK, Color.RED);
    changeRectColor = new ChangeColor(this.rect1, 5, 10, Color.BLACK, Color.PINK);
    changeEclipseDimension =
            new ChangeDimension(this.oval2, 11, 12, 20.0, 25.5, 15.5, 15.5);
    changeRectDimension = new ChangeDimension(this.rect2, 11, 12, 20.0, 25.5, 15.5, 15.5);
    moveEclipse = new MoveAnimation(this.oval1, 8, 10, this.p1, this.p2);
    moveRect = new MoveAnimation(this.rect1, 8, 10, this.p1, this.p2);

  }

  // Test for animating change color
  @Test
  public void animteChangeColor() {
    assertEquals("(0.0,0.0,0.0)",
            Utils.getColorString(this.changeEclipseColor.getShape().getColor()));
    this.changeEclipseColor.animate(4);
    assertEquals("(0.0,0.0,0.0)",
            Utils.getColorString(this.changeEclipseColor.getShape().getColor()));
    this.changeEclipseColor.animate(7);
    assertEquals("(0.4,0.0,0.0)",
            Utils.getColorString(this.changeEclipseColor.getShape().getColor()));
    this.changeEclipseColor.animate(10);
    assertEquals("(1.0,0.0,0.0)",
            Utils.getColorString(this.changeEclipseColor.getShape().getColor()));
    this.changeEclipseColor.animate(12);
    assertEquals("(1.0,0.0,0.0)",
            Utils.getColorString(this.changeEclipseColor.getShape().getColor()));

    assertEquals("(0.0,0.0,0.0)",
            Utils.getColorString(this.changeRectColor.getShape().getColor()));
    this.changeRectColor.animate(4);
    assertEquals("(0.0,0.0,0.0)",
            Utils.getColorString(this.changeRectColor.getShape().getColor()));
    this.changeRectColor.animate(7);
    assertEquals("(0.4,0.27450982,0.27450982)",
            Utils.getColorString(this.changeRectColor.getShape().getColor()));
    this.changeRectColor.animate(10);
    assertEquals("(1.0,0.6862745,0.6862745)",
            Utils.getColorString(this.changeRectColor.getShape().getColor()));
    this.changeRectColor.animate(12);
    assertEquals("(1.0,0.6862745,0.6862745)",
            Utils.getColorString(this.changeRectColor.getShape().getColor()));
  }

  // Test for animating change dimension
  @Test
  public void animateChangeDimension() {
    assertEquals("X radius: 20.0, Y radius: 25.5",
            this.changeEclipseDimension.getShape().getDimensions());
    this.changeEclipseDimension.animate(7);
    assertEquals("X radius: 20.0, Y radius: 25.5",
            this.changeEclipseDimension.getShape().getDimensions());
    this.changeEclipseDimension.animate(11);
    assertEquals("X radius: 20.0, Y radius: 25.5",
            this.changeEclipseDimension.getShape().getDimensions());
    this.changeEclipseDimension.animate(12);
    assertEquals("X radius: 15.5, Y radius: 15.5",
            this.changeEclipseDimension.getShape().getDimensions());
    this.changeEclipseDimension.animate(14);
    assertEquals("X radius: 15.5, Y radius: 15.5",
            this.changeEclipseDimension.getShape().getDimensions());

    assertEquals("Width: 20.0, Height: 25.5",
            this.changeRectDimension.getShape().getDimensions());
    this.changeRectDimension.animate(7);
    assertEquals("Width: 20.0, Height: 25.5",
            this.changeRectDimension.getShape().getDimensions());
    this.changeRectDimension.animate(11);
    assertEquals("Width: 20.0, Height: 25.5",
            this.changeRectDimension.getShape().getDimensions());
    this.changeRectDimension.animate(12);
    assertEquals("Width: 15.5, Height: 15.5",
            this.changeRectDimension.getShape().getDimensions());
    this.changeRectDimension.animate(14);
    assertEquals("Width: 15.5, Height: 15.5",
            this.changeRectDimension.getShape().getDimensions());
  }

  // Test for animating move
  @Test
  public void animteMove() {
    assertEquals("(0.0, 0.0)",
            Utils.getPosnString(this.moveEclipse.getShape().getPosn()));
    this.moveEclipse.animate(7);
    assertEquals("(0.0, 0.0)",
            Utils.getPosnString(this.moveEclipse.getShape().getPosn()));
    this.moveEclipse.animate(9);
    assertEquals("(51.056, 25.25)",
            Utils.getPosnString(this.moveEclipse.getShape().getPosn()));
    this.moveEclipse.animate(10);
    assertEquals("(102.112, 50.5)",
            Utils.getPosnString(this.moveEclipse.getShape().getPosn()));
    this.moveEclipse.animate(12);
    assertEquals("(102.112, 50.5)",
            Utils.getPosnString(this.moveEclipse.getShape().getPosn()));

    assertEquals("(0.0, 0.0)",
            Utils.getPosnString(this.moveRect.getShape().getPosn()));
    this.moveRect.animate(7);
    assertEquals("(0.0, 0.0)",
            Utils.getPosnString(this.moveRect.getShape().getPosn()));
    this.moveRect.animate(9);
    assertEquals("(51.056, 25.25)",
            Utils.getPosnString(this.moveRect.getShape().getPosn()));
    this.moveRect.animate(10);
    assertEquals("(102.112, 50.5)",
            Utils.getPosnString(this.moveRect.getShape().getPosn()));
    this.moveRect.animate(12);
    assertEquals("(102.112, 50.5)",
            Utils.getPosnString(this.moveRect.getShape().getPosn()));
  }

  // Test for getting description
  @Test
  public void getDescription() {
    assertEquals("shape oval 1 changes color from (0.0,0.0,0.0) "
            + "to (1.0,0.0,0.0) from t=5 to t=10", changeEclipseColor.getDescription());
    assertEquals("shape rectangle 1 changes color from (0.0,0.0,0.0) to "
            + "(1.0,0.6862745,0.6862745) from t=5 to t=10", changeRectColor.getDescription());
    assertEquals("shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12",
            this.changeEclipseDimension.getDescription());
    assertEquals("shape rectangle 2 scales from Width: 20.0, Height: 25.5 to Width: 15.5, "
            + "Height: 15.5 from t=11 to t=12", this.changeRectDimension.getDescription());
    assertEquals("shape oval 1 moves from (0.0, 0.0) to (102.112, 50.5) from t=8 to t=10",
            this.moveEclipse.getDescription());
    assertEquals("shape rectangle 1 moves from (0.0, 0.0) to (102.112, 50.5) "
            + "from t=8 to t=10", this.moveRect.getDescription());

  }

  // Test for getting the string representation of the type of movement
  @Test
  public void getMovement() {
    assertEquals("changes color", this.changeRectColor.getMovement());
    assertEquals("changes color", this.changeRectColor.getMovement());
    assertEquals("moves", this.moveRect.getMovement());
    assertEquals("moves", this.moveEclipse.getMovement());
    assertEquals("scales", this.changeRectDimension.getMovement());
    assertEquals("scales", this.changeEclipseDimension.getMovement());
  }

  // Test for getting the beginning state
  @Test
  public void getBeginState() {
    assertEquals("(0.0,0.0,0.0)", this.changeRectColor.getBeginState());
    assertEquals("(0.0,0.0,0.0)", this.changeEclipseColor.getBeginState());
    assertEquals("Width: 20.0, Height: 25.5", this.changeRectDimension.getBeginState());
    assertEquals("X radius: 20.0, Y radius: 25.5",
            this.changeEclipseDimension.getBeginState());
    assertEquals("(0.0, 0.0)", this.moveEclipse.getBeginState());
    assertEquals("(0.0, 0.0)", this.moveRect.getBeginState());
  }

  // Test for getting the end state
  @Test
  public void getEndState() {
    assertEquals("(1.0,0.6862745,0.6862745)", this.changeRectColor.getEndState());
    assertEquals("(1.0,0.0,0.0)", this.changeEclipseColor.getEndState());
    assertEquals("Width: 15.5, Height: 15.5", this.changeRectDimension.getEndState());
    assertEquals("X radius: 15.5, Y radius: 15.5",
            this.changeEclipseDimension.getEndState());
    assertEquals("(102.112, 50.5)", this.moveEclipse.getEndState());
    assertEquals("(102.112, 50.5)", this.moveRect.getEndState());
  }

  // Test for getting shape
  @Test
  public void getShape() {
    assertEquals(this.oval1, this.changeEclipseColor.getShape());
    assertEquals(this.rect1, this.changeRectColor.getShape());
    assertEquals(this.oval2, this.changeEclipseDimension.getShape());
    assertEquals(this.rect2, this.changeRectDimension.getShape());
    assertEquals(this.oval1, this.moveEclipse.getShape());
    assertEquals(this.rect1, this.moveRect.getShape());
  }

  // Test for getting start time
  @Test
  public void getStart() {
    assertEquals(5, this.changeEclipseColor.getStart());
    assertEquals(5, this.changeRectColor.getStart());
    assertEquals(11, this.changeEclipseDimension.getStart());
    assertEquals(11, this.changeRectDimension.getStart());
    assertEquals(8, this.moveEclipse.getStart());
    assertEquals(8, this.moveRect.getStart());
  }

  // Test for getting end time
  @Test
  public void getEnd() {
    assertEquals(10, this.changeEclipseColor.getEnd());
    assertEquals(10, this.changeRectColor.getEnd());
    assertEquals(12, this.changeEclipseDimension.getEnd());
    assertEquals(12, this.changeRectDimension.getEnd());
    assertEquals(10, this.moveEclipse.getEnd());
    assertEquals(10, this.moveRect.getEnd());
  }

  // Test for getting the type of animation
  @Test
  public void getType() {
    assertEquals(AnimationType.CHANGECOLOR, this.changeEclipseColor.getType());
    assertEquals(AnimationType.CHANGECOLOR, this.changeRectColor.getType());
    assertEquals(AnimationType.CHANGEDIMENSION, this.changeEclipseDimension.getType());
    assertEquals(AnimationType.CHANGEDIMENSION, this.changeRectDimension.getType());
    assertEquals(AnimationType.MOVE, this.moveRect.getType());
    assertEquals(AnimationType.MOVE, this.moveEclipse.getType());
  }

  // Test for creating invalid animation
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeStart() {
    Animations a = new ChangeColor(this.oval1, -5, 10, Color.black, Color.RED);
  }

  // Test for creating invalid animation
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeEnd() {
    Animations a = new ChangeColor(this.oval1, 5, -10, Color.BLACK, Color.RED);
  }

  // Test for creating invalid animation
  @Test(expected = IllegalArgumentException.class)
  public void testEndBeforeStart() {
    Animations a = new ChangeColor(this.oval1, 5, 2, Color.black, Color.RED);
  }

  // Test for returning the correct svg tags
  @Test
  public void testToSVGTag() {
    // test for changing color
    assertEquals("<animate attributeType=\"xml\" begin=\"500.0ms\" "
                    + "dur=\"500.0ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" "
                    + "to=\"rgb(255,0,0)\" "
                    + "fill=\"freeze\" />\n",
            this.changeEclipseColor.toSVGTag(10));
    assertEquals("<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,0,0)\" "
                    + "to=\"rgb(255,175,175)\" fill=\"freeze\" />\n",
            this.changeRectColor.toSVGTag(10));

    // test for changing dimension
    assertEquals("<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\" attributeName=\"rx\" from=\"20.0\" to=\"15.5\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\" "
                    + "attributeName=\"ry\" from=\"25.5\" to=\"15.5\" fill=\"freeze\" />\n",
            this.changeEclipseDimension.toSVGTag(10));
    assertEquals("<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\" attributeName=\"width\" from=\"20.0\" to=\"15.5\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\""
                    + " attributeName=\"height\" from=\"25.5\" to=\"15.5\" fill=\"freeze\" />\n",
            this.changeRectDimension.toSVGTag(10));

    // test for moving
    assertEquals("<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"200.0ms\" "
                    + "attributeName=\"x\" from=\"0.0\" to=\"102.112\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"200.0ms\" "
                    + "attributeName=\"y\" from=\"0.0\" to=\"50.5\" fill=\"freeze\" />\n",
            this.moveRect.toSVGTag(10));
    assertEquals("<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"200.0ms\" "
                    + "attributeName=\"cx\" from=\"0.0\" to=\"102.112\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"200.0ms\" "
                    + "attributeName=\"cy\" from=\"0.0\" to=\"50.5\" fill=\"freeze\" />\n",
            this.moveEclipse.toSVGTag(10));
  }

}