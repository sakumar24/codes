import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Gui 
{
	private JFrame frame;
	private JTextField inputFile1;
	private JTextField outputFile1;
	private JTextField arg1Op2;
	private JTextField arg2op2;
	private JTextField arg1op3;
	private JTextField arg2op3;
	
	int option;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
				
		frame = new JFrame();
		frame.setBounds(0, 0, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(2, 2, 1080, 655);
		frame.getContentPane().add(tabbedPane);
		
		
	// Operation-1 panel:
		JPanel panel1 = new JPanel();
		tabbedPane.addTab("Operation-1", null, panel1, null);
		panel1.setLayout(null);
		
		JLabel lblInputFileFor = new JLabel("Input File for operation1:");
		lblInputFileFor.setBounds(10, 11, 154, 14);
		panel1.add(lblInputFileFor);
		
		inputFile1 = new JTextField();
		inputFile1.setBounds(10, 36, 320, 20);
		panel1.add(inputFile1);
		inputFile1.setColumns(10);
		
		final JFileChooser inputFileChooser1 = new JFileChooser();
		final JFileChooser outputFileChooser1 = new JFileChooser();
		
		inputFileChooser1.addPropertyChangeListener(new PropertyChangeListener() {		
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(evt.getPropertyName()))
				{
					JFileChooser chooser = (JFileChooser)evt.getSource();
					inputFile1.setText(chooser.getSelectedFile().getAbsolutePath());
				}			
			}
		});
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
					inputFileChooser1.showDialog(frame, "Choose");
			}
		});
		
		
		btnSelectFile.setBounds(10, 60, 100, 23);
		panel1.add(btnSelectFile);
		
		JLabel lblDestinationFileFor = new JLabel("Ouput File:");
		lblDestinationFileFor.setBounds(10, 107, 116, 14);
		panel1.add(lblDestinationFileFor);
		
		outputFile1 = new JTextField();
		outputFile1.setBounds(10, 126, 320, 20);
		panel1.add(outputFile1);
		outputFile1.setColumns(10);
		
		JButton btnCreateFile = new JButton("Create file");
		btnCreateFile.setBounds(10, 153, 100, 23);
		panel1.add(btnCreateFile);
		
		outputFileChooser1.addPropertyChangeListener(new PropertyChangeListener() {		
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				JFileChooser chooser = (JFileChooser)evt.getSource();
				if(option == JFileChooser.APPROVE_OPTION)
				{  
					if(chooser.getSelectedFile()!=null)
					{  
						outputFile1.setText(chooser.getSelectedFile().getAbsolutePath());
					}
				}
			}
		});
		
		btnCreateFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				option = outputFileChooser1.showDialog(frame,"Save");
				//task2InputFilePath.setText(task1OutputFilePath);
			}
		});
		
		final JTextArea outPut1 = new JTextArea();
		outPut1.setBounds(370, 27, 700, 580);
		panel1.add(outPut1);
		
		JLabel lblOutput = new JLabel("Console Output:");
		lblOutput.setBounds(370, 11, 120, 14);
		panel1.add(lblOutput);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(55, 199, 89, 23);
		panel1.add(btnRun);
		
		btnRun.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ProcessBuilder pb = new ProcessBuilder("python","D:/codes/codes/python/hello.py",inputFile1.getText(),outputFile1.getText());
					Process p = pb.start();

					BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String ret = in.readLine();
					while(ret != null)
					{
						String words[] = ret.split(" ");
						int curX = 0;
						for(String word : words )
						{
							curX+=word.length();
							if(curX < 120)
								outPut1.append(word+" ");
							else
							{
								outPut1.append("\n");
								outPut1.append(word+" ");
								curX = word.length();
							}
						}
						outPut1.append("\n");
						ret = in.readLine();
					}
				}
				catch(Exception exc)
				{
					exc.printStackTrace();
				}
				//outPut1.setText("Running operation-1");
				//JOptionPane.showMessageDialog(frame, "Running operation 1.");
			}          
		});
		
		
	// Operation-2 panel:	
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Operation-2", null, panel2, null);
		panel2.setLayout(null);
		
		JLabel lblArgument1ForOperation2 = new JLabel("Argument-1 for operation-2:");
		lblArgument1ForOperation2.setBounds(10, 11, 154, 14);
		panel2.add(lblArgument1ForOperation2);
		
		arg1Op2 = new JTextField();
		arg1Op2.setBounds(10, 36, 320, 20);
		panel2.add(arg1Op2);
		arg1Op2.setColumns(10);
		
		JLabel lblArgument2ForOperation2 = new JLabel("Argument-2 for operation-2:");
		lblArgument2ForOperation2.setBounds(10, 95, 170, 14);
		panel2.add(lblArgument2ForOperation2);
		
		arg2op2 = new JTextField();
		arg2op2.setBounds(10, 126, 320, 20);
		panel2.add(arg2op2);
		arg2op2.setColumns(10);
		
		JButton btnRun2 = new JButton("Run");
		btnRun2.setBounds(55, 199, 89, 23);
		panel2.add(btnRun2);
		
		final JTextArea outPut2 = new JTextArea();
		outPut2.setBounds(370, 27, 700, 580);
		panel2.add(outPut2);
		
		JLabel lblOutput2 = new JLabel("Console Output:");
		lblOutput2.setBounds(370, 11, 120, 14);
		panel2.add(lblOutput2);
		
		btnRun2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				outPut2.append("Running operation-1");
				outPut2.append("Argumnets:"+arg1Op2.getText()+arg2op2.getText());
				//JOptionPane.showMessageDialog(frame, "Running operation 2.");
			}          
		});
		
		
	// Operation-3 Panel :
		
		JPanel panel3 = new JPanel();
		tabbedPane.addTab("Operation-3", null, panel3, null);
		panel3.setLayout(null);
		
		JLabel lblArgument1ForOperation3 = new JLabel("Argument-1 for operation-3:");
		lblArgument1ForOperation3.setBounds(10, 11, 170, 14);
		panel3.add(lblArgument1ForOperation3);
		
		arg1op3 = new JTextField();
		arg1op3.setBounds(10, 36, 320, 20);
		panel3.add(arg1op3);
		arg1op3.setColumns(10);
		
		JLabel lblArgument2ForOperation3 = new JLabel("Argument-2 for operation-3:");
		lblArgument2ForOperation3.setBounds(10, 95, 170, 14);
		panel3.add(lblArgument2ForOperation3);
		
		arg2op3 = new JTextField();
		arg2op3.setBounds(10, 126, 320, 20);
		panel3.add(arg2op3);
		arg2op3.setColumns(10);		
		
		final JTextArea outPut3 = new JTextArea();
		outPut3.setBounds(370, 27, 700, 580);
		panel3.add(outPut3);
		
		JLabel lblOutput3 = new JLabel("Console Output:");
		lblOutput3.setBounds(370, 11, 120, 14);
		panel3.add(lblOutput3);
		
		JButton btnRun3 = new JButton("Run");
		btnRun3.setBounds(55, 199, 89, 23);
		panel3.add(btnRun3);
		
		btnRun3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				outPut3.append("Running operation-1");
				outPut3.append("Arguments:"+arg1op3.getText()+arg2op3.getText());
				//JOptionPane.showMessageDialog(frame, "Running operation 2.");
			}          
		});		
		
	/** Operation-3 Panel end **/
	}

}
