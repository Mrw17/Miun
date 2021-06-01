package dt066g.assignments.assignment5.task1.server;

import dt066g.assignments.assignment5.task1.common.MathExpression.*;

/**
 * @author Daniel Westerlund
 *
 * Class that calculates two number based on what Operator it is
 */
public class MathCalculator {

    /**
     * Calculates two number based on operator
     * @param num1 number 1
     * @param num2 number2
     * @param operator operation that should be done
     * @return result of the operation
     */
    public int calculateRes(int num1, int num2, Operator operator){
        if(operator == Operator.ADDITION)
            return num1 + num2;
        if(operator == Operator.SUBTRACTION)
            return num1 - num2;
        if(operator == Operator.DIVISION)
            return num1 / num2;
        if(operator == Operator.MULTIPLICATION)
            return num1 * num2;

        return -1;
    }
}
