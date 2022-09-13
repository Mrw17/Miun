package dt066g.assignments.assignment1.task1;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */
public interface GrowableShrinkable extends Growable, Shrinkable {
	// An enumeration for all different states a Growable and/or Shrinkable object can have
	enum State {NOT_MOVING, GROWING, SHRINKING};
}