import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class IterableAndIterator {

    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node() {

        }

        Node(int data) {
            this.data = data;
        }
    }

    public static Node construct(int[] arr){
        Stack<Node> st = new Stack<>();
        Node root = null;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node node = new Node();
                node.data = arr[i];
                if (st.size() == 0) {
                    root = node;
                } else {
                    st.peek().children.add(node);
                }
                st.push(node);
            }
        }

        return root;
    }

    public static void displayNode(Node node) {
        // display(10) -> self(10) + display(20) + display(30) + display(40)

        System.out.print(node.data + " -> ");
        for (Node child : node.children) {
            System.out.print(child.data + ", ");
        }
        System.out.println(".");

        for (Node child : node.children) {
            displayNode(child);
        }
    }

    public static void main(String[] args) {
        //generic tree contructor-
        int[] arr = { 10, 20, 40, -1, 50, -1, -1, 30, 80, -1, 100, -1, -1, 40, 60, -1, -1, -1 };
        Node root = construct(arr);
        
        // displayNode(root);


        GenericTree gt = new GenericTree(root);
        for (int val : gt) {    // Syntactical sugar dependent upon iterable
             System.out.println(val);
        }

        Iterator<Integer> gti = gt.iterator();   //the above for loop gets converted to below while loop
        while (gti.hasNext()) {
            System.out.println(gti.next());
        }
    }


    public static class GenericTree implements Iterable<Integer> {
        Node root;

        GenericTree(Node root) {
            this.root = root;
        }

        @Override
        public Iterator<Integer> iterator() {
            Iterator<Integer> obj = new GtPreIterator(root);
            return obj;
        }
    }

    public static class GtPreIterator implements Iterator<Integer> {
        Integer nval;  //next value
        Stack<Pair> st;

        public GtPreIterator(Node root) {
            st= new Stack<>();
            st.push(new Pair(root, -1));
            next();   // for initialization setting nval to root.data
        }

        @Override
        public boolean hasNext() {
            if (nval == null)
                return false;
            else
                return true;
        }

        @Override
        public Integer next() {
            Integer curr=nval;  // current value


            nval=null;  // moves nval forward or set it to null if not possible
            while (st.size() > 0) {
                Pair p = st.peek();
                if (p.state == -1) {
                    nval=p.node.data;  // set nval to next
                    p.state = 0;
                    break;
                } else if (p.state == p.node.children.size()) {
                    st.pop();
                } else {
                    Node child = p.node.children.get(p.state);
                    st.push(new Pair(child, -1));
                    p.state++;
                }
            }

            return curr;
        }
    }

    private static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

}
