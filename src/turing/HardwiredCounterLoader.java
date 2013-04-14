package turing;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *   Returns a counter program
 */

/*
(0,1) -> (0,1) Right     //This state moves the tape to the right most digit
(0,0) -> (0,0) Right     //This state moves the tape to the right most digit
(0,B) -> (1,B) Left      //When a blank at the right is found we change to state 1

//This next block, state 1, is where the counting really happens
(1,0) -> (0,1) Right     //If we change a 0 to a 1 we change back to state 0
(1,1) -> (1,0) Left      //If we change a 1 to a 0 we keep looking to the left
(1,B) -> (0,1) Right     //If we change a Blank to a 1 we change back to state 0
*/


public class HardwiredCounterLoader implements ProgramLoader {

    HashMap<Pair<Integer, Character>, Triplet<Integer, Character, Movement>> transitions;

    public HardwiredCounterLoader(){
        transitions = new HashMap<Pair<Integer, Character>, Triplet<Integer, Character, Movement>>();
        transitions.put(new Pair<Integer, Character>(0,'1'), new Triplet<Integer, Character, Movement>(0,'1',Movement.RIGHT));
        transitions.put(new Pair<Integer, Character>(0,'0'), new Triplet<Integer, Character, Movement>(0,'0',Movement.RIGHT));
        transitions.put(new Pair<Integer, Character>(0,'_'), new Triplet<Integer, Character, Movement>(0,'_',Movement.LEFT));

        transitions.put(new Pair<Integer, Character>(1,'0'), new Triplet<Integer, Character, Movement>(0,'1',Movement.RIGHT));
        transitions.put(new Pair<Integer, Character>(1,'1'), new Triplet<Integer, Character, Movement>(1,'0',Movement.LEFT));
        transitions.put(new Pair<Integer, Character>(1,'B'), new Triplet<Integer, Character, Movement>(0,'1',Movement.STOP));
    }


    public Program load(String whatever){
        Program program = new Program('_', 1, 1);
        ArrayList<HashMap> transitions = new ArrayList<HashMap>();
        transitions.add(this.transitions);
        program.setTransitions(transitions);
        program.setStates(Arrays.asList(1, 2));
        program.setSymbols(Arrays.asList('0','1'));
        program.setTapeSymbols(Arrays.asList('0','1'));
        program.setFinalStates(Arrays.asList(1));
        program.setInitialState(0);
        return program;
    }
}
