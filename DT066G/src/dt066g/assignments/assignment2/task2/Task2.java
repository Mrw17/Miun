package dt066g.assignments.assignment2.task2;

import java.io.*;
import java.util.*;

/**
 * @author Daniel Westerlund
 * @version 1.0
 */
public class Task2 {
	private static final int SIZE = 1_000_000; // one million
	private static final File UNBUFFERED_FILE =  new File("src/assets" + File.separator + "assignment2", "temps_unbuffered.dat");
	private static final File BUFFERED_FILE = new File("src/assets"  + File.separator + "assignment2", "temps_buffered.dat");

	/**
	 * Program that will generate 1_000_000 temp objects in a list
	 * Will benchmark the output between unbuffered / buffered output stream
	 * @param args argument to the program
	 */
	public static void main(String[] args) {
		System.out.print("Creating temp objects... ");
		List<Temp> temps = createTempObjects(SIZE);
		System.out.println("done");

		System.out.print("Saving unbuffered... ");
		long timeUnbuffered = saveUnbuffered(temps, UNBUFFERED_FILE);
		System.out.println(timeUnbuffered + " ms");

		System.out.print("Saving buffered... ");
		long timeBuffered = saveBuffered(temps, BUFFERED_FILE);
		System.out.println(timeBuffered + " ms");
	}

	/**
	 * Creates temp objects with random temp and saves them in a list
	 * @param numberOfObjects number of temp-objects to be generated
	 * @return list with temp objects
	 */
	private static List<Temp> createTempObjects(int numberOfObjects) {
		List<Temp> temps = new ArrayList<>(numberOfObjects);
		int max = 1000;
		int min = -271;
		Random random = new Random();

		for(int i = 0; i < numberOfObjects; i++){
			int randomNumber = random.nextInt((max - min + 1) + min);
			if(randomNumber <= min * (-1))
				randomNumber = randomNumber *  (random.nextBoolean() ? -1 : 1);

			Temp newTemp = new Temp(randomNumber);
			temps.add(newTemp);
		}

		return temps;
	}

	/**
	 * Saves all temp objects with a unbuffered output-stream to a file and return the time it took
	 * @param temps all temp-objects to be saved
	 * @param file file to be saved to
	 * @return how long it took
	 */
	private static long saveUnbuffered(List<Temp> temps, File file) {
		long start = System.currentTimeMillis();
		try {
			//Creates new file it it doesn't exists
			if(!file.exists()) {
				if(file.createNewFile())
					System.out.println("New filed created: "  +file.getAbsolutePath());
			}

			ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(file));

			// Writes all objects to file
			for(Temp t : temps){
				objectOutput.writeObject(t);
			}

			objectOutput.close();

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis() - start;
	}

	/**
	 * Saves all temp objects with a buffered output-stream to a file and return the time it took
	 * @param temps all temps to be saved
	 * @param file to be saved to
	 * @return how long it took
	 */
	private static long saveBuffered(List<Temp> temps, File file) {
		long start = System.currentTimeMillis();
		try {
			//Creates new file it it doesn't exists
			if(!file.exists()) {
				if(file.createNewFile())
					System.out.println("New filed created: "  +file.getAbsolutePath());
			}

			ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

			// Writes all objects to file
			for(Temp t : temps){
				output.writeObject(t);
			}

			output.close();
			}
		catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return System.currentTimeMillis() - start;
	}
}