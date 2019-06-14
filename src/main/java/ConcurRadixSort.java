import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class ConcurRadixSort {
    private final ThreadPool threadPool;
    private int batchSize;

    ConcurRadixSort(int bufferSize, int threads, int batchSize) {
        this.threadPool = new ThreadPool(bufferSize, threads);
        this.batchSize = batchSize;
    }

    ArrayList<Integer> radixSort(ArrayList<Integer> list) {
        ListBuffer listBuffer = new ListBuffer(list);

        for (int bit = 0; bit < 32; bit++) {
            Collection<List<Integer>> batches = takeBatches(listBuffer.pop());
            SplitBuffer splitBuffer = new SplitBuffer(batches.size());
            int expectedPosition = 0;

            for (List<Integer> batch : batches) {
                threadPool.launch(new SplitTask(batch, splitBuffer, bit, expectedPosition));
                expectedPosition++;
            }

            threadPool.launch(new MergeTask(splitBuffer, listBuffer));
        }

        threadPool.stop();

        return listBuffer.pop();
    }

    private Collection<List<Integer>> takeBatches(ArrayList<Integer> list) {
        AtomicInteger counter = new AtomicInteger();
        return list.stream()
            .collect(Collectors.groupingBy(x -> counter.getAndIncrement() / batchSize))
            .values();
    }

}
