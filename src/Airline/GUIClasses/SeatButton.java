package Airline.GUIClasses;

import javax.swing.*;
import java.awt.*;

import Airline.Passenger.Passenger;
import Airline.Seat.Seat;

@SuppressWarnings("serial")
public class SeatButton extends JButton {
	private String seatName;
	private Passenger passenger;

	public SeatButton(Seat seat, Color color, Passenger passenger) {
		this.passenger = passenger;
		setSeatInfo(seat);
		this.setBackground(color);
		this.setSize(new Dimension(40,40));
	}
	
	/**
	 * Method to set all information of our SeatButton.
	 * Made this to prevent having duplicate code in each of the constructors. 
	 * @param seat The seat of the button
	 */
	public void setSeatInfo(Seat seat) {
		
		

		//If the seat is occupied then you cannot click the button
		if(!seat.isAvailable()) {
			this.setEnabled(false);
			this.seatName = "<html>" + String.valueOf(seat.getId()) + "<p>" + seat.getPassenger().toString() + "</html>";
		}
		else {
			this.seatName = "<html>" + String.valueOf(seat.getId()) + "<p>" + "null" + "</html>";
		}
		
		this.setText(seatName);
		
		if(seat.isExitSeat()) {
			this.setForeground(Color.RED);
		}
				
		this.addActionListener(event -> {
			if(passenger.canRegister(seat.getAirplane()))
			{
				if (seat.isExitSeat()) {
					int n = JOptionPane.showConfirmDialog(null, "This seat is next to an emergency exit."
							+ "\nAre you sure this is the seat you want?",
							"Warning" , JOptionPane.WARNING_MESSAGE);
					if(n == JOptionPane.YES_OPTION){
						System.out.println("Success");
						seat.setPassenger(passenger);
						//The seat is unable to be clicked when somebody has it reserved
						this.setEnabled(false);
						System.out.println(passenger.getFirstName() + " " + passenger.getLastName() + 
								" is now registered for seat " + seat.getId());
						this.seatName = "<html>" + String.valueOf(seat.getId()) + "<p>" + passenger.toString() + "</html>";
						this.setText(seatName);
						//Serialize the Airplane of the Seat after it has been updated
						seat.getAirplane().serialize();
					}
					else {
						System.out.println("No");
					}
				}
				else {
					System.out.println(passenger.getFirstName() + " " + passenger.getLastName() + 
							" is now registered for seat " + seat.getId());
					//The seat is unable to be clicked when somebody has it reserved
					this.setEnabled(false);
					this.seatName = "<html>" + String.valueOf(seat.getId()) + "<p>" + passenger.toString() + "</html>";
					this.setText(seatName);
					//Serialize the Airplane of the Seat after it has been updated
					seat.getAirplane().serialize();
				}
			}
			//If the passenger cannot register for the plane then give error message
			else {
				JOptionPane.showMessageDialog(null, "You cannot register for this Airplane! It is too far away, please upgrade your membership to register for this flight.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		});
	}
	
}
