import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import cs3500.animator.model.SimpleAnimationModel.SimpleAnimationModelBuilder;
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
import cs3500.animator.starter.TweenModelBuilder;

import static org.junit.Assert.assertEquals;

public class SimpleAnimationModelBuilderTest {

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
  TweenModelBuilder<IAnimationModel> builder;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    this.builder = new SimpleAnimationModelBuilder();

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
            new ChangeDimension(this.oval2, 11, 12, 20.0,
                    25.5, 15.5, 15.5);
    changeRectDimension = new ChangeDimension(this.rect2, 11, 12,
            20.0, 25.5, 15.5, 15.5);
    moveEclipse = new MoveAnimation(this.oval1, 8, 10, this.p1, this.p2);
    moveRect = new MoveAnimation(this.rect1, 8, 10, this.p1, this.p2);
  }

  // Test for adding an oval to a model builder
  @Test
  public void addOval() {
    this.builder.addOval(oval1.getName(), 0, 0, 10, 10,
            1, 1, 1, 0, 10);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(this.oval1)), builder.build().getShapes());
  }

  // Test for adding a rectangle to a model builder
  @Test
  public void addRectangle() {
    this.builder.addRectangle(rect1.getName(), 0, 0, 10, 10,
            1, 1, 1, 0, 10);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(this.rect1)), builder.build().getShapes());
  }

  // Test for adding a change dimension animation to a model builder
  @Test
  public void addChangeDimension() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addScaleToChange("rectangle 2", (float) 20.0, (float) 25.5, (float) 15.5,
            (float) 15.5, 11, 12);
    assertEquals(1, builder.build().getAnimations().size());
  }

  // Test for adding a change color animation to a model builder
  @Test
  public void addChangeColor() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addColorChange("oval 1", 1, 1, 1, 1, 0,
            0, 5, 10);
    assertEquals(1, builder.build().getAnimations().size());
  }

  // Test for adding a move animation to a model builder
  @Test
  public void addMove() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addMove("oval 1", 0, 0, (float) 102.112,
            (float) 50.5, 8, 10);
    assertEquals(1, builder.build().getAnimations().size());
  }
}