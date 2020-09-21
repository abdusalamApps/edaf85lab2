import lift.Lift;
import lift.LiftView;

import java.util.concurrent.Semaphore;

public class LiftThread extends Thread {
    private LiftView view;
    private Lift lift;
    private Semaphore passengerSemaphore;

    public LiftThread(LiftView view, Lift lift, Semaphore passengerSemaphore) {
        super();
        this.view = view;
        this.lift = lift;
        this.passengerSemaphore = passengerSemaphore;
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            try {
                passengerSemaphore.acquire();

                view.moveLift(lift.getCurrentFloor(), lift.getFromFloor());
                view.openDoors(lift.getFromFloor());
                passengerSemaphore.release();

                passengerSemaphore.acquire();
                view.closeDoors();
                view.moveLift(lift.getFromFloor(), lift.getToFloor());
                view.openDoors(lift.getToFloor());
                passengerSemaphore.release();
                passengerSemaphore.acquire();
                view.closeDoors();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
