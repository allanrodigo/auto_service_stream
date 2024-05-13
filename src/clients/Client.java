package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import models.dealership.Vehicle;
import org.json.JSONException;
import org.json.JSONObject;
import utils.LoggerSetup;

public class Client {
    private static final String HOST = "192.168.100.7";
    private static final int PORT = 5432;

    public Vehicle connect_and_receive_data() {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String jsonString = reader.readLine();

            JSONObject vehicleData = new JSONObject(jsonString);
            String type = vehicleData.getString("Tipo");
            String color = vehicleData.getString("Cor");
            int id_car = vehicleData.getInt("IdCar");
            int id_employee = vehicleData.getInt("IdFuncionario");
            int id_station = vehicleData.getInt("IdEstacao");

            LoggerSetup.log("Received vehicle data");
            return new Vehicle(type, color, id_car, id_employee, id_station);
        } catch (JSONException | IOException e) {
            LoggerSetup.log("Error connecting to factory: " + e.getMessage());
        }
        return null;
    }
}
