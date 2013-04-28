package main;

import java.util.Scanner;

import machine.handler.MachineHandler;


public class TuringMachineMain {
	private MachineHandler machineHandler;

	private Scanner sc;
	private boolean isRunning;

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
		case "q":
			end();
			break;
		default:
			System.out.println("\n->" + operation + " is not a run modus.\n");
			chooseRunModus();
			return;
		}

	}

	public void end() {
		System.out.println("\n\nBye!");
		isRunning = false;
		sc.close();
		System.exit(0);
	}

	public void chooseOperation() {
		System.out.println("Choose one operation: ");
		System.out.println("********************* ");
		System.out.println("1: Multiply");
		System.out.println("2: Factorial\n");
		System.out.print("Your choice: ");

		String operation = sc.next();

		switch (operation) {
		case "1":
			machineHandler.loadMachine("multiply");
			break;
		case "2":
			machineHandler.loadMachine("factorial");
			break;
		case "end":
			end();
			break;
		case "q":
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
		case "q":
			end();
			break;
		default:
			if (input.length() > 0) {
				try {
					machineHandler.setInput(input);
					machineHandler.initialize();
					System.out.println("\n->Machine is initialized\n");
				} catch (IllegalArgumentException e) {
					System.out.println("\n->Input is not correct: " + input + "\n");
					writeInput();
					return;
				}

			} else {
				System.out.println("\n-> No input is given.\n");
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
