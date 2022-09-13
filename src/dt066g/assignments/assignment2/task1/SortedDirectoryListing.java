package dt066g.assignments.assignment2.task1;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */
public interface SortedDirectoryListing extends DirectoryListing {
	void list(java.util.Comparator<java.io.File> sortOrder);
}
