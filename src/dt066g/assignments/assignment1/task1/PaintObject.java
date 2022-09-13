package dt066g.assignments.assignment1.task1;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */

import java.awt.event.*;
import javax.swing.*;

public abstract class PaintObject implements Drawable, GrowableShrinkable, ActionListener {
	// Protected so subclasses can access the instance variables directly
	protected int x;
	protected int y;
	protected int size; // current size
	protected State state; // current state
	protected final int MAX_SIZE; // maximum size
	protected final int MIN_SIZE; // minimum size

	public PaintObject(int x, int y, int max, int min) {
		this.x = x;
		this.y = y;
		MAX_SIZE = max;
		MIN_SIZE = min;

		// Set initial size to the minimum size
		size = MIN_SIZE;
		
		// Set initial state to growing
		state = State.GROWING;

		/*
		 * Creates and starts a Timer.
		 * Calculate how long it takes for us to grow from our smallest size to our largest.
		 * This method is not optimal for objects with large sizes as the time between each 
		 * event generated becomes too short. Ideally, we should make sure that the time is 
		 * about 50-100ms and instead increase the number of pixels we increase and decrease
		 * our size by.*/
		Timer t = new Timer(1000 / (MAX_SIZE - MIN_SIZE), this);
		t.start();
	}

	// This method is called to let us grow
	@Override
	public void grow() {
		// Only grow as long as our size is less than maximum allowed
		if (size < MAX_SIZE) {
			size++;
		} else {
			// We can not grow any more. Change our state to shrinking.
			state = State.SHRINKING;
		}
	}

	// // This method is called to let us shrink
	@Override
	public void shrink() {
		// Only shrink as long as our size is greater than minimum allowed
		if (size > MIN_SIZE) {
			size--;
		} else {
			// We can not shrink any more. Change our state to growing.
			state = State.GROWING;
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// This method is called every time the Timer fires an event
		// Check our current state to decide if we want to grow or shrink
		if (state == State.GROWING) {
			grow();
		} else if (state == State.SHRINKING) {
			shrink();
		}
	}
}

/*
 * All objects that can shrink and grow do so by reducing or increasing the 
 * size by one. Therefore, these TV methods can be implemented directly in 
 * this superclass.
 * 
 * However, the draw method from the Drawable interface cannot be implemented 
 * because the way to draw different objects differs. Implementing the draw 
 * method must be done in the subclasses.
 */