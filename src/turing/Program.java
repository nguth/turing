package turing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *
 */
class Program {
    private Set<Integer> states;            //Q
    private Set<Character> symbols;         //SIGMA
    private List<List> transitions;         //delta
    private Set<Integer> finalStates;       //F
    private Set<Character> tapeSymbols;     //GAMMA
    private int initialState;               //q0
    private final char BLANK;               //B


    public Program(char blank){
        this.BLANK = '_';
        this.states = new CopyOnWriteArraySet<Integer>();
        this.symbols = new CopyOnWriteArraySet<Character>();
        this.transitions = new ArrayList<List>();
        this.finalStates = new CopyOnWriteArraySet<Integer>();
        this.tapeSymbols = new CopyOnWriteArraySet<Character>();
    }

    public Program(Set<Integer> states, Set<Character> symbols,
                   List<List> transitions, Set<Integer> finalStates,
                   Set<Character> tapeSymbols, int initialState,
                   char blank) {
        this.states = states;
        this.symbols = symbols;
        this.transitions = transitions;
        this.finalStates = finalStates;
        this.tapeSymbols = tapeSymbols;
        this.initialState = initialState;
        this.BLANK = blank;
    }


    public Set<Integer> getStates() {
        return states;
    }
    public void setStates(Set<Integer> states) {
        this.states = states;
    }
    public Set<Character> getSymbols() {
        return symbols;
    }
    public void setSymbols(Set<Character> symbols) {
        this.symbols = symbols;
    }

    public Set<Integer> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(Set<Integer> finalStates) {
        this.finalStates = finalStates;
    }

    public Set<Character> getTapeSymbols() {
        return tapeSymbols;
    }

    public void setTapeSymbols(Set<Character> tapeSymbols) {
        this.tapeSymbols = tapeSymbols;
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

    public List<List> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<List> transitions) {
        this.transitions = transitions;
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
        if(symbols.size() < 1) {
            throw new VerifyError("There are no symbols.");
        }
        // can be extended
    }

}
