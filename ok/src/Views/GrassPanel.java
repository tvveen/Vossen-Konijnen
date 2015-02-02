package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Grass;
import Simulator.Main;

public class GrassPanel {
	
	private static int DEFAULT_BREEDING_AGE = 3;
	private static int DEFAULT_MAX_AGE = 12;
	private static double DEFAULT_BREEDING_PROBABILITY = 0.15;
	private static int DEFAULT_MAX_LITTER_SIZE = 6;
	
	private JPanel grassPanel;
	
	public GrassPanel()
	{
		grassPanel = new JPanel();
		grassPanel.setLayout(null);
		grassPanel.setBounds(1, 41, 500, 559);
		grassPanel.setVisible(false);
		
		
		JLabel grassLabel = new JLabel("Grass Variables");
		grassLabel.setBounds(170, 10, 200, 20);
		grassLabel.setFont(new Font("Bold", Font.BOLD, 22));
		
		
		JLabel grassBreedingAge = new JLabel("Breeding age:");
		grassBreedingAge.setBounds(60, 50, 150, 30);
		
		final JTextField newGrassBreedingAge = new JTextField();
		newGrassBreedingAge.setBounds(205, 50, 200, 30);
		
		
		JLabel grassMaxAge = new JLabel("Max Age:");
		grassMaxAge.setBounds(60, 90, 150, 30);
		
		final JTextField newGrassMaxAge = new JTextField();
		newGrassMaxAge.setBounds(205, 90, 200, 30);
		
		
		JLabel grassBreedingProbability = new JLabel("Breeding probability:");
		grassBreedingProbability.setBounds(60, 130, 150, 30);
		
		final JTextField newGrassBreedingProbability = new JTextField();
		newGrassBreedingProbability.setBounds(205, 130, 200, 30);
		
		
		JLabel grassMaxLitterSize = new JLabel("Max litter size:");
		grassMaxLitterSize.setBounds(60, 170, 150, 30);
		
		final JTextField newGrassMaxLitterSize = new JTextField();
		newGrassMaxLitterSize.setBounds(205, 170, 200, 30);
		
		
		JButton change = new JButton("Change Value(s)");
		change.setBounds(60, 300, 140, 30);
		change.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent changeValues)
			{
				try
				{
						int newBreedingAge = Integer.valueOf(newGrassBreedingAge.getText());
						Grass.setBreedingAge(newBreedingAge);
						newGrassBreedingAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						int newMaxAge = Integer.valueOf(newGrassMaxAge.getText());
						Grass.setMaxAge(newMaxAge);
						newGrassMaxAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						double newProbability = Integer.valueOf(newGrassBreedingProbability.getText());
						Grass.setBreedingProbability(newProbability);
						newGrassBreedingProbability.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}

				
				try
				{
						int newMaxLitter = Integer.valueOf(newGrassMaxLitterSize.getText());
						Grass.setMaxLitterSize(newMaxLitter);
						newGrassMaxLitterSize.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}		
				
				
			}
		});
		
		
		JButton reset = new JButton("Default Value(s)");
		reset.setBounds(210, 300, 140, 30);
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent resetValue)
			{
				Grass.setBreedingAge(DEFAULT_BREEDING_AGE);
				Grass.setMaxAge(DEFAULT_MAX_AGE);
				Grass.setBreedingProbability(DEFAULT_BREEDING_PROBABILITY);
				Grass.setMaxLitterSize(DEFAULT_MAX_LITTER_SIZE);
				Main.getThread().resetThread();
			}
		});
		
		
		
		grassPanel.add(grassLabel);
		grassPanel.add(grassBreedingAge);
		grassPanel.add(newGrassBreedingAge);
		grassPanel.add(grassMaxAge);
		grassPanel.add(newGrassMaxAge);
		grassPanel.add(grassBreedingProbability);
		grassPanel.add(newGrassBreedingProbability);
		grassPanel.add(grassMaxLitterSize);
		grassPanel.add(newGrassMaxLitterSize);
		grassPanel.add(change);
		grassPanel.add(reset);
	}
	
	public JPanel getGrassPanel()
	{
		return grassPanel;
	}

}
