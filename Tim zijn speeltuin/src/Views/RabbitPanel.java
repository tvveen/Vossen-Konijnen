package Views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Rabbit;
import Models.Rabbit;
import Simulator.Main;

public class RabbitPanel {
	
	private static int DEFAULT_BREEDING_AGE = 1;
	private static int DEFAULT_MAX_AGE = 100;
	private static double DEFAULT_BREEDING_PROBABILITY = 0.10;
	private static int DEFAULT_MAX_LITTER_SIZE = 12;
	private static int DEFAULT_GRASS_FOOD_VALUE = 5;
	
	private JPanel rabbitPanel;
	
	public RabbitPanel()
	{
		rabbitPanel = new JPanel();
		rabbitPanel.setLayout(null);
		rabbitPanel.setBounds(1, 41, 500, 559);
		rabbitPanel.setVisible(false);
		
		
		JLabel rabbitLabel = new JLabel("Rabbit Variables");
		rabbitLabel.setBounds(160, 10, 200, 20);
		rabbitLabel.setFont(new Font("Bold", Font.BOLD, 22));
		
		
		JLabel rabbitBreedingAge = new JLabel("Breeding age:");
		rabbitBreedingAge.setBounds(60, 50, 150, 30);
		
		JTextField newRabbitBreedingAge = new JTextField();
		newRabbitBreedingAge.setBounds(205, 50, 200, 30);
		
		
		JLabel rabbitMaxAge = new JLabel("Max Age:");
		rabbitMaxAge.setBounds(60, 90, 150, 30);
		
		JTextField newRabbitMaxAge = new JTextField();
		newRabbitMaxAge.setBounds(205, 90, 200, 30);
		
		
		JLabel rabbitBreedingProbability = new JLabel("Breeding probability:");
		rabbitBreedingProbability.setBounds(60, 130, 150, 30);
		
		JTextField newRabbitBreedingProbability = new JTextField();
		newRabbitBreedingProbability.setBounds(205, 130, 200, 30);
		
		
		JLabel rabbitMaxLitterSize = new JLabel("Max litter size:");
		rabbitMaxLitterSize.setBounds(60, 170, 150, 30);
		
		JTextField newRabbitMaxLitterSize = new JTextField();
		newRabbitMaxLitterSize.setBounds(205, 170, 200, 30);
		
		
		JLabel rabbitFoodValue = new JLabel("Food value:");
		rabbitFoodValue.setBounds(60, 210, 150, 30);
		
		JTextField newRabbitFoodValue = new JTextField();
		newRabbitFoodValue.setBounds(205, 210, 200, 30);
		
		
		JButton change = new JButton("Change Value(s)");
		change.setBounds(60, 300, 140, 30);
		change.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent changeValues)
			{
				try
				{
						int newBreedingAge = Integer.valueOf(newRabbitBreedingAge.getText());
						Rabbit.setBreedingAge(newBreedingAge);
						newRabbitBreedingAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						int newMaxAge = Integer.valueOf(newRabbitMaxAge.getText());
						Rabbit.setMaxAge(newMaxAge);
						newRabbitMaxAge.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						double newProbability = Integer.valueOf(newRabbitBreedingProbability.getText());
						Rabbit.setBreedingProbability(newProbability);
						newRabbitBreedingProbability.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}

				
				try
				{
						int newMaxLitter = Integer.valueOf(newRabbitMaxLitterSize.getText());
						Rabbit.setMaxLitterSize(newMaxLitter);
						newRabbitMaxLitterSize.setText("");
				}
				catch(NumberFormatException e)
				{
					/* Do nothing */
				}
				
				
				try
				{
						int newRabbitFood = Integer.valueOf(newRabbitFoodValue.getText());
						Rabbit.setRabbitFoodValue(newRabbitFood);
						newRabbitFoodValue.setText("");
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
				Rabbit.setBreedingAge(DEFAULT_BREEDING_AGE);
				Rabbit.setMaxAge(DEFAULT_MAX_AGE);
				Rabbit.setBreedingProbability(DEFAULT_BREEDING_PROBABILITY);
				Rabbit.setMaxLitterSize(DEFAULT_MAX_LITTER_SIZE);
				Rabbit.setRabbitFoodValue(DEFAULT_GRASS_FOOD_VALUE);
				Main.getThread().resetThread();
			}
		});
		
		
		rabbitPanel.add(rabbitLabel);
		rabbitPanel.add(rabbitBreedingAge);
		rabbitPanel.add(newRabbitBreedingAge);
		rabbitPanel.add(rabbitMaxAge);
		rabbitPanel.add(newRabbitMaxAge);
		rabbitPanel.add(rabbitBreedingProbability);
		rabbitPanel.add(newRabbitBreedingProbability);
		rabbitPanel.add(rabbitMaxLitterSize);
		rabbitPanel.add(newRabbitMaxLitterSize);
		rabbitPanel.add(rabbitFoodValue);
		rabbitPanel.add(newRabbitFoodValue);
		rabbitPanel.add(change);
		rabbitPanel.add(reset);
	}
	
	public JPanel getRabbitPanel()
	{
		return rabbitPanel;
	}

}
