package de.tu_darmstadt.parking_system;

/**
 * ClassName:ResidentialParkingRegulations
 * Package: de.tu_darmstadt.parking_system
 * Description:
 *
 * @Autor: Tong
 * @Create: 11.01.26 - 06:56
 * @Version: v1.0
 *
 */
public class ResidentialParkingRegulations implements ParkingRegulations {
    private static final int FINE_EUROS = 80;

    @Override
    public int calculateFees(long durationMinutes, Vehicle vehicle, int zoneId) {
        if (vehicle != null && vehicle.residentialZoneID != null && vehicle.residentialZoneID == zoneId) return 0;

        if (durationMinutes < 30) return 0;

        return FINE_EUROS;
    }
}
