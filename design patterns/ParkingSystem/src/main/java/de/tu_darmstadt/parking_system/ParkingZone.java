package de.tu_darmstadt.parking_system;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class ParkingZone {
    private int zoneID;
    private BillingSystem billingSystem;
    private VehicleRegistry registry;

    private HashMap<String, LocalDateTime> arrivals;

    public ParkingZone(int zoneID, BillingSystem billingSystem, VehicleRegistry registry) {
        this.zoneID = zoneID;
        this.billingSystem = billingSystem;
        this.registry = registry;

        arrivals = new HashMap<>();
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
        int feesEuros = 0; // TODO: calculate actual fees

        billingSystem.invoice(vehicle.owner, feesEuros);
        arrivals.remove(licensePlate);
    }
}
