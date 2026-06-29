package ParkingManagement;

import java.util.List;

public class SpotManagementService {
    class SpotAssignment {
        private final Floor floor;
        private final ParkingSpot parkingSpot;

        public SpotAssignment(Floor floor, ParkingSpot parkingSpot) {
            this.floor = floor;
            this.parkingSpot = parkingSpot;
        }

        public Floor getFloor() {
            return floor;
        }

        public ParkingSpot getParkingSpot() {
            return parkingSpot;
        }
    }
    public SpotAssignment findParkingSpot(ParkingLot parkingLot, Vehicle vehicle) {
        List<Floor> floors = parkingLot.getFloors();
        for (Floor floor : floors) {
            List<ParkingSpot> parkingSpots = floor.getAvailableParkingSpotList();
            if (parkingSpots == null) {
                continue;
            }
            for (ParkingSpot parkingSpot : parkingSpots) {
                if (parkingSpot.getVehicleSize() == vehicle.getSize() && parkingSpot.isAvailable()) {
                    return new SpotAssignment(floor, parkingSpot);
                }
            }
        }
        return null;
    }

}


