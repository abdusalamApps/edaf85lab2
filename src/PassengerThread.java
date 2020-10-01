import lift.Lift;
import lift.LiftView;
import lift.Passenger;

import java.util.Random;

public class PassengerThread extends Thread {

    private LiftView view;
    private Lift lift;
    private Random random;

    public PassengerThread(LiftView view, Lift lift) {
        this.view = view;
        this.lift = lift;
        random = new Random();
    }

    @Override
    public void run() {
        super.run();
        try {
            sleep(random.nextInt(46) * 1000);
            Passenger passenger = view.createPassenger();
            passenger.begin();
            lift.enter(passenger);

            lift.exit(passenger);

            passenger.end();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
