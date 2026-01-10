package de.tu_darmstadt.carsharing_app;

public class Alert {
    private String carModel;
    private Lot lot;

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
}
