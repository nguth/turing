package turing;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class MachineHandler {
	private Machine machine;
	private boolean isMachineRunning = false;

	public MachineHandler() {
		this.machine = new Machine(new HardwiredProgramLoader());
	}

	public void stepMachine() {
		this.isMachineRunning = true;

		while (isMachineRunning) {
			try {

				int ascii = System.in.read();

				// KeyEvent.VK_ENTER-> typed 'enter' key, 114-> typed 'r' key

				if (ascii == KeyEvent.VK_ENTER) {
					machine.runOneStep();
				} else if (ascii == 114) {
					runMachine();
					return;
				}

			} catch (MachineStoppedException e) {
				this.isMachineRunning = false;
			} catch (IOException e) {
				this.isMachineRunning = false;
				e.printStackTrace();
			}

		}

	}

	public void runMachine() {
		this.isMachineRunning = true;

		while (isMachineRunning) {
			try {
				machine.runOneStep();
				Thread.sleep(50);
			} catch (MachineStoppedException e1) {
				this.isMachineRunning = false;
			} catch (InterruptedException e) {
				this.isMachineRunning = false;
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}
		}

	}

	public void printResult() {
		System.out.print("\n                                                                " + "C:" + this.machine.getCounter() + "  S: " + this.machine.getState() + "\n");
		System.out.print(this.machine.getDrive().getNormalizedTapeContentAsString(this.machine.getProgram().getBlank()));
		System.out.print("\nMachine stopped after " + this.machine.getCounter() + " steps.\n");
		String valid = this.machine.getProgram().getFinalStates().contains(this.machine.getState()) ? "a valid" : "an invalid";
		System.out.println("State " + this.machine.getState() + " is " + valid + " final state.");
	}

	public void loadMachine(String operation) {
		this.machine.load(operation);
	}

	public void setInput(String input) {
		this.machine.setInput(input);
	}

	public void initialize() {
		this.machine.initialize();
	}
	
	public boolean isMachineRunning() {
		return isMachineRunning;
	}

}
