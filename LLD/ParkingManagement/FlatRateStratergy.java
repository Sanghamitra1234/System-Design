package ParkingManagement;

public class FlatRateStratergy implements ParkingRateStrategy {
    private double rate;

    public FlatRateStratergy(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateRate(ParkingTicket parkingTicket) {
        return rate * parkingTicket.getTimeDifference();
    }
}
