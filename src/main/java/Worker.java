public class Worker extends Thread {
    private final Buffer buffer;

    Worker(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            Runnable task = (Runnable) buffer.pop();
            task.run();
        }
    }
}
