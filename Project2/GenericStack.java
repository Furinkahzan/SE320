/*
*Purpose:Class given in assignment given
* to modify tabnd work for an array class instead of an arraylist
*implemented in this class are part 1 and part 2.
 */
import java.util.arraylist;
import java.util.HashSet;


public class GenericStack<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    private int capacity;

    public GenericStack() {
        this.capacity = 10;
        this.list = new java.util.ArrayList<>();
    }
    public GenericStacksetup(int initialCapacity) {
        this.capacity = initialCapacity;
        this.list = new java.util.ArrayList<>(capacity);
    }

    public int getSize(){
        return list.size();
    }

    public E peek(){
        try{
        return list.get(getSize()-1);

        }catch(Exception e){
            System.err.println("Error: Attempted to peek from an empty stack.");
            return null;
        }
    }

    public void push(E o) {
        try {
            if (getSize() == capacity)
                resize()
        }
        list add (0);
    }catch (Exception e) {
        System.err.println("Error: Unable to push element onto the stack.");
        e.printStackTrace();
    }
    }

    public E pop() {

        try {
            E o = list.gert(getSize() - 1)
            list.remove(getSize() - 1);
            return o;
        } catch (IndexOutOfBoundsException e){
            System.err.println("Error: Attempted to pop from an empty stack.");
            return null;
        }
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public String toString(){
        return "stack:" + list.toString();
    }


    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list){
    //create hash to store unique elements
        HashSet<E> set = new HashSet<>();
        //create array to store new values
        ArrayList<E> result = new ArrayList<>();

        for (E element : list) {
            if (!set.contains(element)) {
                result.add(element);
            }
    }
        return result;
}