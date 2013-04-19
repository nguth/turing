package turing;

import java.util.LinkedList;
import java.util.List;


/**
 *  Infinite tape for use with a Drive.  
 *
 */
public class SingleTrackTape {
	private List<Character> tape;
	private static char BLANK;
	private int slugLeft; // Korrekturfaktor f�r endloses Tape.
	private int tapePosition = 0;
	
	public SingleTrackTape(String content, char blank){
		this.tape = new LinkedList<Character>();
		this.BLANK = blank;
		this.slugLeft = 0;
		for (int i = 0; i < content.length(); i++){
		    tape.add(content.charAt(i)) ;        
		}
	}	
	public SingleTrackTape(char blank){
		this.tape = new LinkedList<Character>();
		this.BLANK = blank;
		this.slugLeft = 0;
	}
	
	public void setValue(String value){
		for (int i = 0; i < value.length(); i++){
		    tape.add(value.charAt(i)) ;        
		}
	}
	public String getValueAsString(){
		StringBuilder builder = new StringBuilder(this.tape.size()+2);
	    for(int i=0; i<this.tape.size(); i++)
	    {
	    	if(i==(this.tapePosition+this.slugLeft)){
	    		builder.append('[');
	    		builder.append(this.tape.get(i));
	    		builder.append(']');
	    	} else {
	    		builder.append(this.tape.get(i));
	    	}
	        
	    }
	    return builder.toString();
	}
	public List<Character> getValue(){
		return this.tape;
	}
		
	public char right() {
		this.tapePosition += 1;
		try {
			return this.tape.get(this.tapePosition+this.slugLeft);
		} catch (ArrayIndexOutOfBoundsException e) {
			this.tape.add(this.BLANK);
			return this.BLANK;
		}
	}
	public char left() {
		this.tapePosition -= 1;
		try {
			return this.tape.get(this.tapePosition+this.slugLeft);
		}
		catch (ArrayIndexOutOfBoundsException e){
			this.tape.add(0, this.BLANK);
			this.slugLeft += 1; // Korrekturfaktor
			return this.BLANK;
		}
	}
	public char getChar(){
		return this.tape.get(this.tapePosition+this.slugLeft);
	}
	public void putChar(char value){
		this.tape.set(this.tapePosition+this.slugLeft, value);
	}
	public int getPosition(){
		return this.tapePosition - this.slugLeft;
	}
	public char gotoStart(){
		this.tapePosition = 0;
		return this.tape.get(0);
	}
	public char gotoEnd(){
		this.tapePosition = this.tape.size() - 1;
		return this.tape.get(this.tapePosition);
	}

}
