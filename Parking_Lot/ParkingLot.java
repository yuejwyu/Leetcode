package Parking_Lot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParkingLot {
    private final Level[] levels;

    public ParkingLot(int numOfLevels, int numSpotsPerLevel) {
        levels = new Level[numOfLevels];
        for (int i = 0; i < numOfLevels; i++) {
            levels[i] = new Level(numSpotsPerLevel);
        }
    }

    public boolean hasSpot(Vehicle v) {
        for (Level l : levels) {
            if (l.hasSpot(v)) return true;
        }
        return false;
    }

    public boolean park(Vehicle v) {
        for (Level l : levels) {
            if (l.park(v)) return true;
        }
        return false;
    }

    public boolean leave(Vehicle v) {
        for (Level l : levels) {
            if (l.leave(v)) return true;
        }
        return false;
    }
}

class Level {
    private final List<ParkingSpot> spots;

    Level(int numOfSpots) {
        List<ParkingSpot> list = new ArrayList<>(numOfSpots);
        int i = 0;
        for (; i < numOfSpots / 2; i++) {
            list.add(new ParkingSpot(VehicleSize.Compact));
        }
        for (; i < numOfSpots; i++) {
            list.add(new ParkingSpot(VehicleSize.Large));
        }
        spots = Collections.unmodifiableList(list); /////////////////////////////// ???
    }

    boolean hasSpot(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) return true;
        }
        return false;
    }

    boolean park(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.fit(v)) {
                s.park(v);
                return true;
            }
        }
        return false;
    }

    boolean leave(Vehicle v) {
        for (ParkingSpot s : spots) {
            if (s.getCurrentVehicle() == v) {
                s.leave();
                return true;
            }
        }
        return false;
    }
}

class ParkingSpot { //////////////////////////////// why not nested?
    private final VehicleSize size;
    private Vehicle currentVehicle;

    ParkingSpot(VehicleSize size) {
        this.size = size;
    }

    boolean fit(Vehicle v) {
        return currentVehicle == null && size.getSize() >= v.getSize().getSize();
    }

    void park(Vehicle v) {
        currentVehicle = v;
    }

    void leave() {
        currentVehicle = null;
    }

    Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}
