package dt066g.assignments.assignment2.task1;

import java.io.File;
import java.util.*;

/**
 * @author Your Name
 * @version 1.0
 */
public class Task1 {
	public static void main(String[] args) {
		Directory dir = new Directory();
		Scanner input = new Scanner(System.in);
		
		/*Comparator<File> sortOrder = (file1, file2) -> {
			return 0;
		};*/

		do {
			System.out.println(dir.toString() + ":");
			dir.list();
			//dir.list(sortOrder);
			
			System.out.print("\nEnter new directory path to list: ");			
			String newDir = input.nextLine();
			
			if (newDir.equalsIgnoreCase("exit")) {
				break;
			}

			dir.setDirectory(newDir);			
		}
		while (true);
		
		input.close();
	}
}