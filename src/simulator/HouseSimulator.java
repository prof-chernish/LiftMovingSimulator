package simulator;

import house.Passenger;
import house.lift.Direction;
import house.Floor;
import house.House;
import house.lift.LiftManager;
import java.util.List;
import java.util.Random;

public class HouseSimulator {
    private final House house = new House();
    private int allPassengersCount;

    public House getHouse() {
        return house;
    }

    public void initialize() {
        Random randomGenerator = new Random();
        int floorsCount = randomGenerator.nextInt(4, 15);
        List<Floor> floors = house.getFloors();
        while (allPassengersCount == 0) {
            for (int i = 1; i <= floorsCount; i++) {
                Floor floor = new Floor();
                int passengersCount = randomGenerator.nextInt(0, 7);
                allPassengersCount += passengersCount;
                for (int j = 0; j < passengersCount; j++) {
                    int requiredFloorNumber;
                    do {
                        requiredFloorNumber = randomGenerator.nextInt(1, floorsCount);
                    } while (requiredFloorNumber == i);
                    Direction direction = requiredFloorNumber > i ? Direction.UP : Direction.DOWN;
                    Passenger passenger = new Passenger(requiredFloorNumber, direction);
                    floor.getPassengersOnTheFloor().add(passenger);
                }
                floors.add(floor);
            }
        }
    }



    public void startSimulation() {
        LiftManager liftManager = house.getLiftManager();
        List<Floor> floors = house.getFloors();
        int releasedPassengersCount = liftManager.letPassengersOut();
        allPassengersCount -= releasedPassengersCount;
        liftManager.letPassengersIn(floors);
    }

    public boolean hasNextFloor() {
        LiftManager liftManager = house.getLiftManager();
        List<Floor> floors = house.getFloors();
        if (allPassengersCount > 0) {
            liftManager.goToTheNextFloor(floors);
            return true;
        } else {
            return false;
        }
    }

}
