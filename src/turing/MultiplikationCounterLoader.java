package turing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class MultiplikationCounterLoader implements ProgramLoader {

	HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> transitions;

	public MultiplikationCounterLoader() {
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
