

public class PQ
{
	private LargeDepositor[] heap; // the heap to store data in
	private int size; // current size of the queue
	private static final int AUTOGROW_COEF = 2; // default auto grow
	private static final int DEFAULT_SIZE = 4;
	private Type currentType;

	public enum Type
	{
		MIN, MAX
	}

	/**
	 * Queue constructor
	 *
	 */
	public PQ(int capacity, Type type)
	{
		currentType = type;
		this.heap = new LargeDepositor[capacity + 1];
		this.size = 0;
	}

	public PQ(Type type)
	{
		this(DEFAULT_SIZE, type);
	}

	boolean isEmpty()
	{
		return size == 0;
	}

	int size()
	{
		return size;
	}

	/**
	 * Inserts the specified element into this priority queue.
	 *
	 * @param largeDepositor
	 */
	public void insert(LargeDepositor largeDepositor)
	{ // System.out.println((int) item);
		// Check available space
		if (size == Math.round(0.75 * (heap.length - 1)))
			grow();

		// Place item at the next available position

		heap[++size] = largeDepositor;

		// Let the newly added item swim
		swim(size);
	}

	/*
	 * We could create max, min that wοuld get data from peek and getMax, getMin
	 * that would get data from getHead according to the type of heap, but it is
	 * preferred to use generic names in order to avoid creating multiple methods.
	 * 
	 */
	/**
	 * Retrieves, but does not remove, the head of this queue, or returns null if
	 * this queue is empty.
	 *
	 * @return the head of the queue
	 */
	public LargeDepositor peek()
	{
		// Ensure not empty
		if (isEmpty())
			return null;

		// return root without removing
		return heap[1];
	}

	/**
	 * Retrieves and removes the head of this queue, or returns null if this queue
	 * is empty.
	 *
	 * @return the head of the queue
	 */
	public LargeDepositor getHead()
	{
		// Ensure not empty
		if (isEmpty())
			return null;

		// Keep a reference to the root item
		LargeDepositor root = heap[1];

		// Replace root item with the one at rightmost leaf
		heap[1] = heap[size];

		// Dispose the rightmost leaf
		size--;

		// Sink the new root element
		sink(1);
		// Return the int removed
		return root;
	}

	/**
	 * Helper function to swim largeDepositor to the top
	 *
	 * @param i the index of the LargeDepositor to swim
	 */
	private void swim(int i)
	{
		// if i is root (i==1) return
		if (i == 1)
			return;

		// find parent
		int parent = i / 2;

		// compare parent with child i
		while (i != 1 && (currentType == Type.MIN && heap[i].CompareTo(heap[parent])
				|| (currentType == Type.MAX && heap[parent].CompareTo(heap[i]))))
		{
			swap(i, parent);
			i = parent;
			parent = i / 2;

		}
		return;
	}

	/**
	 * Helper function to sink LargeDepositor to the bottom
	 *
	 * @param i the index of the LargeDepositor to sink
	 */
	private void sink(int i)
	{
		// determine left, right child
		int left = 2 * i;
		int right = left + 1;

		// if 2*i > size, node i is a leaf, return
		if (left > size)
			return;

		// while haven't reached the leaves
		while (left <= size)
		{
			int min = left;
			int max = left;
			int currentChild = left;
			if (right <= size)
			{
				if ((currentType == Type.MIN && heap[right].CompareTo(heap[left]))
						|| (currentType == Type.MAX && heap[left].CompareTo(heap[right])))
				{
					min = right;
					max = right;
					currentChild = right;
				}
			}

			// If the heap condition holds, stop. Else swap and go on.
			// heap[i] is parent
			if ((currentType == Type.MIN && heap[i].CompareTo(heap[min]))
					|| (currentType == Type.MAX && heap[max].CompareTo(heap[i])))
				return;
			else
			{
				swap(i, currentChild);
				i = currentChild;
				left = i * 2;
				right = left + 1;
			}
		}
		return;
	}

	/**
	 * Helper function to swap two elements in the heap
	 *
	 * @param i the first element's index
	 * @param j the second element's index
	 */
	private void swap(int i, int j)
	{
		LargeDepositor tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	/**
	 * Helper function to grow the size of the heap
	 */
	private void grow()
	{
		LargeDepositor[] newHeap = new LargeDepositor[heap.length * AUTOGROW_COEF];

		// copy array
		// (notice: in the priority queue, elements are located in the array slots with
		// positions in [1, size])
		for (int i = 0; i <= size; i++)
		{
			newHeap[i] = heap[i];
		}

		heap = newHeap;
	}

}