import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import cs3500.animator.controller.InteractiveController;
import cs3500.animator.model.SimpleAnimationModel;
import cs3500.animator.view.IView;
import cs3500.animator.view.InteractiveView;

import static org.junit.Assert.assertEquals;

public class InteractiveControllerTest {

  JButton playButton;
  JButton pauseButton;
  JButton restartButton;
  JButton increaseSpeedButton;
  JButton decreaseSpeedButton;
  JButton fileButton;
  JCheckBox loopCheckbox;

  InteractiveController controller;
  SimpleAnimationModel model;
  IView view;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    this.playButton = new JButton("PLAY");
    this.pauseButton = new JButton("PAUSE");
    this.restartButton = new JButton("RESTART");
    this.increaseSpeedButton = new JButton("INCREASE SPEED");
    this.decreaseSpeedButton = new JButton("DECREASE SPEED");
    this.fileButton = new JButton("FILE");
    this.loopCheckbox = new JCheckBox("LOOP");

    this.model = new SimpleAnimationModel();
    this.view = new InteractiveView(50, model.getShapes(), model.getAnimations(),
            10);
    this.controller = new InteractiveController(this.model, this.view, 50,
            "interactive.svg");
  }

  //Test to see that the start method starts the timer
  @Test
  public void testStart() {
    this.controller.start();
    assertEquals(false, this.controller.getTimer().isRunning());

    ActionEvent e = new ActionEvent(playButton, 1, "PLAY");
    this.controller.actionPerformed(e);
    assertEquals(true, this.controller.getTimer().isRunning());

  }

  //Test for action performed method
  @Test
  public void testActionPerformed() {
    ActionEvent e = new ActionEvent(playButton, 1, "PLAY");
    ActionEvent e1 = new ActionEvent(pauseButton, 2, "PAUSE");
    ActionEvent e2 = new ActionEvent(restartButton, 3, "RESTART");
    ActionEvent e3 = new ActionEvent(increaseSpeedButton, 4, "INCREASE SPEED");
    ActionEvent e4 = new ActionEvent(decreaseSpeedButton, 5, "DECREASE SPEED");
    ActionEvent e5 = new ActionEvent(fileButton, 6, "FILE");
    ActionEvent e6 = new ActionEvent(loopCheckbox, 7, "LOOP");

    this.controller.start();
    assertEquals("", this.controller.getLog().toString());
    this.controller.actionPerformed(e);
    assertEquals(true, this.controller.getTimer().isRunning());
    assertEquals("Play Button hit. \n", this.controller.getLog().toString());

    this.controller.actionPerformed(e1);
    assertEquals(false, this.controller.getTimer().isRunning());

    this.controller.actionPerformed(e2);
    assertEquals(true, this.controller.getTimer().isRunning());

    this.controller.actionPerformed(e3);
    this.controller.actionPerformed(e4);
    this.controller.actionPerformed(e5);
    this.controller.actionPerformed(e6);
    assertEquals("Play Button hit. \n"
            + "Pause Button hit. \n"
            + "Restart Button hit. \n"
            + "Increase Speed Button hit. \n"
            + "Decrease Speed Button hit. \n"
            + "Loop Button hit. \n", this.controller.getLog().toString());
  }

}