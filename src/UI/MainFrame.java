package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.parser.ParseException;

import fileOperators.JSONloader;
import fileOperators.JSONsaver;
import fileOperators.Test;
import generator.GeneratorAlpha;

import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private Options options;
	private GeneralConfiguration general;
	private Start start;
	private Elements elements;
	private End end;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("AutoTestMakeUp");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		options = new Options(this);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		newConfiguration();
	}
	
	
	
	public void newConfiguration(){
		tabbedPane.removeAll();
		
		general = new GeneralConfiguration();
		tabbedPane.addTab("General Configuration", null, general, null);
		
		start = new Start();
		tabbedPane.addTab("Start", null, start, null);
		
		elements = new Elements();
		tabbedPane.addTab("Elements", null, elements, null);
		
		end = new End();
		tabbedPane.addTab("End", null, end, null);
		
		
		tabbedPane.addTab("Options", null, options, null);
		
		tabbedPane.setSelectedComponent(options);
		
	}
	
	public void loadConfiguration(){
	
		JFileChooser fc = new JFileChooser();
		
		int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            try {
				new JSONloader(file, general, options, start, elements, end);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            new JOptionPane().showMessageDialog(this, "Opening: " + file.getName() + ".", "fileLoader", JOptionPane.INFORMATION_MESSAGE);
        } else {
        	System.out.println("Open command cancelled by user.");
        }
	
	}
	
	public void saveConfiguration(){
		
		JFileChooser fc = new JFileChooser();
		
		int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            new JSONsaver(file, general, options, start, elements, end );
            
            new JOptionPane().showMessageDialog(this, "Saving: " + file.getName() + ".", "fileSaver", JOptionPane.INFORMATION_MESSAGE);
        } else {
        	System.out.println("Open command cancelled by user.");
        }
		
	}
	
	public void closeApplication(){
		System.exit(0);
		
	}
	
	public void generateTests(){
		
		GeneratorAlpha gen = new GeneratorAlpha();
		System.out.println("RETURNED DATA : " + start.returnData());
		gen.generateAlpha(start.returnData());
		
	}
	
}
