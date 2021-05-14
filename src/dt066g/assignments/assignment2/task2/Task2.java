package dt066g.assignments.assignment2.task2;

import java.io.File;
import java.util.*;

/**
 * @author Your Name
 * @version 1.0
 */
public class Task2 {
	private static final int SIZE = 1_000_000; // one million
	private static final File UNBUFFERED_FILE =  new File("assets" + File.separator + "assignment2", "temps_unbuffered.dat");
	private static final File BUFFERED_FILE = new File("assets"  + File.separator + "assignment2", "temps_buffered.dat");
	
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
	
	private static List<Temp> createTempObjects(int numberOfObjects) {
		List<Temp> temps = new ArrayList<>(numberOfObjects);
		
		// TODO: Add code to create 'numberOfObjects' Temp objects with
		// random temperature (range: −273 to 1000 inclusive)
		
		return temps;
	}

	private static long saveUnbuffered(List<Temp> temps, File file) {
		long start = System.currentTimeMillis();

		// TODO: Add code to save object for object in 'temps' to 'file' using
		// ObjectOutputStream and FileOutputStream

		return System.currentTimeMillis() - start;
	}

	private static long saveBuffered(List<Temp> temps, File file) {
		long start = System.currentTimeMillis();
		
		// TODO: Add code to save object for object in 'temps' to 'file' using
		// ObjectOutputStream, BufferedOutputStream and FileOutputStream

		return System.currentTimeMillis() - start;
	}
}