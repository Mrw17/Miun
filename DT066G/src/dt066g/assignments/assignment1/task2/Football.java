package dt066g.assignments.assignment1.task2;

import java.util.Random;

/**
 * Class that represents a football
 * @author Daniel Westerlund
 */
public class Football {
    private static final int MAX_SIZE = 10;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SPEED = 10;
    private static final int MIN_SPEED = -10;
    private int x;
    private int y;
    private int size = 1;
    private int speedX = 0;
    private int speedY = 0;
    private int life = 10;

    /**
     * Default constructor that takes coordinates on the object
     * Will generate a random size and speed
     * @param x-coordinate
     * @param y-coordinate
     */
    public Football(int x, int y){
        this.x = x;
        this.y = y;
        fixSize();
        fixSpeed();
    }


    /**
     * Generate new speed on x/y-coordinates
     */
    private void fixSpeed(){
        speedX = generateRandomNumberPosAndNeg(MAX_SPEED,MIN_SPEED);
        speedY = generateRandomNumberPosAndNeg(MAX_SPEED,MIN_SPEED);
    }


    private void fixSize(){
        size = generateRandomNumberPosOnly(MAX_SIZE, MIN_SIZE);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void decreaseLife(){
        life = life - 1;
    }

    /**
     * Randomize a number between max and min and randomize + or - (inc)
     * @param max max number (inc)
     * @param min min number (inc)
     * @return a randomize integer
     */
    private int generateRandomNumberPosAndNeg(int max, int min){
        Random rand = new Random();
        return rand.nextInt((max-min + 1) + min) *  (rand.nextBoolean() ? -1 : 1);
    }

    /**
     * Generates a random positive random number
     * @return random positive number between max and min
     */
    private static int generateRandomNumberPosOnly(int max, int min) {
        return (int) ((Math.random() * max) + min);
    }
}

