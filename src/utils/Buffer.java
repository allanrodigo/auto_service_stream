package utils;

import models.dealership.Vehicle;

import java.util.concurrent.Semaphore;

public class Buffer
{
    private final Vehicle[] data;
    private final int size;
    private int out, in;
    private final Semaphore mutex, empty, full;

    public Buffer(int size)
    {
        this.size = size;
        data = new Vehicle[size];
        in = 0;
        out = 0;
        mutex = new Semaphore(1);
        empty = new Semaphore(size);
        full = new Semaphore(0);
    }

    public void buy(Vehicle vehicle) throws InterruptedException
    {
        empty.acquire();
        mutex.acquire();
        data[in] = vehicle;
        in = (in + 1) % size;
        LoggerSetup.log("Stock: Vehicle purchased - " + vehicle);
        mutex.release();
        full.release();
    }

    public Vehicle sell() throws InterruptedException
    {
        full.acquire();
        mutex.acquire();
        Vehicle vehicle = data[out];
        out = (out + 1) % size;
        LoggerSetup.log("Stock: Vehicle sold - " + vehicle);
        mutex.release();
        empty.release();
        return vehicle;
    }

    public int get_size()
    {
        return size;
    }

    public int get_current_size()
    {
        return (in - out + size) % size;
    }
}
