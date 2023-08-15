import java.util.List;

public class View {
    private final HouseSimulator houseSimulator = new HouseSimulator();

    public void start() {
        houseSimulator.initialize();
        showIteration(0);
        int iteration = 1;
        do {
            houseSimulator.startSimulation();
            showIteration(iteration);
            iteration++;
        } while (houseSimulator.hasNextFloor());
    }


    private void showIteration(int iteration) {
        House house = houseSimulator.getHouse();
        int floors = house.getFloors().size();
        System.out.println("============== Шаг " + iteration + " ===============");
        for (int floor = floors; floor > 0 ; floor--) {
            System.out.println(getHouseView(floor));
        }
        System.out.println();
    }

    private String getHouseView(int floor) {
        String stringFloor = floor < 10 ? "0" + floor + " этаж" : floor + " этаж";
        House house = houseSimulator.getHouse();
        List<Passenger> passengersOnTheFloor = house.getFloors().get(floor - 1).getPassengersOnTheFloor();
        StringBuilder sb = new StringBuilder();
        sb.append(stringFloor).append("| ").append(getLiftView(floor)).append(" | ");
        for (Passenger passenger: passengersOnTheFloor) {
            int floorNumber = passenger.getRequiredFloorNumber();
            sb.append(floorNumber).append(" ");
        }
        return sb.toString();
    }

    private String getLiftView(int floor) {
        Lift lift = houseSimulator.getHouse().getLiftManager().getLift();
        StringBuilder sb = new StringBuilder();
        if (floor == lift.getCurrentFloorNumber()) {
            sb.append(lift.getDirection().getLabel()).append(" ");
            List<Passenger> liftPassengers = lift.getLiftPassengers();
            for (Passenger passenger: liftPassengers) {
                int floorNumber = passenger.getRequiredFloorNumber();
                sb.append(floorNumber).append(" ");
                if (floorNumber < 10) {
                    sb.append(" ");
                }
            }
            sb.append("   ".repeat(Math.max(0, Lift.CAPACITY - liftPassengers.size())));
        } else {
            sb.append("  ").append("   ".repeat(Lift.CAPACITY));;
        }
        return sb.toString();
    }


}
