import java.util.*;

public class SplitTask implements Runnable {
    private final List<Integer> batch;
    private SplitBuffer splitBuffer;
    private int bit;
    private int priority;

    SplitTask(List<Integer> batch, SplitBuffer splitBuffer, int bit, int priority) {
        this.batch = batch;
        this.splitBuffer = splitBuffer;
        this.bit = bit;
        this.priority = priority;
    }

    @Override
    public synchronized void run() {
        ArrayList[] tuple = split();
        splitBuffer.push(priority, tuple);
    }

    private ArrayList[] split() {
        ArrayList<Integer> zeros = new ArrayList<>();
        ArrayList<Integer> ones = new ArrayList<>();
        int mask = 1 << bit;

        for (Integer element : batch) {
            if ((element & mask) != 0) {
                ones.add(element);
            } else {
                zeros.add(element);
            }
        }

        return new ArrayList[]{zeros, ones};
    }
}
