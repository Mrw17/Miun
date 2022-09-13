package dt066g.assignments.assignment1.task2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 *
 * Class that represents a Football Field
 * @author Daniel Westerlund
 */
public class FootballField extends JPanel  implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	
	private final double LENGTH = 100; // Length in meter
	private final double WIDTH = 64; // Width in meter
	private final double LINE_WIDTH = 0.24; // Line width in meter (normally max 12 centimeters)
	private final static int SIZE_TO_PIXEL_CONSTANT = 10;
	private final static int UPDATE_DELAY = 100; // Default delay between updates in ms
	int footballLifeCounter = 0; // Counter that keeps track on when to decrease a footballs "life"
	private AffineTransform original = null;
	private AffineTransform centerAndScale = null;
	ArrayList<Football> footballs = new ArrayList<>(); //All footballs

	/**
	 * Default constructor that will create default listeners
	 * Will create a timer that updates the graphic every UPDATE_DELAY
	 */
	public FootballField() {
		//testData();
		Timer t = new Timer(UPDATE_DELAY, this);
		t.start();

		// The panel must listen for mouse events (left, right click) so we register
		// our own class as a listener after such events
		addMouseListener(this);

	}

	/**
	 * Method that will create some footballs
	 */
	private void testData(){
		for (int i = 1; i < 10; i++) {
			Football fb = new Football(i * 100, i * 50);
			footballs.add(fb);
		}
	}

	/**
	 * Event that will update a footballs:
	 * coordinates
	 * Remove all inactive footballs
	 * Decrease all footballs life
	 * And repaint all footballs
	 * @param e ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		updateCoordinates();
		removeInactiveFootballs();
		decreaseFootballsLife();
		super.repaint();
	}

	/**
	 * By using a counter that increase every UPDATE_DELAY
	 * it will decrease footballs life by 1 every second
	 */
	private void decreaseFootballsLife() {
		if(footballLifeCounter == (1000/UPDATE_DELAY)) {
			footballLifeCounter = 0;
			for (Football football : footballs) {
				football.decreaseLife();
			}
		}
		else
			footballLifeCounter++;
	}

	/**
	 * Removes all footballs that are inactive(dead by time or size)
	 *
	 */
	private void removeInactiveFootballs() {
		ArrayList<Football>inactiveFootballs = new ArrayList<>(); // List of all footballs that are dead
		for(Football football : footballs){
			//Size or life == 0
			if(football.getSize() <= 0 || football.getLife() <= 0)
				inactiveFootballs.add(football);
		}

		//Remove all inactive footballs from footballs list
		if(inactiveFootballs.size() > 0)
			footballs.removeAll(inactiveFootballs);


	}

	/**
	 * Update all footballs coordinates
	 */
	private void updateCoordinates(){
		for(Football football : footballs){
			fixXCoordinates(football);
			fixYCoordinates(football);
		}
	}

	/**
	 * Fixes a football y-coordinate by
	 * checking if it bounces to top or bottom of the football field
	 * @param football to be updated
	 */
	private void fixYCoordinates(Football football){
		if(football.getY() <= 0)
			bounceTop(football);
		else if(football.getY() >= getHeight() - (football.getSize()*SIZE_TO_PIXEL_CONSTANT))
			bounceBottom(football);
		else
			moveFootballY(football);

	}

	/**
	 * Decrease a footballs size
	 * @param football to be decreased
	 */
	private void decreaseFootballSize(Football football){
		football.setSize(football.getSize()-1);
	}

	/**
	 * Updates a footballs y-coordinate
	 * @param football to be updated
	 */
	private void moveFootballY(Football football) {
		football.setY(football.getY() + football.getSpeedY());
	}

	/**
	 * If a football bounces to the bottom
	 * it will decrease its life
	 * reverse its y-speed
	 * @param football to be updated
	 */
	private void bounceBottom(Football football) {
		decreaseFootballSize(football);
		football.setSpeedY(-football.getSpeedY());
		football.setY(football.getY() - (football.getSize()*SIZE_TO_PIXEL_CONSTANT));
	}

	/**
	 * If a football bounces to the top
	 * it will decrease its life
	 * reverse its y-speed
	 * @param football to be updated
	 */
	private void bounceTop(Football football) {
		decreaseFootballSize(football);
		football.setSpeedY(-football.getSpeedY());
		football.setY(football.getY() + (football.getSize()*SIZE_TO_PIXEL_CONSTANT));
	}

	/**
	 * Updates a footballs x-coordinates
	 * by checking if bounces to the right or left of the football-field
	 * else if will move the ball forward
	 * @param football to be updated
	 */
	private void fixXCoordinates(Football football){
		if(football.getX() >= getWidth() - (football.getSize()*SIZE_TO_PIXEL_CONSTANT))
			bounceRight(football);
		else if(football.getX() <= 0)
			bounceLeft(football);
		else
			moveFootballX(football);

	}

	/**
	 * If the football bounces to the right it will
	 * decrease its life
	 * reverse its speed
	 * @param football to be updated
	 */
	private void bounceRight(Football football){
		decreaseFootballSize(football);
		football.setSpeedX(-football.getSpeedX());
		football.setX(football.getX() - (football.getSize()*SIZE_TO_PIXEL_CONSTANT));
	}

	/**
	 *
	 * @param football to be updated
	 */
	private void bounceLeft(Football football){
		decreaseFootballSize(football);
		football.setSpeedX(-football.getSpeedX());
		football.setX(football.getX() + (football.getSize()*SIZE_TO_PIXEL_CONSTANT));
	}

	/**
	 * Moves a football forward, new coordinates is old + speed
	 * @param football to be moved
	 */
	private void moveFootballX(Football football){
		football.setX(football.getX() + football.getSpeedX());
	}

	/**
	 * Displays how many active footballs there is
	 * Number is taking for how big footballs-list is.
	 * @param g Graphic object that will be shown
	 */
	private void currentFootballsLbl(Graphics g){
		String output = "Antal fotbollar: %s".formatted(footballs.size());
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		//Where it be displayed
		g2.drawString(output,getWidth()/2,50);
	}

	/**
	 * When a mouse button is clicked it will generate a football
	 * at that (x,y)-coordinates
	 * @param me
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		// Find out if left or right mouse button was clicked
		if (me.getButton() == MouseEvent.BUTTON1) {
			Football fb = new Football(me.getX(), me.getY());
			footballs.add(fb);
		}
	}

	/**
	 * Will paint:
	 * Football-field
	 * All footballs
	 * Current footballs label
	 * @param g
	 */
	@Override
	public void paintComponent(Graphics g) {
		drawFootballField(g);
		currentFootballsLbl(g);
		showFootballImage(g);
	}

	/**
	 * Displays a footballs-image based on which size it has
	 * @param g
	 */
	private void showFootballImage(Graphics g){
		for (Football f:footballs){
			Image img = createImageIcon(f.getSize());
			g.drawImage(img, f.getX(), f.getY(),null);
		}

	}

	/**
	 * Creates an image from footballs-images based on its size
	 * @param size
	 * @return
	 */
	private Image createImageIcon(int size){
		String fileExtentsion = ".png";
		String path = "src/assets/assignment1/football" + size + fileExtentsion;
		return new ImageIcon(path).getImage();
	}

	/**
	 * Draws the football-field
	 * @param g
	 */
	private void drawFootballField(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Calculate scale (so that the field always is as large as it can be in the frame) 
		final double SCALE = Math.min(getWidth() / LENGTH, getHeight() / WIDTH);

		// Save current transform
		original = g2.getTransform();

		// Create a new transform to center and scale the field
		centerAndScale = new AffineTransform();
		centerAndScale.translate((getWidth() - LENGTH * SCALE) / 2,
				(getHeight() - WIDTH * SCALE) / 2);
		centerAndScale.scale(SCALE, SCALE);
		
		// Use the new transform when drawing
		g2.transform(centerAndScale);

		// Draw the field and lines surrounding it
		g2.setPaint(new Color(2, 152, 52));
		g2.fill(new Rectangle2D.Double(0, 0, LENGTH, WIDTH));
		g2.setStroke(new BasicStroke((float) (LINE_WIDTH)));
		g2.setPaint(Color.white);
		g2.draw(new Rectangle2D.Double(0, 0, LENGTH, WIDTH));

		// Draw center line and circle
		g2.draw(new Line2D.Double(LENGTH / 2, 0, LENGTH / 2, WIDTH));
		g2.fill(new Ellipse2D.Double(LENGTH / 2 - 0.5 / 2, WIDTH / 2 - 0.5 / 2, 	0.5, 0.5));
		g2.draw(new Ellipse2D.Double(LENGTH / 2 - 9.15, WIDTH / 2 - 9.15, 9.15 * 2, 9.15 * 2));

		// Draw the four corners
		g2.draw(new Arc2D.Double(-1, -1, 2, 2, 270, 90, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-1, -1 + WIDTH, 2, 2, 0, 90, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-1 + LENGTH, -1, 2, 2, 180, 90, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-1 + LENGTH, -1 + WIDTH, 2, 2, 90, 90,	Arc2D.OPEN));

		// Draw the penalty areas
		g2.draw(new Rectangle2D.Double(0, WIDTH / 2 - 30.32 / 2, 16.5, 30.32));
		g2.draw(new Rectangle2D.Double(LENGTH - 16.5, WIDTH / 2 - 30.32 / 2, 16.5, 30.32));

		// Draw goal areas
		g2.draw(new Rectangle2D.Double(0, WIDTH / 2 - 12.82 / 2, 5.5, 12.82));
		g2.draw(new Rectangle2D.Double(LENGTH - 5.5, WIDTH / 2 - 12.92 / 2, 5.5, 12.82));

		// Draw penalty shot circle
		g2.fill(new Ellipse2D.Double(11, WIDTH / 2 - 0.5 / 2, 0.5, 0.5));
		g2.fill(new Ellipse2D.Double(-11 + LENGTH, WIDTH / 2 - 0.5 / 2, 0.5, 0.5));

		// Draw an arc outside the Penalty areas
		g2.draw(new Arc2D.Double(11, WIDTH / 2 - 9.15 / 2, 9.15, 9.15, 285,	150, Arc2D.OPEN));
		g2.draw(new Arc2D.Double(-(11 + 9.15) + LENGTH, WIDTH / 2 - 9.15 / 2, 9.15, 9.15, 105, 150, Arc2D.OPEN));

		// Reset original transform
		g2.setTransform(original);
	}

	/**
	 * When a mouse-button is pressed nothing is happening
	 * @param ignore
	 */
	@Override
	public void mousePressed(MouseEvent ignore) {

	}

	/**
	 * When a mouse-button is released nothing is happening
	 * @param ignore
	 */
	@Override
	public void mouseReleased(MouseEvent ignore) {

	}

	/**
	 * When a mouse-button is entering a component nothing is happening
	 * @param ignore
	 */
	@Override
	public void mouseEntered(MouseEvent ignore) {

	}

	/**
	 * When a mouse-button is leaving a component nothing is happening
	 * @param ignore
	 */
	@Override
	public void mouseExited(MouseEvent ignore) {

	}



}