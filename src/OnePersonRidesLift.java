
import lift.LiftView;
import lift.Passenger;

public class OnePersonRidesLift {

    public static void main(String[] args) {

        LiftView  view = new LiftView();
        Passenger passenger = view.createPassenger();
        int  fromFloor = passenger.getStartFloor();
        int    toFloor = passenger.getDestinationFloor();

        passenger.begin();                        // walk in (from left)
        if (fromFloor != 0) {
            view.moveLift(0, fromFloor);
        }
        view.openDoors(fromFloor);
        passenger.enterLift();                    // step inside

        view.closeDoors();
        view.moveLift(fromFloor, toFloor);   // ride lift
        view.openDoors(toFloor);

        passenger.exitLift();                     // leave lift
        passenger.end();                          // walk out (to the right)
    }
}
