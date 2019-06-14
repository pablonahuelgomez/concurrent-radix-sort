class ThreadPool {
    private final Buffer buffer;
    private final int threads;

    ThreadPool(int bufferSize, int threads) {
        this.buffer = new Buffer(bufferSize);
        this.threads = threads;

        for(int n = 0; n < threads; n++) {
            new Worker(buffer).start();
        }
    }

    void launch(Runnable task) {
        buffer.push(task);
    }

    void stop() {
        for (int n = 0; n < threads; n++) {
            buffer.push(new PoisonPill());
        }
    }
}
