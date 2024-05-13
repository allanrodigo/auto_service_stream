package models.dealership;

import utils.Buffer;
import utils.LoggerSetup;
import clients.Client;

public abstract class Dealership {
    protected final Buffer stock;
    protected final String name;
    private final Client client;

    public Dealership(String name, int capacity) {
        stock = new Buffer(capacity);
        this.name = name;
        this.client = new Client();
    }

    public void add_vehicle() {
        try {
            Vehicle vehicle = client.connect_and_receive_data();
            stock.buy(vehicle);
            log_vehicle_received(vehicle);
        } catch (InterruptedException e) {
            LoggerSetup.log("Dealership " + name + ": Failed to receive vehicle due to interruption: " + e.getMessage());
        }
    }

    public Vehicle sell_vehicle() throws InterruptedException {
        Vehicle sold_vehicle = stock.sell();
        log_vehicle_sold(sold_vehicle);
        return sold_vehicle;
    }

    protected abstract void log_vehicle_received(Vehicle vehicle);

    public abstract void log_vehicle_sold(Vehicle vehicle);

    public String get_name() {
        return name;
    }
}