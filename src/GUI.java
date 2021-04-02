import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class GUI {

	
	JFrame frame;
	JPanel panel;
	private JTextField numProcessesText;
	private File program;
	private int numProcesses;
	private JButton executeButton;
	private static JTable table;
	private static ArrayList<Process> processes;
	private final Color darkestDark = new Color(24, 24, 24);
	private final Color primaryDark = new Color(34, 34, 34);
	private final Color secondaryDark = new Color(59, 59, 59);
	private final Color tertiaryDark = new Color(49, 49, 49);
	private final Color blue = new Color(13, 160, 255);
	private JButton selectedProgramButton = null;
	
	
	public GUI() {
		
		processes = new ArrayList<>();
		program = null;
		numProcesses = 0;
		frame = new JFrame("SageOS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(darkestDark);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(primaryDark);
		frame.setSize(900, 600);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
			
		panel = new JPanel();
		panel.setBackground(darkestDark);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(9, 1, 10, 5));
		
		JLabel programsLabel = new JLabel("Programs");
		programsLabel.setFont(programsLabel.getFont().deriveFont(programsLabel.getFont().getStyle() | Font.BOLD));
		programsLabel.setForeground(Color.WHITE);
		panel.add(programsLabel);
		programsLabel.setHorizontalAlignment(SwingConstants.CENTER);	
		
		numProcessesText = new JTextField();
		numProcessesText.setText("Enter number of processes to run...");
		numProcessesText.addMouseListener(new MouseAdapter() {
			  @Override
			  public void mouseClicked(MouseEvent e) {
			      numProcessesText.setText("");
			  }
		});
		
		executeButton = new JButton("Go!");
		executeButton.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
					execute();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
		    }
		});
			
		initTable();	
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setViewportBorder(null);
		scrollPane.setBackground(secondaryDark);
		frame.getContentPane().add(scrollPane);

		frame.setVisible(true);
	}
	
	
	public void initTable() {
		
		String[] header = {"PID", "Status", "Current Task", "Cycles"};
		String[][] data = {};
		
		//make all cells non-editable to users
		DefaultTableModel tableModel = new DefaultTableModel(data, header) {
			private static final long serialVersionUID = 1L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		table = new JTable();
		table.setShowGrid(false);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setBackground(tertiaryDark);
		table.setForeground(Color.WHITE);
		table.setModel(tableModel);
		table.setBackground(primaryDark);
	}
	
	
	public static void updateTable(int pid, int currentCycleCount) {	
		
		    int index = pid - 1;
			String processState = processes.get(index).pcb.getState().toString();
			table.setValueAt(processState, index, 1);
			table.setValueAt(currentCycleCount+"", index, 3);
			
			if(processState.equals("RUNNING")) 
				table.setValueAt("CALCULATE", index, 2);
			
			else if(processState.equals("WAITING")) 
				table.setValueAt("I/O", index, 2);
			
			else if(processState.equals("TERMINATED"))
				table.setValueAt("NONE", index, 2);					
	}
	
	
	private void execute() throws FileNotFoundException {
		
		try {	
			numProcesses = Integer.parseInt(numProcessesText.getText()); 
		}
		catch(NumberFormatException e1) {
			numProcessesText.setText("Invalid input");
			numProcesses = 0;
		}	
		
		if(program != null && numProcesses > 0) {
			CPU cpu = new CPU();
			ProcessScheduler scheduler = new ProcessScheduler();
			Runner.lastPID = 0;
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			processes = new ArrayList<>();
		   	
		   	for(int i = 0; i < numProcesses; i++) {
		   		ArrayList<String[]> textSection = Extractor.getInstructionsWithRandomCycleCounts(program);
		   		Process p = new Process();
		   		p.setTextSection(textSection);
		   		processes.add(p);
		   		model.addRow(new Object[]{p.pcb.getPID(), p.pcb.getState(), "NONE"});
		   	}		   	
		  
		    for(Process p : processes) {
		    	scheduler.scheduleNewProcess(p);
		    	scheduler.processReady(p);	
		    }
		    
		    cpu.execute();
		}
	}
	
	
	public void formatButton(JButton button) {	
		button.setForeground(Color.WHITE);
		button.setBackground(secondaryDark);
		Border line = new LineBorder(primaryDark);
	    button.setBorder(line);
		button.setOpaque(true);
	}
	
	
	private void updateSelectedProgramButton(JButton b) {	
		b.setBackground(blue);
	    if(selectedProgramButton != null && selectedProgramButton != b)
	    	selectedProgramButton.setBackground(secondaryDark);
		    	
	    selectedProgramButton = b;
	}
	
	
	public void createProgramButtons(ArrayList<File> programNames) {
		
		for(File f : programNames) {
			JButton button = new JButton(f.getName());
			
			
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					updateSelectedProgramButton(button);
					program = f;
				}
			});
			formatButton(button);
			panel.add(button);	
		}
		
		numProcessesText.setBackground(tertiaryDark);
		numProcessesText.setForeground(Color.WHITE);
		
		
		panel.add(numProcessesText);
		panel.add(executeButton);
		panel.revalidate();
		panel.repaint();
		formatButton(executeButton);
	}
	
}