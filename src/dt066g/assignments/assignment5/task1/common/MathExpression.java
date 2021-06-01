package dt066g.assignments.assignment5.task1.common;

import java.io.Serializable;
import java.util.Random;

public class MathExpression implements Serializable {
	private static final long serialVersionUID = 1L;

	// The supported operators to use in a MathExpression
	public static enum Operator {
		ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("x"), DIVISION("/");
		
		private String operator;

		Operator(String operator) {
			this.operator = operator;
		}
		
		public static Operator getRandom() {
	        return values()[(int) (Math.random() * values().length)];
	    }
	};
	
	// For generating a random expression
	private static Random random = new Random();
	
	// Max and min values for the two numbers (inclusive)
	private static final int MAX = 20;
	private static final int MIN = 0;
	
	// A MathExpression consist of two numbers and an operator
	private int number1;
	private int number2;
	private Operator operator;
	
	// The answer from the user
	private int userAnswer;
	
	// Constructs a new expression with random values
	public MathExpression() {
		this.number1 = getRandomNumber();
		this.operator = getRandomOperator();
		// we should check that number2 is non zero if operator is DIVISION
		this.number2 = getRandomNumber();
	}

	// Constructs a new expression with given values
	public MathExpression(int number1, Operator operator, int number2) {
		// we should check that number1 and number2 is within MIN and MAX
		this.number1 = number1;
		this.operator = operator;
		this.number2 = number2;
	}
	
	// Returns a random operator
	private Operator getRandomOperator() {
		return Operator.getRandom();
	}

	// Returns a random number between MIN and MAX (inclusive)
	private int getRandomNumber() {
		return random.nextInt((MAX - MIN + 1)) - MIN;
	}

	public void setUserAnswer(int userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	public int getUserAnswer() {
		return userAnswer;
	}

	public int getNumber1() {
		return number1;
	}

	public int getNumber2() {
		return number2;
	}

	public Operator getOperator() {
		return operator;
	}

	// Returns the expression as a String (e.g. 4 + 4)
	@Override
	public String toString() {
		return String.format("%s %s %s",  number1, operator.operator, number2);
	}
}