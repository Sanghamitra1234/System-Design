package ParkingManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingManagementDemo {
    public static void main(String[] args) {
        // Initialize parking spots for Floor 1
        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new ParkingSpot(true, VehicleSize.SMALL, null));
        floor1Spots.add(new ParkingSpot(true, VehicleSize.MEDIUM, null));
        floor1Spots.add(new ParkingSpot(true, VehicleSize.LARGE, null));

        Floor floor1 = new Floor(floor1Spots);
        floor1.getAvailableParkingSpotList().addAll(floor1Spots);

        // Initialize parking spots for Floor 2
        List<ParkingSpot> floor2Spots = new ArrayList<>();
        floor2Spots.add(new ParkingSpot(true, VehicleSize.SMALL, null));
        floor2Spots.add(new ParkingSpot(true, VehicleSize.MEDIUM, null));
        floor2Spots.add(new ParkingSpot(true, VehicleSize.LARGE, null));

        Floor floor2 = new Floor(floor2Spots);
        floor2.getAvailableParkingSpotList().addAll(floor2Spots);

        List<Floor> floors = Arrays.asList(floor1, floor2);

        SpotManagementService spotManagementService = new SpotManagementService();
        ParkingLot parkingLot = ParkingLot.getparkingLot();
        parkingLot.initialize(floors, spotManagementService);
        parkingLot.setParkingRateStratergy(new FlatRateStratergy(20));

        System.out.println("Initial availability:");
        parkingLot.displayAvaiableParkingSpots();

        Vehicle bike = new Vehicle("KA-01-AA-1111", VehicleSize.SMALL);
        Vehicle car = new Vehicle("KA-02-BB-2222", VehicleSize.MEDIUM);
        Vehicle truck = new Vehicle("KA-03-CC-3333", VehicleSize.LARGE);

        ParkingTicket bikeTicket = parkingLot.parkVehicle(bike);
        ParkingTicket carTicket = parkingLot.parkVehicle(car);
        ParkingTicket truckTicket = parkingLot.parkVehicle(truck);

        System.out.println("\nAfter parking three vehicles:");
        parkingLot.displayAvaiableParkingSpots();

        parkingLot.unParkVehicle(bikeTicket.getParkingSpot());
        parkingLot.unParkVehicle(carTicket.getParkingSpot());

        System.out.println("\nAfter unparking bike and car:");
        parkingLot.displayAvaiableParkingSpots();

        // Note: No payment calculation implemented yet; just demonstrating ticket flow.
        System.out.printf("Bike parked at spot %s on floor %s%n",
            bikeTicket.getParkingSpot().getParkingSpotId(),
            bikeTicket.getFloor() != null ? bikeTicket.getFloor().toString() : "Unknown");
        System.out.printf("Truck parked at spot %s\n", truckTicket.getParkingSpot().getParkingSpotId());
    }
}
