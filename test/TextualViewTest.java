import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs3500.animator.model.IAnimationModel;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.model.Utils;
import cs3500.animator.model.animation.Animations;
import cs3500.animator.model.animation.ChangeColor;
import cs3500.animator.model.animation.ChangeDimension;
import cs3500.animator.model.animation.MoveAnimation;
import cs3500.animator.model.shape.Oval;
import cs3500.animator.model.shape.Posn;
import cs3500.animator.model.shape.RectangleShape;
import cs3500.animator.model.shape.Shapes;
import cs3500.animator.view.IView;
import cs3500.animator.view.TextualView;

import static org.junit.Assert.assertEquals;

public class TextualViewTest {

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
  //IAnimationController controller;

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

    view = new TextualView(10, model.getShapes(), model.getAnimations());
    //controller = new TextController(model, (TextualView) view, "output");
  }

/*  // Test for getting the model
  @Test
  public void getModel() {
    assertEquals(this.model, this.view.getModel());
  }*/

  // Test for get tempo
  @Test
  public void testGetTempo() {
    assertEquals(true,
            Utils.checkDoubles(10, this.view.getTempo()));
  }

  // Test for getting the description
  @Test
  public void testGetDescription() {
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Lower-left corner: (200.0, 200.0), Width: 50.0, Height: 100.0, "
                    + "Color: (1.0,0.0,0.0)\n" + "Appears at t=0.1s\n"
                    + "Disappears at t=10.0s\n" + "\n" + "Name: O\n" + "Type: oval\n"
                    + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, "
                    + "Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=0.6s\n" + "Disappears at t=10.0s\n" + "\n"
                    + "shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1.0s to t=5.0s\n"
                    + "shape O moves from (500.0, 100.0) to (500.0, 400.0) from t=2.0s to t=7.0s\n"
                    + "shape O changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) "
                    + "from t=5.0s to t=8.0s\n"
                    + "shape R scales from Width: 50.0, "
                    + "Height: 100.0 to Width: 25.0, Height: 100.0 "
                    + "from t=5.1s to t=7.0s\n"
                    + "shape R moves from (300.0, 300.0) to (200.0, 200.0) "
                    + "from t=7.0s to t=10.0s\n",
            this.view.getDescription());

    TextualView empty = new TextualView(10, new ArrayList<Shapes>(), new ArrayList<Animations>());

    assertEquals("", empty.getDescription());
  }

  // Test for writing out to a file
  @Test
  public void testWriteOut() {
    this.view.writeOut("test/output.txt");

    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("test/output.txt"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      assertEquals("Shapes:\n"
              + "Name: R\n"
              + "Type: rectangle\n"
              + "Lower-left corner: (200.0, 200.0), Width: 50.0, Height: 100.0, "
              + "Color: (1.0,0.0,0.0)\n"
              + "Appears at t=0.1s\n" + "Disappears at t=10.0s\n" + "\n"
              + "Name: O\n" + "Type: oval\n"
              + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
              + "Appears at t=0.6s\n" + "Disappears at t=10.0s\n" + "\n"
              + "shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1.0s to t=5.0s\n"
              + "shape O moves from (500.0, 100.0) to (500.0, 400.0) from t=2.0s to t=7.0s\n"
              + "shape O changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=5.0s to t=8.0s\n"
              + "shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
              + "from t=5.1s to t=7.0s\n"
              + "shape R moves from (300.0, 300.0) to (200.0, 200.0) "
              + "from t=7.0s to t=10.0s\n", sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }

  }

  // Test for writing out an empty view
  @Test
  public void testWriteOutEmptyView() {
    TextualView empty = new TextualView(10, new ArrayList<Shapes>(), new ArrayList<Animations>());

    empty.writeOut("test/mt.txt");

    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("test/mt.txt"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      assertEquals("", sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }
  }

  // Test for making visible
  @Test(expected = UnsupportedOperationException.class)
  public void testMakeVisible() {
    this.view.makeVisible();
  }

  // Test for showing error message
  @Test(expected = UnsupportedOperationException.class)
  public void testShowErrorMessage() {
    this.view.showErrorMessage("error");
  }

  // Test for refresh
  @Test(expected = UnsupportedOperationException.class)
  public void testRefresh() {
    this.view.refresh();
  }

  // Test for setting list of shapes
  @Test(expected = UnsupportedOperationException.class)
  public void testSetShapes() {
    this.view.setShapes(new ArrayList<Shapes>());
  }

/*  // Test for setting button listener
  @Test(expected = UnsupportedOperationException.class)
  public void setButtonListener() {
    this.view.setButtonListener(new ActionListener);
  }*/

  // Test for getting filename command
  @Test(expected = UnsupportedOperationException.class)
  public void testGetFilenameCommand() {
    this.view.getFilenameCommand();
  }

  // Test for getting check box list
  @Test(expected = UnsupportedOperationException.class)
  public void testGetCheckBoxList() {
    this.view.getCheckBoxList();
  }

  // Test for getting list of shapes
  @Test(expected = UnsupportedOperationException.class)
  public void testGetShapes() {
    this.view.getShapes();
  }

  // Test for getting list of animations
  @Test
  public void testGetAnimations() {
    assertEquals(this.model.getAnimations(), this.view.getAnimations());
  }

  // Test for setting the boolean is loop
  @Test(expected = UnsupportedOperationException.class)
  public void testSetIsLoop() {
    this.view.setIsLoop(true);
  }

  // Test for getting the boolean is loop
  @Test(expected = UnsupportedOperationException.class)
  public void testGetLoop() {
    this.view.getIsLoop();
  }


}