import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HouseSimulator {
    private final LiftManager liftManager;
    private final static Random RANDOM_GENERATOR = new Random();
    private final int floorsCount = RANDOM_GENERATOR.nextInt(4, 10);
    private int allPassengersCount;

    public HouseSimulator() {
        List<List<Integer>> passengersOnEachFloor = new ArrayList<>();
        while (allPassengersCount == 0) {
            passengersOnEachFloor = new ArrayList<>();

            for (int i = 1; i <= floorsCount; i++) {
                List<Integer> passengers = new ArrayList<>();
                int passengersCount = RANDOM_GENERATOR.nextInt(0, 7);
                allPassengersCount += passengersCount;
                for (int j = 0; j < passengersCount; j++) {
                    int passenger;
                    do {
                        passenger = RANDOM_GENERATOR.nextInt(1, floorsCount);

                    } while (passenger == i);
                    passengers.add(passenger);
                }
                passengersOnEachFloor.add(passengers);
            }
        }
        liftManager = new LiftManager(passengersOnEachFloor);
    }

    public LiftManager getLiftManager() {
        return liftManager;
    }

    public int getFloorsCount() {
        return floorsCount;
    }

    public void startSimulation() {
        boolean isPassengerExist = true;
        int iteration = 0;
        while (isPassengerExist) {
            iteration++;
            int releasedPassengersCount = liftManager.letPassengersOut();
            allPassengersCount -= releasedPassengersCount;
            liftManager.letPassengersIn();
            View view = new View(this);
            view.showIteration(iteration);
            if (allPassengersCount > 0) {
                liftManager.goToTheNextFloor();

            } else {
                isPassengerExist = false;
            }
        }
    }

}
