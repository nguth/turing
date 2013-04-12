package turing;

import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 *
 */
public class Machine {

    private Program program;  // configuration: M = (Q,Σ, Γ, δ, q0, B, F)

    private List<List> tapes;
    private int state;
    private boolean stop = false;
    private final char BLANK;
    private final Drive drive;
    private String originalInput;
    private String inputString;

    public Machine(ProgramLoader loader, String program, Drive drive){
        this.program = loader.load(program);
        this.state = this.program.getInitialState();
        this.BLANK = this.program.getBlank();
        this.drive = drive;
        this.originalInput = "";
        this.inputString = "";
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

    public void setInput(String data){
        this.originalInput = data;
    }
    public String getInput(){
        return this.originalInput;
    }

    public void initialize(){
        this.inputString = this.originalInput;
        this.state = this.program.getInitialState();
    }

    // one step
    public void step(){


    }

    public void stop(){
        this.stop = true;
        System.out.println("turing.Machine stopped.");
    }
}
