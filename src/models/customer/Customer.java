package models.customer;

import models.dealership.Dealership;
import models.dealership.Vehicle;
import utils.Buffer;
import utils.LoggerSetup;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Customer implements Runnable {
    private final UUID id;
    private final List<Dealership> dealerships;
    private final Random random;
    private final Buffer garage;

    public Customer(List<Dealership> dealerships, Random random) {
        this.id = UUID.randomUUID();
        this.dealerships = dealerships;
        this.random = random;
        this.garage = new Buffer(5);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Dealership random_dealership = dealerships.get(random.nextInt(dealerships.size()));
                
                Vehicle purchased_vehicle = random_dealership.sell_vehicle();

                if (purchased_vehicle != null) {
                    garage.buy(purchased_vehicle);
                    LoggerSetup.log("Customer " + id + " bought a beautiful " + purchased_vehicle.color() + " " + purchased_vehicle.type() + " from " + random_dealership.get_name() + "! (Garage: " + garage.get_current_size() + "/" + garage.get_size() + ")");
                } else {
                    LoggerSetup.log("Customer " + id + " waiting for cars at " + random_dealership.get_name() + "...");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LoggerSetup.log("Customer " + id + " was interrupted.");
        }
    }
}