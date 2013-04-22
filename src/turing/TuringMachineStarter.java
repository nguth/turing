package turing;

import java.util.Scanner;

public class TuringMachineStarter {
	private Machine machine;
	private Scanner sc;
	private boolean isRunning;

	public TuringMachineStarter() {
		this.machine = new Machine(new HardwiredProgramLoader());
		this.sc = new Scanner(System.in);
		this.isRunning = true;
	}

	public void run() {
		System.out.println("Hello to the Turing Machine!\n");
		while (isRunning) {
			chooseOperation();
			writeInput();
			chooseRunModus();
			System.out.println("\n\n");
		}
	}

	private void chooseRunModus() {
		System.out.println("Choose one run modus: ");
		System.out.println("********************* ");
		System.out.println("1: Step");
		System.out.println("2: Run");
		System.out.print("Your choice: ");

		String operation = sc.next();

		switch (operation) {
		case "1":
			machine.stepMachine();
			break;
		case "2":
			System.out.println("\nMachine is started:");
			machine.runMachine();
			break;
		case "end":
			end();
			break;
		default:
			System.out.println("\n->" + operation + " is not a run modus.\n");
			chooseOperation();
			return;
		}

	}

	public void end() {
		System.out.println("\n\nYou want to end the Turing Machine. Bye!");
		isRunning = false;
		sc.close();
		System.exit(0);
	}

	public void chooseOperation() {
		System.out.println("Choose one operation: ");
		System.out.println("********************* ");
		System.out.println("1: Counter");
		System.out.println("2: Multiply");
		System.out.println("3: Factorial\n");
		System.out.print("Your choice: ");

		String operation = sc.next();

		switch (operation) {
		case "1":
			machine.load("counter");
			break;
		case "2":
			machine.load("multiply");
			break;
		case "3":
			machine.load("factorial");
			break;
		case "end":
			end();
			break;
		default:
			System.out.println("\n->" + operation + " is not a operation.\n");
			chooseOperation();
			return;
		}
	}

	public void writeInput() {
		System.out.print("Write the input:");
		String input = sc.next();

		switch (input) {
		case "end":
			end();
			break;
		default:
			// Regex überprüfen
			if (input.length() > 0 && input.endsWith("1") && input.matches("(0*1*)1")) {
				machine.setInput(input);
				machine.initialize();
				System.out.println("\n->Machine is initialized\n");
			} else {
				System.out.println("\n->" + input + " is not correct\n");
				writeInput();
				return;
			}
		}

	}

	public static void main(String[] args) {
		TuringMachineStarter starter = new TuringMachineStarter();
		starter.run();
	}

}
