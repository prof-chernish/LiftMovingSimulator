package house.lift;

import java.util.ArrayList;
import house.Passenger;
import java.util.List;

public class Lift {
    private final List<Passenger> liftPassengers = new ArrayList<>();
    private int lastFloorNumber = 1;
    private Direction direction = Direction.UP;
    public final static int CAPACITY = 5;
    private int currentFloorNumber = 1;

    public int getCurrentFloorNumber() {
        return currentFloorNumber;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCurrentFloorNumber(int currentFloorNumber) {
        this.currentFloorNumber = currentFloorNumber;
    }

    public void setLastFloorNumber(int lastFloorNumber) {
        this.lastFloorNumber = lastFloorNumber;
    }

    public List<Passenger> getLiftPassengers() {
        return liftPassengers;
    }

    public int getLastFloorNumber() {
        return lastFloorNumber;
    }

    public Direction getDirection() {
        return direction;
    }
}
