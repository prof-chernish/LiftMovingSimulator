package house_system;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Passenger> passengersOnTheFloor = new ArrayList<>();

    public List<Passenger> getPassengersOnTheFloor() {
        return passengersOnTheFloor;
    }
}
