package turing;

public class SingleTapeDrive implements Drive {
	
	private SingleTrackTape tape1;
	
	public SingleTapeDrive(String content, char blank){
		tape1 = new SingleTrackTape(content, blank);
	}
	public SingleTapeDrive(char blank){
		tape1 = new SingleTrackTape(blank);
	}

	@Override
	public char right(int tape) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char left(int tape) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setValue(int tape, String value) {
		this.tape1.setValue(value);
	}

}
