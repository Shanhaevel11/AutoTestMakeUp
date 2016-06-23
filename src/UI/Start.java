package UI;

import javax.swing.JPanel;

import org.json.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import testTools.Tools;

import com.jgoodies.forms.layout.FormSpecs;

import java.lang.reflect.Method;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class Start extends JPanel {

	JCheckBox testCheck;
	JCheckBox useStart;
	JComboBox methodBox;
	JCheckBox useOtherMethod;
	
	/**
	 * Create the panel.
	 */
	public Start() {
		setBounds(100, 100, 800, 640);
		setLayout(null);
		
		testCheck = new JCheckBox("New check box");
		testCheck.setBounds(56, 56, 265, 23);
		add(testCheck);
		
		useStart = new JCheckBox("Use Start Method at the beggining");
		useStart.setBounds(56, 82, 265, 23);
		useStart.setSelected(true);
		add(useStart);
		
		methodBox = new JComboBox();
		methodBox.setBounds(56, 204, 265, 20);
		add(methodBox);
		JLabel lblChooseOtherMethod = new JLabel("Choose other method to run at the beginning of the test");
		lblChooseOtherMethod.setBounds(56, 179, 323, 14);
		add(lblChooseOtherMethod);
		
		useOtherMethod = new JCheckBox("Use Other Method at the beggining");
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
		
		JSONObject startData = new JSONObject();
		startData.put("Test", testCheck.isSelected());
		startData.put("useStart", useStart.isSelected());
		startData.put("useOtherMethod", useOtherMethod.isSelected());
		startData.put("otherMethod", methodBox.getSelectedItem().toString());
		//startData.put("Test2", testCheck);		
		
		return startData;
			
	}
	
	public void loadData(JSONObject data){
		
		testCheck.setSelected((boolean) data.get("Test"));	
		useStart.setSelected((boolean) data.get("useStart"));
		useOtherMethod.setSelected((boolean) data.get("useOtherMethod"));	
		methodBox.setSelectedItem(data.getString("otherMethod"));
	}
	
	public String returnData(){
		
		return methodBox.getSelectedItem().toString();
		
	}
}
