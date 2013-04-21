package turing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class MultiplikationCounterLoader implements ProgramLoader {

	HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> transitions;

	public MultiplikationCounterLoader() {
		transitions = new HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>>();
		
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('_','0','_'), Arrays.asList(Movement.RIGHT, Movement.RIGHT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('1','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(1, Arrays.asList('_','_','_'), Arrays.asList(Movement.RIGHT, Movement.NONE, Movement.NONE)));
		
		transitions.put(new Pair<Integer, List<Character>>(1, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(2, Arrays.asList('0','_','_'), Arrays.asList(Movement.NONE, Movement.LEFT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(1, Arrays.asList('1','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(4, Arrays.asList('_','_','_'), Arrays.asList(Movement.STOP, Movement.STOP, Movement.STOP)));
		
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(2, Arrays.asList('0','0','_'), Arrays.asList(Movement.NONE, Movement.LEFT, Movement.NONE)));
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(3, Arrays.asList('0','_','_'), Arrays.asList(Movement.NONE, Movement.RIGHT, Movement.NONE)));
	
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','0','_')), new Triplet<Integer, List<Character>, List<Movement>>(3, Arrays.asList('0','0','0'), Arrays.asList(Movement.NONE, Movement.RIGHT, Movement.RIGHT)));
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','_','_')), new Triplet<Integer, List<Character>, List<Movement>>(1, Arrays.asList('_','_','_'), Arrays.asList(Movement.RIGHT, Movement.NONE, Movement.NONE)));
	
	}

	@Override
	public Program load(String whatever) {
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
