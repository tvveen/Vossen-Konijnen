package Views;


import java.awt.event.*;

import javax.swing.*;

import Views.FoxPanel;

public class Settings {

	
	
	private JPanel panel;
	
	private String[] list = {"Wolf", "Fox", "Rabbit", "Grass"};
	
	public Settings()
	{
		WolfPanel wolfpanel = new WolfPanel();
		FoxPanel foxpanel = new FoxPanel();
		RabbitPanel rabbitpanel = new RabbitPanel();
		GrassPanel grasspanel = new GrassPanel();
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 593);
		
		JComboBox<String> dropList = new JComboBox<String>(list);
		dropList.setBounds(1, 1, 500, 40);
		
		final JPanel wolfPanel = wolfpanel.getWolfPanel();
		final JPanel foxPanel = foxpanel.getFoxPanel();
		final JPanel rabbitPanel = rabbitpanel.getRabbitPanel();
		final JPanel grassPanel = grasspanel.getGrassPanel();
				
		panel.add(wolfPanel);
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
				
				if(selected == "Wolf")
				{		
					wolfPanel.setVisible(true);
					foxPanel.setVisible(false);
					rabbitPanel.setVisible(false);
					grassPanel.setVisible(false);
				}
				if(selected == "Fox")
				{					
					foxPanel.setVisible(true);
					wolfPanel.setVisible(false);
					rabbitPanel.setVisible(false);
					grassPanel.setVisible(false);
				}
				else if(selected== "Rabbit")
				{
					rabbitPanel.setVisible(true);
					wolfPanel.setVisible(false);
					foxPanel.setVisible(false);
					grassPanel.setVisible(false);
					
				}
				else if(selected == "Grass")
				{
					grassPanel.setVisible(true);
					wolfPanel.setVisible(false);
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
