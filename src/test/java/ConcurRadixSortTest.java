import java.util.*;

public class ConcurRadixSortTest {

    public static void main(String[] args) {
        ArrayList<Integer> randomList = randomList();

        int bufferSize = 250;
        int threads = 4;
        int batchSize = 250;

        ConcurRadixSort sorter = new ConcurRadixSort(bufferSize, threads, batchSize);

        long startTime = System.nanoTime();

        ArrayList<Integer> result = sorter.radixSort(randomList);

        long duration = (System.nanoTime() - startTime) / 1000000;

        System.out.println(String.format("%s threads: %s ms = %s", threads, duration, result));
    }

    private static ArrayList<Integer> randomList() {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int value = rand.nextInt(100000);
            list.add(value);
        }

        return list;
    }

}
