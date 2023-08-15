package house_system;

import house_system.lift_system.Direction;

public class Passenger {
    private final int requiredFloorNumber;
    private final Direction direction;

    public int getRequiredFloorNumber() {
        return requiredFloorNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    public Passenger(int requiredFloorNumber, Direction direction) {
        this.requiredFloorNumber = requiredFloorNumber;
        this.direction = direction;
    }



}
