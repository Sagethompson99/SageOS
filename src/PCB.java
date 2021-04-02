import java.util.ArrayList;

public class PCB {
	
	private ProcessState state;
    private int processID;
    private int programCounter;
    private int priorityValue;
    private ArrayList<Integer> registers;
    private ArrayList<String> IOStatus;

    
	public PCB() {
		processID = Runner.lastPID + 1;
		Runner.lastPID += 1;
		this.state = ProcessState.NEW;
		programCounter = 0;
	} 
	
    public ProcessState getState(){
    	return state;
    }
    
    public void setState(ProcessState state) {
    	this.state = state;
    }
    
    public void incrementPC() {
    	programCounter++;
    }
    
    public int getPID() {
    	return processID;
    }

}