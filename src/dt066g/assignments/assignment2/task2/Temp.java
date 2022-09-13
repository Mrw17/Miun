package dt066g.assignments.assignment2.task2;

import java.io.Serializable;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */
public class Temp implements Serializable {
	private static final long serialVersionUID = 1L;	
	private int temp;

	public Temp(int temp) {
		this.temp = temp;
	}

	@Override
	public String toString() {
		return temp + " °C";
	}
}