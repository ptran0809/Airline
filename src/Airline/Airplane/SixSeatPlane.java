/**
 * SixSeatPlane.java is a subclass of Airplane which represents an Airplane with six seats
 * @author Matthew Gimbut, Phong Tran, Sean Zimmerman
 * @version 2015.11.20
 */

package Airline.Airplane;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Airline.Seat.CoachClassSeat;

public class SixSeatPlane extends Airplane {
	
	//Fields
	private final int NUM_OF_SEATS = 6;
	
	private final String FILE_NAME = "Files\\Airplanes\\airplane" + getId() + ".ser";
	private final boolean DO_NOT_APPEND = false;
	
	/**
	 * Alternate Constructor which defines all fields
	 * @param id The id of the Airplane
	 * @param flightNo The flight number of the Airplane
	 * @param route The Route of the Airplane
	 */
	public SixSeatPlane(int id, String flightNo, Route route) {
		super(id, flightNo, route);
		/*this.id = Airplane.NEXTID;
		Airplane.NEXTID = Airplane.NEXTID + 1;
		this.flightNo = flightNo;*/
	}
	
	/**
	 * Serialization method to save Airplane objects to file
	 */
	public void serialize() {
		try(FileOutputStream fs = new FileOutputStream(FILE_NAME, DO_NOT_APPEND);
				ObjectOutputStream os = new ObjectOutputStream(fs)) {
			os.writeObject(this);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deserialization method to get passenger objects from file
	 */
	public static Airplane deserialize(int id) {
		try(FileInputStream fs = new FileInputStream("Files\\Airplanes\\airplane" + id + ".ser");
				ObjectInputStream os = new ObjectInputStream(fs)) {
			return (Airplane) os.readObject();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//If the deserialize fails return null
		return null;
	}

	@Override
	/**
	 * Adds anonymous Seat objects to the seats ArrayList.
	 * Sets the third seat to be an emergency exit.
	 */
	public void createSeats() {
			for (int i = 1; i < 7; i++) {
				if (i != 3) {
					this.getSeats().add(new CoachClassSeat(i, false, this));
				}
				else {
					this.getSeats().add(new CoachClassSeat(i, true, this));
				}
			}
	}
	
	@Override
	/**
	 * An accessor for the private final int field NUM_OF_SEATS
	 * @return The number of seats on the Airplane
	 */
	public int getNumberOfSeats() {
		return NUM_OF_SEATS;
	}

}
