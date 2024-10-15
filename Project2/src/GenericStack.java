/*
*Purpose:Class given in assignment given
* to modify tabnd work for an array class instead of an arraylist
*implemented in this class are part 1 and part 2.
 */

import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashSet;

public class GenericStack<E> {
    private ArrayList<E> list;
    private int capacity;

    // Default constructor with initial capacity of 10
    public GenericStack() {
        this.capacity = 10;
        this.list = new ArrayList<>(capacity);
    }

    // Constructor with a user-defined initial capacity
    public GenericStack(int initialCapacity) {
        this.capacity = initialCapacity;
        this.list = new ArrayList<>(capacity);
    }

    // Returns the current size of the stack
    public int getSize() {
        return list.size();
    }

    // Returns the top element without removing it
    public E peek() {
        try {
            return list.get(getSize() - 1);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Attempted to peek from an empty stack.");
            return null;
        }
    }

    // Adds an element to the top of the stack
    public void push(E o) {
        try {
            if (getSize() == capacity) {
                resize(); // Resize if capacity is reached
            }
            list.add(o);
        } catch (Exception e) {
            System.err.println("Error: Unable to push element onto the stack.");
            e.printStackTrace();
        }
    }

    // Removes and returns the top element from the stack
    public E pop() {
        try {
            E o = list.get(getSize() - 1);
            list.remove(getSize() - 1);
            return o;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: Attempted to pop from an empty stack.");
            return null;
        }
    }

    // Checks if the stack is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Converts the stack to a string representation
    @Override
    public String toString() {
        return "Stack: " + list.toString();
    }

    // Resizes the stack capacity when needed
    private void resize() {
        capacity *= 2; // Double the capacity
        ArrayList<E> newList = new ArrayList<>(capacity);
        newList.addAll(list);
        list = newList;
        System.out.println("Stack capacity increased to " + capacity);
    }

    // Static method to remove duplicates from an ArrayList
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        // Create a HashSet to store unique elements
        HashSet<E> set = new HashSet<>();
        // Create an ArrayList to store the result without duplicates
        ArrayList<E> result = new ArrayList<>();

        for (E element : list) {
            // If the element is not in the set, add it to the result and the set
            if (set.add(element)) {
                result.add(element);
            }
        }
        return result;
    }
}