package ParkingManagement;
import java.util.HashMap;
import java.util.List;

import ParkingManagement.SpotManagementService.SpotAssignment;

public class ParkingLot {
    private List<Floor> floors;
    private static volatile ParkingLot parkingLot;
    private HashMap<ParkingSpot, ParkingTicket> parkingTicketsMap;
    private SpotManagementService spotManagementService;
    private ParkingRateStrategy parkingRateStrategy;
    private static final Object lock = new Object();

    public static ParkingLot getparkingLot() {
        if (parkingLot == null) {
            synchronized (lock) {
                if (parkingLot == null) {
                    parkingLot = new ParkingLot();
                }
            }
        }
        return parkingLot;
    }

    public void initialize(List<Floor> floors, SpotManagementService spotManagementService) {
        this.floors = floors;
        this.spotManagementService = spotManagementService;
        this.parkingTicketsMap = new HashMap<>();
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setParkingRateStratergy(ParkingRateStrategy parkingRateStrategy) {
        this.parkingRateStrategy = parkingRateStrategy;
    }

    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) {
        SpotAssignment spotAssignment = spotManagementService.findParkingSpot(this, vehicle);
        if (spotAssignment == null || spotAssignment.getParkingSpot() == null) {
            throw new IllegalArgumentException("No parking spot available for the vehicle");
        }
        Floor parkingFloor = spotAssignment.getFloor();
        ParkingSpot parkingSpot = spotAssignment.getParkingSpot();
        parkingFloor.parkVehicle(vehicle, parkingSpot);

        //generate ParkingTicket
        ParkingTicket parkingTicket = new ParkingTicket(System.currentTimeMillis(), parkingFloor, parkingSpot, vehicle);
        parkingTicketsMap.put(parkingSpot, parkingTicket);

        return parkingTicket;
    }


    public synchronized void unParkVehicle(ParkingSpot parkingSpot) {
        if (parkingSpot == null ) {
            throw new IllegalArgumentException("No vehicle to unpark");
        }
        if (!parkingTicketsMap.containsKey(parkingSpot)) {
            throw new IllegalArgumentException("No vehicle to unpark");
        }
        ParkingTicket parkingTicket = parkingTicketsMap.get(parkingSpot);

        Floor parkingFloor = parkingTicket.getFloor();
        parkingFloor.unparkVehicle(parkingSpot);
    
        parkingTicket.setEndTime(System.currentTimeMillis());
        double parkingRate = parkingRateStrategy.calculateRate(parkingTicket);
        parkingTicketsMap.remove(parkingSpot, parkingTicket);

        System.out.println("Parking Amount to be Paid is: " + parkingRate);

    }

    public void displayAvaiableParkingSpots() {
        for (int i = 0; i < floors.size(); i++) {
            System.out.println("Floor: "+ i  + " : Avaiable Parkings: "+ floors.get(i).getAvailableSpots());
        }
    }
}
