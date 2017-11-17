package cs3500.animator.controller;

import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;

/**
 * Represents the controller for the textual view and implements IAnimationController and its
 * associated operations.
 */
public class SVGController implements IAnimationController {

  private IView view;
  private String filename;

  /**
   * Constructs a {@code TextController} object.
   *
   * @param view     the view that the controller will be using to display
   * @param filename the output file for the controller to write out to
   */
  public SVGController(IView view, String filename) {
    this.view = view;
    this.filename = filename;
  }

  @Override
  public void start() {
    this.view.writeOut(filename);
  }
}
