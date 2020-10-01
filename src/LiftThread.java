import lift.Lift;
import lift.LiftView;
import lift.Passenger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

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
            lift.setMoving(false);
            view.openDoors(currentFloor);
            try {
                sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lift.setMoving(true);
            view.closeDoors();
            view.moveLift(currentFloor, lift.nextFloor());

        }
    }
}

