import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.animation.ChangeDimension;
import cs3500.animator.model.animation.MoveAnimation;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.Shapes;

import static junit.framework.TestCase.assertEquals;

public class SimpleAnimationModelTest {
  IAnimationModel aop;
  Posn p1;
  Posn p2;
  Shapes oval1;
  Shapes oval2;
  Shapes rect1;
  Shapes rect2;

  Animations changeEclipseColor;
  Animations changeRectColor;
  Animations changeEclipseDimension;
  Animations changeRectDimension;
  Animations moveEclipse;
  Animations moveRect;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    this.aop = new SimpleAnimationModel();

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

  // Test for adding shape to model
  @Test
  public void addShape() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addShape(this.rect1);
    assertEquals("Shapes:\n"
            + "Name: rectangle 1\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (0.0, 0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n\n", this.aop.getDescription());
    this.aop.addShape(this.oval2);
    assertEquals("Shapes:\n"
            + "Name: rectangle 1\n"
            + "Type: rectangle\n"
            + "Lower-left corner: (0.0, 0.0), Width: 10.0, Height: 10.0, Color: (0.0,0.0,0.0)\n"
            + "Appears at t=0\n"
            + "Disappears at t=10\n\n"
            + "Name: oval 2\n"
            + "Type: oval\n"
            + "Center: (102.112, 50.5), X radius: 20.0, Y radius: 25.5, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=10\n"
            + "Disappears at t=15\n\n", this.aop.getDescription());
  }

  // Test for adding animation to model
  @Test
  public void addAnimations() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addAnimations(this.changeEclipseColor);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to "
                    + "(1.0,0.0,0.0) from t=5 to t=10\n",
            this.aop.getDescription());

    this.aop.addAnimations(this.changeEclipseDimension);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
    this.aop.addAnimations(this.moveEclipse);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 1 moves from (0.0, 0.0) to (102.112, 50.5) from t=8 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
  }

  // Test for adding animation to model
  @Test
  public void addAnimationsInDifferentOrder() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addAnimations(this.changeEclipseDimension);
    assertEquals("Shapes:\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());

    this.aop.addAnimations(this.changeEclipseColor);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
    this.aop.addAnimations(this.moveEclipse);
    assertEquals("Shapes:\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 1 moves from (0.0, 0.0) to (102.112, 50.5) from t=8 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
  }

  // Test for adding in animation for Incompatible move for the same shape during overlapping
  // time intervals
  @Test(expected = IllegalArgumentException.class)
  public void addInvalidAnimation() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addAnimations(this.changeEclipseColor);
    this.aop.addAnimations(this.changeEclipseColor);
  }

  // Test for getting description
  @Test
  public void getDescription() {
    assertEquals("Shapes:\n", this.aop.getDescription());
    this.aop.addShape(this.rect1);
    this.aop.addShape(this.oval2);
    this.aop.addAnimations(this.changeEclipseColor);
    this.aop.addAnimations(this.changeEclipseDimension);
    this.aop.addAnimations(this.moveEclipse);
    assertEquals("Shapes:\n"
                    + "Name: rectangle 1\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (0.0, 0.0), Width: 10.0, Height: 10.0, "
                    + "Color: (0.0,0.0,0.0)\n"
                    + "Appears at t=0\n"
                    + "Disappears at t=10\n" + "\n"
                    + "Name: oval 2\n"
                    + "Type: oval\n"
                    + "Center: (102.112, 50.5), X radius: 20.0, Y radius: 25.5, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=10\n"
                    + "Disappears at t=15\n"
                    + "\n"
                    + "shape oval 1 changes color from (0.0,0.0,0.0) to (1.0,0.0,0.0) "
                    + "from t=5 to t=10\n"
                    + "shape oval 1 moves from (0.0, 0.0) to (102.112, 50.5) from t=8 to t=10\n"
                    + "shape oval 2 scales from X radius: 20.0, Y radius: 25.5 to "
                    + "X radius: 15.5, Y radius: 15.5 from t=11 to t=12\n",
            this.aop.getDescription());
  }

  // Test for getting the list of shapes
  @Test
  public void getShapes() {
    this.aop.addShape(this.rect1);
    this.aop.addShape(this.oval2);
    this.aop.addAnimations(this.changeEclipseColor);
    this.aop.addAnimations(this.changeEclipseDimension);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(this.rect1, this.oval2)),
            this.aop.getShapes());
  }

  // Test for getting the list of animations
  @Test
  public void getAnimations() {
    this.aop.addShape(this.rect1);
    this.aop.addShape(this.oval2);
    this.aop.addAnimations(this.changeEclipseColor);
    this.aop.addAnimations(this.changeEclipseDimension);
    assertEquals(new ArrayList<Animations>(Arrays.asList(this.changeEclipseColor,
            this.changeEclipseDimension)),
            this.aop.getAnimations());
  }

}