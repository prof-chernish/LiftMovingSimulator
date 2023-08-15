package house.lift;

import house.Floor;
import house.Passenger;
import java.util.List;

public class LiftManager {
    private final Lift lift = new Lift();

    public Lift getLift() {
        return lift;
    }

    public int letPassengersOut() {
        int releasedPassengersCount = 0;
        List<Passenger> liftPassengers = lift.getLiftPassengers();
        int i = 0;
        Passenger passenger;
        while (i < liftPassengers.size()) {
            passenger = liftPassengers.get(i);
            if (passenger.getRequiredFloorNumber() == lift.getCurrentFloorNumber()) {
                liftPassengers.remove(i);
                releasedPassengersCount++;
            } else {
                i++;
            }
        }
        return releasedPassengersCount;
    }

    public void letPassengersIn(List<Floor> floors) {
        int currentFloorNumber = lift.getCurrentFloorNumber();
        List<Passenger> liftPassengers = lift.getLiftPassengers();
        List<Passenger> passengersOnTheFloor = floors.get(currentFloorNumber - 1).getPassengersOnTheFloor();
        int i = 0;
        Passenger passenger;
        while (i < passengersOnTheFloor.size()){
            passenger = passengersOnTheFloor.get(i);
            if (liftPassengers.size() == Lift.CAPACITY) {
                break;
            }
            if (passenger.getDirection() == lift.getDirection()) {
                liftPassengers.add(passenger);
                int lastFloorNumber = getNewLastFloorNumber(passenger);
                lift.setLastFloorNumber(lastFloorNumber);
                passengersOnTheFloor.remove(i);
            } else {
                i++;
            }
        }

    }

    public void goToTheNextFloor(List<Floor> floors) {
        int currentFloorNumber = lift.getCurrentFloorNumber();
        int nextFloorNumber = currentFloorNumber;
        if (currentFloorNumber != lift.getLastFloorNumber()) {
            List<Passenger> passengersOnTheFloor;
            do {
                if (lift.getDirection() == Direction.UP) {
                    nextFloorNumber = nextFloorNumber + 1;
                } else {
                    nextFloorNumber = nextFloorNumber - 1;
                }
                passengersOnTheFloor = floors.get(nextFloorNumber - 1).getPassengersOnTheFloor();

            } while (!isStopRequired(nextFloorNumber, passengersOnTheFloor));

        } else {
            nextFloorNumber = findNearestFloorWithPassengers(floors);
            if (nextFloorNumber == currentFloorNumber) {
                    changeDirection();
            } else {
                lift.setLastFloorNumber(nextFloorNumber);
                Direction newDirection = nextFloorNumber > currentFloorNumber ? Direction.UP : Direction.DOWN;
                lift.setDirection(newDirection);
            }
        }
        lift.setCurrentFloorNumber(nextFloorNumber);
    }

    private boolean isStopRequired(int nextFloor, List<Passenger> passengers) {
        return hasExitingPassengers(nextFloor) || canPickUpPassenger(passengers);
    }

    private boolean hasExitingPassengers(int nextFloor) {
        List<Passenger> liftPassengers = lift.getLiftPassengers();
        return liftPassengers.
                stream().
                anyMatch(passenger -> passenger.getRequiredFloorNumber() == nextFloor);
    }

    private boolean canPickUpPassenger(List<Passenger> passengers) {
        return hasMoreSpace() &&
                passengers.
                stream().
                anyMatch(passenger -> passenger.getDirection() == lift.getDirection());
    }

    private boolean hasMoreSpace() {
        return lift.getLiftPassengers().size() < Lift.CAPACITY;
    }

    private int findNearestFloorWithPassengers(List<Floor> floors) {
        int floorNumber = lift.getCurrentFloorNumber();
        List<Passenger> passengersOnTheFloor;
        int i = 1;
        boolean isFloorEmpty = true;
        while (isFloorEmpty) {
            passengersOnTheFloor = floors.get(floorNumber - 1).getPassengersOnTheFloor();
            if (passengersOnTheFloor.size() > 0) {
                isFloorEmpty = false;

            } else {
                do {
                    floorNumber = floorNumber + i;
                    i = (int) Math.signum(i) * (Math.abs(i) + 1) * (-1);

                } while (floorNumber < 1 || floorNumber >= floors.size() + 1);
            }
        }
        return floorNumber;
    }


    private void changeDirection() {
        if (lift.getDirection() == Direction.UP) {
            lift.setDirection(Direction.DOWN);
        } else {
            lift.setDirection(Direction.UP);
        }
    }

    private int getNewLastFloorNumber(Passenger passenger) {
        if (lift.getDirection() == Direction.UP) {
            return Math.max(lift.getLastFloorNumber(), passenger.getRequiredFloorNumber());
        } else {
            return Math.min(lift.getLastFloorNumber(), passenger.getRequiredFloorNumber());
        }
    }

}
