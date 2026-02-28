class BTreeNode {
    int key;
    BTreeNode left,right;

    BTreeNode(int k){ key=k; }
}

public class BTreeIndex {
    static BTreeNode insert(BTreeNode r,int k){
        if(r==null) return new BTreeNode(k);
        if(k<r.key) r.left=insert(r.left,k);
        else r.right=insert(r.right,k);
        return r;
    }

    static void inorder(BTreeNode r){
        if(r==null) return;
        inorder(r.left);
        System.out.print(r.key+" ");
        inorder(r.right);
    }

    public static void main(String[] args){
        BTreeNode root=null;
        root=insert(root,5);
        insert(root,2);
        insert(root,9);
        inorder(root);
    }
}
