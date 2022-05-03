package BinarySearchTree;


public class BinarySearchTreeExplained {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    public static Node construct(int[] arr, int low, int high){
          if(low > high) return null;

          int mid = (low+high)/2;
          
          Node lc = construct(arr, low, mid-1);
          Node rc = construct(arr, mid+1, high); 

          Node node=new Node(arr[mid], lc, rc);
          return node;
    }

    public static void display(Node node) {
        String s = "";

        s += (node.left == null) ? " . " : node.left.data;
        s += " -> " + node.data + " <- ";
        s += (node.right == null) ? " . " : node.right.data;

        System.out.println(s);
        if (node.left != null)  display(node.left);
        if (node.right != null) display(node.right);
    }

    public static void main(String[] args) {
        int[] arr={12,25,37,50,62,75,87};
        Node root = construct(arr, 0, arr.length-1);
        display(root);
    }
}
