public class Passenger {
    private final int requiredFloorNumber;

    public Direction getDirection() {
        return direction;
    }

    private final Direction direction;

    public Passenger(int requiredFloorNumber, Direction direction) {

        this.requiredFloorNumber = requiredFloorNumber;
        this.direction = direction;
    }

    public int getRequiredFloorNumber() {
        return requiredFloorNumber;
    }

}
