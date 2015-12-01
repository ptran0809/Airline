/*
 * 
 */
package Airline;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import Airline.Airplane.Airplane;
import Airline.Airplane.Airplanes;
import Airline.Airplane.Route;
import Airline.Airplane.SixSeatPlane;
import Airline.Airplane.TwelveSeatPlane;
import Airline.GUIClasses.LoginGUI;
import Airline.Passenger.Passenger;


public class Driver {

	public static void main(String[] args) {

		Passenger.setHighestId();
		Airplane.setHighestId();
		ArrayList<Airplane> planes = new ArrayList<Airplane>();
		planes = createAirplanes();
		Airplanes airplanes;
		airplanes = new Airplanes(planes);
		//airplanes.serialize();
		//airplanes = Airplanes.deserialize();
		//System.out.println(airplanes);
		new LoginGUI(airplanes);
	}

	private static ArrayList<Airplane> createAirplanes() {
		ArrayList<Airplane> planes = new ArrayList<Airplane>();

		planes.add(new SixSeatPlane(1, "S601", new Route( 10101.0, "Chicago", "New York City",
				LocalDateTime.of(2015, 11, 30, 9, 00, 00), LocalDateTime.of(2015, 11, 30, 15, 00, 00))));
		planes.add(new SixSeatPlane(2, "S602", new Route( 10101.0, "New York City", "Philadelphia",
				LocalDateTime.of(2015, 12, 31, 12, 00, 00), LocalDateTime.of(2015, 12, 31, 15, 00, 00))));
		planes.add(new SixSeatPlane(3, "S603", new Route( 10101.0, "Los Angeles", "Saint Louis",
				LocalDateTime.of(2015, 12, 11, 8, 00, 00), LocalDateTime.of(2015, 12, 11, 15, 00, 00))));
		planes.add(new SixSeatPlane(4, "S604", new Route( 10101.0, "Saint Louis", "Houston",
				LocalDateTime.of(2015, 12, 14, 8, 00, 00), LocalDateTime.of(2015, 12, 14, 12, 00, 00))));
		planes.add(new SixSeatPlane(5, "S605", new Route( 10101.0, "Philadelphia", "Chicago",
				LocalDateTime.of(2015, 12, 15, 8, 00, 00), LocalDateTime.of(2015, 12, 16, 17, 00, 00))));
		planes.add(new SixSeatPlane(6, "S606", new Route( 10101.0, "Houston", "New York City",
				LocalDateTime.of(2015, 12, 15, 8, 00, 00), LocalDateTime.of(2015, 12, 16, 14, 00, 00))));
		planes.add(new TwelveSeatPlane(7, "S1201", new Route( 1010101010.0, "Chicago",
				"Philadelphia",
				LocalDateTime.of(2015, 12, 17, 8, 00, 00), LocalDateTime.of(2015, 12, 17, 14, 00, 00))));
		planes.add(new TwelveSeatPlane(8, "S1202", new Route( 1010101010.0, "Saint Louis",
				"Dallas",
				LocalDateTime.of(2015, 12, 18, 10, 00, 00), LocalDateTime.of(2015, 12, 18, 13, 00, 00))));
		planes.add(new TwelveSeatPlane(9, "S1203", new Route( 1010101010.0, "New York City",
				"Washington DC",
				LocalDateTime.of(2015, 12, 19, 9, 00, 00), LocalDateTime.of(2015, 12, 19, 14, 00, 00))));
		planes.add(new TwelveSeatPlane(10, "S1204", new Route( 1010101010.0, "Washington DC",
				"Chicago",
				LocalDateTime.of(2015, 12, 20, 10, 00, 00), LocalDateTime.of(2015, 12, 20, 20, 00, 00))));
		planes.add(new TwelveSeatPlane(11, "S1205", new Route( 1010101010.0, "New York City",
				"Chicago",
				LocalDateTime.of(2015, 12, 21, 15, 00, 00), LocalDateTime.of(2015, 12, 21, 22, 00, 00))));
		planes.add(new TwelveSeatPlane(12, "S1206", new Route( 1010101010.0, "Houston",
				"New York City",
				LocalDateTime.of(2015, 12, 22, 17, 00, 00), LocalDateTime.of(2015, 12, 22, 23, 00, 00))));


		return planes;
	}
	
	/**
	 * A static method which automatically deserializes all the already serialized planes
	 * @return
	 */
	private static ArrayList<Airplane> deserializePlanes() {
		ArrayList<Airplane> planes = new ArrayList<Airplane>();
		
		for(int i = 1; i <= Airplane.getHighestId(); i++) {
			planes.add(Airplane.deserialize(i));
		}
		
		return planes;
	}

}
