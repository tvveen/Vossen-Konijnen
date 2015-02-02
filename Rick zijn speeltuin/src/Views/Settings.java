package Views;


import java.awt.event.*;

import javax.swing.*;

import Views.FoxPanel;

public class Settings {

	
	// Declareer het hoofd JPanel
	private JPanel panel;
	// Lijst van termen in het dropdown menu
	private String[] list = {"Wolf", "Fox", "Rabbit", "Grass"};
	
	public Settings()
	{
		// Declareer JPanels voor alle menu koppen
		WolfPanel wolfpanel = new WolfPanel();
		FoxPanel foxpanel = new FoxPanel();
		RabbitPanel rabbitpanel = new RabbitPanel();
		GrassPanel grasspanel = new GrassPanel();
		
		// Standaard waarden voor het hoofd JPanel
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 500, 593);
		
		// Constructueer het dropdown menu
		JComboBox<String> dropList = new JComboBox<String>(list);
		dropList.setBounds(1, 1, 500, 40);
		
		// Link en voeg de apparte JPanels aan de menu koppen
		final JPanel wolfPanel = wolfpanel.getWolfPanel();
		final JPanel foxPanel = foxpanel.getFoxPanel();
		final JPanel rabbitPanel = rabbitpanel.getRabbitPanel();
		final JPanel grassPanel = grasspanel.getGrassPanel();
				
		panel.add(wolfPanel);
		panel.add(foxPanel);
		panel.add(rabbitPanel);
		panel.add(grassPanel);	
		
		// Defineer wat elke menu kop moet doen als er één is geselecteerd
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
	
	// Methode om het JPanel weer te geven
	public JPanel getPanel()
	{
		return panel;
	}
	
}
