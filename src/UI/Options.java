package UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Options extends JPanel {

	MainFrame parent = null;
	
	/**
	 * Create the panel.
	 */
	public Options(MainFrame parentFrame) {

		parent = parentFrame;
		
		setLayout(null);
		setBounds(100, 100, 800, 640);
		
		JButton newButton = new JButton("New");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickNew();
			}
		});
		newButton.setBounds(599, 55, 128, 37);
		add(newButton);
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickLoad();
			}
		});
		loadButton.setBounds(599, 103, 128, 37);
		add(loadButton);
		
		JButton btnSaveAs = new JButton("Save As");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickSave();
			}
		});
		btnSaveAs.setBounds(599, 151, 128, 37);
		add(btnSaveAs);
		
		JButton btnExitApplication = new JButton("Exit Application");
		btnExitApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickExit();
			}
		});
		btnExitApplication.setBounds(599, 199, 128, 37);
		add(btnExitApplication);
		
		JButton btnNewButton = new JButton("GENERATE TEST");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickGenerate();
			}
		});
		btnNewButton.setBounds(70, 55, 356, 181);
		add(btnNewButton);
		
	}
	
	private void clickNew(){
		//todo: check if it's ok
		
		//tell MainFrame to prepare new configuration
		parent.newConfiguration();
		
	}
	
	private void clickLoad(){
		//todo: check if it's ok
		
		//tell MainFrame to load configuration
		parent.loadConfiguration();
		
	}
	
	private void clickSave(){
		//todo: check if it's ok
		
		//tell MainFrame to load configuration
		parent.saveConfiguration();
		
	}
	
	private void clickExit(){
		//todo: check if it's ok
		
		//tell MainFrame to load configuration
		parent.closeApplication();

	}
	
	private void clickGenerate(){
		//todo: check if it's ok
		
		//tell MainFrame to load configuration
		parent.generateTests();

	}
}
