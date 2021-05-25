package dt066g.assignments.assignment3.task1;

import java.io.*;

/**
 * Class that will encode text/chars to robber language and output in a stream
 * @author Daniel Westerlund
 */
public class RobberWriter extends FilterWriter {
	public RobberWriter(Writer out) {
		super(out);
	}

	/**
	 * Writes a string from a specific spot to a stream
	 * @param str default string
	 * @param off start spot
	 * @param len end spot
	 * @throws IOException if it could not write to file
	 */
	@Override
	public void write(String str, int off, int len) throws IOException {
		str = str.substring(off,len);
		str = RobberLanguage.toRobber(str);
		out.write(str, off, str.length());
	}

	/**
	 *
	 * @param cbuf char array with message
	 * @param off start spot
	 * @param len end spot
	 * @throws IOException if it could not write to file
	 */
	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		for(int i = off; i < len; i++)
			 write(cbuf[i]);
	}

	/**
	 * Writes char/s that has been encoded with robberlanguage
	 * @param c
	 * @throws IOException
	 */
	@Override
	public void write(int c) throws IOException {
		char[] tmp = RobberLanguage.encodeCharToRobber(c);
		if(tmp == null)
			return;

		for(char ch : tmp){
			if(RobberLanguage.validChar(ch))
				out.write(ch);
		}

	}

	/**
	 * Writes a string to the stream
	 * @param str the be written
	 * @throws IOException if it could not write to file
	 */
	@Override
	public void write(String str) throws IOException {
		write(str, 0, str.length());
	}

}