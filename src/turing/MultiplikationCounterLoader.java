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
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('0','B','B')), new Triplet<Integer, List<Character>, List<Movement>>(0, Arrays.asList('B','0','B'), Arrays.asList(Movement.RIGHT, Movement.RIGHT, Movement.STOP)));
		transitions.put(new Pair<Integer, List<Character>>(0, Arrays.asList('1','B','B')), new Triplet<Integer, List<Character>, List<Movement>>(1, Arrays.asList('B','B','B'), Arrays.asList(Movement.RIGHT, Movement.STOP, Movement.STOP)));
		
		transitions.put(new Pair<Integer, List<Character>>(1, Arrays.asList('0','B','B')), new Triplet<Integer, List<Character>, List<Movement>>(2, Arrays.asList('0','B','B'), Arrays.asList(Movement.STOP, Movement.LEFT, Movement.STOP)));
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','0','B')), new Triplet<Integer, List<Character>, List<Movement>>(2, Arrays.asList('0','0','B'), Arrays.asList(Movement.STOP, Movement.LEFT, Movement.STOP)));
		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('1','B','B')), new Triplet<Integer, List<Character>, List<Movement>>(2, Arrays.asList('B','B','B'), Arrays.asList(Movement.RIGHT, Movement.LEFT, Movement.RIGHT)));

		transitions.put(new Pair<Integer, List<Character>>(2, Arrays.asList('0','B','B')), new Triplet<Integer, List<Character>, List<Movement>>(3, Arrays.asList('0','B','B'), Arrays.asList(Movement.STOP, Movement.RIGHT, Movement.STOP)));
	
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','0','B')), new Triplet<Integer, List<Character>, List<Movement>>(3, Arrays.asList('0','0','0'), Arrays.asList(Movement.STOP, Movement.RIGHT, Movement.RIGHT)));
		transitions.put(new Pair<Integer, List<Character>>(3, Arrays.asList('0','B','B')), new Triplet<Integer, List<Character>, List<Movement>>(1, Arrays.asList('B','B','B'), Arrays.asList(Movement.RIGHT, Movement.STOP, Movement.RIGHT)));


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
