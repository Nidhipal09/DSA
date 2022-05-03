package BinarySearchTree;

import java.util.*;

import BinarySearchTree.BinarySearchTreeExplained.Node;

public class BinarySearchTree {
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
        if (node.right == null)
            return node.data;

        return max(node.right);
    }

    public static int min(Node node) {
        if (node.left == null)
            return node.data;

        return min(node.left);
    }

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;

        if (node.data == data) {
            return true;
        }

        else if (data < node.data) {
            return find(node.left, data);
        }

        else
            return find(node.right, data);
    }

    public static Node add(Node node, int data) {
        if (node == null) {
            return new Node(data, null, null);
        }

        if (data < node.data) {
            node.left = add(node.left, data);
        } else if (data > node.data) {
            node.right = add(node.right, data);
        }

        return node;
    }

    public static Node remove(Node node, int data) {
        if (node == null)
            return null;
        else if (node.data == data) {
            if (node.left == null && node.right == null)
                return null;
            else if (node.left == null && node.right != null)
                return node.right;
            else if (node.left != null && node.right == null)
                return node.left;
            else {

                int lmax = max(node.left);
                node.data = lmax;
                node.left = remove(node.left, lmax);
                return node;

                // or
                // int rmin = min(node.right);
                // node.data = rmin;
                // remove(node.right, rmin);
                // return node;
            }
        }

        if (data < node.data)
            node.left = remove(node.left, data);
        if (data > node.data)
            node.right = remove(node.right, data);

        return node;
    }

    static int sum = 0;

    public static void rwsol(Node node) {

        if (node.right != null)
            rwsol(node.right);

        int temp = node.data;
        node.data = sum;
        sum += temp;

        if (node.left != null)
            rwsol(node.left);

    }

    public static int lca(Node node, int d1, int d2) {
        if (d1 < node.data && d2 < node.data)
            return lca(node.left, d1, d2);
        else if (d1 > node.data && d2 > node.data)
            return lca(node.right, d1, d2);
        else
            return node.data;
    }

    public static void pir(Node node, int d1, int d2) {
        if (node == null)
            return;

        if (d1 < node.data && d2 < node.data) {
            pir(node.left, d1, d2);
        } else if (d1 > node.data && d2 > node.data) {
            pir(node.right, d1, d2);
        } else {
            pir(node.left, d1, d2);
            System.out.println(node.data);
            pir(node.right, d1, d2);
        }
    }

    public static void targetSumPair(Node root, Node node, int tar) {
        if (node == null)
            return;

        targetSumPair(root, node.left, tar);

        int comp = tar - node.data;
        if (node.data < comp && find(root, comp))
            System.out.println(node.data + " " + comp);

        targetSumPair(root, node.right, tar);
    }

    public static void targetSumPair1(Node node, int tar, ArrayList<Integer> al) {
        if (node == null)
            return;

        targetSumPair1(node.left, tar, al);
        al.add(node.data);
        targetSumPair1(node.right, tar, al);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tar = sc.nextInt();
        Node root = null;
        ArrayList<Integer> al = new ArrayList<>();
        targetSumPair1(root, tar, al);
        int i = 0;
        int j = al.size() - 1;
        while (i < j) {
            if (al.get(i) + al.get(j) < tar)
                i++;
            else if (al.get(i) + al.get(j) > tar)
                j--;
            else {
                System.out.println(al.get(i) + " " + al.get(j));
                i++;
                j--;
            }
        }
    }

    public static class ITpair {
        Node node;
        int state;

        ITpair(Node node, int state) {
            this.node = node;
            this.state = state;

        }
    }

    public static Node getNextFromNormalInOrder(Stack<ITpair> st) {
        while (st.size() > 0) {
            ITpair top = st.peek();
            if (top.state == 0) {
                if (top.node.left != null) {
                    ITpair p = new ITpair(top.node.left, 0);
                    st.push(p);
                }
                top.state++;
            } else if (top.state == 1) {
                if (top.node.right != null) {
                    ITpair p = new ITpair(top.node.right, 0);
                    st.push(p);
                }
                top.state++;
                return top.node;
            } else {
                st.pop();
            }
        }

        return null;
    }

    public static Node getNextFromReverseInOrder(Stack<ITpair> st) {
        while (st.size() > 0) {
            ITpair top = st.peek();
            if (top.state == 0) {
                if (top.node.right != null) {
                    ITpair p = new ITpair(top.node.right, 0);
                    st.push(p);
                }
                top.state++;
            } else if (top.state == 1) {
                if (top.node.left != null) {
                    ITpair p = new ITpair(top.node.left, 0);
                    st.push(p);
                }
                top.state++;
                return top.node;
            } else {
                st.pop();
            }
        }

        return null;
    }

    public static void bestApproach(Node node, int tar) {
        Stack<ITpair> ls = new Stack<>();
        Stack<ITpair> rs = new Stack<>();

        ls.push(new ITpair(node, 0));
        rs.push(new ITpair(node, 0));

        Node left = getNextFromNormalInOrder(ls);
        Node right = getNextFromReverseInOrder(rs);

        while (left.data < right.data) {
            if (left.data + right.data < tar) {
                left = getNextFromNormalInOrder(ls);
            } else if (left.data + right.data > tar) {
                right = getNextFromReverseInOrder(rs);
            } else {
                System.out.println(left.data + " " + right.data);
                left = getNextFromNormalInOrder(ls);
                right = getNextFromReverseInOrder(rs);
            }
        }

    }

}
