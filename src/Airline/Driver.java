package Airline;

import java.util.ArrayList;
import java.util.Date;

import Airline.Airplane.Airplane;
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
		new LoginGUI(planes);
	}

	private static ArrayList<Airplane> createAirplanes() {
		ArrayList<Airplane> planes = new ArrayList<Airplane>();

		planes.add(new SixSeatPlane(1, "S601", new Route(new Date(), 1010101010.0, "TestDeparture", "TestDestination",
				"TestDeptTime", "TestLandTime")));
		planes.add(new SixSeatPlane(2, "S602", new Route(new Date(), 1010101010.0, "TestDeparture", "TestDestination",
				"TestDeptTime", "TestLandTime")));
		planes.add(new SixSeatPlane(3, "S603", new Route(new Date(), 1010101010.0, "TestDeparture", "TestDestination",
				"TestDeptTime", "TestLandTime")));
		planes.add(new SixSeatPlane(4, "S604", new Route(new Date(), 1010101010.0, "TestDeparture", "TestDestination",
				"TestDeptTime", "TestLandTime")));
		planes.add(new SixSeatPlane(5, "S605", new Route(new Date(), 1010101010.0, "TestDeparture", "TestDestination",
				"TestDeptTime", "TestLandTime")));
		planes.add(new SixSeatPlane(6, "S606", new Route(new Date(), 1010101010.0, "TestDeparture", "TestDestination",
				"TestDeptTime", "TestLandTime")));
		planes.add(new TwelveSeatPlane(7, "S1201", new Route(new Date(), 1010101010.0, "TestDeparture",
				"TestDestination", "TestDeptTime", "TestLandTime")));
		planes.add(new TwelveSeatPlane(8, "S1202", new Route(new Date(), 1010101010.0, "TestDeparture",
				"TestDestination", "TestDeptTime", "TestLandTime")));
		planes.add(new TwelveSeatPlane(9, "S1203", new Route(new Date(), 1010101010.0, "TestDeparture",
				"TestDestination", "TestDeptTime", "TestLandTime")));
		planes.add(new TwelveSeatPlane(10, "S1204", new Route(new Date(), 1010101010.0, "TestDeparture",
				"TestDestination", "TestDeptTime", "TestLandTime")));
		planes.add(new TwelveSeatPlane(11, "S1205", new Route(new Date(), 1010101010.0, "TestDeparture",
				"TestDestination", "TestDeptTime", "TestLandTime")));
		planes.add(new TwelveSeatPlane(12, "S1206", new Route(new Date(), 1010101010.0, "TestDeparture",
				"TestDestination", "TestDeptTime", "TestLandTime")));


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
