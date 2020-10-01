import lift.Lift;
import lift.LiftView;

public class LiftThread extends Thread {
    private LiftView view;
    private Lift lift;

    public LiftThread(LiftView view, Lift lift) {
        super();
        this.view = view;
        this.lift = lift;
    }

    @Override
    public void run() {
        super.run();
        while (true) {

            int currentFloor = lift.getCurrentFloor();
            view.openDoors(currentFloor);
            lift.setMoving(false);
            lift.waitForPassengers();
            lift.setMoving(true);
            view.closeDoors();
            view.moveLift(currentFloor, lift.nextFloor());

        }
    }
}

