import lift.Lift;
import lift.LiftView;

import java.util.concurrent.Semaphore;

public class MultiplePassengersLift {
    public static void main(String[] args) {
        Semaphore passengerSemaphore = new Semaphore(0);
        Lift lift = new Lift(passengerSemaphore);
        LiftView liftView = new LiftView();
        PassengerThread passengerThread = new PassengerThread(liftView, lift, passengerSemaphore);
        LiftThread liftThread = new LiftThread(liftView, lift, passengerSemaphore);
        liftThread.start();
        passengerThread.start();
/*
        PassengerThread[] passengerThreads = new PassengerThread[20];
        for (int i = 0; i < passengerThreads.length; i++) {
            passengerThreads[i] = new PassengerThread(liftView, lift);
        }
        for (int i = 0; i < passengerThreads.length; i++) {
            passengerThreads[i].start();
        }
*/
    }
}
