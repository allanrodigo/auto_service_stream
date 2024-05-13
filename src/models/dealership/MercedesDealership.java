package models.dealership;

import utils.LoggerSetup;

public class MercedesDealership extends Dealership {
    public MercedesDealership(String name, int capacity) { super(name, capacity); }

    @Override
    protected void log_vehicle_received(Vehicle vehicle) {
        LoggerSetup.log("Mercedes Dealership: " + name + " received vehicle: " + vehicle);
    }

    @Override
    public void log_vehicle_sold(Vehicle vehicle) {
        LoggerSetup.log("Mercedes Dealership: " + name + " sold vehicle: " + vehicle);
    }
}