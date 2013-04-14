package turing;

import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

/** The base machine class. Can run a program.
 * 
 */
public class Machine {

    private Program program;  // configuration: M = (Q,Σ, Γ, δ, q0, B, F)
    private List<List> tapes;
    private int state;
    private boolean stop = false;
    private final ProgramLoader loader;
    private Drive drive;
    private String originalInput;
    private String inputString;

    public Machine(ProgramLoader loader){
        this.loader = loader;
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

    public void setInput(int tape, String data){
        this.originalInput = data;
        if (this.drive != null) {
        	this.drive.setValue(tape, data);
        }
    }
    // if no tape is given, write to tape 1.
    public void setInput(String data){
        this.originalInput = data;
        if (this.drive != null) {
        	this.drive.setValue(1, data);
        }
    }
    
    public String getInput(){
        return this.originalInput;
    }

    public void initialize(){
        this.inputString = this.originalInput;
        this.state = this.program.getInitialState();
    }
    
    /** load the program and initialize the machine */
    public void load(String program) {
    	this.program = loader.load(program);
    	if(this.program.getTapes() == 1 && this.program.getTracks() == 1) {
    		this.drive = new SingleTapeDrive(this.program.getBlank());
    	}
    	this.initialize();
    }

    
    
    // one step
    public void step(){


    }

    public void stop(){
        this.stop = true;
        System.out.println("turing.Machine stopped.");
    }
    
    public static void main(String[] args) {
		Machine machine = new Machine(new HardwiredCounterLoader());
		machine.load("");
	}
    
}
