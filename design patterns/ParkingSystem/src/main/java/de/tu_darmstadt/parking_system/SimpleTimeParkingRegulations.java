package de.tu_darmstadt.parking_system;

/**
 * ClassName:SimpleTimeParkingRegulations
 * Package: de.tu_darmstadt.parking_system
 * Description:
 *
 * @Autor: Tong
 * @Create: 11.01.26 - 06:50
 * @Version: v1.0
 *
 */
public class SimpleTimeParkingRegulations implements ParkingRegulations {
    @Override
    public int calculateFees(long durationMinutes, Vehicle vehicle, int zoneId) {
        if (durationMinutes < 5) return 0;

        // ceil(duration/30)
        long halfHours = (durationMinutes + 29) / 30;
        return (int) (2 * halfHours);
    }
}
