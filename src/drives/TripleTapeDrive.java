package drives;

import java.util.ArrayList;
import java.util.List;

import machine.Movement;


import exceptions.MachineStoppedException;

import tapes.SingleTrackTape;

public class TripleTapeDrive implements Drive {

	private ArrayList<SingleTrackTape> tapes;

	public TripleTapeDrive(String content, char blank) {
		this.tapes = new ArrayList<SingleTrackTape>();
		this.tapes.add(new SingleTrackTape(blank));
		this.tapes.add(new SingleTrackTape(blank));
		this.tapes.add(new SingleTrackTape(blank));

		this.tapes.get(0).setValue(content);
	}

	public TripleTapeDrive(char blank) {
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
			default:
				break;
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
	public String getTapeContentAsString() {
		return this.tapes.get(0).getValueAsString() + "\n" + this.tapes.get(1).getValueAsString() + "\n" + this.tapes.get(2).getValueAsString() + "\n";
	}

	@Override
	public String getNormalizedTapeContentAsString(char blank) {
		int slug = 15;
		int maxLength = slug * 4 + 3;
		String output = "";
		// create the blank string
		for (SingleTrackTape tape : this.tapes) {
			StringBuilder builder = new StringBuilder(maxLength);
			builder.append('|');
			for (int i = 0; i <= slug * 2; i++) {
				builder.append(blank);
				builder.append('|');
			}

			String tapeContent = tape.getValueAsString();

			int left = (slug * 2) - (tape.getPosition() * 2);
			if (tapeContent.equals("")) {
				builder.replace(left, left + 3, "[" + blank + "]");
			} else if (left < 0) {
				String substring = tapeContent.substring(-left);
				builder.replace(0, substring.length(), substring);
			} else {
				builder.replace(left, left + tapeContent.length(), tapeContent);
			}
			if (builder.length() > maxLength) {
				builder.setLength(maxLength);
			}
			output += builder.toString();
			output += "\n";

		}
		return output;
	}

	@Override
	public int getResult() {
		SingleTrackTape resultTape = tapes.get(2);

		int counter = 0;
		String value =  resultTape.getValueAsString();
		
		for (int i = 0; i < value.length(); i++) {
			if(value.charAt(i)=='0'){
				counter++;
			}
		}
		
		return counter;
	}

}
