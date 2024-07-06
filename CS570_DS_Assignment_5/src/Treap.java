// Name - Yash Deshpande
//CWID - 20025089
import java.util.Random;
import java.util.Stack;

import static java.util.Objects.isNull;

public class Treap<E extends Comparable<E>>{

    private class Node<E> {
        public E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        public Node(E data, int priority) {
            if (isNull(data)) {
                throw new IllegalArgumentException("Data variable empty");
            }
            this.data = data;
            this.priority = priority;
            this.left = left;
            this.right = right;
        }

        public Node<E> rotateRight() {
            Node<E> N = new Node<E>(this.data, this.priority);
            if(this.left!= null){
                if(this.right!= null){
                    N.right = this.right;
                }
                if(this.left.right!=null){
                    N.left = this.left.right;
                }
                this.priority = this.left.priority;
                this.data = this.left.data;
                this.right = N;
                if(this.left.left == null){
                    this.left = null;
                }else{
                    this.left = this.left.left;
                }
            }
            return N;
        }
        public Node<E> rotateLeft() {
            Node<E> M = new Node<E>(this.data, this.priority);
            if(this.right!= null){
                if(this.left!= null){
                    M.left = this.left;
                }
                if(this.right.left!= null){
                    M.right = this.right.left;
                }
                this.priority = this.right.priority;
                this.data = this.right.data;
                this.left = M;
                if(this.right.right == null){
                    this.right = null;
            }else{
                this.right = this.right.right;
            }

        }
            return M;


    }

}   private Random priorityGenerator;
    private Node<E> root;

    public Treap(){
        priorityGenerator = new Random();
        this.root = null;

    }
    public Treap(long seed){
        priorityGenerator = new Random(seed);
        this.root = null;

    }

    boolean add(E key){
        return add(key,priorityGenerator.nextInt());
    }
    private Stack<Node<E>> s=new Stack<Node<E>>();
    boolean add(E key,int priority){
        Node<E> K = new Node<>(key,priority);
        if(root == null){
            root = K;
            return true;
        }
        Node<E> temp = root;
        while(temp!= null){
            s.push(temp);
            if(key.compareTo(temp.data)>0){
                temp = temp.right;
            }
            else{
                temp = temp.left;
            }
        }
        if(key.compareTo(s.peek().data)>0){
            s.peek().right = K;
        }else{
            s.peek().left = K;
        }
        reheap(K);
        return true;
    }
    private void reheap(Node<E> newNode){
        Node<E> current = s.pop();
        while(current!=null && current.priority<newNode.priority){
            if(newNode.data.compareTo(current.data)<0){
                current.rotateRight();
                if(s.isEmpty()){
                    return;
                }
                current = s.pop();
            }else{
                current.rotateLeft();
                if(s.isEmpty()){
                    return;
                }
                current = s.pop();
            }
        }
    }
    public boolean delete(E key){
        if(find(key) == true){
            Node<E> temp = root;
            Node<E> i =null;
            while(temp.data.compareTo(key)!=0){
                i = temp;
                if(temp.data.compareTo(key)>0){
                    temp=  temp.left;
                }else{
                    temp = temp.right;
                }
            }
            while(!(temp.left == null && temp.right == null)){
                i = temp;
                if(temp.left == null){
                    temp = temp.rotateLeft();
                }else if(temp.right == null){
                    temp = temp.rotateRight();
                }else if(temp.left.priority > temp.right.priority){
                    temp = temp.rotateRight();
                }else{
                    temp = temp.rotateLeft();
                }
            }
            if(root.data.compareTo(key)==0 && root.left == null && root.right == null){
                root = null;
            }else if(i.left!= null && i.left.data.compareTo(key)==0){
                i.left = null;
            }else{
                i.right = null;
            }
            return true;
        }
        return false;
    }
    private boolean find(Node<E> root,E key) {
        if(root==null) {
            return false;
        }
        if(key.compareTo(root.data)==0){
            return true;
        }if(key.compareTo(root.data)<0){
            return find(root.left,key);
        }
        return find(root.right,key);
    }
    public boolean find(E key){
        if(key == null){
            throw new IllegalArgumentException("Key variable empty");
        }else{
            return find(root,key);
        }
    }
    private void preorderTraversal(Node<E> rootNode,StringBuilder str,int a){
        for(int j=0;j<a;j++){
            str.append(" ");
        }
        if(rootNode == null){
            str.append("null\n");
        }else{
            str.append("(key="+rootNode.data.toString()+", priority="+rootNode.priority+")\n");
            preorderTraversal(rootNode.left, str, a+1);
            preorderTraversal(rootNode.right, str, a+1);
        }

    }
    public String toString()
    {
        StringBuilder str=new StringBuilder();
        preorderTraversal(root,str,1);
        return str.toString();
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Treap<Integer> testTree=new Treap<Integer>();
        testTree.add(4,19);
        testTree.add(2,31);
        testTree.add(6,70);
        testTree.add(1,84);
        testTree.add(3,12);
        testTree.add(5,83);
        testTree.add(7,26);
        System.out.println("Tree :- :\n"+testTree.toString());


    }

}
