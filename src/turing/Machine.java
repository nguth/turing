package turing;

import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;

/** The base machine class. Can run a program.
 * 
 */
public class Machine {

    private Program program;  // configuration: M = (Q,Σ, Γ, δ, q0, B, F)
    private int state;
    private boolean stop = false;
    private final ProgramLoader loader;
    private Drive drive;
    private String originalInput;
    private boolean verbose = false;
    private int counter = 0;

    public Machine(ProgramLoader loader){
        this.loader = loader;
        this.originalInput = "";
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
        this.state = this.program.getInitialState();
        this.drive.gotoStartAllTapes();
        this.counter = 0;
    	if (this.verbose) {
	    	System.out.print("Initial value\n"
	    					  + this.drive.getNormalizedTapeContentAsString(this.program.getBlank())
	    					  +"\n");
    	}
    }
    
    /** load the program and initialize the machine */
    public void load(String program) {
    	this.program = loader.load(program);
    	
    	if (this.originalInput.isEmpty()) {
    		this.originalInput = Character.toString(this.program.getBlank());
    	}
    	
    	if(this.program.getTapesRequired() == 1 && this.program.getTracksRequired() == 1) {
    		this.drive = new SingleTapeDrive(this.program.getBlank());
    	}else if(this.program.getTapesRequired() == 3 && this.program.getTracksRequired() == 1){
    		this.drive = new TripleTapeDrive(this.program.getBlank());
    	}
    	
    }

    
    
    // one step
    public void step() throws MachineStoppedException {
    	this.counter += 1;
    	List<Character> tapeContent = drive.read();
    	Pair<Integer, List<Character>> input = new Pair<Integer, List<Character>>(this.state, tapeContent);
    	Triplet<Integer, List<Character>, List<Movement>> next = this.program.step(input);
    	
    	this.state = next.getValue0();
    	this.drive.write(next.getValue1());
    	this.drive.move(next.getValue2());
    	if (this.verbose) {
	    	System.out.print("                                                                " + 
	    					 "C:" + this.counter + "  S: " + this.state + "  W: " + next.getValue1() 
	    						  + "  M: " + next.getValue2() + "\n"
	    					  + this.drive.getNormalizedTapeContentAsString(this.program.getBlank()));
    	}
    }

    // run continously
    public void run(){
        this.stop = false;
        while(!stop){
            try {
				step();
			} catch (MachineStoppedException e1) {
				this.stop = true;
			}
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        System.out.print("\n                                                                " + 
        				   "C:" + this.counter + "  S: " + this.state+"\n");
		System.out.print(this.drive.getNormalizedTapeContentAsString(this.program.getBlank()));
		System.out.print("\nMachine stopped after " + this.counter + " steps.\n");
		String valid = this.program.getFinalStates().contains(this.state) ? "a valid" : "an invalid";
		System.out.println("State " + this.state + " is " + valid + " final state.");
    }
    
    public void stop(){
        this.stop = true;
        System.out.println("turing.Machine stopped.");
    }
    
    public static void main(String[] args) {

		Machine machine = new Machine(new HardwiredProgramLoader());
		// TODO: Make those args
		machine.verbose = true;
		machine.load("factorial");
		machine.setInput("0001");
		System.out.println("Set Tape content to: " + machine.getInput());
		machine.initialize();
		System.out.println("Machine initialized.");
		machine.run();
	}
    
}

