package dt066g.assignments.assignment3.task1;

import java.io.*;

public class RobberReader extends FilterReader {
	
	public RobberReader(Reader in) {
		// TODO: Initialize in stream correctly
	}

	@Override
	public int read() throws IOException {
		// TODO: Translate/filter one character from robber language
		return -1;
	}

	@Override
	public int read(char cbuf[], int off, int len) throws IOException {
		// TODO: Translate/filter a portion of a char array from robber language
		return -1;
	}

	public String readAll() throws IOException {
		// TODO: Read all characters as a String from stream (to the end of stream)
		// TODO: Translate/filter this String to robber language
		return null;
	}
	
	// TODO: Free to add any additional methods you need
}