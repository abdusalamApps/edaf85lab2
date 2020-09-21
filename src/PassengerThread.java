import lift.Lift;
import lift.LiftView;
import lift.Passenger;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class PassengerThread extends Thread {

    private LiftView liftView;
    private Lift lift;
    private Random random;
    private Semaphore passengerSemaphore;

    public PassengerThread(LiftView liftView, Lift lift, Semaphore passengerSemaphore) {
        this.liftView = liftView;
        this.lift = lift;
        this.passengerSemaphore = passengerSemaphore;
        random = new Random();
    }

    @Override
    public void run() {
        super.run();
        try {
//            sleep(random.nextInt(46) * 1000);
            Passenger passenger = liftView.createPassenger();
            lift.makeRide(passenger);
            passengerSemaphore.release();

            passenger.begin();

            passengerSemaphore.acquire();
            passenger.enterLift();
            passengerSemaphore.release();

            passengerSemaphore.acquire();
            passenger.exitLift();
            passengerSemaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
