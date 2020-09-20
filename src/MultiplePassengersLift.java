import lift.Lift;
import lift.LiftView;

public class MultiplePassengersLift {
    public static void main(String[] args) {
        LiftView liftView = new LiftView();
        Lift lift = new Lift(liftView);
/*
        PassengerThread passengerThread = new PassengerThread(liftView);
        passengerThread.start();
*/
        PassengerThread[] passengerThreads = new PassengerThread[20];
        for (int i = 0; i < passengerThreads.length; i++) {
            passengerThreads[i] = new PassengerThread(liftView, lift);
        }
        for (int i = 0; i < passengerThreads.length; i++) {
            passengerThreads[i].start();
        }
    }
}
