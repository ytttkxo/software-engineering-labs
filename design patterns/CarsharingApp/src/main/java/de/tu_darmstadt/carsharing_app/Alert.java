package de.tu_darmstadt.carsharing_app;

import java.util.List;

public class Alert implements Visualization {
    private String carModel;
    private Lot lot;

    private boolean alreadyNotified = false;

    public Alert(String carModel, Lot lot) {
        this.carModel = carModel;
        this.lot = lot;
    }

    public String getModel() {
        return carModel;
    }

    public Lot getLot() {
        return lot;
    }

    @Override
    public void update(Lot updateLot) {

        // Null safety + only react to the assigned lot
        if (alreadyNotified) return;
        if (updateLot == null || carModel == null || lot == null) return;
        if (!lot.equals(updateLot)) return;

        // Check if the desired car model is currently available
        List<Car> cars = updateLot.getAvailableCars();
        if (cars == null) return;

        for (Car car : cars) {
            if (car == null) continue;
            String model = car.getModel();
            if (model.equals(carModel)) {
                System.out.println("ALERT: " + carModel + "available in lot: " +updateLot.getAddress());
                alreadyNotified = true;
                // Only notify once, even if multiple cars become available at the same time
                break;
            }
        }
    }
}
