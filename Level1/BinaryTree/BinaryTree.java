package BinaryTree;

import BinaryTree.BinaryTreeExplained.*;
import java.util.*;

public class BinaryTree {
    public static int size(Node node) {
        if (node == null)
            return 0;

        return size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;

        return sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int max = node.data;

        max = Math.max(max, Math.max(max(node.left), max(node.right)));

        return max;
    }

    public static int height(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;
        int h = -1;

        h = Math.max(h, Math.max(height(node.left), height(node.right)));

        return h + 1;
    }

    public static int size1(Node node) {
        int sz = 1;

        if (node.left != null)
            sz += size(node.left);
        if (node.right != null)
            sz += size(node.right);

        return sz;
    }

    public static int sum1(Node node) {
        int sum = node.data;

        if (node.left != null)
            sum += sum(node.left);
        if (node.right != null)
            sum += sum(node.right);

        return sum;
    }

    public static int max1(Node node) {
        int max = node.data;

        if (node.left != null)
            max = Math.max(max, max(node.left));
        if (node.right != null)
            max = Math.max(max, max(node.right));

        return max;
    }

    public static int height1(Node node) {
        int h = -1;

        if (node.left != null)
            h = Math.max(h, height(node.left));
        if (node.right != null)
            h = Math.max(h, height(node.right));

        return h + 1;
    }

    public static void traversal(Node node) {

        System.out.println(node.data + " in pre ");
        if (node.left != null)
            traversal(node.left);
        System.out.println(node.data + " in between ");
        if (node.right != null)
            traversal(node.right);
        System.out.println(node.data + " in post ");
    }

    public static void levelOrder(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        while (q.size() > 0) {
            int s = q.size();

            for (int i = 1; i <= s; i++) {
                Node rem = q.remove();
                System.out.print(rem.data + " ");
                if (rem.left != null)
                    q.add(rem.left);
                if (rem.right != null)
                    q.add(rem.right);
            }
            System.out.println();
        }
    }

    public static void iterativePrePostInTraversal(Node node) {

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, 1));

        String pre = "";
        String in = "";
        String post = "";

        while (st.size() > 0) {
            Pair p = st.peek();

            if (p.state == 1) {
                pre += p.node.data + "  ";
                p.state++;
                if (p.node.left != null)
                    st.push(new Pair(p.node.left, 1));

            } else if (p.state == 2) {
                in += p.node.data + "  ";
                p.state++;
                if (p.node.right != null)
                    st.push(new Pair(p.node.right, 1));

            } else {
                post += p.node.data + "  ";
                st.pop();
            }
        }

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);

    }

    public static boolean find(Node node, int data) {
        if (node.data == data)
            return true;

        if (node.left != null)
            if (find(node.left, data))
                return true;
        if (node.right != null)
            if (find(node.right, data))
                return true;

        return false;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        if (node.data == data) {
            ArrayList<Integer> bal = new ArrayList<>();
            bal.add(node.data);
            return bal;
        }

        ArrayList<Integer> al = new ArrayList<>();
        if (node.left != null) {
            al = nodeToRootPath(node.left, data);
            if (al.size() > 0) {
                al.add(node.data);
                return al;
            }
        }
        if (node.right != null) {
            al = nodeToRootPath(node.right, data);
            if (al.size() > 0) {
                al.add(node.data);
                return al;
            }
        }

        return al;
    }

    public static ArrayList<Integer> nodeToRootPath1(Node node, int data) { // wrong-identify the mistake
        if (node.data == data) {
            ArrayList<Integer> bal = new ArrayList<>();
            bal.add(node.data);
            return bal;
        }

        ArrayList<Integer> al = new ArrayList<>();
        if (node.left != null) {
            al = nodeToRootPath(node.left, data);
        }
        if (node.right != null) {
            al = nodeToRootPath(node.right, data);
        }

        if (al.size() > 0) {
            al.add(node.data);
            return al;
        }

        return al;
    }

    public static void printKLevelsDown(Node node, int k) {
        if (k == 0) {
            System.out.println(node.data);
            return;
        }
        if (node.left != null)
            printKLevelsDown(node.left, k - 1);
        if (node.right != null)
            printKLevelsDown(node.right, k - 1);
    }

    public static ArrayList<Node> nodeToRootPath1(Node node, int data) {
        if (node.data == data) {
            ArrayList<Node> bal = new ArrayList<>();
            bal.add(node);
            return bal;
        }

        ArrayList<Node> al = new ArrayList<>();
        if (node.left != null) {
            al = nodeToRootPath1(node.left, data);
            if (al.size() > 0) {
                al.add(node);
                return al;
            }
        }
        if (node.right != null) {
            al = nodeToRootPath1(node.right, data);
            if (al.size() > 0) {
                al.add(node);
                return al;
            }
        }

        return al;
    }

    public static void printKLevelsDown1(Node node, int k, Node blocker) {
        if (node == blocker)
            return;
        if (k == 0) {
            System.out.println(node.data);
            return;
        }
        if (node.left != null)
            printKLevelsDown1(node.left, k - 1, blocker);
        if (node.right != null)
            printKLevelsDown1(node.right, k - 1, blocker);
    }

    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> path = nodeToRootPath1(node, data);

        for (int i = 0; i < path.size() && i <= k; i++) {
            Node blocker = i == 0 ? null : path.get(i - 1);
            printKLevelsDown1(path.get(i), k - i, blocker);
        }
    }

    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi) {
        path += node.data + " ";
        sum += node.data;

        if (node.left == null && node.right == null) {
            if (sum >= lo && sum <= hi)
                System.out.println(path);
            return;
        }

        if (node.left != null)
            pathToLeafFromRoot(node.left, path, sum, lo, hi);
        if (node.right != null)
            pathToLeafFromRoot(node.right, path, sum, lo, hi);
    }

    public static Node createLeftCloneTree(Node node) {
        if (node == null)
            return null;

        Node leftCloneTree = createLeftCloneTree(node.left);
        Node leftNode = new Node(node.data, leftCloneTree, null);
        node.left = leftNode;
        createLeftCloneTree(node.right);

        return node;
    }

    public static Node transBackFromLeftClonedTree(Node node) {
        if (node == null)
            return null;

        Node nleft = transBackFromLeftClonedTree(node.left.left);

        node.left.left = null;
        node.left = nleft;

        transBackFromLeftClonedTree(node.right);

        return node;
    }

    public static void printSingleChildNodes(Node node, Node parent) {
        if (node == null)
            return;

        if ((parent != null && parent.left == null && parent.right == node)
                || (parent != null && parent.left == node && parent.right == null)) {
            System.out.println(node.data);
            return;
        }

        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }

    public static Node removeLeaves(Node node) {
        if (node == null)
            return null;
        if (node.left == null && node.right == null)
            return null;

        Node leftNewRoot = removeLeaves(node.left);
        Node rightNewRoot = removeLeaves(node.right);

        node.left = leftNewRoot;
        node.right = rightNewRoot;

        return node;
    }

    // inefficient- O(n2)
    public static int diameter2(Node node) { // max dia can be fully on the left side or right side or on both sides
                                             // passing through root
        if (node == null)
            return 0;

        int leftd = diameter2(node.left);
        int rightd = diameter2(node.right);

        int myd = height(node.left) + height(node.right) + 2; // recursion in post order

        return Math.max(myd, Math.max(leftd, rightd));
    }

    public static class HtDiaPair {
        int ht;
        int dia;
    }

    // efficient- O(n)
    public static HtDiaPair diameter1(Node node) { // returns dia
        if (node == null) {
            HtDiaPair mhtdp = new HtDiaPair();
            mhtdp.ht = -1;
            mhtdp.dia = 0;
            return mhtdp;
        }

        HtDiaPair lhtdp = diameter1(node.left); // returns max distance btw two nodes on LHS
        HtDiaPair rhtdp = diameter1(node.right); // returns max distance btw two nodes on RHS

        HtDiaPair mhtdp = new HtDiaPair();
        mhtdp.ht = Math.max(lhtdp.ht, rhtdp.ht) + 1;
        mhtdp.dia = Math.max(lhtdp.ht + rhtdp.ht + 2, Math.max(lhtdp.dia, rhtdp.dia));

        return mhtdp;
    }

    static int tilt = 0;

    public static int tilt(Node node) { // return sum and changes tilt
        if (node == null)
            return 0;

        int ls = tilt(node.left);
        int rs = tilt(node.right);
        tilt += Math.abs(ls - rs);

        int sum = ls + rs + node.data;

        return sum;
    }

    public static class BST {
        boolean isbst;
        int min;
        int max;
    }

    public static BST isBST(Node node) {
        if (node == null) {
            BST bst = new BST();
            bst.isbst = true;
            bst.min = Integer.MAX_VALUE;
            bst.max = Integer.MIN_VALUE;
            return bst;
        }

        BST lbst = isBST(node.left);
        BST rbst = isBST(node.right);

        boolean isnodebst = node.data > lbst.max &&
                node.data < rbst.min;

        BST mybst = new BST();
        mybst.isbst = lbst.isbst && rbst.isbst && isnodebst;
        mybst.min = Math.min(node.data, Math.min(lbst.min, rbst.min));
        mybst.max = Math.max(node.data, Math.max(lbst.max, rbst.max));

        return mybst;
    }



    public static class Bpair {
        boolean tb; // tb-treebalance
        int ht;
    }

    public static Bpair isBalanced(Node node) {
        if (node == null) {
            Bpair bp = new Bpair();
            bp.tb = true;
            bp.ht = 0;
            return bp;
        }

        Bpair lbp = isBalanced(node.left);
        Bpair rbp = isBalanced(node.right);

        boolean nb = Math.abs(lbp.ht - rbp.ht) < 2; // nodebalance

        Bpair mybp = new Bpair();
        mybp.tb = nb && lbp.tb && rbp.tb;
        mybp.ht = Math.max(lbp.ht, rbp.ht) + 1;

        return mybp;
    }

    static boolean flag = true; // isBalanced

    public static int isBalanced1(Node node) { // returns height
        if (node == null) {
            return 0;
        }

        int lh = isBalanced1(node.left);
        int rh = isBalanced1(node.right);

        int gap = Math.abs(lh - rh);

        if (gap > 1) {
            flag = false;
        }

        int ht = Math.max(lh, rh) + 1;

        return ht;
    }



    public static class LBST {
        boolean isbst;
        int min;
        int max;
        int lgbst; // largest bst in it 
        int lgbstsize; //  largest bst'size in it 
    }
    
    public static BST largestBST(Node node) {
        if (node == null) {
            LBST bst = new LBST();
            bst.isbst = true;
            bst.min = Integer.MAX_VALUE;
            bst.max = Integer.MIN_VALUE;
            bst.lgbst = 0;
            bst.lgbstsize = 0;
            return bst;
        }
    
        LBST lbst = largestBST(node.left);
        LBST rbst = largestBST(node.right);
    
        boolean isnodebst = node.data > lbst.max &&
                node.data < rbst.min;
    
        LBST mybst = new LBST();
        mybst.isbst = lbst.isbst && rbst.isbst && isnodebst;
        mybst.min = Math.min(node.data, Math.min(lbst.min, rbst.min));
        mybst.max = Math.max(node.data, Math.max(lbst.max, rbst.max));
        
        if(mybst.isbst){
           mybst.lgbst = node.data;
           mybst.lgbstsize = lbst.lgbstsize + rbst.lgbstsize + 1;
        }else if(lbst.lgbstsize >= rbst.lgbstsize){
            mybst.lgbst = node.left.data;
            mybst.lgbstsize = lbst.lgbstsize;
        }else{
            mybst.lgbst = node.right.data;
            mybst.lgbstsize = rbst.lgbstsize;
        }
    
        return mybst;
    }
    


}