package turing;

import java.util.ArrayList;
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
	public int getNumberOfTapes(){ return 1; }

	@Override
	public void move(List<Movement> movement) throws MachineStoppedException {
		switch (movement.get(0)) {
		case LEFT:
			this.tape.left();
			break;
		case RIGHT:
			this.tape.right();
			break;
		case STOP:
			throw new MachineStoppedException();
		}
	}
	
	public char right(int tape) {
		return this.tape.right();
	}

	public char left(int tape) {
		return this.tape.left();
	}
	
	public void setValue(int tapeNr, String value) {
		this.tape.setValue(value);
	}
	@Override
	public void gotoStartAllTapes() {
		this.tape.gotoStart();
	}
	@Override
	public char gotoStart(int tapeNr) {
		return this.tape.gotoStart();
	}
	public char gotoEnd() {
		return this.tape.gotoEnd();
	}
	@Override
	public List<Character> read(){
		ArrayList<Character> chars =  new ArrayList<Character>();
		chars.add(this.tape.getChar());
		return chars;
		// return Arrays.asList(this.tape.getChar());
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
	public String getTapeContentAsString(){
		return this.tape.getValueAsString();
	}
	@Override
	public void write(List<Character> chars) {
		this.tape.putChar(chars.get(0));
	}
	@Override
	public String getNormalizedTapeContentAsString(char blank) {
		// TODO Auto-generated method stub
		return null;
	}
}