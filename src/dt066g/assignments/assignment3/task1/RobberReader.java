package dt066g.assignments.assignment3.task1;

import java.io.*;

/**
 * @author Daniel Westerlund
 * Class that will read text from a stream that is encoded with robber-language
 * and decode it to normal text
 */
public class RobberReader extends FilterReader {

	/**
	 * Constructor that takes a reader and sends it to parent class
	 * @param in given Reader
	 */
	public RobberReader(Reader in) {
		super(in);
	}

	/**
	 * Reads a single char
	 * @return first char of a stream or -1 if empty
	 * @throws IOException if it could not be read
	 */
	@Override
	public int read() throws IOException {
		int temp = in.read();
		if(temp != -1) {
			char c = (char) temp;
			if(!RobberLanguage.validChar(c))
				return -1;

			else if (RobberLanguage.checkIfConstant(c))
				in.skip(2);
		}

		return temp;
	}

	/**
	 * Transform a char[] to a char
	 * bob -> b
	 * a -> a
	 * @param cbuf to be transformed
	 * @param off where to start
	 * @param len max number to be read
	 * @return char decoded
	 * @throws IOException if it could not be read
	 */
	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		for(int i = off; i < len; i++){
			char c = (char) read();
			if(RobberLanguage.validChar(c))
				cbuf[i] = c;
		}

		return -1;
	}

	/**
	 * Reads all of a stream
	 * @return Decoded robber-language string
	 * @throws IOException if it could not be read
	 */
	public String readAll() throws IOException {
		StringBuilder sb = new StringBuilder();
		int i;
		//Reads all chars and adds it to stringbuilder
		while((i = read()) != -1){
			char c = (char) i;
			sb.append(c);
		}
		return sb.toString();
	}
}