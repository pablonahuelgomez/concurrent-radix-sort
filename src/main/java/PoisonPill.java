public class PoisonPill implements Runnable {
    public void run() {
        throw new PoisonException("Thread Dying");
    }

    private class PoisonException extends RuntimeException {
        public PoisonException(String message) {
            super(message);
        }
    }
}
