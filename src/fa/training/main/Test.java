package fa.training.main;

import java.util.List;

import fa.training.entities.FixedWing;
import fa.training.services.AirportService;
import fa.training.services.FixedWingService;
import fa.training.services.HelicopterService;
import fa.training.services.implement.AirportServiceImplement;
import fa.training.services.implement.FixedWingServiceImplement;
import fa.training.services.implement.HelicopterServiceImplement;

public class Test {
	public static void main(String[] args) {
		AirportService service = new AirportServiceImplement();
		HelicopterService heliService = new HelicopterServiceImplement();
		FixedWingService fxService = new FixedWingServiceImplement();
		try {
			List<FixedWing> fxs = fxService.getAll();
			fxs.forEach(e -> System.out.println(e));
			// fxService.changeTypeAndMinRunwaySize(fxs, "CAG", 50);
			// Airport a = service.getAll().get(0);
			// service.addFixedWing(f1, a);
			// fxService.changeTypeAndMinRunwaySize(f1, "CAG", 600000);

			// System.out.println();;
			// service.create(airport);
			// List<Airport> airports = service.getAll();
//			List<Helicopter> helicopters = heliService.getAll();
//			List<Helicopter> addHelicopters = new ArrayList<>();
//			addHelicopters.add(helicopters.get(0));
//			addHelicopters.add(helicopters.get(1));
//			service.addHelicopter(addHelicopters, airports.get(0));
//			service.addHelicopter(helicopters.get(2), airports.get(1));

			// System.out.println(helicopters);
			// System.out.println(fxService.getAll());
			// System.out.println(fxService.getAll());
			System.out.println(service.getAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
