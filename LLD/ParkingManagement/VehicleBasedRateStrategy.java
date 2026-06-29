package ParkingManagement;

import java.util.Map;

public class VehicleBasedRateStrategy implements ParkingRateStrategy {
    private final Map<VehicleSize, Double> ratesPerHour;

    public VehicleBasedRateStrategy(Map<VehicleSize, Double> ratesPerHour) {
        this.ratesPerHour = ratesPerHour;
    }

    @Override
    public double calculateRate(ParkingTicket parkingTicket) {
        double rate = ratesPerHour.get(parkingTicket.getVehicle().getSize());
        return rate * parkingTicket.getTimeDifference();
    }
}
