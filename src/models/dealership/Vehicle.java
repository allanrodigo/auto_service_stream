package models.dealership;

public record Vehicle(String type, String color, int id_car, int id_employee, int id_station) {
    @Override
    public String toString() {
        return "Vehicle{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", id_car=" + id_car +
                ", id_employee=" + id_employee +
                ", id_station=" + id_station +
                '}';
    }
}