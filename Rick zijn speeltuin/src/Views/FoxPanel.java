package Views;


import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import Models.Fox;
import Simulator.Main;

public class FoxPanel {

	// Hier staan de standaard waarden van de fox in gesteld; gelijk aan die van de klasse
	private static int DEFAULT_BREEDING_AGE = 2;
	private static int DEFAULT_MAX_AGE = 200;
	private static double DEFAULT_BREEDING_PROBABILITY = 0.08;
	private static int DEFAULT_MAX_LITTER_SIZE = 12;
	private static int DEFAULT_RABBIT_FOOD_VALUE = 7;
	
	// Declareer een nieuw JPanel
	private JPanel foxPanel;
	
	// Contructueer de JPanel
	public FoxPanel()
	{
		// Stel vaste waarden aan de JPanel
		foxPanel = new JPanel();
		foxPanel.setLayout(null);
		foxPanel.setBounds(1, 41, 500, 559);
		foxPanel.setVisible(true);
		
		
		// Defineer de velden voor de JPanel
		JLabel foxLabel = new JLabel("Fox Variables");
		foxLabel.setBounds(180, 10, 200, 20);
		foxLabel.setFont(new Font("Bold", Font.BOLD, 22));
		
		
		JLabel foxBreedingAge = new JLabel("Breeding age:");
		foxBreedingAge.setBounds(60, 50, 150, 30);
		
		final JTextField newFoxBreedingAge = new JTextField();
		newFoxBreedingAge.setBounds(205, 50, 200, 30);
		
		
		JLabel foxMaxAge = new JLabel("Max Age:");
		foxMaxAge.setBounds(60, 90, 150, 30);
		
		final JTextField newFoxMaxAge = new JTextField();
		newFoxMaxAge.setBounds(205, 90, 200, 30);
		
		
		JLabel foxBreedingProbability = new JLabel("Breeding probability:");
		foxBreedingProbability.setBounds(60, 130, 150, 30);
		
		final JTextField newFoxBreedingProbability = new JTextField();
		newFoxBreedingProbability.setBounds(205, 130, 200, 30);
		
		
		JLabel foxMaxLitterSize = new JLabel("Max litter size:");
		foxMaxLitterSize.setBounds(60, 170, 150, 30);
		
		final JTextField newFoxMaxLitterSize = new JTextField();
		newFoxMaxLitterSize.setBounds(205, 170, 200, 30);
		
		
		JLabel foxFoodValue = new JLabel("Food value:");
		foxFoodValue.setBounds(60, 210, 150, 30);
		
		final JTextField newFoxFoodValue = new JTextField();
		newFoxFoodValue.setBounds(205, 210, 200, 30);
		
		
		// Defineer de submit knop voor de JPanel
		JButton change = new JButton("Change Value(s)");
		change.setBounds(60, 300, 140, 30);
		change.addActionListener(new ActionListener()
		{
			// Declareer de actie voor deze knop
			public void actionPerformed(ActionEvent changeValues)
			{
				try
				{
						int newBreedingAge = Integer.valueOf(newFoxBreedingAge.getText());
						Fox.setBreedingAge(newBreedingAge);
						newFoxBreedingAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						int newMaxAge = Integer.valueOf(newFoxMaxAge.getText());
						Fox.setMaxAge(newMaxAge);
						newFoxMaxAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						double newProbability = Integer.valueOf(newFoxBreedingProbability.getText());
						Fox.setBreedingProbability(newProbability);
						newFoxBreedingProbability.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}

				
				try
				{
						int newMaxLitter = Integer.valueOf(newFoxMaxLitterSize.getText());
						Fox.setMaxLitterSize(newMaxLitter);
						newFoxMaxLitterSize.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						int newFoxFood = Integer.valueOf(newFoxFoodValue.getText());
						Fox.setRabbitFoodValue(newFoxFood);
						newFoxFoodValue.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				
			}
		});
		
		// Defineer de reset knop van de JPanel
		JButton reset = new JButton("Default Value(s)");
		reset.setBounds(210, 300, 140, 30);
		reset.addActionListener(new ActionListener()
		{
			// Declareer de actie voor deze knop
			public void actionPerformed(ActionEvent resetValue)
			{
				Fox.setBreedingAge(DEFAULT_BREEDING_AGE);
				Fox.setMaxAge(DEFAULT_MAX_AGE);
				Fox.setBreedingProbability(DEFAULT_BREEDING_PROBABILITY);
				Fox.setMaxLitterSize(DEFAULT_MAX_LITTER_SIZE);
				Fox.setRabbitFoodValue(DEFAULT_RABBIT_FOOD_VALUE);
				Main.getThread().resetThread();
			}
		});
		
		// Zorg dat alle componenten aan de JPanel worden toegevoegd
		foxPanel.add(foxLabel);
		foxPanel.add(foxBreedingAge);
		foxPanel.add(newFoxBreedingAge);
		foxPanel.add(foxMaxAge);
		foxPanel.add(newFoxMaxAge);
		foxPanel.add(foxBreedingProbability);
		foxPanel.add(newFoxBreedingProbability);
		foxPanel.add(foxMaxLitterSize);
		foxPanel.add(newFoxMaxLitterSize);
		foxPanel.add(foxFoodValue);
		foxPanel.add(newFoxFoodValue);
		foxPanel.add(change);
		foxPanel.add(reset);
		
	}
	
	// Methode om het JPanel weer te geven
	public JPanel getFoxPanel()
	{
		return foxPanel;
	}
	
}
