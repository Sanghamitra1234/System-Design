package ParkingManagement;

public class ParkingTicket {
    private final String ticketId;
    private final long startTime;
    private Long endTime;
    private final ParkingSpot parkingSpot;
    private final Floor floor;
    private final Vehicle vehicle;

    public ParkingTicket(long startTime, Floor floor, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.ticketId = generateTicketId();
        this.startTime = startTime;
        this.floor = floor;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    private String generateTicketId() {
        return "TICKET-" + System.currentTimeMillis();
    }

    public String getTicketId() {
        return ticketId;
    }

    public int getTimeDifference() {
        return (int) (this.endTime - this.startTime) / 1000;
    }

    public long getStartTime() {
        return startTime;
    }

    public Floor getFloor() {
        return this.floor;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
