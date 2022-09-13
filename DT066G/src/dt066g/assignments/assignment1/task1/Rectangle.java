package dt066g.assignments.assignment1.task1;

import java.awt.*;
import java.util.Random;

/**
 * @Author Daniel Westerlund
 * Rectangle class that will extend Paintobject
 * It will randomize a size between MIN_SIZE and MAX_SIZE
 * It will randomize a color to the rectangle
 * It will randomize if the rectangle will be filled or not
 */

public class Rectangle extends PaintObject {
    private static final int MIN_SIZE = 10;
    private static final int MAX_SIZE = 100;
    boolean filled = false;
    Color color;

    /**
     * Constructor that takes x,y-coordinates
     * @param x
     * @param y
     */
    public Rectangle(int x, int y) {
        super(x, y, generateRandomNumber(MAX_SIZE,MIN_SIZE), 0);
        fixColor();
        fixFilled();
        fixSize();
    }

    /**
     * Generates a random max size between MAX_SIZEand MIN_SIZE
     *
     * @return
     */
    private static int generateRandomNumber(int max, int min) {
        return (int) ((Math.random() * max) + min);
    }

    /**
     * Draws a rectangle based on attributes
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if(filled)
            g.fillRect(x,y,size,size);
        else
            g.drawRect(x,y,size,size);
    }

    /**
     * Randomize size of the triangle between
     * MIN_SIZE and MAX_SIZE
     */
    private void fixSize(){
        super.size = generateRandomNumber(MAX_SIZE, MIN_SIZE);
    }

    /**
     * Randomize a color of the triangle
     */
    private void fixColor(){
        int red = generateRandomNumber(255,0);
        int green = generateRandomNumber(255,0);
        int blue = generateRandomNumber(255,0);
        this.color = new Color(red,green,blue);
    }

    /**
     * Randomize if polygon should be filled or not.
     */
    private void fixFilled(){
        if(generateRandomNumber(11,0) % 2 == 0)
            filled = true;
    }
}
