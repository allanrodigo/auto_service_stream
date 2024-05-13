package models.dealership;

import utils.LoggerSetup;

public class PorscheDealership extends Dealership {

    public PorscheDealership(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    protected void log_vehicle_received(Vehicle vehicle) {
        LoggerSetup.log("Porsche Dealership: " + name + " received vehicle: " + vehicle);
    }

    @Override
    public void log_vehicle_sold(Vehicle vehicle) {
        LoggerSetup.log("Porsche Dealership: " + name + " sold vehicle: " + vehicle);
    }
}
