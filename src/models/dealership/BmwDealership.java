package models.dealership;

import utils.LoggerSetup;

public class BmwDealership extends Dealership {
    public BmwDealership(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    protected void log_vehicle_received(Vehicle vehicle) {
        LoggerSetup.log("BMW Dealership: " + name + " received vehicle: " + vehicle);
    }

    @Override
    public void log_vehicle_sold(Vehicle vehicle) {
        LoggerSetup.log("BMW Dealership: " + name + " sold vehicle: " + vehicle);
    }
}