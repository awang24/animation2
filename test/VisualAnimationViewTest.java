import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

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
import cs3500.animator.view.IView;
import cs3500.animator.view.VisualAnimationView;

import static org.junit.Assert.*;

public class VisualAnimationViewTest {

  IAnimationModel model;
  Shapes rect;
  Shapes oval;
  Posn r1;
  Posn r2;
  Posn c1;
  Posn c2;
  Animations moveR1;
  Animations moveC1;
  Animations changeColorC1;
  Animations moveR2;
  Animations scaleR1;
  IView view;

  List<Shapes> los;


  /**
   * Initializes data.
   */
  @Before
  public void initData() {
    this.model = new SimpleAnimationModel();
    this.r1 = new Posn(200, 200);
    this.r2 = new Posn(300, 300);
    this.c1 = new Posn(500, 100);
    this.c2 = new Posn(500, 400);
    this.rect = new RectangleShape("R", 1, 100,
            this.r1, Color.RED, 50, 100);
    this.oval = new Oval("O", 6, 100, this.c1, Color.BLUE, 60, 30);
    this.moveR1 = new MoveAnimation(this.rect, 10, 50, this.r1, this.r2);
    this.moveC1 = new MoveAnimation(this.oval, 20, 70, this.c1, this.c2);
    this.changeColorC1 = new ChangeColor(this.oval, 50, 80, Color.BLUE, Color.GREEN);
    this.moveR2 = new MoveAnimation(this.rect, 70, 100, this.r2, this.r1);
    this.scaleR1 = new ChangeDimension(this.rect, 51, 70, 50,
            100, 25, 100);

    model.addShape(this.rect);
    model.addShape(this.oval);
    model.addAnimations(this.moveR1);
    model.addAnimations(this.moveC1);
    model.addAnimations(this.changeColorC1);
    model.addAnimations(this.moveR2);
    model.addAnimations(this.scaleR1);
    this.los = model.getShapes();

    view = new VisualAnimationView(10, model.getShapes());
    //controller = new TextController(model, (TextualView) view, "output");
  }

  // test for getting the description
  @Test(expected = UnsupportedOperationException.class)
  public void testGetDescription() throws Exception {
    this.view.getDescription();
  }

  // test for writing out to a file
  @Test(expected = UnsupportedOperationException.class)
  public void testWriteOut() throws Exception {
    this.view.writeOut("output.svg");
  }

  // test for getting the tempo
  @Test(expected = UnsupportedOperationException.class)
  public void getTempo() throws Exception {
    this.view.getTempo();
  }

  // Test for making visible
  @Test
  public void testMakeVisible() {
    assertEquals(false, ((VisualAnimationView)this.view).isVisible());
    this.view.makeVisible();
    assertEquals(true, ((VisualAnimationView)this.view).isVisible());
  }

/*  // Test for showing error message
  @Test
  public void testShowErrorMessage() {
    assertEquals("", this.view.showErrorMessage("error"));
    this.view.showErrorMessage("error");
  }

  // Test for refresh
  @Test
  public void testRefresh() {
    this.view.refresh();
  }*/

  // Test for setting list of shapes
  @Test
  public void testSetShapes() {
    assertEquals(this.los, this.view.getShapes());
    this.view.setShapes(new ArrayList<Shapes>());
    assertEquals(new ArrayList<Shapes>(), this.view.getShapes());
  }

/*  // Test for setting button listener
  @Test(expected = UnsupportedOperationException.class)
  public void setButtonListener() {
    this.view.setButtonListener(new ActionListener);
  }*/

  // Test for getting filename command
  @Test(expected = UnsupportedOperationException.class)
  public void getFilenameCommand() {
    this.view.getFilenameCommand();
  }

  // Test for getting check box list
  @Test(expected = UnsupportedOperationException.class)
  public void getCheckBoxList() {
    this.view.getCheckBoxList();
  }

  // Test for getting list of shapes
  @Test
  public void getShapes() {
    assertEquals(this.los, this.view.getShapes());
  }

  // Test for getting list of animations
  @Test(expected = UnsupportedOperationException.class)
  public void getAnimations() {
    assertEquals(this.model.getAnimations(), this.view.getAnimations());
  }

  // Test for setting the boolean is loop
  @Test(expected = UnsupportedOperationException.class)
  public void setIsLoop() {
    this.view.setIsLoop(true);
  }

  // Test for getting the boolean is loop
  @Test(expected = UnsupportedOperationException.class)
  public void testGetLoop() {
    this.view.getIsLoop();
  }


}