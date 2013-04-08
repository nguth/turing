package turing;

import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 *
 */
public class Machine {
    private Set<Integer> states;
    private Set<Character> symbols;
    private List<List> transitions;
    private Set<Integer> finalStates;
    private Set<Character> tapeSymbols;
    private char blank;

    private Program program;  // configuration: M = (Q,Σ, Γ, δ, q0, B, F)

    private List<List> tapes;
    private int state;
    private boolean stop = false;

    public Machine(ProgramLoader loader, String program){
        this.program = loader.load(program);
        this.state = this.program.getInitialState();
        blank = this.program.getBlank();
    }

    // run continously
    public void run(){
        this.stop = false;
        while(!stop){
            step();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    // one step
    public void step(){


    }

    public void stop(){
        this.stop = true;
        System.out.println("turing.Machine stopped.");
    }
}
