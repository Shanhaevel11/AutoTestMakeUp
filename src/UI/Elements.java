package UI;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import generator.Entry;
import testTools.Tools;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Elements extends JSplitPane {
	private JTextField nameOfElement;
	private JTextField IdOfElement;
	private JTextField NameOfElement;
	private JTextField XpathOfElement;
	private JComboBox executionMethod;
	private JRadioButton radioUseId;
	private JRadioButton radioUseName;
	private JRadioButton radioUseXpath;
	private JCheckBox chkPassElement;
	private JCheckBox chkPassBrowser;
	private JList list;
	private Vector<Entry> entries = new Vector();
	private Vector names = new Vector();
	private JScrollPane scrollPane;
	private JButton btnDeleteSelected;
	private Entry selectedEntry;
	private JLabel lblHowManyTimes;
	private JTextField usageLimit;
	private JCheckBox chkPageWillChange;
	private JCheckBox chkUseWaitMethod;
	
	
	/**
	 * Create the panel.
	 */
	public Elements() {

		setBounds(100, 100, 800, 640);

		JPanel panel = new JPanel();
		this.setRightComponent(panel);
		panel.setLayout(null);

		JLabel labelName = new JLabel("Name of entry: ");
		labelName.setBounds(52, 206, 197, 23);
		panel.add(labelName);

		nameOfElement = new JTextField();
		nameOfElement.setBounds(263, 207, 230, 20);
		panel.add(nameOfElement);
		nameOfElement.setColumns(10);

		radioUseId = new JRadioButton("Use id to detect element");
		radioUseId.setBounds(52, 237, 201, 23);
		panel.add(radioUseId);

		radioUseName = new JRadioButton("Use name to detect element");
		radioUseName.setBounds(52, 264, 201, 23);
		panel.add(radioUseName);

		radioUseXpath = new JRadioButton("Use xpath to detect element");
		radioUseXpath.setBounds(52, 290, 201, 23);
		panel.add(radioUseXpath);

		ButtonGroup group = new ButtonGroup();
		group.add(radioUseId);
		group.add(radioUseName);
		group.add(radioUseXpath);
		
		radioUseId.setSelected(true);

		IdOfElement = new JTextField();
		IdOfElement.setColumns(10);
		IdOfElement.setBounds(263, 238, 230, 20);
		panel.add(IdOfElement);

		NameOfElement = new JTextField();
		NameOfElement.setColumns(10);
		NameOfElement.setBounds(263, 265, 230, 20);
		panel.add(NameOfElement);

		XpathOfElement = new JTextField();
		XpathOfElement.setColumns(10);
		XpathOfElement.setBounds(263, 291, 230, 20);
		panel.add(XpathOfElement);

		JButton btnAddNewElement = new JButton("Add new Element");
		btnAddNewElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				addNewEntry();

			}
		});
		btnAddNewElement.setBounds(52, 29, 150, 23);
		panel.add(btnAddNewElement);

		JButton btnSaveElement = new JButton("Save Element");
		btnSaveElement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				saveEntry();
				
			}
		});
		btnSaveElement.setBounds(263, 29, 150, 23);
		panel.add(btnSaveElement);

		JButton btnNewButton = new JButton("Move Element Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveSelectedUp();
			}
		});
		btnNewButton.setBounds(52, 130, 150, 23);
		panel.add(btnNewButton);

		JButton btnMoveElementDown = new JButton("Move Element Down");
		btnMoveElementDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				moveSelectedDown();
			}
		});
		btnMoveElementDown.setBounds(52, 165, 150, 23);
		panel.add(btnMoveElementDown);

		JLabel lblChooseMethodTo = new JLabel("Choose method to run:");
		lblChooseMethodTo.setBounds(56, 332, 323, 14);
		panel.add(lblChooseMethodTo);

		executionMethod = new JComboBox();
		executionMethod.setBounds(56, 357, 265, 20);

		getPossibleMethods();

		panel.add(executionMethod);
		
		chkPassElement = new JCheckBox("Pass Detected Element as argument");
		chkPassElement.setBounds(52, 384, 265, 23);
		panel.add(chkPassElement);
		
		chkPassBrowser = new JCheckBox("Pass Browser as argument");
		chkPassBrowser.setBounds(52, 410, 265, 23);
		panel.add(chkPassBrowser);
		
		btnDeleteSelected = new JButton("Delete selected");
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				deleteEntry();
			
			}
		});
		btnDeleteSelected.setBounds(52, 83, 150, 23);
		panel.add(btnDeleteSelected);
		
		lblHowManyTimes = new JLabel("How many times can be used:");
		lblHowManyTimes.setBounds(52, 463, 208, 23);
		panel.add(lblHowManyTimes);
		
		usageLimit = new JTextField();
		usageLimit.setText("100");
		usageLimit.setColumns(10);
		usageLimit.setBounds(263, 464, 230, 20);
		
		usageLimit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				
				try
			      { 
			         if(Integer.parseInt(usageLimit.getText())<=0){
			        	 usageLimit.setText("100");
			         }
			      }
			      catch (NumberFormatException ex)
			      {
			    	  usageLimit.setText("100");
			      }
			}
		});
		
		panel.add(usageLimit);
		
		chkUseWaitMethod = new JCheckBox("Use Wait method after execution");
		chkUseWaitMethod.setSelected(true);
		chkUseWaitMethod.setBounds(52, 497, 269, 23);
		panel.add(chkUseWaitMethod);
		
		chkPageWillChange = new JCheckBox("Page will change after handling this entry");
		chkPageWillChange.setBounds(52, 523, 269, 23);
		panel.add(chkPageWillChange);

		scrollPane = new JScrollPane();
		this.setLeftComponent(scrollPane);

		list = new JList(names);
		scrollPane.setViewportView(list);

		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				for (Entry en : entries){
					if(en.getName().equals(list.getSelectedValue())){
						selectedEntry = en;
					}
				}
				
				if(list.getSelectedIndex()>=0)loadSelectedEntry();
				
			}
		});
		
		
	}

	private void getPossibleMethods() {

		Tools tools = new Tools();
		Method[] methods = tools.getClass().getDeclaredMethods();

		for (Method m : methods) {
			executionMethod.addItem(m.getName());
		}

	}

	private void loadSelectedEntry(){
		//nameOfElement.getText(), detectionType, detectionValue, executionMethod.getSelectedItem().toString(), chkPassElement.isSelected(), chkPassBrowser.isSelected()
		
		nameOfElement.setText(selectedEntry.getName());
		IdOfElement.setText("");
		NameOfElement.setText("");
		XpathOfElement.setText("");
		
		
		if(selectedEntry.getDetectionType().equals("ID")){
			radioUseId.setSelected(true);
			IdOfElement.setText(selectedEntry.getDetectionValue());
			
		}else if(selectedEntry.getDetectionType().equals("NAME")){
			radioUseName.setSelected(true);
			NameOfElement.setText(selectedEntry.getDetectionValue());

		}else{
			radioUseXpath.setSelected(true);
			XpathOfElement.setText(selectedEntry.getDetectionValue());

		}
		
		//System.out.println("Yolo3" + selectedEntry.getName() + selectedEntry.getDetectionType() + selectedEntry.getDetectionValue());
		usageLimit.setText(Integer.toString(selectedEntry.getUsageLimit()));
		executionMethod.setSelectedItem(selectedEntry.getMethodName());
		chkPassElement.setSelected(selectedEntry.isPassElement());
		chkPassBrowser.setSelected(selectedEntry.isPassBrowser());
		chkUseWaitMethod.setSelected(selectedEntry.isWaitAfterMethod());
		chkPageWillChange.setSelected(selectedEntry.isPageChanger());
		
	}
	
	private void moveSelectedUp(){
		if(list.isSelectedIndex(0) || list.isSelectionEmpty())return;
		//System.out.println("movin up!");
		int pos = list.getSelectedIndex();
		Entry en = entries.get(pos);
		entries.add((pos -1), en);
		entries.remove(pos +1);
		names.add(pos-1,en.getName());
		names.remove(pos+1);
		list.setListData( names );
		scrollPane.revalidate();
		scrollPane.repaint();
		list.setSelectedIndex(pos-1);
	}
	
	private void moveSelectedDown(){
		if(list.isSelectedIndex((entries.size()-1)) || list.isSelectionEmpty())return;
		//System.out.println("movin down!");
		int pos = list.getSelectedIndex();
		Entry en = entries.get(pos);
		entries.add((pos +2), en);
		entries.remove(pos);
		names.add(pos+2,en.getName());
		names.remove(pos);
		list.setListData( names );
		scrollPane.revalidate();
		scrollPane.repaint();
		list.setSelectedIndex(pos+1);
	}
	
	private void deleteEntry(){
		
		if(list.getSelectedIndex()<0){
			
			new JOptionPane().showMessageDialog(this,"Select entry of list to erase");
			return;
			
		}
		
		if(entries.get(list.getSelectedIndex()).getName().equals(selectedEntry.getName()))selectedEntry=null;
		
		names.remove(list.getSelectedIndex());
		entries.remove(list.getSelectedIndex());
		list.setListData( names );
		scrollPane.revalidate();
		scrollPane.repaint();
	}
	
	private void addEntryAtPosition(int index){
		
		if(!validateForSaving())return;
		String detectionType = "ID";
		String detectionValue = "NONE";
		if(radioUseId.isSelected()){
			detectionType="ID";
			detectionValue=IdOfElement.getText();
		}else if(radioUseName.isSelected()){
			detectionType="NAME";
			detectionValue=NameOfElement.getText();
		}else if(radioUseXpath.isSelected()){
			detectionType="XPATH";
			detectionValue=XpathOfElement.getText();
		}
		
		int limit = 100;
		try
	      { 
	         if(Integer.parseInt(usageLimit.getText())>0){
	        	 limit = Integer.parseInt(usageLimit.getText());
	         }
	      }
	      catch (NumberFormatException ex)
	      {
	    	  usageLimit.setText("100");
	      }
		
		
		Entry entry = new Entry(nameOfElement.getText(), detectionType, detectionValue, 
				executionMethod.getSelectedItem().toString(), chkPassElement.isSelected(), chkPassBrowser.isSelected(), limit, chkUseWaitMethod.isSelected(), chkPageWillChange.isSelected() );
		
		if(names.contains(entry.getName())){
			
			new JOptionPane().showMessageDialog(this,"This name of entry already exist on the list!");
			return;
		}

		entries.add(index, entry);
		names.add(index, entry.getName());
		list.setListData( names );
		scrollPane.revalidate();
		scrollPane.repaint();
		
		selectedEntry=entry;
		
	}
	
	private void addNewEntry() {

		if(!validateForSaving())return;
		String detectionType = "ID";
		String detectionValue = "NONE";
		if(radioUseId.isSelected()){
			detectionType="ID";
			detectionValue=IdOfElement.getText();
		}
		if(radioUseName.isSelected()){
			detectionType="NAME";
			detectionValue=NameOfElement.getText();
		}
		if(radioUseXpath.isSelected()){
			detectionType="XPATH";
			detectionValue=XpathOfElement.getText();
		}
		
		int limit = 100;
		try
	      { 
	         if(Integer.parseInt(usageLimit.getText())>0){
	        	 limit = Integer.parseInt(usageLimit.getText());
	         }
	      }
	      catch (NumberFormatException ex)
	      {
	    	  usageLimit.setText("100");
	      }
		
		
		Entry entry = new Entry(nameOfElement.getText(), detectionType, detectionValue, executionMethod.getSelectedItem().toString(), 
				chkPassElement.isSelected(), chkPassBrowser.isSelected(), limit, chkUseWaitMethod.isSelected(), chkPageWillChange.isSelected());
		
		if(names.contains(entry.getName())){
			
			new JOptionPane().showMessageDialog(this,"This name of entry already exist on the list!");
			return;
		}
		
		entries.addElement(entry);
		names.addElement(entry.getName());
		list.setListData( names );
		scrollPane.revalidate();
		scrollPane.repaint();
		list.setSelectedIndex(names.indexOf(entry.getName()));
		
	}
	
	private void saveEntry() {

		if(selectedEntry==null){
			new JOptionPane().showMessageDialog(this,"You haven't chosen any entry from the list!\n Maybe you want to use \"Add new Element\" instead? ");
			return;
		}else if(!selectedEntry.getName().equals(list.getSelectedValue())){
			
			String[] options = new String[] {"Yes", "No"};
		    int response = JOptionPane.showOptionDialog(null, "The selected item is different then the one loaded into workspace...odd\n Do you want to save over " + selectedEntry.getName() + " ?", "Double check!",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
			if(response==1){
				return;
			}
			
		}
		
		if(!validateForSaving())return;
		
		int position = entries.indexOf(selectedEntry);
		names.remove(position);
		entries.remove(position);
		addEntryAtPosition(position);
		list.setSelectedIndex(position);
		
	}
	
	private boolean validateForSaving(){
		if(nameOfElement.getText().isEmpty()){
			new JOptionPane().showMessageDialog(this,"You need to add Name of Entry!");
			return false;
		}
		
		if((radioUseId.isSelected() && IdOfElement.getText().isEmpty()) || (radioUseName.isSelected() && NameOfElement.getText().isEmpty()) || (radioUseXpath.isSelected() && XpathOfElement.getText().isEmpty()) ){
			new JOptionPane().showMessageDialog(this,"You need to fill ID/Name/Xpath of Element!");
			return false;
		}
		
		return true;
	}
	
	public JSONObject saveData(){
		
		JSONObject ElementsData = new JSONObject();
		ElementsData.put("entries", entries);
		//startData.put("Test2", testCheck);		
		
		return ElementsData;
			
	}
	
	public void loadData(JSONObject data){
		entries.removeAllElements();
		names.removeAllElements();
		JSONArray toParse = (JSONArray) data.get("entries");
		for(Object en : toParse){
			JSONObject ent = (JSONObject) en;
					Entry entry = new Entry(ent.getString("name"), ent.getString("detectionType"), ent.getString("detectionValue"),
							 ent.getString("methodName"), (boolean) ent.get("passElement"), (boolean) ent.get("passBrowser"), (int) ent.get("usageLimit"), (boolean) ent.getBoolean("waitAfterMethod"), (boolean) ent.getBoolean("pageChanger"));
					entries.addElement(entry);
		}
		for(Entry e : entries){
			names.add(e.getName());
			
		}
		list.setListData( names );
		scrollPane.revalidate();
		scrollPane.repaint();
		if(entries.size()>0)list.setSelectedIndex(0);
	}

	public Vector<Entry> getEntries() {
		return entries;
	}

	public void setEntries(Vector<Entry> entries) {
		this.entries = entries;
	}
	
	
}
