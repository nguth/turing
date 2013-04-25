package turing;

import java.util.LinkedList;
import java.util.List;

/**
 * Infinite tape for use with a Drive.
 * 
 */
public class SingleTrackTape {
	private List<Character> tape;
	private char blank;
	private int tapePosition = 0; // Position in der lokalen Liste.

	public SingleTrackTape(char blank) {
		this.tape = new LinkedList<Character>();
		this.blank = blank;
	}

	public void setValue(String value) {
		for (int i = 0; i < value.length(); i++) {
			tape.add(i, value.charAt(i));
		}
	}

	public String getValueAsString() {
		if (this.tape.size() == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(this.tape.size() * 2 + 2);
		for (int i = 0; i < this.tape.size(); i++) {
			if (i == 0) {
				builder.append('|');
			} // only on first field.
			builder.append(this.tape.get(i));
			builder.append('|');
		}
		int tp = (this.tapePosition);
		builder.replace(tp * 2, tp * 2 + 1, "[");
		builder.replace(tp * 2 + 2, tp * 2 + 3, "]");
		return builder.toString();
	}

	public List<Character> getValue() {
		return this.tape;
	}

	public char right() {
		this.tapePosition += 1;
		try {
			return this.tape.get(this.tapePosition);
		} catch (IndexOutOfBoundsException e) {
			this.tape.add(this.blank);
			return this.blank;
		}
	}

	public char left() {
		this.tapePosition -= 1;
		try {
			return this.tape.get(this.tapePosition);
		} catch (IndexOutOfBoundsException e) {
			this.tape.add(0, this.blank);
			this.tapePosition = 0;
			return this.blank;
		}
	}

	public char getChar() {
		try {
			return this.tape.get(this.tapePosition);
		} catch (IndexOutOfBoundsException e) {
			return this.blank;
		}
	}

	public void putChar(char value) {
		try {
			this.tape.set(this.tapePosition, value);
		} catch (Exception e) {
			this.tape.add(value);
		}
	}

	public int getPosition() {
		return this.tapePosition;
	}

	public char gotoStart() {
		this.tapePosition = 0;

		try {
			return this.tape.get(0);
		} catch (IndexOutOfBoundsException e) {
			return this.blank;
		}
	}

	public char gotoEnd() {
		this.tapePosition = this.tape.size() - 1;
		return this.tape.get(this.tapePosition);
	}

}
