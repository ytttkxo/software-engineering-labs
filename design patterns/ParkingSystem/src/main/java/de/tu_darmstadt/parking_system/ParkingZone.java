package de.tu_darmstadt.parking_system;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ParkingZone {
    private int zoneID;
    private BillingSystem billingSystem;
    private VehicleRegistry registry;

    private HashMap<String, LocalDateTime> arrivals;

    private ParkingRegulations regulations;

    public ParkingZone(int zoneID, BillingSystem billingSystem, VehicleRegistry registry) {
        this.zoneID = zoneID;
        this.billingSystem = billingSystem;
        this.registry = registry;

        arrivals = new HashMap<>();

        this.regulations = new SimpleTimeParkingRegulations();
    }

    public void setRegulations(ParkingRegulations regulations) {
        if (regulations ==  null) return;
        this.regulations = regulations;
    }

    public void processArrival(String licensePlate, LocalDateTime time) {
        if (arrivals.containsKey(licensePlate)) {
            throw new IllegalArgumentException();
        }
        arrivals.put(licensePlate, time);
    }

    public void processDeparture(String licensePlate, LocalDateTime time) {
        LocalDateTime arrivalTime = arrivals.get(licensePlate);
        if (arrivalTime == null) {
            throw new IllegalArgumentException();
        }

        long durationMinutes = Duration.between(arrivalTime, time).toMinutes();
        Vehicle vehicle = registry.getVehicle(licensePlate);
        if (vehicle == null) {
            throw new IllegalArgumentException();
        };

        int feesEuros = regulations.calculateFees(durationMinutes, vehicle, zoneID);

        billingSystem.invoice(vehicle.owner, feesEuros);
        arrivals.remove(licensePlate);
    }
}
