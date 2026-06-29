package ParkingManagement;

public class Vehicle {
    private String number;
    private VehicleSize size;

    public Vehicle(String number, VehicleSize size) {
        this.number = number;
        this.size = size;
    }
    public String getNumber() {
        return number;
    }
    public VehicleSize getSize() {
        return size;
    }

    public void setNumber(String number) {
        this.number = number;
    } 

    public void setSize(VehicleSize size) {
        this.size = size;
    }   

    public String toString() {
        return "Vehicle{" +
                "number='" + number + '\'' +
                ", size=" + size +
                '}';
    }
}
