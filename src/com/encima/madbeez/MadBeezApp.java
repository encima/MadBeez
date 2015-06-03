package com.encima.madbeez;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MadBeezApp {
	
	static Vector<Bee> bees;
	static QueenBee[] queens;
	static DroneBee[] drones;
	
	static JPanel workerFrame;
	static JPanel queenFrame;
	static JPanel droneFrame;
	
	static ImageIcon queenIcon, workerIcon, droneIcon;

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				//TODO: Error handle if not found
				workerIcon = new ImageIcon("icons/worker.png");
				queenIcon = new ImageIcon("icons/queen_gb.png");
				droneIcon = new ImageIcon("icons/drone.png");
				
				JFrame appFrame = new JFrame("MadBeez");
				appFrame.setSize(1000, 800);
				appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				appFrame.setLayout(new BorderLayout());
				
				JLabel titleLbl = new JLabel("MadBeez", JLabel.CENTER);
				appFrame.getContentPane().add(titleLbl, BorderLayout.NORTH);
				
				//create a table of 3 cols for each bee type
				JPanel beesPanel = new JPanel();
				beesPanel.setLayout(new GridLayout(1, 3));
				appFrame.getContentPane().add(beesPanel, BorderLayout.CENTER);
				
				//create a grid layout with spacing
				GridLayout beeLayout = new GridLayout(10,1);
				beeLayout.setVgap(5);
				
				//set up panels
				queenFrame = new JPanel();
				queenFrame.setLayout(beeLayout);
				beesPanel.add(queenFrame);
				
				workerFrame = new JPanel();
				workerFrame.setLayout(beeLayout);
				beesPanel.add(workerFrame);
				
				droneFrame = new JPanel();
				droneFrame.setLayout(beeLayout);
				beesPanel.add(droneFrame);
				
				// instantiate vector and add bees, as well as default labels
				bees = new Vector<Bee>();
				JLabel lbl;
				for(int i = 0; i < 10; i++) {
					bees.add(new DroneBee());
					lbl = new JLabel("Health: 100% Dead? false", droneIcon, JLabel.LEFT);
					droneFrame.add(lbl);
					
					bees.add(new WorkerBee());
					lbl = new JLabel("Health: 100% Dead? false", workerIcon, JLabel.LEFT);
					workerFrame.add(lbl);
					
					bees.add(new QueenBee());
					lbl = new JLabel("Health: 100% Dead? false", queenIcon, JLabel.LEFT);
					queenFrame.add(lbl);
				}
				
				//TODO: Add a reload button when all bees die
				JButton damageBtn = new JButton("Damage!");
				appFrame.add(damageBtn, BorderLayout.SOUTH);
				
				appFrame.pack();
				appFrame.repaint();
				appFrame.setVisible(true);
				
				damageBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						//TODO: Store labels globally without needing to create new ones for each click
						workerFrame.removeAll();
						queenFrame.removeAll();
						droneFrame.removeAll();
						
						//TODO: Add a popup when all bees die
						for(Bee b : bees) {
							// Damage the bee and store the resulting health
							int damage = new Random().nextInt(100);
							float health = b.damage(damage);
							String dead = b.isDead() ? "You got him" : "Not dead yet";
							JLabel lbl; 
							//update label based on Bee type
							switch(b.getClass().getSimpleName()) {
								case "QueenBee":
									lbl  = new JLabel(String.format("Health: %.0f%% Dead? %s", health, dead), queenIcon, JLabel.LEFT);
									queenFrame.add(lbl);
									break;
								case "WorkerBee":
									lbl  = new JLabel(String.format("Health: %.0f%% Dead? %s", health, dead), workerIcon, JLabel.LEFT);
									workerFrame.add(lbl);
									break;
								case "DroneBee":
									lbl  = new JLabel(String.format("Health: %.0f%% Dead? %s", health, dead), droneIcon, JLabel.LEFT);
									droneFrame.add(lbl);
									break;
							}
						}
						appFrame.pack();
						appFrame.repaint();
					}
				});
			}
		});
		
	}

}
