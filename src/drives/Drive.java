package drives;

import java.util.List;

import program.movements.Movement;

import exceptions.MachineStoppedException;


public interface Drive {
	int getNumberOfTapes();
	
	/**
	 * Moves the tape(s)
	 * @param movment A list of Movement Types.
	 */
	void move(List<Movement> movment) throws MachineStoppedException;
	
	/**
	 * Reads the contents from all tapes
	 * @return A list with the current character from all tapes.
	 */
    List<Character> read();
    
    /**
     * Writes to the tape(s)
     * @param chars A List of Characters. One for each tape.
     */
    void write(List<Character> chars);
    
    /**
     * Writes an initial value onto the tape.
     * @param tape Which Tape
     * @param value A String to write.
     */
    void setValue(int tape, String value);
    char gotoStart(int tape);
    void gotoStartAllTapes();
    int getHeadPosition(int tape);

    
    
    List<Character> getTapeContent(int tape);
    
	/**
	 * Returns the content of the tape with a head marker
	 * as in "111[0]11".
	 * @param tape tape number for multi tape drives.
	 * @return a String with the current position marked.
	 */
	String getTapeContentAsString();

	
	/** 
	 * returns the tape with 15 chars before and after the head position.
	 * @return A String
	 */
	String getNormalizedTapeContentAsString(char blank);
	
	/**
	 * result in numbers. counts all zeros (Ex. 000000 -> returns 5)
	 * @return int
	 */
	int  getResult();
}
