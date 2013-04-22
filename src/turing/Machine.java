package turing;

import java.util.List;
import java.util.Set;

import org.javatuples.Pair;
import org.javatuples.Triplet;

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
    	List<Character> tapeContent = drive.read();
    	Pair<Integer, List<Character>> input = new Pair<Integer, List<Character>>(this.state, tapeContent);
    	Triplet<Integer, List<Character>, List<Movement>> next = this.program.step(input);
    	
    	this.state = next.getValue0();
    	this.drive.write(next.getValue1());
    	this.drive.move(next.getValue2());
    	if (this.verbose) {
	    	System.out.print("C:" + this.counter + "  S: " + this.state + "\n"
	    					  + this.drive.getNormalizedTapeContentAsString(this.program.getBlank()));
    	}
    	this.counter += 1;
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
		System.out.println("Machine stopped");
		System.out.println(this.drive.getNormalizedTapeContentAsString(this.program.getBlank()));
		System.out.println();
    }
    
    public void stop(){
        this.stop = true;
        System.out.println("turing.Machine stopped.");
    }
    
    public static void main(String[] args) {
		Machine machine = new Machine(new HardwiredProgramLoader());
		// TODO: Make those args
		machine.verbose = true;
		machine.load("multiply");
		machine.setInput("0001001");
		
		System.out.println("Set Tape content to: " + machine.getInput());
		machine.initialize();
		System.out.println("Machine initialized.");
		machine.run();
	}
    
}

