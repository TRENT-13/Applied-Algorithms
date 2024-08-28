import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // resize the underlying array holding the elements
    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (size == queue.length) resize(2 * queue.length);
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int index = StdRandom.uniformInt(size);
        Item item = queue[index];
        queue[index] = queue[size - 1]; // Swap with the last item
        queue[size - 1] = null; // Avoid loitering
        size--;
        if (size > 0 && size == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        int index = StdRandom.uniform(size);
        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private final Item[] copy;
        private int current;

        public RandomizedIterator() {
            copy = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copy[i] = queue[i];
            }
            StdRandom.shuffle(copy);
            current = 0;
        }

        public boolean hasNext() {
            return current < copy.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        System.out.println("Is empty? " + rq.isEmpty());
        rq.enqueue(6);
        rq.enqueue(4234);
        rq.enqueue(252);
        System.out.println("Size: " + rq.size());
        System.out.println("Sample: " + rq.sample());
        System.out.println("Dequeue: " + rq.dequeue());
        System.out.println("Size after dequeue: " + rq.size());
        for (int i : rq) {
            System.out.println(i);
        }
    }
}
