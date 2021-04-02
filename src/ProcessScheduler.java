import java.util.LinkedList;
import java.util.Queue;

public class ProcessScheduler {
    
	private Queue<Process> jobsQueue;
	public static Queue<Process> readyQueue;
	private Queue<Process> deviceQueue;
	
	public ProcessScheduler() {
		jobsQueue = new LinkedList<>();
		readyQueue = new LinkedList<>();
		deviceQueue = new LinkedList<>();
	}
	
	public void scheduleNewProcess(Process p) {
		if(!jobsQueue.contains(p)) {
			p.pcb.setState(ProcessState.NEW);
			jobsQueue.add(p);
		}
	}
	
	public void processReady(Process p) {
		jobsQueue.remove(p);
		p.pcb.setState(ProcessState.READY);
		readyQueue.add(p);
	}
	
	public void newIORequestFrom(Process p) {
		deviceQueue.add(p);
		p.pcb.setState(ProcessState.WAITING);
	}
	
	public void removeFromIOQueue(Process p) {
		if(deviceQueue.contains(p)) 
			deviceQueue.remove(p);
		p.pcb.setState(ProcessState.READY);
	}
	
	public static Process getNextReadyProcess() {
		return readyQueue.poll();
	}
	
}
