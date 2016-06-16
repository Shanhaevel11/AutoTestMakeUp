package UI;

import javax.swing.JPanel;

import org.json.*;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JCheckBox;

public class Start extends JPanel {

	JCheckBox testCheck;
	
	/**
	 * Create the panel.
	 */
	public Start() {
		setBounds(100, 100, 800, 640);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		testCheck = new JCheckBox("New check box");
		add(testCheck, "6, 6");

		
	}

	
	public JSONObject saveData(){
		
		JSONObject startData = new JSONObject();
		startData.put("Test", testCheck.isSelected());
		//startData.put("Test2", testCheck);		
		
		
		return startData;
		
		
	}
	
}
