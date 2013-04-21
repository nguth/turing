package turing;

import java.util.ArrayList;
import java.util.List;

public class TripletTapeDrive implements Drive {

	private SingleTrackTape tape1;
	private SingleTrackTape tape2;
	private SingleTrackTape tape3;
	private ArrayList<SingleTrackTape> tapes;
	private static char BLANK;

	public TripletTapeDrive(String content, char blank) {
		this.tape1 = new SingleTrackTape(blank);
		this.tape2 = new SingleTrackTape(blank);
		this.tape3 = new SingleTrackTape(blank);
		tapes = new ArrayList<SingleTrackTape>();
		tapes.add(tape1);
		tapes.add(tape2);
		tapes.add(tape3);
		this.tape1.setValue(content);
	}

	@Override
	public int getNumberOfTapes() {
		return 3;
	}

	@Override
	public void move(List<Movement> movement) {
		int moveIndex = 0;

		for (SingleTrackTape tape : tapes) {

			switch (movement.get(moveIndex)) {
			case LEFT:
				tape.left();
				break;
			case RIGHT:
				tape.right();
				break;
			}

			moveIndex++;
		}
	}

	@Override
	public List<Character> read() {
		ArrayList<Character> chars = new ArrayList<Character>();
		chars.add(this.tape1.getChar());
		chars.add(this.tape2.getChar());
		chars.add(this.tape3.getChar());
		return chars;
	}

	@Override
	public void write(List<Character> chars) {
		this.tape1.putChar(chars.get(0));
		this.tape2.putChar(chars.get(1));
		this.tape3.putChar(chars.get(2));
	}

	@Override
	public void setValue(int tape, String value) {
		this.tapes.get(tape - 1).setValue(value);
	}

	@Override
	public char gotoStart(int tape) {
		return this.tapes.get(tape - 1).gotoStart();
	}

	@Override
	public void gotoStartAllTapes() {
		this.tape1.gotoStart();
		this.tape2.gotoStart();
		this.tape3.gotoStart();
	}

	@Override
	public int getHeadPosition(int tape) {
		return this.tapes.get(tape - 1).getPosition();
	}

	@Override
	public List<Character> getTapeContent(int tape) {
		return this.tapes.get(tape - 1).getValue();
	}

	@Override
	public String getTapeContentAsString(int tape) {
		return this.tapes.get(tape - 1).getValueAsString();
	}

}
