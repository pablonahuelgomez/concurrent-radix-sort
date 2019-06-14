import java.util.*;

public class ConcurRadixSortTest {

    public static void main(String[] args) {
        ArrayList<Integer> randomList = fillList(new ArrayList<>());

        int bufferSize = 10;
        int threads = 1;
        int batchSize = 10;

        ConcurRadixSort sorter = new ConcurRadixSort(bufferSize, threads, batchSize);

        long startTime = System.nanoTime();

        ArrayList<Integer> result = sorter.radixSort(randomList);

        long duration = (System.nanoTime() - startTime) / 1000000;

        System.out.println(String.format("%s threads: %s ms = %s> ", threads, duration, result));
    }

    private static ArrayList<Integer> fillList(ArrayList<Integer> list) {
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int value = rand.nextInt(100000);
            list.add(value);
        }

        return list;
    }

}
