public class Class3{
    // https://leetcode.com/problems/binary-tree-coloring-game/submissions/

    

    //morris traversal
    //https://leetcode.com/problems/binary-tree-inorder-traversal/submissions/
    //https://leetcode.com/problems/binary-tree-preorder-traversal/submissions/

    //https://leetcode.com/problems/recover-binary-search-tree/
    //https://practice.geeksforgeeks.org/problems/inorder-successor-in-bst/1/#



    //https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/submissions/
    //https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/submissions/
    //https://leetcode.com/problems/count-complete-tree-nodes/submissions/



    //https://practice.geeksforgeeks.org/problems/maximum-sum-leaf-to-root-path/1/#
    //max sum root to node
    //https://leetcode.com/problems/binary-tree-maximum-path-sum/submissions/


    //https://leetcode.com/problems/path-sum-iii/submissions/
    //https://practice.geeksforgeeks.org/problems/maximum-path-sum/1/

    //https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/submissions/
    //from post order traversal



    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/submissions/
    //https://practice.geeksforgeeks.org/problems/image-multiplication0627/1

    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/submissions/

    //https://leetcode.com/problems/validate-binary-search-tree/
    //https://leetcode.com/problems/sum-of-distances-in-tree/ - doubt


    //https://practice.geeksforgeeks.org/problems/find-the-closest-element-in-bst/1/

    
    //https://leetcode.com/problems/binary-tree-cameras/submissions/

    //https://practice.geeksforgeeks.org/problems/clone-a-binary-tree/1/#

    //https://leetcode.com/problems/delete-node-in-a-bst/submissions/


    //https://leetcode.com/problems/sum-of-distances-in-tree/submissions/

    //https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/submissions/
    

    //https://leetcode.com/problems/flatten-binary-tree-to-linked-list/submissions/
    //https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1/

    //https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
    

    //https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1/
    //https://leetcode.com/problems/distribute-coins-in-binary-tree/

    //https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
    // https://www.geeksforgeeks.org/kth-smallest-element-in-bst-using-o1-extra-space/  -using morris traversal
    // https://leetcode.com/problems/longest-univalue-path/submissions/


    // https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    // https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-binary-tree/1
    // https://practice.geeksforgeeks.org/problems/number-of-turns-in-binary-tree/1  - doubt
    // https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
        
    //https://leetcode.com/problems/sum-root-to-leaf-numbers/
    //https://leetcode.com/problems/maximum-width-of-binary-tree/
    //https://leetcode.com/problems/convert-bst-to-greater-tree/
    //https://binarysearch.com/problems/Longest-Even-Sum-Path



    
    //AVL
    public static void main(String[] args){
        int[] arr = {12,25,37,50,62,75,87};
        int n = arr.length;
        Node root = construct(arr,0,n-1);
        // display(root);
        root = add(root,5);
        root = add(root,1);
        root = add(root,11);
        display(root);
    }
    
    static Node construct(int[] arr, int lo, int hi){
        if(lo>hi) return null;
        
        int mid = (lo+hi)/2;
        Node node = new Node(arr[mid]);
        node.left = construct(arr,lo,mid-1);
        node.right = construct(arr,mid+1,hi);
        
        node.ht = height(node);
        node.bal = balance(node);
        
        return node;
    }
    
    static int height(Node node){
        int lh = (node.left==null ? 0 : node.left.ht);
        int rh = (node.right==null ? 0 : node.right.ht);
        return Math.max(lh,rh)+1;
    } 
    
    static int balance(Node node){
        int lh = (node.left==null ? 0 : node.left.ht);
        int rh = (node.right==null ? 0 : node.right.ht);
        return lh-rh;
    } 
    
    static void display(Node node){
        
        if(node==null) return;
        
        String lstr = (node.left==null ? "" : node.left.data+"");
        String rstr = (node.right==null ? "" : node.right.data+"");  
        String str = lstr+ "  <- "+node.data+"["+node.ht+","+node.bal+"]"+" -> " +rstr;
        
        System.out.println(str);
        
        display(node.left);
        display(node.right);
    }
    
    
    static Node add(Node node, int val){
        if(node==null) return new Node(val);
        
        if(val<node.data){
            node.left = add(node.left, val);
        }else if(val>node.data){
            node.right = add(node.right, val);
        }
        
        node.ht = height(node);
        node.bal = balance(node);
        
        if(node.bal>1){ //ll or lr
            
            if(node.left.bal>=0){ //ll
                node = rightRotate(node);
            }
            else{ //lr
                node.left = leftRotate(node.left); 
                node = rightRotate(node);
            }
        }
        else if(node.bal<-1){ //rr or rl
            
            if(node.right.bal>=0){ //rl
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
            else{ //rr
                node = leftRotate(node);
            }
        }
        
        return node;
    }
    
    static Node rightRotate(Node z){
        
        Node y=z.left;
        
        Node t3=y.right;
        
        //rotate
        z.left=t3;
        y.right=z;
        
        z.ht = height(z);
        z.bal = balance(z);
        
        y.ht = height(y);
        y.bal = balance(y);
        
        return y;
    }
    
    static Node leftRotate(Node z){
        
        Node y=z.right;
        
        Node t2=y.left;
        
        //rotate
        z.right=t2;
        y.left=z;
        
        z.ht = height(z);
        z.bal = balance(z);
        
        y.ht = height(y);
        y.bal = balance(y);
        
        return y;
    }
    
    public static Node remove(Node node, int val){
        if(node == null){
            return null;
        }
        
        if(val == node.data){
            if(node.left != null && node.right != null){
                int lmax = max(node.left);
                node.data = lmax;
                node.left = remove(node.left, lmax);
            } else if(node.left != null){
                return node.left;
            } else if(node.right != null){
                return node.right;
            } else {
                return null;
            }
        } else if(val < node.data){
            node.left = remove(node.left, val);
        } else if(val > node.data){
            node.right = remove(node.right, val);
        }
        
        node.ht = getHeight(node);
        node.bal = getBalance(node);
        
        // bal = lh - rh
        if(node.bal > 1){ // ll, lr 
            if(node.left.bal >= 0){ // ll
                node = rightRotate(node);
            } else { // lr
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        } else if(node.bal < -1){ // rr, rl
            if(node.right.bal < 0){ // rr
                node = leftRotate(node);
            } else { // rl
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        }
        
        return node;
    }
    
    public static int max(Node node){
        if(node.right == null){
            return node.data;
        } else {
            return max(node.right);
        }
    }
}