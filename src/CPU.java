import java.util.Iterator;

public class CPU {

	private int cycleCount;
//	private final int coreCount = 4;
//	private final int threadsPerCore = 4;
//	private int availableCores;
//	private int[] threadsAvailableInCore;
	
	public CPU() {
		cycleCount = 0;
//		availableCores = 4;
//		threadsAvailableInCore = new int[]{4, 4, 4, 4};
	}
	
	
	public void execute() {

		if(!ProcessScheduler.readyQueue.isEmpty()) {
		    Process pr = ProcessScheduler.getNextReadyProcess();
		    
		    //Create new thread for process, execute instructions, update process info on GUI table
			Thread t = new Thread() {
	            @Override
	            public void run() { 
	            	
	            	int totalProcessCycles = pr.getTotalCycleCount();
	            	Iterator<String[]> iterator = pr.getTextSection().iterator();
	        		String[] currentInstruction = iterator.next();
	        		String currentTask = currentInstruction[0];
	        		int currentTaskCyclesRemaining = Integer.parseInt(currentInstruction[1]);
	        		int currentCycle = 1;
	        		
					while(totalProcessCycles > 0) {		
						
						if(currentTaskCyclesRemaining == 0) {
							currentInstruction = iterator.next();
							currentTask = currentInstruction[0];
							currentTaskCyclesRemaining = Integer.parseInt(currentInstruction[1]);	
						}
						
						if(currentTask.equals("CALCULATE"))
							pr.pcb.setState(ProcessState.RUNNING);
						
						else if(currentTask.equals("I/O"))
							pr.pcb.setState(ProcessState.WAITING);
					
				        GUI.updateTable(pr.pcb.getPID(), currentCycle);
				        
				        try {
				        	sleep(1);  // milliseconds
				        } catch (InterruptedException ex) {}
					
						advanceCycleCount();
						currentCycle++;
						currentTaskCyclesRemaining--;
						totalProcessCycles--;
			        }
			        	       
			        pr.pcb.setState(ProcessState.TERMINATED);
				    GUI.updateTable(pr.pcb.getPID(), currentCycle);	
				    
				    execute();
	          }
	            
	       };
	       t.start();	              
	   }
	}
	
	
	public void advanceCycleCount() {
		cycleCount++;
	}
	
}
