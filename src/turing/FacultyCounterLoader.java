package turing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class FacultyCounterLoader implements ProgramLoader {

	HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> transitions;

	public FacultyCounterLoader() {
		transitions = new HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>>();
		
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','0','_'), Arrays.asList(Movement.RIGHT, Movement.RIGHT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('1','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('_','_','_'), Arrays.asList(Movement.LEFT, Movement.LEFT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(1, Arrays.asList('0','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','_','_'), Arrays.asList(Movement.NONE, Movement.LEFT, Movement.RIGHT)));
		
		transitions.put(new Pair<Integer, List<Character>>(1, Arrays.asList('_','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','_','_'), Arrays.asList(Movement.NONE, Movement.NONE, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','0','0'), Arrays.asList(Movement.LEFT, Movement.NONE, Movement.RIGHT)));
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','0','0')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','0','0'), Arrays.asList(Movement.LEFT, Movement.NONE, Movement.RIGHT)));
		
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('_','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('_','0','_'), Arrays.asList(Movement.RIGHT, Movement.LEFT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','_','_'), Arrays.asList(Movement.NONE, Movement.RIGHT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','0','0'), Arrays.asList(Movement.RIGHT, Movement.NONE, Movement.RIGHT)));
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','0','0')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('0','0','0'), Arrays.asList(Movement.RIGHT, Movement.NONE, Movement.RIGHT)));
		
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('_','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('_','0','_'), Arrays.asList(Movement.LEFT, Movement.LEFT, Movement.NONE)));
		
	}
	
	@Override
	public Program load(String whatever) {
		Program program = new Program('_', 3, 1);
		program.setTransitions(transitions);
		program.setStates(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
		program.setSymbols(Arrays.asList('0', '1'));
		program.setTapeSymbols(Arrays.asList('0', '1'));
		program.setFinalStates(Arrays.asList(10));
		program.setInitialState(0);
		return program;
	}

}
