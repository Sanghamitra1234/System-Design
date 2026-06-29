package ParkingManagement;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private List<ParkingSpot> parkingSpots;
    private List<ParkingSpot> availableParkingSpots;

    public Floor( List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
        this.availableParkingSpots = new ArrayList<>();
    }
    public int getAvailableSpots() {
        int count = 0;
        for (ParkingSpot parkingSpot: parkingSpots) {
            if (parkingSpot.isAvailable()) count++;
        }
        return count;
    }

    public void setParkingSpots(ParkingSpot parkingSpot) {
        this.parkingSpots.add(parkingSpot);
    }

    public List<ParkingSpot> getParkingSpot() {
        return this.parkingSpots;
    }

    public List<ParkingSpot> getAvailableParkingSpotList() {
        return this.availableParkingSpots;
    }

    public void parkVehicle(Vehicle vehicle, ParkingSpot parkingSpot) {
        if (!parkingSpot.isAvailable()) {
            throw new IllegalArgumentException("No available parking spot");
        } else {
            parkingSpot.parkVehicle(vehicle);
            if (availableParkingSpots.contains(parkingSpot)) {
                availableParkingSpots.remove(parkingSpot);
            }
        }
    }

    public void unparkVehicle(ParkingSpot parkingSpot) {
        if (parkingSpot == null) {
            throw new IllegalArgumentException("No available parking spot");
        } 
        parkingSpot.unParkVehicle();
        availableParkingSpots.add(parkingSpot);
        
    }
}
