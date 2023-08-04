import java.util.ArrayList;
import java.util.List;

public class Lift {
    private final List<Integer> liftPassengers = new ArrayList<>();
    private int lastFloor = 1;
    private Direction direction = Direction.UP;
    public final static int CAPACITY = 5;
    private int currentFloor = 1;

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setLastFloor(int lastFloor) {
        this.lastFloor = lastFloor;
    }

    public List<Integer> getLiftPassengers() {
        return liftPassengers;
    }

    public int getLastFloor() {
        return lastFloor;
    }

    public Direction getDirection() {
        return direction;
    }
}
