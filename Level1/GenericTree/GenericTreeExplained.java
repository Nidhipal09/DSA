import java.util.ArrayList;
import java.util.Stack;

public class GenericTreeExplained {
    public static void main(String[] args) {
        // generic tree contructor-
        int[] arr = { 10, 20, 40, -1, 50, -1, -1, 30, 80, -1, 100, -1, -1, 40, 60, -1, -1, -1 };
        Node root = construct(arr);

        // displayNode(root);
    }

    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node() {

        }

        Node(int data) {
            this.data = data;
        }
    }

    public static Node construct(int[] arr) {
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



    static int size = 0, height = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void multisolver(Node node, int depth) {

        size++;
        height = Math.max(height, depth);
        max = Math.max(max, node.data);
        min = Math.min(min, node.data);

        for (Node child : node.children) {
            multisolver(child, depth + 1);
        }

    }

}
