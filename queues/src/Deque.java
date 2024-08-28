import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // Node class to store each element
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    // Construct an empty deque
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    // Is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the number of items on the deque
    public int size() {
        return size;
    }

    // Add the item to the front
    public void addFirst(Item item) {
        if (item == null) { // checking edge case
            throw new IllegalArgumentException("Null item is not allowed");
        }
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        first.prev = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        size++;
    }

    // Add the item to the back
    public void addLast(Item item) {
        if (item == null) { // checking edge case
            throw new IllegalArgumentException("Null item is not allowed");
        }
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        size++;
    }

    // Remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {  // checking edge case
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if (isEmpty()) {
            last = null;
        } else {
            first.prev = null;
        }
        return item;
    }

    // Remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) { // checking edge case
            throw new NoSuchElementException("Deque is empty");
        }
        Item item = last.item;
        last = last.prev;
        size--;
        if (isEmpty()) {
            first = null;
        } else {
            last.next = null;
        }
        return item;
    }

    // Return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // basic iterator implementation for generics
    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // Unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        System.out.println("Deque is empty: " + deque.isEmpty());
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(0);
        System.out.println("Size of deque: " + deque.size());
        System.out.println("Removed first item: " + deque.removeFirst());
        System.out.println("Removed last item: " + deque.removeLast());
        System.out.println("Size of deque: " + deque.size());
        System.out.println("Deque is empty: " + deque.isEmpty());

        // Test iterator
        deque.addFirst(3);
        deque.addLast(4);
        for (int item : deque) {
            System.out.println("Iterator item: " + item);
        }

        // Test corner cases
        try {
            deque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            deque.removeFirst();
            deque.removeFirst();
            deque.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            Iterator<Integer> iter = deque.iterator();
            iter.next();
            iter.next();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            Iterator<Integer> iter = deque.iterator();
            iter.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
