import java.util.LinkedList;

class Buffer {

    private LinkedList elements;
    private final int size;

    Buffer(int size) {
        elements = new LinkedList<>();
        this.size = size;
    }

    synchronized void push(Object element) {
        while(isFull()) {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        elements.add(element);

        notifyAll();
    }

    private boolean isFull() {
        return size == elements.size();
    }

    synchronized Object pop() {
        while(isEmpty()) {
            try {
                wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        Object element = elements.remove();

        notifyAll();

        return element;
    }

    private boolean isEmpty() {
        return elements.isEmpty();
    }
}
