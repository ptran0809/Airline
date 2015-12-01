package Airline.Airplane;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;



public class Airplanes  implements Serializable  {

	
	private ArrayList<Airplane> airplanes;
	private static final long serialVersionUID = 8327205192035724262L;
	private final static String FILE_NAME = "Files\\Airplanes\\airplanes.ser";
	private final static boolean APPEND_TO_FILE = true;
	private final static boolean DO_NOT_APPEND = false;

	public Airplanes(ArrayList<Airplane> airplanes) {
		super();
		this.airplanes = airplanes;
	}

	public ArrayList<Airplane> getAirplanes() {
		return airplanes;
	}

	public void setAirplanes(ArrayList<Airplane> airplanes) {
		this.airplanes = airplanes;
	}
	
	public ArrayList<Airplane> getPlaneAvailNow(){
		return this.getPlaneAvailAfterDate(LocalDateTime.now());
	}
	
	public ArrayList<Airplane> getPlaneAvailNowByDeparture(String departure){
		return this.getPlaneAvailAfterDateByDeparture(departure,LocalDateTime.now());
	}
	public ArrayList<Airplane> getPlaneAvailNowByDepartureAndDestination(String departure,String destination){
		return this.getPlaneAvailAfterDateByDepartureAndDestination(departure,destination,LocalDateTime.now());
	}
	
	public ArrayList<Airplane> getPlaneAvailAfterDate(LocalDateTime datetime){
		ArrayList<Airplane> result = new ArrayList<Airplane>();
		for(Airplane a : this.airplanes){
			if(a.getRoute().getDepartTime().isAfter(datetime)){
				result.add(a);
			}
		}
		return result;
	}
	public ArrayList<Airplane> getPlaneAvailAfterDateByDeparture(String departure,LocalDateTime datetime){
		ArrayList<Airplane> result = new ArrayList<Airplane>();
		for(Airplane a : this.airplanes){
			if(a.getRoute().getDepartTime().isAfter(datetime) 
					&& a.getRoute().getDeparture().toLowerCase() == departure.toLowerCase() ){
				result.add(a);
			}
		}
		return result;
	}
	public ArrayList<Airplane> getPlaneAvailAfterDateByDepartureAndDestination(String departure,String destination,LocalDateTime datetime){
		ArrayList<Airplane> result = new ArrayList<Airplane>();
		for(Airplane a : this.airplanes){
			if(a.getRoute().getDepartTime().isAfter(datetime) 
					&& a.getRoute().getDeparture().toLowerCase() == departure.toLowerCase()
					&& a.getRoute().getDestination().toLowerCase() == destination.toLowerCase() ){
				result.add(a);
			}
		}
		return result;
	}
	
	public String toString(){
 		String returnString = "";
 		for(Airplane a : this.airplanes){
 			returnString += "\n"
 					+"========================================="
 					+"\n"+a.toString();
 		}
 		return returnString;
	}
	/**
	 * Deserialization method to get Airplanes objects from file
	 */
	public static Airplanes deserialize() {
		try(FileInputStream fs = new FileInputStream(Airplanes.FILE_NAME);
				ObjectInputStream os = new ObjectInputStream(fs)) {
			return (Airplanes) os.readObject();
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
	/**
	 * Serialization method to save Airplanes objects to file
	 */
	public void serialize() {
		try(FileOutputStream fs = new FileOutputStream(FILE_NAME, APPEND_TO_FILE);
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
}
