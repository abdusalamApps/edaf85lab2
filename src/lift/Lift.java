package lift;

import java.util.concurrent.Semaphore;

public class Lift {
    private int currentFloor;

    private int fromFloor;
    private int toFloor;

    private boolean moving;
    private int direction;
    private int[] waitEntry;
    private int[] waitExit;
    private int load;

    private Semaphore passengerSemaphore;


    public Lift(Semaphore passengerSemaphore) {
        this.moving = false;
        this.currentFloor = 0;
        this.waitEntry = new int[6];
        this.waitExit = new int[6];
        this.direction = 1;
        this.load = 0;
        this.passengerSemaphore = passengerSemaphore;
    }

    public void makeRide(Passenger passenger) {
        fromFloor = passenger.getStartFloor();
        toFloor = passenger.getDestinationFloor();
    }

    public synchronized void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getFromFloor() {
        return fromFloor;
    }

    public int getToFloor() {
        return toFloor;
    }
}
