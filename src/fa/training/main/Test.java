package fa.training.main;

import fa.training.entities.Airport;
import fa.training.entities.FixedWing;
import fa.training.services.FixedWingService;
import fa.training.services.implement.AirportServiceImplement;
import fa.training.services.implement.FixedWingServiceImplement;

public class Test {
	public static void main(String[] args) {
		AirportServiceImplement service = new AirportServiceImplement();
		FixedWingService fxService = new FixedWingServiceImplement();

		try {
//			Airport airport = new Airport("airport A", 50, 5, 5);
//			service.create(airport);
//			FixedWing fx = new FixedWing();
//			fx.setModel("model1");
//			fx.setCruiseSpeed(500);
//			fx.setEmptyWeight(1000);
//			fx.setMaxTakeoffWeight(1500);
//
//			fxService.create(fx);
//			System.out.println(service.getAll());
//			System.out.println(fxService.getAll());
			System.out.println("adding");
			Airport airport = service.getAll().get(0);
			System.out.println(airport);
			FixedWing fixedWing = fxService.getAll().get(0);
			System.out.println(fixedWing);
			service.addFixedWing(fixedWing, airport);
//			System.out.println(service.getAll());
//			System.out.println(fxService.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
