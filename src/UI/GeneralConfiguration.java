package UI;

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;

import configurations.GeneralConfig;
import fileOperators.JSONloader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.beans.PropertyChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class GeneralConfiguration extends JPanel {
	private JTextField limitOfSteps;
	private JTextField browserPath;
	private JCheckBox chkLimitOfSteps;
	private JComboBox browser;
	private JTextField testDirectory;
	
	
	/**
	 * Create the panel.
	 */
	public GeneralConfiguration() {

		setBounds(100, 100, 800, 640);
		setLayout(null);
		
		chkLimitOfSteps = new JCheckBox("Set limit of steps for test:");
		chkLimitOfSteps.setBounds(56, 234, 175, 23);
		add(chkLimitOfSteps);
		
		limitOfSteps = new JTextField();
		limitOfSteps.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try
			      { 
			         if(Integer.parseInt(limitOfSteps.getText())<=0){
			        	 limitOfSteps.setText("100");
			         }
			      }
			      catch (NumberFormatException ex)
			      {
			         limitOfSteps.setText("100");
			      }
			}
		});
		limitOfSteps.setText("100");
		limitOfSteps.setBounds(237, 235, 86, 20);
		add(limitOfSteps);
		limitOfSteps.setColumns(10);
		
		JLabel lblSteps = new JLabel("steps");
		lblSteps.setBounds(333, 238, 46, 14);
		add(lblSteps);
		
		JButton btnSelectBrowserdriverPath = new JButton("Select browser/driver path");
		btnSelectBrowserdriverPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				loadBrowser();
				
			}
		});
		btnSelectBrowserdriverPath.setBounds(56, 152, 210, 23);
		add(btnSelectBrowserdriverPath);
		
		browserPath = new JTextField();
		browserPath.setBounds(56, 186, 500, 20);
		add(browserPath);
		browserPath.setColumns(10);
		
		JLabel lblChooseYourBrowser = new JLabel("Choose your browser");
		lblChooseYourBrowser.setBounds(56, 73, 175, 14);
		add(lblChooseYourBrowser);
		
		browser = new JComboBox();
		browser.setBounds(56, 98, 240, 20);
		browser.addItem("Chrome");
		browser.addItem("Firefox");
		add(browser);
		
		JLabel lblSaveTestIn = new JLabel("Save test in this directory:");
		lblSaveTestIn.setBounds(56, 294, 128, 14);
		add(lblSaveTestIn);
		
		testDirectory = new JTextField();
		testDirectory.setText("C:\\AutoTestMakeUp\\GeneratedTests");
		testDirectory.setBounds(56, 319, 500, 20);
		add(testDirectory);
		testDirectory.setColumns(10);
		
	}
	
	private void loadBrowser(){
		
		
		JFileChooser fc = new JFileChooser();
		
		int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            //This is where a real application would open the file.
            browserPath.setText(file.getAbsolutePath());
            //new JOptionPane().showMessageDialog(this, "Opening: " + file.getName() + ".", "fileLoader", JOptionPane.INFORMATION_MESSAGE);
        } else {
        	System.out.println("Open command cancelled by user.");
        }
		
	}
	
	public JSONObject saveData(){
		
		JSONObject generalConfigurationData = new JSONObject();
		generalConfigurationData.put("browserPath", browserPath.getText());
		generalConfigurationData.put("chkLimitOfSteps", chkLimitOfSteps.isSelected());
		generalConfigurationData.put("limitOfSteps", limitOfSteps.getText());
		generalConfigurationData.put("browser", browser.getSelectedItem().toString());
		generalConfigurationData.put("testDirectory", testDirectory.getText());
		
		//startData.put("Test2", testCheck);		
		
		return generalConfigurationData;
			
	}
	
	public void loadData(JSONObject data){
		
		browserPath.setText(data.getString("browserPath"));
		chkLimitOfSteps.setSelected((boolean) data.get("chkLimitOfSteps"));	
		limitOfSteps.setText(data.getString("limitOfSteps"));
		browser.setSelectedItem(data.getString("browser"));
		testDirectory.setText(data.getString("testDirectory"));

	}
	
	public GeneralConfig getConfigutration(){
		
		if(browserPath.getText().isEmpty()){
			
			new JOptionPane().showMessageDialog(this, "Some data in GeneralConfiguration is missing.", "GeneralConfigurationMessage", JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		GeneralConfig conf = new GeneralConfig(browserPath.getText(), chkLimitOfSteps.isSelected(), Integer.parseInt(limitOfSteps.getText()), browser.getSelectedItem().toString(), testDirectory.getText());
		
		return conf;
	}
}
