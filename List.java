import java.util.Comparator;

/**
 * Single-link List. Uses {@link ListNode} for list Listnodes.
 */
public class List<T> {

    private ListNode<T> head = null;
    private ListNode<T> tail = null;
    private int size=0;
    /**
     * Default constructor
     */
    public List() {
    }

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    public void insertAtFront(T data) {
        ListNode<T> n = new ListNode<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
        ++size;
    }

    /**
     * Inserts the data at the end of the list
     *
     * @param data the inserted item
     */
    public void insertAtBack(T data) {
        ListNode<T> n = new ListNode<>(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            tail.setNext(n);
            tail = n;
        }
        ++size;
    }

    /**
     * Returns and removes the data from the list head
     *
     * @return the data contained in the list head
     * @throws EmptyListException if the list is empty
     */
    public T removeFromFront(){
        if (isEmpty())
            System.out.println("Nothing to remove");

        T data = head.getData();

        if (head == tail)
            head = tail = null;
        else
            head = head.getNext();

        --size;
        return data;
    }

    /**
     * Returns and removes the data from the list tail
     *
     * @return the data contained in the list tail
     * @throws EmptyListException if the list is empty
     */
    public T removeFromBack(){
        if (isEmpty())
            System.out.println("Nothing to remove");

        T data = tail.getData();

        if (head == tail)
            head = tail = null;
        else {
            ListNode<T> iterator = head;
            while (iterator.getNext() != tail)
                iterator = iterator.getNext();

            iterator.setNext(null);
            tail = iterator;
        }
        --size;
        return data;
    }

    /**
     * Returns list as String
     */
    public String toString() {
        if (isEmpty()) {
            return "List is empty :(";
        }

        ListNode<T> current = head;

        StringBuilder ret = new StringBuilder();

        while (current != null) {
            ret.append(current.data.toString());

            if (current.getNext() != null)
                ret.append("\n\n");

            current = current.next;
        }

        return ret.toString();
    }

    /**
     * Sorts the list
     *
     * @param comparator
     */
    public void sort(Comparator<T> comparator) {
        // No need to sort if list is empty or has a single element
        if (head == null || head == tail)
            return;

        ListNode<T> newHead = null;
        ListNode<T> newTail = null;

        while (head != null) {
            // get next item
            ListNode<T> swap = head;

            // move head
            head = head.getNext();

            // move swap to new-sorted list
            if (newHead == null) {
                newHead = newTail = swap;
                swap.setNext(null);
            } else {
                ListNode<T> prev = null;
                ListNode<T> iterator = newHead;

                // iterate newList until we get to a point where our data is smaller or reach the end
                while (iterator != null && comparator.compare(iterator.getData(), swap.getData()) >= 0) {
                    prev = iterator;
                    iterator = iterator.getNext();
                }

                // reached the point where we should place the Listnode

                // if prev == null then its the head of the list
                if (prev == null)
                    newHead = swap;
                else
                    prev.setNext(swap);

                // if iterator == null then its the tail of the list
                swap.setNext(iterator);
                if(iterator == null)
                    newTail = swap;
            }
        }

        head = newHead;
        tail = newTail;
    }

    public int size()
    {
        return size; 
    }
}
