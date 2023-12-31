package house_system;

import house_system.lift_system.LiftManager;
import java.util.ArrayList;
import java.util.List;

public class House {
    private final List<Floor> floors = new ArrayList<>();
    private final LiftManager liftManager = new LiftManager();

    public List<Floor> getFloors() {
        return floors;
    }

    public LiftManager getLiftManager() {
        return liftManager;
    }

}
