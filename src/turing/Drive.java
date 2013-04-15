package turing;

import java.util.List;

public interface Drive {
    char right(int tape);
    char left(int tape);
    void setValue(int tape, String value);
    char gotoStart(int tape);
    int getHeadPosition(int tape);
    
    List<Character> getTapeContent(int tape);
    
	/**
	 * Returns the content of the tape with a head marker
	 * as in "111[0]11".
	 * @param tape tape number for multi tape drives.
	 * @return a String with the current position marked.
	 */
	String getTapeContentAsString(int tape);
}
