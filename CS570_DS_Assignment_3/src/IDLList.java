import java.util.*;
public class IDLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;
   private class Node<E>{
       E data;
       Node<E> next;
       Node<E> prev;

        Node(E data){
           this.data = data;
           this.next = null;
           this.prev = null;
       }
       Node (E elem, Node<E> prev, Node<E> next){
           data = elem;
           prev = null;
           next = null;
       }
   }
    // Creating an empty doubly Linked list
   public IDLList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<Node<E>>();
   }
    // adds an element at the start of list
    public boolean add (E elem){
       Node<E> a = new Node<E>(elem,null,head);
       a.next = head;
       a.prev = null;
       if(head!=null){
           head.prev = a;
       }
        head = a;
       if(tail == null){
           tail = a;
       }
       size = size + 1;
       indices.add(0,a);
       return true;
    }
    // adds the element at a specific index
    public boolean add (int index, E elem){
       if(index<0 || index > size){
           throw new IllegalArgumentException("Invalid Index");
       }
       Node<E> a = new Node<E>(elem);
       if(index == 0){
          return this.add(elem);

       }else if(index == size){
           return this.append(elem);
       }else{
           Node<E> particular_node = indices.get(index);
           particular_node.prev.next = a;
           a.prev = particular_node.prev;
           particular_node.prev = a;
           a.next = particular_node;
           size = size+1;
           indices.add(index,a);
           return true;


       }
    }
    // returns the size of list
    public int size(){
       return size;
    }
    // Appends an element to the list
    public boolean append(E elem){
       Node<E> new_element = new Node<E>(elem);
       if(head == null){
           head = new_element;
           tail = head;
           size = size+1;
           return indices.add(head);

       }
       else if(head == tail){
           tail = new Node<E>(elem,null,head);
           head.next = tail;
           size = size+1;
           return indices.add(tail);

       }
       tail.next = new Node<E>(elem,null,tail);
       tail = tail.next;
       size = size + 1;
       return indices.add(tail);
    }
    // The below function returns an element on a given index

    public E get(int index){
       if(index<0 || index>size){
           System.out.println("Invalid size");
       }
       Node<E> b = indices.get(index);
       return b.data;
    }
    // The below function will return the first element of Linked list

    public E getHead(){
        if(size == 0){
            throw new IllegalArgumentException("Linkedlist is empty");
        }
        Node<E> c = indices.get(0);
        return c.data;
    }
    // The below function will return the last element of Linked list
    public E getLast(){
       if(size == 0){
           throw new IllegalArgumentException("Linkedlist is empty");
       }
       Node<E> c = indices.get(size-1);
       return c.data;
    }
    // removing the head element
    public E remove(){
        Node<E> c = indices.get(0);
        Node<E> d = indices.get(1);
        d.prev = null;
        head = d;
        size --;
        indices.remove(0);
        return c.data;
    }
    // removing the last index
    public E removeLast(){
        Node<E> c = indices.get(size-1);
        Node<E> d = indices.get(size-2);
        d.next = null;
        tail = d;
        indices.remove(size-1);
        size--;

        return c.data;

    }
    // removing an element on a specific index
    public E removeAt(int index){
       if(index<0 || index>=size){
           System.out.println("Invalid Index");
           throw new IllegalArgumentException("Invalid Index");
       }
       if(size == 0){
           throw new IllegalArgumentException("Size of Linkedlist is 0");
       }
       if(index == size-1){
           return removeLast();
       }
       else if(index == 0){
           return remove();
       }else{
           Node<E> to_del = indices.get(index);
           to_del.prev.next = to_del.next;
           to_del.next.prev = to_del.prev;
           size = size - 1;
           indices.remove(index);
           return to_del.data;
       }

    }
    // removing the given element
    public boolean remove(E elem){
        if(size == 0){
            System.out.println("Error: Linkedlist Empty");
            throw new IllegalArgumentException("Index less than 0");
        }
        if(elem == head.data){
            remove();
            return true;
        }
        if(elem == tail.data){
            removeLast();
            return true;
        }
        int x = 0;
        Node<E> curr = head;
        while(curr!= null){
            if (curr.equals(elem)) {
                curr.prev.next = curr.next;
                curr.next.prev = curr.prev;
                size = size - 1;
                indices.remove(x);
                return true;
            }
            curr = curr.next;
            x=x+1;
        }
        return false;
   }
    // Converting result to string
   public String toString(){
       Node<E> string_node;
       String string_ll = "";
       for(int j = 0; j< size;j++){
           string_node = indices.get(j);
           string_ll = string_ll + string_node.data+" -> ";
       }
       return string_ll;
   }


}

