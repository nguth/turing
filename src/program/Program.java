package program;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Pattern;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import program.movements.Movement;


/**
 *
 */
public class Program {
    private Set<Integer> states;            //Q
    private Pattern inputRe;    		       //SIGMA
    private HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> transitions;      //delta
    private Set<Integer> finalStates;       //F
    private Set<Character> tapeSymbols;     //GAMMA
    private int initialState;               //q0
    private final char BLANK;               //B
    private final int tapesRequired;		// number of tapes
    private final int tracksRequired;		// tracks per tape


    public Program(char blank, int tapesRequired, int tracksRequired){
        this.BLANK = '_';
        this.states = new CopyOnWriteArraySet<Integer>();
        this.inputRe = null;
        this.transitions = new HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>>();
        this.finalStates = new CopyOnWriteArraySet<Integer>();
        this.tapeSymbols = new CopyOnWriteArraySet<Character>();
        this.tapesRequired = tapesRequired;  //for validation
        this.tracksRequired = tracksRequired;  //for validation
    }

	public Program(char blank, int tapesRequired, int tracksRequired,
    		Set<Integer> states, Set<Character> symbols,
    		HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> transitions, 
    		Set<Integer> finalStates,
    		Set<Character> tapeSymbols, int initialState) {
        this.states = states;
        this.inputRe = null;
        this.transitions = transitions;
        this.finalStates = finalStates;
        this.tapeSymbols = tapeSymbols;
        this.initialState = initialState;
        this.BLANK = blank;
        this.tapesRequired = tapesRequired;
        this.tracksRequired = tracksRequired;
    }

    public int getTapesRequired() {
		return tapesRequired;
	}

	public int getTracksRequired() {
		return tracksRequired;
	}
    public Set<Integer> getStates() {
        return states;
    }
    public void setStates(Iterable<Integer> states) {
        for (Integer state:states) {
            this.states.add(state);
        }
    }
    public Pattern getInputRe(){
    	return inputRe;
    }
    
    public void setInputRe(String re) {
        this.inputRe = Pattern.compile(re);
    }

    public Set<Integer> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(Iterable<Integer> finalStates) {
        for (int state:finalStates){
            this.finalStates.add(state);
        }
    }

    public Set<Character> getTapeSymbols() {
        return tapeSymbols;
    }

    public void setTapeSymbols(Iterable<Character> tapeSymbols) {
        for (Character symbol:tapeSymbols){
            this.tapeSymbols.add(symbol);
        }
    }

    public int getInitialState() {
        return initialState;
    }

    public void setInitialState(int initialState) {
        this.initialState = initialState;
    }

    public char getBlank() {
        return BLANK;
    }

    public HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> getTransitions() {
        return transitions;
    }

    public void setTransitions(HashMap<Pair<Integer, List<Character>>, Triplet<Integer, List<Character>, List<Movement>>> transitions) {
        this.transitions = transitions;
    }
    
    /** This method checks for transitions and returns the next step */
    public Triplet<Integer, List<Character>, List<Movement>> step(Pair<Integer, List<Character>> input){
    	return this.transitions.get(input);
    }

    public void validate() throws VerifyError {
        for (Integer state : finalStates) {
            if (!states.contains(state)) {
                throw new VerifyError("Final state " + state + " not in states.");
            }
        }
        if (!states.contains(initialState)) {
            throw new VerifyError("Initial state " + initialState + " not in states.");
        }
        if(states.size() < 2) {
            throw new VerifyError("There are less than 2 states.");
        }
        if(!(inputRe instanceof Pattern)) {
            throw new VerifyError("There are no symbols.");
        }
        // can be extended
    }
    

}
