package lift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Lift {
    private int currentFloor;

    private boolean moving;
    private int direction;
    private int[] waitEntry;
    private int[] waitExit;
    private int load;

    public Lift(Semaphore passengerSemaphore) {
        this.moving = false;
        this.currentFloor = 0;
        this.waitEntry = new int[7];
        this.waitExit = new int[7];
        this.direction = 1;
        this.load = 0;
    }

    public synchronized void enter(Passenger passenger) {
        waitEntry[passenger.getStartFloor()]++;
        notifyAll();
        while (currentFloor != passenger.getStartFloor() || moving || load > 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        load++;
        passenger.enterLift();
        waitEntry[passenger.getStartFloor()]--;
        waitExit[passenger.getDestinationFloor()]++;
        notifyAll();
    }

    public synchronized void exit(Passenger passenger) {
        while (currentFloor != passenger.getDestinationFloor() || moving) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        load--;
        passenger.exitLift();
        waitExit[passenger.getDestinationFloor()]--;
        notifyAll();
    }


/*
    public synchronized boolean openDoors() {
        int wen = waitEntry;
        int wet = waitExit.get(currentFloor);
        return wen > 0 || wet > 0;
    }
*/

/*
    public void addWaitEntry(int floor) {
        waitEntry.put(floor, waitEntry.get(floor) + 1);
    }
*/

/*
    public void addWaitExit(int floor, Passenger passenger) {
        if (waitExit.get(floor) == null) {
            List<Passenger> passengerList = new ArrayList<>();
            passengerList.add(passenger);
            waitExit.put(floor, passengerList);
        } else {
            waitExit.get(floor).add(passenger);
        }
    }
*/

    public synchronized int nextFloor() {
        if (currentFloor > 5)
            direction = -1;
        if (currentFloor < 1)
            direction = 1;
        int nextFloor = currentFloor + direction;
        currentFloor = nextFloor;
        notifyAll();
        return nextFloor;
    }

    public synchronized void waitForPassengers() {
        while ((waitEntry[currentFloor] > 0 && load < 4) || waitExit[currentFloor] > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        notifyAll();
    }

    public synchronized void setMoving(boolean moving) {
        this.moving = moving;
        notifyAll();
    }

    public synchronized void setDirection(int direction) {
        this.direction = direction;
        notifyAll();
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public synchronized int getCurrentFloor() {
        return currentFloor;
    }

}
