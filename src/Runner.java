import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JPanel;

public class Runner {
	
	
	static int lastPID = 0;
//	private static int numPrograms = 0;
//	private CPU cpu;
//	static ArrayList<Process> processes;
	
	
	static void getPrograms(File file, ArrayList<File> allPrograms) {
	    File[] children = file.listFiles();
	    if (children != null) {
	        for (File child : children) {
	            allPrograms.add(child);
//	            numPrograms++;
//	            System.out.println(numPrograms + " " + child.getName());
	            getPrograms(child, allPrograms);
	        }
	    }
	}
	
	
//	public static void displayProcessInfo(String currentTask) {
//		System.out.println("pid |  status  |  instruction");
//	
//		
//		for(Process p : processes) {
//			
//			System.out.println(p.pcb.getPID() + "      " + p.pcb.getState() + "     " + currentTask);
//		}
//		System.out.println();
//	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
//		System.out.println("SageOS loading...\n");
//		System.out.println("Available Programs:");
//		System.out.println("-------------------");
		
		ArrayList<File> allPrograms = new ArrayList<File>();
	    getPrograms(new File("program files"), allPrograms);   
	    
	    
	    GUI screen = new GUI(); 
        screen.createProgramButtons(allPrograms);
	        

	 
//	    System.out.println("\n\nEnter your Selection:");
//	    
//	    Scanner input = new Scanner(System.in);
//	    int choice = input.nextInt();
//	    
//	    File chosenProgram = null;
//	    
//	   	if(choice - 1 <= all.size())
//	   		chosenProgram = all.get(choice - 1);
//	   	
//	   	int numProcesses = 0;
//	   	
//	   	if(chosenProgram != null) {
//	   		System.out.println("\n\nEnter number of processes to execute:");
//	   		numProcesses = input.nextInt();
//	   	}
//	    else
//	    	System.out.println("\nInvalid selection");
//	   	
//	   	
//	   	if(chosenProgram != null)
//	    	System.out.println("\nOpening " + chosenProgram.getName() + "...\n");
//        
//        for(int i = 0; i < numProcesses; i++) {
//	   		ArrayList<String[]> textSection = Extractor.getInstructionsWithRandomCycleCounts(chosenProgram);
//	   		Process p = new Process();
//	   		p.setTextSection(textSection);
//	   		processes.add(p);
//	   		
//	   	}
//	   	
//	   	CPU cpu = new CPU();
//	   	
//	   	
//	    input.close();    
	}
	
}