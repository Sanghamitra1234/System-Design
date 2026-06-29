package ParkingManagement;

import java.util.UUID;

public class ParkingSpot {
    private String parkingSpotId;
    private boolean isAvailable;
    private VehicleSize vehicleSize;
    private Vehicle vehicle;

    public ParkingSpot(boolean isAvailable, VehicleSize vehicleSize, Vehicle vehicle) {
        this.parkingSpotId = generateParkingSpotId();
        this.isAvailable = true;
        this.vehicleSize = vehicleSize;
        this.vehicle = null;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    private String generateParkingSpotId() {
        return "SPOT-" + UUID.randomUUID().toString();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public VehicleSize getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleSize vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public void unParkVehicle() {
        this.vehicle = null;
        this.isAvailable = true;
    }
}
