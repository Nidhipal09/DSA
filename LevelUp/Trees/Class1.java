import java.util.ArrayDeque;
import java.util.Stack;

import org.w3c.dom.Node;

public class Trees {
    void levelOrderLinewise(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        // Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(new Node(-1));

        while (q.size() > 0) {
            Node node = q.remove();

            if (node.data == -1) {
                if (q.size() > 0) {
                    System.out.println();
                    q.add(new Node(-1));
                }

            } else {
                System.out.print(node.data + " ");

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

            }
        }

    }

    void ReverselevelOrderLinewise(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        Stack<StringBuilder> st = new Stack<>();
        // Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(new Node(-1));

        StringBuilder sb = new StringBuilder();
        while (q.size() > 0) {
            Node node = q.remove();

            if (node.data == -1) {
                st.push(sb);
                sb = new StringBuilder();
                if (q.size() > 0) {
                    q.add(new Node(-1));
                }

            } else {
                sb.append(" " + node.data);

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

            }
        }

        while (st.size() > 0) {
            System.out.println(st.pop());
        }

    }

    void ReverselevelOrderLinewise2(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        // Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(new Node(-1));

        StringBuilder sb = new StringBuilder();
        while (q.size() > 0) {
            Node node = q.remove();

            if (node.data == -1) {
                if (q.size() > 0) {
                    sb.append("@");
                    q.add(new Node(-1));
                }

            } else {
                sb.append(node.data+" ");

                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

            }
        }

        String[] s = sb.toString().split(" @");
        for(int i=s.length-1; i>=0; i--){
            System.out.println(s[i]);
        }

    }

    
    void ReverselevelOrderLinewise1(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        Stack<Node> st = new Stack<>();
        // Queue<Node> q = new LinkedList<>();

        q.add(root);
        q.add(new Node(-1));

        while (q.size() > 0) {
            Node node = q.remove();

            if (node.data == -1) {
                if (q.size() > 0) {
                    st.push(new Node(-1));
                    q.add(new Node(-1));
                }

            } else {
                st.push(node);
                
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

            }
            
            
        }

        while (st.size()>0) {
            
           StringBuilder sb = new StringBuilder();
           while(st.size()>0 && st.peek().data!=-1){
               sb.insert(0, st.pop().data+" ");
           }
           System.out.println(sb);
           if(st.size()>0) st.pop();
        }

    }

    // https://practice.geeksforgeeks.org/problems/reverse-level-order-traversal/1/
    // leetcode- 107


    
    // left,right,top,bottom views - gfg

    

}