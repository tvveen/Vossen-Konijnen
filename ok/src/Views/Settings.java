package Views;


import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;

import Models.Fox;
import Simulator.Main;
import Views.FoxPanel;

public class Settings {

	
	
	private JPanel panel;
	
	private String[] list = {"Fox", "Rabbit", "Grass"};
	
	public Settings()
	{
		FoxPanel foxpanel = new FoxPanel();
		RabbitPanel rabbitpanel = new RabbitPanel();
		GrassPanel grasspanel = new GrassPanel();
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 593);
		
		JComboBox<String> dropList = new JComboBox<String>(list);
		dropList.setBounds(1, 1, 500, 40);
		
		final JPanel foxPanel = foxpanel.getFoxPanel();
		final JPanel rabbitPanel = rabbitpanel.getRabbitPanel();
		final JPanel grassPanel = grasspanel.getGrassPanel();
				
			
		panel.add(foxPanel);
		panel.add(rabbitPanel);
		panel.add(grassPanel);	
		
		
		dropList.addItemListener(new ItemListener()
		{
			@SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent itemEvent)
			{
				String selected = ((JComboBox<String>) itemEvent.getSource()).getSelectedItem().toString();
				//System.out.println(((JComboBox<String>) itemEvent.getSource()).getSelectedItem());
				
				if(selected == "Fox")
				{					
					foxPanel.setVisible(true);
					rabbitPanel.setVisible(false);
					grassPanel.setVisible(false);
				}
				else if(selected== "Rabbit")
				{
					rabbitPanel.setVisible(true);
					foxPanel.setVisible(false);
					grassPanel.setVisible(false);
					
				}
				else if(selected == "Grass")
				{
					grassPanel.setVisible(true);
					foxPanel.setVisible(false);
					rabbitPanel.setVisible(false);
				}
			}
		});
		
		panel.add(dropList);	
		
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
}
