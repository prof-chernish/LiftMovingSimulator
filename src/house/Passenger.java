package house;

import house.lift.Direction;

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
