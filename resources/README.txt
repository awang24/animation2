After adding new functionality to EasyAnimator, very minimal changes were made from the
previous assignment.  To accomodate the rendering of shapes (and allowing the user to
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