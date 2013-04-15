package turing;

import java.util.LinkedList;
import java.util.List;

/**
 * A drive with one single track tape.
 * 
 */

public class SingleTapeDrive implements Drive {
	
	private SingleTrackTape tape;
	private static char BLANK;
	
	public SingleTapeDrive(String content, char blank){
		this.tape = new SingleTrackTape(blank);
		this.tape.setValue(content);
	}
	public SingleTapeDrive(char blank){
		this.tape = new SingleTrackTape(blank);
	}

	@Override
	public char right(int tape) {
		return this.tape.right();
	}

	@Override
	public char left(int tape) {
		return this.tape.left();
	}
	
	public void setValue(int tapeNr, String value) {
		this.tape.setValue(value);
	}
	@Override
	public char gotoStart(int tapeNr) {
		return this.tape.gotoStart();
	}
	public char gotoEnd() {
		return this.tape.gotoEnd();
	}
	
	@Override
	public int getHeadPosition(int tape) {
		return this.tape.getPosition();
	}
	@Override
	public List<Character> getTapeContent(int tape) {
		return this.tape.getValue();
	}
	@Override
	public String getTapeContentAsString(int tape){
		return this.tape.getValueAsString();
	}
}
