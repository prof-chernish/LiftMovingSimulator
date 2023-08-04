import java.util.List;

public class View {
    private final HouseSimulator houseSimulator;

    public View(HouseSimulator houseSimulator) {
        this.houseSimulator = houseSimulator;

    }

    public void showIteration(int iteration) {
        System.out.println("========= Шаг " + iteration + " =============");

        for (int floor = houseSimulator.getFloorsCount(); floor > 0 ; floor--) {
            System.out.println(getHouseView(floor));
        }
        System.out.println();

    }

    private String getHouseView(int floor) {
        List<Integer> passengersOnTheFloor = houseSimulator.getLiftManager().getPassengersOnEachFloor().get(floor - 1);
        StringBuilder sb = new StringBuilder();
        sb.append("| ").append(getLiftView(floor)).append(" | ");

        for (Integer passenger: passengersOnTheFloor) {
            sb.append(passenger).append(" ");
        }

        return sb.toString();
    }

    private String getLiftView(int floor) {
        Lift lift = houseSimulator.getLiftManager().getLift();
        StringBuilder sb = new StringBuilder();
        if (floor == lift.getCurrentFloor()) {
            sb.append(lift.getDirection().getLabel()).append(" ");
            List<Integer> liftPassengers = lift.getLiftPassengers();

            for (Integer passenger: liftPassengers) {
                sb.append(passenger).append(" ");
            }

            for (int i = 0; i < Lift.CAPACITY - liftPassengers.size(); i++) {
                sb.append("  ");
            }

        } else {
            sb.append("  ");
            for (int i = 0; i < Lift.CAPACITY; i++) {
                sb.append("  ");
            }
        }

        return sb.toString();
    }


}
