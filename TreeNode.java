/**
 * @author Mike Zastre, UVic
 *
 * Part of CSC 115, Spring 2015, Assignment #5
 */

class TreeNode {
    WordRefs item;
    TreeNode left;
    TreeNode right;

    public TreeNode(WordRefs item) {
        this.item = item;
        this.left = this.right = null;
    }

    public TreeNode(WordRefs item, TreeNode left, TreeNode right) {
        this.item  = item;
        this.left  = left;
        this.right = right;
    }
}
