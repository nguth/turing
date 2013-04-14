package turing;

import java.util.LinkedList;
import java.util.List;

public class SingleTrackTape {
	private List<Character> tape;
	private static char BLANK;
	
	public SingleTrackTape(String content, char blank){
		this.tape = new LinkedList<Character>();
		this.BLANK = blank;
		for (int i = 0; i < content.length(); i++){
		    tape.add(content.charAt(i)) ;        
		}
	}
	
	public SingleTrackTape(char blank){
		this.tape = new LinkedList<Character>();
		this.BLANK = blank;
	}
	public void setValue(String value){
		for (int i = 0; i < value.length(); i++){
		    tape.add(value.charAt(i)) ;        
		}
	}
	public String getValueAsString(){
		StringBuilder builder = new StringBuilder(this.tape.size());
	    for(Character ch: this.tape)
	    {
	        builder.append(ch);
	    }
	    return builder.toString();
	}

}
