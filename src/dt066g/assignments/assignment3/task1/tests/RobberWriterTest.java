package dt066g.assignments.assignment3.task1.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dt066g.assignments.assignment3.task1.RobberWriter;

public class RobberWriterTest {

	public static void main(String[] args) {
		final String testString = "Testar att översätta till och från rövarspråket!";
		final char[] testStringAsCharArray = testString.toCharArray();
		final char testStringFirstChar = testString.charAt(0);
		final File path = new File("assets" + File.separator + "assignment3");
		
		try {
			// Test all 4 write methods in RobberWriter
			// The content of the 3 first files should be equal to what is returned by:
			// RobberLanguage.toRobber(testString);
			// The last file (test4.txt) should only contain the first character in the test string translated to robber
			
			// Test 1 - write(String str)
			RobberWriter writer = new RobberWriter(new FileWriter(new File(path, "test1.txt")));
			writer.write(testString);
			writer.close();
			
			// Test 2 - write(String str, int off, int len)
			writer = new RobberWriter(new FileWriter(new File(path, "test2.txt")));
			writer.write(testString, 0, testString.length());
			writer.close();
			
			// Test 3 - write(char[] cbuf, int off, int len)
			writer = new RobberWriter(new FileWriter(new File(path, "test3.txt")));
			writer.write(testStringAsCharArray, 0, testStringAsCharArray.length);
			writer.close();
			
			// Test 4 - write(int c)
			writer = new RobberWriter(new FileWriter(new File(path, "test4.txt")));
			writer.write(testStringFirstChar);
			writer.close();
		} catch (IOException e) {
			System.err.println("Error writing file: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
