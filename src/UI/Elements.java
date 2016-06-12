package UI;

import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout.Alignment;

public class Elements extends JSplitPane {

	/**
	 * Create the panel.
	 */
	public Elements() {

		setBounds(100, 100, 800, 640);
		
		JPanel panel = new JPanel();
		this.setRightComponent(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 504, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 562, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		this.setLeftComponent(scrollPane);
		
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
	}
}
