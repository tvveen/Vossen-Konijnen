package Views;


import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import Models.Wolf;
import Simulator.Main;

public class WolfPanel {

	// Hier staan de standaard waarden van de wolf in gesteld; gelijk aan die van de klasse
	private static int DEFAULT_BREEDING_AGE = 6;
	private static int DEFAULT_MAX_AGE = 200;
	private static double DEFAULT_BREEDING_PROBABILITY = 0.02;
	private static int DEFAULT_MAX_LITTER_SIZE = 4;
	private static int DEFAULT_FOX_FOOD_VALUE = 5;
	
	// Declareer een nieuw JPanel
	private JPanel wolfPanel;
	
	// Contructueer de JPanel
	public WolfPanel()
	{
		// Stel vaste waarden aan de JPanel
		wolfPanel = new JPanel();
		wolfPanel.setLayout(null);
		wolfPanel.setBounds(1, 41, 500, 559);
		wolfPanel.setVisible(true);
		
		
		// Defineer de velden voor de JPanel
		JLabel wolfLabel = new JLabel("Wolf Variables");
		wolfLabel.setBounds(180, 10, 200, 20);
		wolfLabel.setFont(new Font("Bold", Font.BOLD, 22));
		
		
		JLabel wolfBreedingAge = new JLabel("Breeding age:");
		wolfBreedingAge.setBounds(60, 50, 150, 30);
		
		final JTextField newWolfBreedingAge = new JTextField();
		newWolfBreedingAge.setBounds(205, 50, 200, 30);
		
		
		JLabel wolfMaxAge = new JLabel("Max Age:");
		wolfMaxAge.setBounds(60, 90, 150, 30);
		
		final JTextField newWolfMaxAge = new JTextField();
		newWolfMaxAge.setBounds(205, 90, 200, 30);
		
		
		JLabel wolfBreedingProbability = new JLabel("Breeding probability:");
		wolfBreedingProbability.setBounds(60, 130, 150, 30);
		
		final JTextField newWolfBreedingProbability = new JTextField();
		newWolfBreedingProbability.setBounds(205, 130, 200, 30);
		
		
		JLabel wolfMaxLitterSize = new JLabel("Max litter size:");
		wolfMaxLitterSize.setBounds(60, 170, 150, 30);
		
		final JTextField newWolfMaxLitterSize = new JTextField();
		newWolfMaxLitterSize.setBounds(205, 170, 200, 30);
		
		
		JLabel wolfFoodValue = new JLabel("Food value:");
		wolfFoodValue.setBounds(60, 210, 150, 30);
		
		final JTextField newWolfFoodValue = new JTextField();
		newWolfFoodValue.setBounds(205, 210, 200, 30);
		
		
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
						int newBreedingAge = Integer.valueOf(newWolfBreedingAge.getText());
						Wolf.setBreedingAge(newBreedingAge);
						newWolfBreedingAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						int newMaxAge = Integer.valueOf(newWolfMaxAge.getText());
						Wolf.setMaxAge(newMaxAge);
						newWolfMaxAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						double newProbability = Integer.valueOf(newWolfBreedingProbability.getText());
						Wolf.setBreedingProbability(newProbability);
						newWolfBreedingProbability.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}

				
				try
				{
						int newMaxLitter = Integer.valueOf(newWolfMaxLitterSize.getText());
						Wolf.setMaxLitterSize(newMaxLitter);
						newWolfMaxLitterSize.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						int newWolfFood = Integer.valueOf(newWolfFoodValue.getText());
						Wolf.setFoxFoodValue(newWolfFood);
						newWolfFoodValue.setText("");
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
				Wolf.setBreedingAge(DEFAULT_BREEDING_AGE);
				Wolf.setMaxAge(DEFAULT_MAX_AGE);
				Wolf.setBreedingProbability(DEFAULT_BREEDING_PROBABILITY);
				Wolf.setMaxLitterSize(DEFAULT_MAX_LITTER_SIZE);
				Wolf.setFoxFoodValue(DEFAULT_FOX_FOOD_VALUE);
				Main.getThread().resetThread();
			}
		});
		
		// Zorg dat alle componenten aan de JPanel worden toegevoegd
		wolfPanel.add(wolfLabel);
		wolfPanel.add(wolfBreedingAge);
		wolfPanel.add(newWolfBreedingAge);
		wolfPanel.add(wolfMaxAge);
		wolfPanel.add(newWolfMaxAge);
		wolfPanel.add(wolfBreedingProbability);
		wolfPanel.add(newWolfBreedingProbability);
		wolfPanel.add(wolfMaxLitterSize);
		wolfPanel.add(newWolfMaxLitterSize);
		wolfPanel.add(wolfFoodValue);
		wolfPanel.add(newWolfFoodValue);
		wolfPanel.add(change);
		wolfPanel.add(reset);
		
	}
	
	// Methode om het JPanel weer te geven
	public JPanel getWolfPanel()
	{
		return wolfPanel;
	}
	
}
