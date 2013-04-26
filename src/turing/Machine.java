package turing;

import java.util.List;
import java.util.regex.Matcher;

import org.javatuples.Pair;
import org.javatuples.Triplet;

/**
 * The base machine class. Can run a program.
 * 
 */
public class Machine {

	private Program program; // configuration: M = (Q,Σ, Γ, δ, q0, B, F)
	private int state;
	private final ProgramLoader loader;
	private Drive drive;
	private String originalInput;
	// TODO sollte standardmässig auf false sein. Der User kann es evlt selber
	// setzen
	private boolean verbose = true;
	private int counter = 0;

	public Machine(ProgramLoader loader) {
		this.loader = loader;
		this.originalInput = "";
	}
	private void validateInput(String data) {
		if(!(program instanceof Program)) { 
			throw new IllegalStateException("No program loaded.");
		}
		Matcher matcher = program.getInputRe().matcher(data);
		if(!matcher.matches()) {
			throw new IllegalArgumentException("Input not valid.");
		}
	}

	public void setInput(int tape, String data) {
		validateInput(data);
		this.originalInput = data;
		if (this.drive != null) {
			this.drive.setValue(tape, data);
		}
	}

	// if no tape is given, write to tape 1.
	public void setInput(String data) {
		validateInput(data);
		this.originalInput = data;
		if (this.drive != null) {
			this.drive.setValue(1, data);
		}
	}

	public String getInput() {
		return this.originalInput;
	}

	public void initialize() {
		this.state = this.program.getInitialState();
		this.drive.gotoStartAllTapes();
		this.counter = 0;
		if (this.verbose) {
			System.out.print("Initial value\n" + this.drive.getNormalizedTapeContentAsString(this.program.getBlank()) + "\n");
		}
	}

	/** load the program and initialize the machine */
	public void load(String program) {
		this.program = loader.load(program);
		this.program.validate();

		if (this.originalInput.isEmpty()) {
			this.originalInput = Character.toString(this.program.getBlank());
		}

		if (this.program.getTapesRequired() == 1 && this.program.getTracksRequired() == 1) {
			this.drive = new SingleTapeDrive(this.program.getBlank());
		} else if (this.program.getTapesRequired() == 3 && this.program.getTracksRequired() == 1) {
			this.drive = new TripleTapeDrive(this.program.getBlank());
		}

	}

	// one step
	public void runOneStep() throws Exception {
		this.counter += 1;
		List<Character> tapeContent = drive.read();
		Pair<Integer, List<Character>> input = new Pair<Integer, List<Character>>(this.state, tapeContent);
		Triplet<Integer, List<Character>, List<Movement>> next = this.program.step(input);

		this.state = next.getValue0();
		this.drive.write(next.getValue1());
		this.drive.move(next.getValue2());
		if (this.verbose) {
			System.out.print("                                                                " + "C:" + this.counter + "  S: " + this.state + "  W: " + next.getValue1() + "  M: " + next.getValue2() + "\n"
					+ this.drive.getNormalizedTapeContentAsString(this.program.getBlank()));
		}
	}

	public Program getProgram() {
		return program;
	}

	public int getCounter() {
		return counter;
	}

	public Drive getDrive() {
		return drive;
	}

	public int getState() {
		return state;
	}
	
}
