package dt066g.assignments.assignment1.task1;

import java.awt.*;
import java.util.Random;

/**
 * @Author Daniel Westerlund
 * @Version 1.0
 * Triangle class that will extend Paintobject
 * It will randomize a size between MIN_SIZE and MAX_SIZE
 * It will randomize a color to the triangle
 * It will randomize if the triangle will be filled or not
 */
public class Triangle extends PaintObject{
    private static final int MIN_SIZE = 10;
    private static final int MAX_SIZE = 100;
    int size;
    boolean filled = false;
    Color color;

    /**
     * Constructor that will randomize size,color
     * and if the triangle will be filled or not
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Triangle(int x, int y) {
        super(x, y, MAX_SIZE, MIN_SIZE);
        fixSize();
        fixColor();
        fixFilled();
    }

    /**
     * Draws a polygon on the screen.
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        Polygon polygon = createPolygon(x,y);
        g.setColor(color);
        if(filled)
            g.fillPolygon(polygon);
        else
            g.drawPolygon(polygon);
    }

    /**
     * Creates a polygon with edges based on parameter + size
     * @param x - Start x-point
     * @param y - Start y-point
     * @return a polygon
     */
    private Polygon createPolygon(int x, int y){
        Polygon polygon = new Polygon();
        polygon.npoints = 3;
        int[] xx  = new int[]{x, x+size, x+size};
        int[] yy  = new int[]{y, y+size, y};

        polygon.xpoints = xx;
        polygon.ypoints = yy;
        return polygon;
    }

    /**
     * Randomize size of the triangle between
     * MIN_SIZE and MAX_SIZE
     */
    private void fixSize(){
        size = generateRandomNumber(MAX_SIZE, MIN_SIZE);
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
        if(generateRandomNumber(1,0) == 1)
            filled = true;
    }

    /**
     * Randomize a number between max and min (inc)
     * @param max max number (inc)
     * @param min min number (inc)
     * @return a randomize integer
     */
    private int generateRandomNumber(int max, int min){
        Random rand = new Random();
        return rand.nextInt((max-min + 1) + min);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}