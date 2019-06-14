import java.util.ArrayList;

public class MergeTask implements Runnable {
    private SplitBuffer splitBuffer;
    private ListBuffer listBuffer;

    MergeTask(SplitBuffer splitBuffer, ListBuffer listBuffer) {
        this.splitBuffer = splitBuffer;
        this.listBuffer = listBuffer;
    }

    @Override
    public synchronized void run() {
        ArrayList<Integer> partialList = splitBuffer.pop();
        listBuffer.push(partialList);
    }
}
