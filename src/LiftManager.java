import java.util.List;

public class LiftManager {
    private final Lift lift = new Lift();
    private final List<List<Integer>> passengersOnEachFloor;

    public LiftManager(List<List<Integer>> passengersOnEachFloor) {
        this.passengersOnEachFloor = passengersOnEachFloor;
    }

    public List<List<Integer>> getPassengersOnEachFloor() {
        return passengersOnEachFloor;
    }

    public Lift getLift() {
        return lift;
    }

    public int letPassengersOut() {
        int releasedPassengersCount = 0;
        List<Integer> liftPassengers = lift.getLiftPassengers();
        int i = 0;
        while (i < liftPassengers.size()) {
            if (liftPassengers.get(i) == lift.getCurrentFloor()) {
                liftPassengers.remove(i);
                releasedPassengersCount++;
            } else {
                i++;
            }
        }
        return releasedPassengersCount;
    }

    public void letPassengersIn() {
        List<Integer> passengersOnTheFloor = passengersOnEachFloor.get(lift.getCurrentFloor() - 1);
        List<Integer> liftPassengers = lift.getLiftPassengers();
        int i = 0;
        while (i < passengersOnTheFloor.size()){
            int passenger = passengersOnTheFloor.get(i);

            if (liftPassengers.size() == lift.CAPACITY) {
                break;
            }

            if (getDirectionForPassenger(passenger) == lift.getDirection()) {
                liftPassengers.add(passenger);
                int lastFloor = getNewLastFloor(passenger);
                lift.setLastFloor(lastFloor);
                passengersOnTheFloor.remove(i);

            } else {
                i++;
            }
        }

    }

    public void goToTheNextFloor() {
        int currentFloor = lift.getCurrentFloor();
        if (currentFloor == lift.getLastFloor()) {
            if (passengersOnEachFloor.get(currentFloor - 1).size() > 0) {
                changeDirection();
                return;
            }
            int floor = findNearestFloorWithPassengers();
            lift.setLastFloor(floor);
            if (floor > currentFloor) {
                lift.setDirection(Direction.UP);
            } else {
                lift.setDirection(Direction.DOWN);
            }

        }

        if (lift.getDirection() == Direction.UP) {
            lift.setCurrentFloor(currentFloor + 1);
        } else {
            lift.setCurrentFloor(currentFloor - 1);
        }
    }

    private int findNearestFloorWithPassengers() {
        int floor = lift.getCurrentFloor();
        int i = 1;
        while (true) {
            if (passengersOnEachFloor.get(floor - 1).size() > 0) {
                return floor;
            } else {
                do {
                    floor = floor + i;
                    i = (int) Math.signum(i) * (Math.abs(i) + 1) * (-1);

                } while (floor < 1 || floor >= passengersOnEachFloor.size() + 1);

            }
        }

    }


    private void changeDirection() {
        if (lift.getDirection() == Direction.UP) {
            lift.setDirection(Direction.DOWN);
        } else {
            lift.setDirection(Direction.UP);
        }
    }

    private int getNewLastFloor(int passenger) {
        if (lift.getDirection() == Direction.UP) {
            return Math.max(lift.getLastFloor(), passenger);
        } else {
            return Math.min(lift.getLastFloor(), passenger);
        }
    }

    private Direction getDirectionForPassenger(int passenger) {
        if (passenger > lift.getCurrentFloor()) {
            return Direction.UP;
        } else {
            return Direction.DOWN;
        }
    }
}
