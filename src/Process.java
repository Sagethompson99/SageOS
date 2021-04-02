import java.util.ArrayList;

public class Process {
	
    public PCB pcb;
    private ArrayList<String[]> textSection;
	
	public Process() {
		pcb = new PCB();
		textSection = new ArrayList<>();
	}
	
	
	public ArrayList<String[]> getTextSection(){
		return textSection;
	}
	
	
	public int getTotalCycleCount() {
		int totalProcessCycles = 0;
		
		for(String[] s: textSection) 
			totalProcessCycles += Integer.parseInt(s[1]);
		
		return totalProcessCycles;
	}
	
	
	public void setTextSection(ArrayList<String[]> textSection) {
		this.textSection = textSection;
	}
	
    
	@Override
	public boolean equals(Object otherProcess) {
		if(this.pcb.getPID() == ((Process) otherProcess).pcb.getPID())
			return true;
		else
			return false;
	}
}