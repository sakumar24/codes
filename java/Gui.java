import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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


public class Gui 
{
	private JFrame frame;
	private JTextField inputFile;
	private JTextField outputFile1;
	private JTextField arg1Op2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	String task1OutputFilePath=null;
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
		frame.setBounds(200, 200, 750, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(2, 2, 730, 555);
		frame.getContentPane().add(tabbedPane);
		
		
	// Operation-1 panel:
		JPanel panel1 = new JPanel();
		tabbedPane.addTab("Operation-1", null, panel1, null);
		panel1.setLayout(null);
		
		JLabel lblInputFileFor = new JLabel("Input File for operation1:");
		lblInputFileFor.setBounds(10, 11, 154, 14);
		panel1.add(lblInputFileFor);
		
		inputFile = new JTextField();
		inputFile.setBounds(10, 36, 220, 20);
		panel1.add(inputFile);
		inputFile.setColumns(10);
		
		final JFileChooser inputFileChooser1 = new JFileChooser();
		final JFileChooser outputFileChooser1 = new JFileChooser();
		
		inputFileChooser1.addPropertyChangeListener(new PropertyChangeListener() {		
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				if(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(evt.getPropertyName()))
				{
					JFileChooser chooser = (JFileChooser)evt.getSource();
					inputFile.setText(chooser.getSelectedFile().getAbsolutePath());
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
		outputFile1.setBounds(10, 126, 220, 20);
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
						task1OutputFilePath = chooser.getSelectedFile().getAbsolutePath();
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
		outPut1.setBounds(270, 27, 450, 450);
		panel1.add(outPut1);
		
		JLabel lblOutput = new JLabel("Output:");
		lblOutput.setBounds(267, 11, 46, 14);
		panel1.add(lblOutput);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(55, 199, 89, 23);
		panel1.add(btnRun);
		
		btnRun.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				outPut1.setText("Running operation-1");
				//JOptionPane.showMessageDialog(frame, "Running operation 1.");
			}          
		});
		
		
	// Operation-2 panel:	
		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Operation-2", null, panel2, null);
		panel2.setLayout(null);
		
		JLabel lblArgument1ForOperation2 = new JLabel("Argument-1 for operation-2:");
		lblArgument1ForOperation2.setBounds(10, 11, 170, 14);
		panel2.add(lblArgument1ForOperation2);
		
		arg1Op2 = new JTextField();
		arg1Op2.setBounds(10, 36, 220, 20);
		panel2.add(arg1Op2);
		arg1Op2.setColumns(10);
		
		JLabel lblArgument2ForOperation2 = new JLabel("Argument-2 for operation-2:");
		lblArgument2ForOperation2.setBounds(10, 95, 170, 14);
		panel2.add(lblArgument2ForOperation2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(10, 126, 220, 20);
		panel2.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnRun2 = new JButton("Run");
		btnRun2.setBounds(55, 199, 89, 23);
		panel2.add(btnRun2);
		
		final JTextArea outPut2 = new JTextArea();
		outPut2.setBounds(270, 27, 450, 450);
		panel2.add(outPut2);
		
		JLabel lblOutput2 = new JLabel("Output:");
		lblOutput2.setBounds(267, 11, 46, 14);
		panel2.add(lblOutput2);
		
		btnRun2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				outPut2.setText("Running operation-1");
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
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 36, 220, 20);
		panel3.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblArgument2ForOperation3 = new JLabel("Argument-2 for operation-3:");
		lblArgument2ForOperation3.setBounds(10, 95, 170, 14);
		panel3.add(lblArgument2ForOperation3);
		
		textField_5 = new JTextField();
		textField_5.setBounds(10, 126, 220, 20);
		panel3.add(textField_5);
		textField_5.setColumns(10);		
		
		final JTextArea outPut3 = new JTextArea();
		outPut3.setBounds(270, 27, 450, 450);
		panel3.add(outPut3);
		
		JLabel lblOutput3 = new JLabel("Output:");
		lblOutput3.setBounds(267, 11, 46, 14);
		panel3.add(lblOutput3);
		
		JButton btnRun3 = new JButton("Run");
		btnRun3.setBounds(55, 199, 89, 23);
		panel3.add(btnRun3);
		
		btnRun3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				outPut3.setText("Running operation-1");
				//JOptionPane.showMessageDialog(frame, "Running operation 2.");
			}          
		});		
		
	/** Operation-3 Panel end **/
	}

}
