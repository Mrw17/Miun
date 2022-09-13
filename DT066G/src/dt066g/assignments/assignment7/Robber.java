package dt066g.assignments.assignment7.task1;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Robert Jonsson, DSV Östersund
 * @version 1.0
 */
public interface Robber extends Remote {
	static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
	static final String VOWELS = "aeiouyåäö";

	/**
	 * Translates the text in <code>text</code> to robber language.
	 * @param text The text to be translated into robber language.
	 * @return The translated text.
	 * @throws RemoteException If any communication-related error occurs 
	 * during the execution of the remote method call.
	 */
	public String toRobber(String text) throws RemoteException;

	/**
	 * Translates the text in <code>text</code> from robber language.
	 * 
	 * @param text The text in robber language to be translated.
	 * @return The translated text.
	 * @throws RemoteException If any communication-related error occurs 
	 * during the execution of the remote method call.
	 */
	public String fromRobber(String text) throws RemoteException;

}
