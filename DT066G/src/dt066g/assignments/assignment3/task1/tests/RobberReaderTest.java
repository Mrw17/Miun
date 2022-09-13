package dt066g.assignments.assignment3.task1.tests;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import dt066g.assignments.assignment3.task1.RobberLanguage;
import dt066g.assignments.assignment3.task1.RobberReader;

public class RobberReaderTest {

	public static void main(String[] args) {
		final String testString = "Testar att översätta till och från rövarspråket!";
		final String testStringAsRobber = RobberLanguage.toRobber(testString);
		final File testRobberFile = new File("src" + File.separator +"assets" + File.separator + "assignment3", "robber.txt");
		
		try {			
			// Init - Write testStringAsRobber to file with a FileWriter
			FileWriter writer = new FileWriter(testRobberFile);
			writer.write(testStringAsRobber);
			writer.close();
			
			// Test all 3 read methods in RobberReader
			// The System.out.print of each test should equal 
			// the content of the string 'testString'
			// Test 1 - readAll()
			RobberReader reader = new RobberReader(new FileReader(testRobberFile));
			System.out.println(reader.readAll());
			reader.close();
			// Test 2 - read(char cbuf[], int off, int len)
			char[] robberCharArray = new char[testStringAsRobber.length()];
			reader = new RobberReader(new FileReader(testRobberFile));
			reader.read(robberCharArray, 0, robberCharArray.length);
			System.out.println(new String(robberCharArray));
			reader.close();

			// Test 3 - read()
			reader = new RobberReader(new FileReader(testRobberFile));
			int c;
			while ((c = reader.read()) != -1) {
				System.out.print((char) c);
			}
			System.out.println();
			reader.close();
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
