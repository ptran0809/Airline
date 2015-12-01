package Airline.GUIClasses;

import javax.swing.*;

import Airline.Airplane.Airplane;
import Airline.Airplane.Airplanes;
import Airline.Passenger.GoldMember;
import Airline.Passenger.MemberPassenger;
import Airline.Passenger.NotMember;
import Airline.Passenger.Passenger;
import Airline.Passenger.PlatinumMember;
import Airline.Passenger.SilverMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginGUI extends JFrame {

	private JPanel panel;
	private JLabel username;
	private JLabel password;
	private Dimension minSize;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton createButton;
	
	private final String FILE_NAME = "Files\\accounts.txt";
	private final String ID_FILE_NAME = "FIles\\Passengers\\current_highest_id.txt";
	private final String PLANE = "Images\\airbus-a350-580x366.jpg";
	
	public LoginGUI(Airplanes planes) {
		
		panel = new JPanel();
		password = new JLabel("Password: ");
		username = new JLabel("Email Address: ");
		usernameText = new JTextField(20);
		passwordText = new JPasswordField(20);
		loginButton = new JButton("Login");
		createButton = new JButton("Create account");
		minSize = new Dimension(650, 475);
	
		loginButton.addActionListener(new LoginListener(planes));
		createButton.addActionListener(new CreateListener());
		
		panel.setLayout(new FlowLayout());
		panel.add(username);
		panel.add(usernameText);
		panel.add(password);
		panel.add(passwordText);
		panel.add(loginButton);
		panel.add(createButton);
		panel.add(new JLabel(new ImageIcon(PLANE)));

		this.add(panel);
		this.pack();
		this.setTitle("Welcome to Acme Airlines!");
		this.setSize(minSize);
		this.setMinimumSize(minSize);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Closes the login screen after the user logs in.
	 * Can't call dispose() from inside inner class, so 
	 * need to make a method in the LoginGUI class to take care of it.
	 */
	private void closeForm() {
		this.dispose();
	}
	
	/**
	 * Listener for the login button 
	 * @author Matthew Gimbut
	 *
	 */
	private class LoginListener implements ActionListener {

		private Airplanes planes;
		
		/**
		 * Constructor for LoginListeners that takes an arraylist of airplanes and a passenger
		 * @param planes
		 * @param passenger
		 */
		public LoginListener(Airplanes planes) {
			this.planes = planes;
		}
		
		@Override
		/**
		 * Checks to make sure the username and password match, then logs the user in.
		 */
		public void actionPerformed(ActionEvent e) {
		
			String currentId = "";
			String currentUser = "";
			String currentPass = "";			

			String enteredPass = new String(passwordText.getPassword());
			String enteredUser = usernameText.getText();
			
			try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));) {
				StringBuilder sb = new StringBuilder();
				String line = br.readLine();

				while (line != null) {
		            sb.append(line);	//Appends each line of accounts file to a StringBuilder object
		            sb.append("\n,");
		            line = br.readLine();
		        }

				System.out.println(sb.toString()); //TODO: Remove before release, just for testing
				
				String[] info = sb.toString().split(",");	
				
				/*
				 * Used regular for loop instead of for each because I needed the index.
				 * In the info array, the password is always 1 after the username so it makes it simple to
				 * find that way. 
				 */
				for(int i = 0; i < info.length; i++) {
					if (info[i].equals(usernameText.getText())) {	//Tests to see if the entered username is equal to that of a stored user
						//User info
						currentId = info[i-1];
						currentUser = info[i];
						currentPass = info[i+1];
						
						break;		//Without breaking, gives ArrayIndexOutOFBounds error. Not sure why, but this fixes it. 
					}
				}

			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
				
			if(currentPass.equals(enteredPass) && currentUser.equals(enteredUser)) {
				//Create the WelcomeGUI and close this form
				new WelcomeGUI(planes, Passenger.deserialize(Integer.parseInt(currentId)));
				closeForm();
			}
			else {
				JOptionPane.showMessageDialog(null, "Invalid password/username.", "Error" , JOptionPane.ERROR_MESSAGE);
			}	
			
		}
		
	}
	
	/**
	 * Listener for the create new account button
	 * @author Matthew Gimbut
	 *
	 */
	private class CreateListener implements ActionListener {

		@Override
		/**
		 * Creates the GUI for a user to create their account
		 */
		public void actionPerformed(ActionEvent e) {
			new CreateUserGUI();
		}
		
	}
	
}
