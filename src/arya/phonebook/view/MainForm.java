package arya.phonebook.view;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainForm extends JFrame{
	
	private static final long serialVersionUID = -6585916574883893128L;
	private final int width = 400;
	private final int height = 300;
	
	private JButton button;
	private JPanel jPanel;
	
	public MainForm() {
		init();
	}
	
	private void init() {
		super.setVisible(true);
		super.setSize(width, height);
		super.setLocation(getLocationOnScreen());
		super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		super.add(getjPanel());
	}
	
	public JButton getButton() {
		if (button == null) {
			button = new JButton("hello");
			button.addActionListener((ActionEvent e) -> {
				JOptionPane.showMessageDialog(null, "hii!!");
			});
			button.setSize(10, 10);
		}
		return button;
	}
	
	public JPanel getjPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.add(getButton());
		}
		return jPanel;
	}
}
