import java.util.*;

class SplitBuffer {
    private ArrayList<ArrayList[]> tuples;
    private final int size;

    SplitBuffer(int size) {
        tuples = new ArrayList<>();

        this.size = size;
    }

    synchronized void push(int expectedPosition, ArrayList[] tuple) {
        while(tuples.size() < expectedPosition) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tuples.add(expectedPosition, tuple);

        notifyAll();
    }

    synchronized ArrayList<Integer> pop() {
        while(!isFull()) {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        ArrayList<Integer> result = new ArrayList<>();

        tuples.stream().map(tuple -> tuple[0]).forEach(result::addAll);
        tuples.stream().map(tuple -> tuple[1]).forEach(result::addAll);

        notifyAll();

        return result;
    }

    private boolean isFull() {
        return size == tuples.size();
    }
}
