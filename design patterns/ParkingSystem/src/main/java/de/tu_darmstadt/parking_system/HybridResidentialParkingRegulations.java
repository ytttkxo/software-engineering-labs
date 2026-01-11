package de.tu_darmstadt.parking_system;

/**
 * ClassName:HybridResidentialParkingRegulations
 * Package: de.tu_darmstadt.parking_system
 * Description:
 *
 * @Autor: Tong
 * @Create: 11.01.26 - 07:01
 * @Version: v1.0
 *
 */
public class HybridResidentialParkingRegulations implements ParkingRegulations {
    @Override
    public int calculateFees(long durationMinutes, Vehicle vehicle, int zoneId) {
        if (vehicle != null && vehicle.residentialZoneID != null && vehicle.residentialZoneID == zoneId) return 0;

        if (durationMinutes < 30) return 0;

        long chargeable = durationMinutes - 30;
        long halfHours = (chargeable + 29) / 30;
        return (int) (4 * halfHours);
    }
}
