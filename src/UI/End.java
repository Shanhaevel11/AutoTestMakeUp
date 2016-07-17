package UI;

import java.lang.reflect.Method;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.JSONObject;

import configurations.EndConfig;
import configurations.StartConfig;
import testTools.Tools;


public class End extends JPanel {

	JCheckBox useEnd;
	JComboBox methodBox;
	JCheckBox useOtherMethod;
	
	/**
	 * Create the panel.
	 */
	public End() {
		setBounds(100, 100, 800, 640);
		setLayout(null);
		
		useEnd = new JCheckBox("Use End Method after test");
		useEnd.setBounds(56, 82, 265, 23);
		useEnd.setSelected(true);
		add(useEnd);
		
		methodBox = new JComboBox();
		methodBox.setBounds(56, 204, 265, 20);
		add(methodBox);
		JLabel lblChooseOtherMethod = new JLabel("Choose other method to run at the beginning of the test");
		lblChooseOtherMethod.setBounds(56, 179, 323, 14);
		add(lblChooseOtherMethod);
		
		useOtherMethod = new JCheckBox("Use Other Method after test");
		useOtherMethod.setBounds(56, 108, 265, 23);
		add(useOtherMethod);
	
		getPossibleMethods();
	}

	
	private void getPossibleMethods(){
		
		Tools tools = new Tools();
		Method[] methods = tools.getClass().getDeclaredMethods();
		
		for (Method m  : methods) {
	         methodBox.addItem(m.getName());
	      }
		
		
	}
	
	public JSONObject saveData(){
		
		JSONObject endData = new JSONObject();
		endData.put("useEnd", useEnd.isSelected());
		endData.put("useOtherMethod", useOtherMethod.isSelected());
		endData.put("otherMethod", methodBox.getSelectedItem().toString());
		//startData.put("Test2", testCheck);		
		
		return endData;
			
	}
	
	public void loadData(JSONObject data){
		
		useEnd.setSelected((boolean) data.get("useEnd"));
		useOtherMethod.setSelected((boolean) data.get("useOtherMethod"));	
		methodBox.setSelectedItem(data.getString("otherMethod"));
	}
	
	public String returnData(){
		
		return methodBox.getSelectedItem().toString();
		
	}

	public EndConfig getConfigutration(){
		
//		if(browserPath.getText().isEmpty()){
//			
//			new JOptionPane().showMessageDialog(this, "Some data in GeneralConfiguration is missing.", "GeneralConfigurationMessage", JOptionPane.INFORMATION_MESSAGE);
//			
//		}
		
		EndConfig conf = new EndConfig(useEnd.isSelected(), useOtherMethod.isSelected(), methodBox.getSelectedItem().toString());
		
		return conf;
	}
	
}
