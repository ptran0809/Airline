package Airline.GUIClasses;

import javax.swing.*;

import Airline.Driver;
import Airline.Airplane.Airplane;
import Airline.Airplane.TwelveSeatPlane;
import Airline.Passenger.Passenger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WelcomeGUI extends JFrame {

	private JPanel centerPanel;
	private BorderLayout borderLayout;
	private Dimension minimumSize;
	private ArrayList<Airplane> planes;
	private Passenger passenger;
	private JPanel southPanel;
	private JPanel northPanel;
	private JButton logout;
	private JLabel welcome;
	private final String BIG_PLANE = "Images\\big_plane_small.jpg";
	private final String SMALL_PLANE = "Images\\small_plane_small.jpg";
	
	public WelcomeGUI(ArrayList<Airplane> planes, Passenger passenger) {
		//NEED TO AUTOMATICALLY GENERATE A BUTTON AND INFORMATION FOR EACH PLANE
		
		this.planes = planes;
		this.passenger = passenger;
		
		centerPanel = new JPanel();
		southPanel = new JPanel();
		northPanel = new JPanel();
		borderLayout = new BorderLayout();
		minimumSize = new Dimension(300, 300);
		logout = new JButton("Log Out");
		logout.addActionListener(new LogOutListener());
		welcome = new JLabel("Welcome, " + passenger.getFirstName() + "! Click any button containing flight details to see seating.");
		System.out.println(passenger);
		
		northPanel.setLayout(new FlowLayout());
		northPanel.add(welcome);
		centerPanel.setLayout(new GridLayout(4,4));
		southPanel.setLayout(new FlowLayout());
		
		for (Airplane currentPlane : planes ) {
			if (currentPlane instanceof TwelveSeatPlane) {
				centerPanel.add(new AirplanePanel(currentPlane, BIG_PLANE, passenger));
			}
			else {
				centerPanel.add(new AirplanePanel(currentPlane, SMALL_PLANE, passenger));
			}
		}
		
		southPanel.add(logout);

		this.setLayout(borderLayout);
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
		this.setTitle("Available Flights");
		this.pack();
		this.setVisible(true);
		this.setMinimumSize(minimumSize);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void close() {
		LoginGUI login = new LoginGUI(planes);
		this.dispose();
	}
	
	private class LogOutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?",
					"Warning" , JOptionPane.WARNING_MESSAGE);
			if (n == JOptionPane.YES_OPTION) {
				close();
			}
			else {
				System.out.println("User chose not to log out");
			}			
		}
		
	}
	
}
