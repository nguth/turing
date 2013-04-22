package turing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class HardwiredProgramLoader implements ProgramLoader {
	
    HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> transitions;

    public HardwiredProgramLoader(){
        transitions = new HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>>();
    }
    
	@Override
	public Program load(String program) {
		switch (program) {
		case "counter":
			return load_counter();
		case "multiply":
			return load_multiply();
		case "factorial":
			return load_factorial();
		}
		return null;
	}
	
	private Program load_factorial() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Counts up:
	 * 
	 * (0,1) -> (0,1) Right     //This state moves the tape to the right most digit
	 * (0,0) -> (0,0) Right     //This state moves the tape to the right most digit
	 * (0,B) -> (1,B) Left      //When a blank at the right is found we change to state 1
	 *	
	 *	//This next block, state 1, is where the counting really happens
	 *	(1,0) -> (0,1) Right     //If we change a 0 to a 1 we change back to state 0
	 *	(1,1) -> (1,0) Left      //If we change a 1 to a 0 we keep looking to the left
	 *	(1,B) -> (0,1) Right     //If we change a Blank to a 1 we change back to state 0 
	 * 
	 * @return the counter program
	 */
	private Program load_counter(){
        transitions.put(new Pair<Integer, List<Character>>(0,Arrays.asList('1')), new Triplet<Integer, List<Character>, List<Movement>>(0,Arrays.asList('1'),Arrays.asList(Movement.RIGHT)));
        transitions.put(new Pair<Integer, List<Character>>(0,Arrays.asList('0')), new Triplet<Integer, List<Character>, List<Movement>>(0,Arrays.asList('0'),Arrays.asList(Movement.RIGHT)));
        transitions.put(new Pair<Integer, List<Character>>(0,Arrays.asList('_')), new Triplet<Integer, List<Character>, List<Movement>>(1,Arrays.asList('_'),Arrays.asList(Movement.LEFT)));

        transitions.put(new Pair<Integer, List<Character>>(1,Arrays.asList('0')), new Triplet<Integer, List<Character>, List<Movement>>(0,Arrays.asList('1'),Arrays.asList(Movement.RIGHT)));
        transitions.put(new Pair<Integer, List<Character>>(1,Arrays.asList('1')), new Triplet<Integer, List<Character>, List<Movement>>(1,Arrays.asList('0'),Arrays.asList(Movement.LEFT)));
        transitions.put(new Pair<Integer, List<Character>>(1,Arrays.asList('_')), new Triplet<Integer, List<Character>, List<Movement>>(0,Arrays.asList('1'),Arrays.asList(Movement.RIGHT)));
		
        Program program = new Program('_', 1, 1);
        program.setTransitions(transitions);
        program.setStates(Arrays.asList(1, 2));
        program.setSymbols(Arrays.asList('0','1'));
        program.setTapeSymbols(Arrays.asList('0','1'));
        program.setFinalStates(Arrays.asList(1));
        program.setInitialState(0);
        return program;
	}
	
	/**
	 * Multiplikation. Input Format: 00001001 := 4x2
	 * @return Programm
	 */
	private Program load_multiply(){		
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('_','0','_'), Arrays.asList(Movement.RIGHT, Movement.RIGHT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('1','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(1, Arrays.asList('_','_','_'), Arrays.asList(Movement.RIGHT, Movement.NONE, Movement.NONE)));
		
		transitions.put(new Pair<Integer, List<Character>>(1, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(2, Arrays.asList('0','_','_'), Arrays.asList(Movement.NONE, Movement.LEFT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(1, Arrays.asList('1','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(4, Arrays.asList('_','_','_'), Arrays.asList(Movement.STOP, Movement.STOP, Movement.STOP)));
		
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(2, Arrays.asList('0','0','_'), Arrays.asList(Movement.NONE, Movement.LEFT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(3, Arrays.asList('0','_','_'), Arrays.asList(Movement.NONE, Movement.RIGHT, Movement.NONE)));
	
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(3, Arrays.asList('0','0','0'), Arrays.asList(Movement.NONE, Movement.RIGHT, Movement.RIGHT)));
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(1, Arrays.asList('_','_','_'), Arrays.asList(Movement.RIGHT, Movement.NONE, Movement.NONE)));

		Program program = new Program('_', 3, 1);
		program.setTransitions(transitions);
		program.setStates(Arrays.asList(0, 1, 2, 3, 4));
		program.setSymbols(Arrays.asList('0', '1'));
		program.setTapeSymbols(Arrays.asList('0', '1'));
		program.setFinalStates(Arrays.asList(4));
		program.setInitialState(0);
		return program;

	}

}
