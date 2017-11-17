import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

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
import cs3500.animator.view.SVGView;

import static org.junit.Assert.assertEquals;

public class SVGViewTest {
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
  SVGView view;

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
    model.addAnimations(this.scaleR1);
    model.addAnimations(this.moveR2);

    view = new SVGView(10, model.getShapes(), model.getAnimations());
  }

  // Test for getting the description of an empty view that has not shapes and animations
  @Test
  public void getDescriptionEmptyView() {
    SVGView empty = new SVGView(10, new ArrayList<Shapes>(), new ArrayList<Animations>());

    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "</svg>", empty.getDescription());
  }

  // Test for getting the description
  @Test
  public void getDescription() {
    assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" "
            + "dur=\"1900.0ms\" attributeName=\"width\" from=\"50.0\" to=\"25.0\" "
            + "fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" "
            + "dur=\"1900.0ms\" attributeName=\"height\" from=\"100.0\" to=\"100.0\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
            + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
            + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "\n"
            + "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
            + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
            + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"3000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>", this.view.getDescription());
  }

  // Test for writing out an empty view
  @Test
  public void writeOutEmptyView() {
    SVGView empty = new SVGView(10, new ArrayList<Shapes>(), new ArrayList<Animations>());

    empty.writeOut("test/mt.svg");

    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("test/mt.svg"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
              + "xmlns=\"http://www.w3.org/2000/svg\">\n"
              + "</svg>\n", sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }
  }


  // Test for writing out to a file
  @Test
  public void writeOut() {
    this.view.writeOut("test/outSVG.svg");

    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("test/outSVG.svg"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      assertEquals("<svg width=\"700\" height=\"500\" version=\"1.1\"\n"
              + "xmlns=\"http://www.w3.org/2000/svg\">\n"
              + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
              + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
              + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
              + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
              + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" "
              + "dur=\"1900.0ms\" attributeName=\"width\" from=\"50.0\" to=\"25.0\" "
              + "fill=\"freeze\" /> \n"
              + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" "
              + "dur=\"1900.0ms\" attributeName=\"height\" from=\"100.0\" to=\"100.0\" "
              + "fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
              + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
              + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
              + "</rect>\n"
              + "\n"
              + "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
              + "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n"
              + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
              + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
              + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"3000.0ms\" "
              + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" "
              + "fill=\"freeze\" />\n"
              + "</ellipse>\n"
              + "</svg>\n", sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }

  }

}