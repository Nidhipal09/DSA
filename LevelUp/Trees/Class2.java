import java.util.ArrayDeque;
import java.util.Queue;

public class Class2{
    static class Pair{
        TreeNode node;
        int l;
        Pair(TreeNode node,int l){
            this.node=node;
            this.l=l;
        }
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans= new ArrayList<>();
        if(root==null) return ans;
        
        Queue<Pair> q = new ArrayDeque<>();
       q.add(new Pair(root, 1));
       int cl = 1; //current level
       
        List<Integer> al = new ArrayList<>();

       while(q.size()>0){
           Pair p=q.remove();

           if(p.l!=cl){
               cl=p.l;
               ans.add(al);
               al = new ArrayList<>();
           }
           
               al.add(p.node.val);

           if(p.node.left!=null) q.add(new Pair(p.node.left, p.l+1));
           if(p.node.right!=null) q.add(new Pair(p.node.right, p.l+1));
       }
        
        ans.add(al);
        return ans;
    }

    //vertical level order - leetcode
    // https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1/# - second approach issue
    //boundary traversal - gfg


}