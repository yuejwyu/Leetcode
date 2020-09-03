package Parking_Lot;

public enum VehicleSize {
    Compact(1), Large(2);

    private final int size;

    VehicleSize(int size) { ////////////////////////////////// public?
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
