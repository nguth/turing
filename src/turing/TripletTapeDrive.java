package turing;

import java.util.ArrayList;
import java.util.List;

public class TripletTapeDrive implements Drive {

	private ArrayList<SingleTrackTape> tapes;
	private static char BLANK;

	public TripletTapeDrive(String content, char blank) {
		this.tapes = new ArrayList<SingleTrackTape>();
		this.tapes.add(new SingleTrackTape(blank));
		this.tapes.add(new SingleTrackTape(blank));
		this.tapes.add(new SingleTrackTape(blank));

		this.tapes.get(0).setValue(content);
	}

	public TripletTapeDrive(char blank) {
		this.tapes = new ArrayList<SingleTrackTape>();

		this.tapes.add(new SingleTrackTape(blank));
		this.tapes.add(new SingleTrackTape(blank));
		this.tapes.add(new SingleTrackTape(blank));
	}

	@Override
	public int getNumberOfTapes() {
		return 3;
	}

	@Override
	public void move(List<Movement> movement) throws MachineStoppedException {
		int moveIndex = 0;

		for (SingleTrackTape tape : tapes) {

			switch (movement.get(moveIndex)) {
			case LEFT:
				tape.left();
				break;
			case RIGHT:
				tape.right();
				break;
			case STOP:
				throw new MachineStoppedException();
			}
			moveIndex++;
		}
	}

	@Override
	public List<Character> read() {
		ArrayList<Character> chars = new ArrayList<Character>();
		chars.add(this.tapes.get(0).getChar());
		chars.add(this.tapes.get(1).getChar());
		chars.add(this.tapes.get(2).getChar());
		return chars;
	}

	@Override
	public void write(List<Character> chars) {
		this.tapes.get(0).putChar(chars.get(0));
		this.tapes.get(1).putChar(chars.get(1));
		this.tapes.get(2).putChar(chars.get(2));
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
		this.tapes.get(0).gotoStart();
		this.tapes.get(1).gotoStart();
		this.tapes.get(2).gotoStart();
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
