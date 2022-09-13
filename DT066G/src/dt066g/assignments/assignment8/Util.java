package dt066g.assignments.assignment8.task1;

/**
 * Some utility (helper) methods and variables for connecting to, and using, the Mid Sweden University's
 * PostgreSQL server.
 * @author Robert Jonsson (robert.jonsson@miun.se), DSV Östersund
 * @version 1.0
 */
public class Util {
	/**
	 * The database url in the form jdbc:postgresql://host:port/database?properties
	 */
	public static final String DB_URL = "jdbc:postgresql://studentpsql.miun.se:5432/dt066g";

	/**
	 * The database user on whose behalf the connection is being made
	 */
	public static final String DB_USER = "dt066g";

	/**
	 * The database user's password
	 */
	public static final String DB_PASSWORD = "ohDz0bEZb";
}
