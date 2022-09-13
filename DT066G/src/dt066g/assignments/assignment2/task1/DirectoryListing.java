package dt066g.assignments.assignment2.task1;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */
public interface DirectoryListing {
	void setDirectory(String dir);
	void setDirectory(java.io.File dir);
	java.io.File[] getFiles();
	java.io.File[] getDirectories();
	String toString();
	void list();
}
