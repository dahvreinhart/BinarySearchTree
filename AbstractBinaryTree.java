/**
 * @author Mike Zastre, UVic
 *
 * Part of CSC 115, Spring 2015, Assignment #5
 */

public abstract class AbstractBinaryTree {

    /**
     * @return true if there are no nodes in the tree, false otherwise.
     */
    public abstract boolean isEmpty();

    /**
     * After this method the tree will be empty.
     */
    public abstract void makeEmpty();

    /**
     * @return The TreeNode values stored as the root node of the
     * tree. This should not depend upon implementation (i.e., either
     * array-based or ref-based.
     */
    protected abstract TreeNode getRoot();

    /**
     * Using the TreeNode passed to the method, set the current tree's
     * root node to this value.
     *
     * @param r The TreeNode reference that will be assigned to the current
     *        tree's root variable.
     */
    protected abstract void setRoot(TreeNode r);

    /**
     * If the tree is empty -- that is, its root is equal to null --
     * then throw an exception.
     *
     * Otherwise, obtain the values of item field of thee root node
     * (which is a TreeNode whose item field is of type WordRefs)
     * and return this.
     * 
     * @return The item field stored in the root node (if it exists).
     */
    public abstract WordRefs getRootItem() throws TreeException;

    /**
     * If the tree not empty, then overwrite the item field in the
     * Tree's current root node with the value passed in. Otherwise
     * create a new node with the item stored in the node, and set
     * the tree's root to this new node.
     *
     * @param item The item reference that will be stored in the Tree's
     *        root node's item field.
     */
    public abstract void setRootItem(WordRefs item);

    /**
     * If the tree is both non-empty and has no left subtree, the
     * method will create a new one-node tree whose root has the value
     * passed as item, and that new tree will become the left-subtree
     * of the current tree.
     *
     * If the current tree is empty, then an exception must be thrown.
     *
     * if the current tree is not empty but has a left subtree, then
     * an exception must be thrown.
     *
     * @param item The value that will be stored in the root of the
     *        tree's left-subtree
     */
    public abstract void attachLeft(WordRefs item) throws TreeException;

    /**
     * If the tree is both non-empty and has no left subtree, the
     * method will assign the root of left to become the root of the
     * current tree's left subtree. The left tree passed in as a
     * parameter will be made empty (i.e., nodes in the tree that
     * was passed in as a parameter will now only be reachable by
     * the current tree).
     *
     * If the current tree is empty, then an exception must be thrown.
     *
     * if the current tree is not empty but has a left subtree, then
     * an exception must be thrown.
     *
     * @param left The tree that will become the current tree's left
     *        subtree.
     */
    public abstract void attachLeftSubtree(AbstractBinaryTree left);

    /**
     * If the tree is both non-empty and has no right subtree, the
     * method will create a new one-node tree whose root has the value
     * passed as item, and that new tree will become the right-subtree
     * of the current tree.
     *
     * If the current tree is empty, then an exception must be thrown.
     *
     * if the current tree is not empty but has a right subtree, then
     * an exception must be thrown.
     *
     * @param item The value that will be stored in the root node of the
     *        tree's right-subtree
     */
    public abstract void attachRight(WordRefs item) throws TreeException;

    /**
     * If the tree is both non-empty and has no right subtree, the
     * method will assign the root of right to become the root of the
     * current tree's right subtree. The eifht tree passed in as a
     * parameter will be made empty (i.e., nodes in the tree that
     * was passed in as a parameter will now only be reachable by
     * the current tree).
     *
     * If the current tree is empty, then an exception must be thrown.
     *
     * if the current tree is not empty but has a right subtree, then
     * an exception must be thrown.
     *
     * @param right The tree that will become the current tree's right
     *        subtree.
     */
    public abstract void attachRightSubtree(AbstractBinaryTree right);

    /**
     * If the tree is non-empty, then the root node of the tree's
     * left-subtree will be assigned as the root node of a new
     * tree's root node. The tree's current left subtree will be
     * assigned to null. Note that the left subtree may itself be
     * empty, and this will *not* result in a thrown excpetion.
     *
     * If the tree is empty, then an exception is thrown.
     *
     * @return New tree whose root node is that which was the root
     * of the tree's left sub-tree.
     */
    public abstract AbstractBinaryTree detachLeftSubtree()
        throws TreeException;

    /**
     * If the tree is non-empty, then the root node of the tree's
     * right-subtree will be assigned as the root node of a new
     * tree's root node. The tree's current right subtree will be
     * assigned to null. Note that the right subtree may itself be
     * empty, and this will *not* result in a thrown excpetion.
     *
     * If the tree is empty, then an exception is thrown.
     *
     * @return New tree whose root node is that which was the root
     * of the tree's right sub-tree.
     */
    public abstract AbstractBinaryTree detachRightSubtree()
        throws TreeException;
}
