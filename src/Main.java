import models.customer.Customer;
import models.dealership.Dealership;
import models.dealership.DealershipFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Configuração da simulação
        int dealerships_len = 3;
        int customers_len = 20;

        // Criação das concessionárias
        List<Dealership> dealerships = new ArrayList<>();
        DealershipFactory factory = new DealershipFactory();
        dealerships.add(factory.get_dealership("Mercedes", 10));
        dealerships.add(factory.get_dealership("Porsche", 25));
        dealerships.add(factory.get_dealership("BMW", 7));

        // Criação dos consumidores
        List<Customer> customers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < customers_len; i++) {
            customers.add(new Customer(dealerships, random));
        }

        // Inicia as threads das concessionárias e dos consumidores
        ExecutorService executor = Executors.newFixedThreadPool(dealerships_len + customers_len);
        for (Dealership dealership : dealerships) {
            executor.execute(dealership::add_vehicle);
        }
        for (Customer customer : customers) {
            executor.execute(customer);
        }

        // Mantém a simulação rodando por um tempo
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Encerra a simulação
        executor.shutdownNow();
        System.out.println("Simulação encerrada.");
    }
}