package turing;

import java.util.Scanner;

public class TuringMachineMain {
	private MachineHandler machineHandler;
	
	private Scanner sc;
	private boolean isRunning;

	// TODO Alle Auswahlkombinationen testen
	public TuringMachineMain() {
		this.machineHandler = new MachineHandler();
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

		// TODO Es wäre vlt besser wenn die stepMachine() und runMachine()
		// Methoden Exceptions werfen, dann kann man die Fehlermeldungen auf der
		// Konsole ausgeben und danach wieder die chooseOption() ausführen.
		// Beim Präsentieren muss die Applikation dann nicht nochmal gestartet
		// werden

		switch (operation) {
		case "1":
			System.out.println("\nMachine is started. Type the 'enter' key for step, otherwise type the 'r' key for run");
			machineHandler.stepMachine();
			machineHandler.printResult();
			break;
		case "2":
			System.out.println("\n");
			machineHandler.runMachine();
			machineHandler.printResult();
			break;
		case "end":
			end();
			break;
		default:
			System.out.println("\n->" + operation + " is not a run modus.\n");
			chooseRunModus();
			return;
		}

	}

	public void end() {
		System.out.println("\n\nYou end the Turing Machine. Bye!");
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
			machineHandler.loadMachine("counter");
			break;
		case "2":
			machineHandler.loadMachine("multiply");
			break;
		case "3":
			machineHandler.loadMachine("factorial");
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
			// TODO Regex überprüfen (001001 funktioniert nicht)
			if (input.length() > 0 && input.matches("(0*1*)*1")) {
				machineHandler.setInput(input);
				machineHandler.initialize();
				System.out.println("\n->Machine is initialized\n");
			} else {
				System.out.println("\n->" + input + " is not correct\n");
				writeInput();
				return;
			}
		}

	}

	public static void main(String[] args) {
		TuringMachineMain starter = new TuringMachineMain();
		starter.run();
	}

}
