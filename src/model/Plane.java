package model;

public class Plane {
    private String serialNumber;
    private String model;
    private Airport actualAirport;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Airport getActualAirport() {
        return actualAirport;
    }

    public void setActualAirport(Airport actualAirport) {
        this.actualAirport = actualAirport;
    }
}
