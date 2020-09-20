import lift.Lift;
import lift.LiftView;
import lift.Passenger;

import java.util.Random;

public class PassengerThread extends Thread {

    private LiftView liftView;
    private Lift lift;
    private Random random;

    public PassengerThread(LiftView liftView, Lift lift) {
        this.liftView = liftView;
        this.lift = lift;
        random = new Random();
    }

    @Override
    public void run() {
        super.run();
        try {
            sleep(random.nextInt(46));
            Passenger passenger = liftView.createPassenger();
            int from = passenger.getStartFloor();
            int to = passenger.getDestinationFloor();
            passenger.begin();
            lift.moveLift(0, from);
            liftView.openDoors(from);
            passenger.enterLift();
            liftView.closeDoors();
            liftView.moveLift(from, to);
            liftView.openDoors(to);
            passenger.exitLift();
            liftView.closeDoors();
            passenger.end();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
