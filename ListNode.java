

/**
 * ListListNode represents a list Listnode
 * Each Listnode contains a generic type T as data and a reference to the next Listnode in the list.
 */
public class ListNode<T> {
    protected T data;
    protected ListNode<T> next = null;

    /**
     * Constructor. Sets data
     *
     * @param data the data stored
     * @return
     */
    ListNode(T data) {
        this.data = data;
    }

    /**
     * Returns this Listnode's data
     *
     * @return the reference to Listnode's data
     */
    public T getData() {
        // return data stored in this Listnode
        return data;
    }

    /**
     * Get reference to next Listnode
     *
     * @return the next Listnode
     */
    public ListNode<T> getNext() {
        // get next Listnode
        return next;
    }

    /**
     * Set reference to next Listnode
     *
     * @param next reference
     */
    public void setNext(ListNode<T> next) {
        this.next = next;
    }
}
