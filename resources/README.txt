After adding new functionality to EasyAnimator, very minimal changes were made from the
previous assignment.  To accommodate the rendering of shapes (and allowing the user to
choose what shapes to render), we added a boolean flag to our shapes to determine whether
they should be rendered or not.  Additionally, we consolidated our multiple view interfaces
into one IView interface, and suppressed functionality in all views that implemented it by
overriding methods.  Additionally, to further separate our views from our model, we had
views access a list of shapes and a list of animations rather than the model itself.

For our InteractiveView, we combined the functionality of our SVG View and Visual View using
the IView interface and suppressed all other unneeded functionality.  As far as a User
Interface, we felt that having users click labeled buttons would be more intuitive than
keyboard input where they would have to figure out which keys were linked with functionality.
On the bottom panel of the JFrame, the user can play, pause, restart, increase/decrease speed
(incrementally by pressing the button), and loop the animation.  On the right hand side of the
JFrame, the user can select shapes to render if and only if the animation is not running.  To
ensure this, the checkboxes are "grayed out" in the event the animation is running.

For the InteractiveController, the controller implemented the existing overarching
IAnimationController.  The controller uses a Timer to keep track of when animations would render
and designates functionality of buttons with respect to the timer. Since animation rendering is
determined by the timer, the buttons essentially control when animations start, stop, loop, etc.
To ensure that users could not select shapes while an animation is running, we "grayed out" the
checkboxes associated with specific shapes in the event the timer was not paused/stopped.

For adding loop functionality to SVG files, we added a method in the Animations that returned the
proper SVG tag assuming that the animation was looping, in addition to the existing SVG tag.  In
the getDescription method, a base SVG tag was added to take care of looping functionality.