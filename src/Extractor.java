import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Extractor {

	
	//Gets all instructions from a given program and generates random cycle counts for each one
	//Used to generate the textSection of a process PCB
	public static ArrayList<String[]> getInstructionsWithRandomCycleCounts(File program) throws FileNotFoundException {
			
		ArrayList<String[]> instructions = new ArrayList<String[]>();
		Scanner s = new Scanner(program);
		
		while(s.hasNextLine()) {
			String instruction = s.next();
			s.next();
			int randomCycleCount = 0;
			
			if(instruction.equals("I/O"))
				randomCycleCount = (int)(Math.random()*25)+25;
			else
				randomCycleCount = (int)(Math.random()*100)+1;
			
			instructions.add(new String[] {instruction, String.valueOf(randomCycleCount)});
		}
		s.close();
		
		return instructions;
	}
	
}
