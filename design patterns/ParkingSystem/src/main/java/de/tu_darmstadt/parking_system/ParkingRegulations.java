package de.tu_darmstadt.parking_system;

/**
 * ClassName:ParkingRegulations
 * Package: de.tu_darmstadt.parking_system
 * Description:
 *
 * @Autor: Tong
 * @Create: 11.01.26 - 06:47
 * @Version: v1.0
 *
 */
public interface ParkingRegulations {
    int calculateFees(long durationMinutes, Vehicle vehicle, int zoneId);
}
