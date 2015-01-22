import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ImageViewer {
	private JFrame frame;
	/**
	 * Define main class
	 */
	public static void main(String[] args) {
    	new ImageViewer();
    }
	
	/**
	 * Build the ImageViewer and show it on screen
	 */
	public ImageViewer() {
		makeFrame();
	}
	
	/**
	 * Construct the Swing-frame and the content
	 */
	private void makeFrame() {
		frame = new JFrame("ImageViewer");
		Container contentPane = frame.getContentPane();
		frame.setSize(1000, 700);
		
		JPanel toolbar = new JPanel();
		BoxLayout buttons = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
		frame.setLayout(buttons);
		JButton stepOne = new JButton("Step 1");
		toolbar.add(stepOne);
		JButton stepHundred = new JButton("Step 100");
		toolbar.add(stepHundred);
		JButton Simulatie = new JButton("Sumulatie");
		toolbar.add(Simulatie);
		contentPane.add(toolbar, BorderLayout.WEST);
		
		JLabel label = new JLabel("I am a label.");
		contentPane.add(label);
		
		frame.setVisible(true);
	}
}
