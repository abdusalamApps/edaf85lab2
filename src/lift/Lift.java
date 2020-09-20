package lift;

public class Lift {
    private LiftView liftView;
    private boolean doorOpen;

    public Lift(LiftView liftView) {
        this.liftView = liftView;
        this.doorOpen = true;
    }

    public synchronized void moveLift(int from, int to) {
        while (doorOpen) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        liftView.moveLift(from, to);
        doorOpen = false;
        notifyAll();
    }

    public synchronized void openDoors(int from) {
        while (doorOpen) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        liftView.openDoors(from);
        doorOpen = false;
        notifyAll();
    }
}
