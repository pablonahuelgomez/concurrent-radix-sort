import java.util.ArrayList;

class ListBuffer {
    private ArrayList<Integer> list;
    private boolean updated;

    ListBuffer(ArrayList<Integer> list) {
        this.list = list;
        this.updated = true;
    }

    synchronized ArrayList<Integer> pop() {
        try {
            while(!updated) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        updated = false;

        return list;
    }

    synchronized void push(ArrayList<Integer> list) {
        this.list = list;
        this.updated = true;

        notify();
    }
}
