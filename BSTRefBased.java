/*BSTRefBased - for CSC115 Assignment 5 (BSTs).
 *
 *Structure and initial methods written by Michael Zastre.
 *
 *Methods insert, insertItem, retrieve, retrieveItem, delete, deleteItem, deleteNode,
 *findLeftMost, deleteLeftMost, callFindParent and findParent Written by Dahv Reinhart - V00735279.
 *
 *All tests for this class are done in the main method. See associated file 'test_output.txt'
 *for full results
 */
import java.util.Iterator;

public class BSTRefBased extends AbstractBinaryTree 
    implements Iterable<WordRefs>
{
    private TreeNode root;


    public BSTRefBased() {
        root = null; //root initially set to null upon creation
    }


    public BSTRefBased(WordRefs item,
        AbstractBinaryTree left,
        AbstractBinaryTree right)
    {
        root = new TreeNode(item, null, null);
        if (left != null) {
            attachLeftSubtree(left);
        }

        if (right != null) {
            attachRightSubtree(right);
        }
    }

    //since the root is the reference into the tree, if it is null, the tree must be empty.
    public boolean isEmpty() {
        return (root == null); //if true, the tree is empty.
    }

    //setting the root to null will throw away the reference to the tree and enable garbage collection
    //on the entire tree and all its nodes. Thus, isEmpty() will return true after a call to the below method.
    public void makeEmpty() {
        root = null;
    }

    //Returnes the root node of the tree. will return null on an empty tree since the root is
    //initially set to null.
    protected TreeNode getRoot() {
        return root;
    }

    //Sets the root equal to a parameter node. Since this is not an call to insertion, this will nullify
    //references possessed by the previous root if used on a tree of size >1.
    protected void setRoot(TreeNode r) {
        this.root = r;
    }

    //Returns the data item (WordRef item) stored in the root node.
    public WordRefs getRootItem() throws TreeException {
        if (root == null) { //if the root is null, there is no item. exception thrown.
            throw new TreeException("getRootItem() on empty tree");
        }

        return root.item;
    }

    //Sets the root item equal to a WordRefs item parameter.
    public void setRootItem(WordRefs item) {
        if (root == null) { //if the root is null, there is no instantiated item field. Thus, exception thrown.
            root = new TreeNode(item);
        } else {
            root.item = item;
        }
    }

    //will attach a new TreeNode containing the WordRefs parameter item to the left side of the root.
    //this only works if there is space and if there is a root.
    public void attachLeft(WordRefs item) throws TreeException {
        if (isEmpty()) { //if the tree does not have a root, you cannot attach something to the non-existant root...
            throw new TreeException("attachLeft to empty tree");
        }

        if (!isEmpty() && root.left != null) { //if there is already an item occupying the left side, no attachment possible.
            throw new TreeException("attachLeft to occupied left child");
        }

        root.left = new TreeNode(item, null, null); //the item attached is a new node with no references to other nodes.

        return;
    } 

    //Will attach a subTree to the root item of a new tree. To do this, the root item of the new tree must not
    //be null and there must be space for insertion in the left reference of this node.
    public void attachLeftSubtree(AbstractBinaryTree left) {
        if (isEmpty()) { //the root item of tree being inserted upon is null
            throw new TreeException("attachLeftSubtree to empty tree");
        }

        if (!isEmpty() && root.left != null) { //no space in the left reference
            throw new 
                TreeException("attachLeftSubtree to occupied right child");
        }

        root.left = left.getRoot(); //attaches the root items of both trees
        left.makeEmpty();

        return;    
    }

    //will attach a new TreeNode containing the WordRefs parameter item to the right side of the root.
    //this only works if there is space and if there is a root.
    public void attachRight(WordRefs item) throws TreeException {
        if (isEmpty()) { //if the tree does not have a root, you cannot attach something to the non-existant root...
            throw new TreeException("attachRight to empty tree");
        }

        if (!isEmpty() && root.right != null) { //if there is already an item occupying the right side, no attachment possible.
            throw new TreeException("attachRight to occupied right child");
        }

        root.right = new TreeNode(item, null, null); //the item attached is a new node with no references to other nodes.

        return;
    } 

    //Will attach a subTree to the root item of a new tree. To do this, the root item of the new tree must not
    //be null and there must be space for insertion in the right reference of this node.
    public void attachRightSubtree(AbstractBinaryTree right) {
        if (isEmpty()) { //the root item of tree being inserted upon is null
            throw new TreeException("attachRightSubtree to empty tree");
        }

        if (!isEmpty() && root.right != null) { //no space in the right reference
            throw new 
                TreeException("attachRightSubtree to occupied right child");
        }

        root.right = right.getRoot(); //attaches the root items of both trees
        right.makeEmpty();

        return;
    }

    //will break the connection between the root item and its left subtree. To do this, the method simply sets the left
    //reference of the root item in the tree to null. this removes any connection to the left subtree which will
    //enable garbage collection on the detached portion.
    public AbstractBinaryTree detachLeftSubtree()
        throws TreeException 
    {
        if (root == null) { //and empty tree with no root item to detach from
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased(); //the detached subtree is made into a new tree of its own and returned
        result.setRoot(root.left); 
        root.left = null; //the connection to the left subtree is then nullified. 

        return result;
    }

    //will break the connection between the root item and its right subtree. To do this, the method simply sets the right
    //reference of the root item in the tree to null. this removes any connection to the right subtree which will
    //enable garbage collection on the detached portion.
    public AbstractBinaryTree detachRightSubtree()
        throws TreeException
    {
        if (root == null) { //and empty tree with no root item to detach from
            throw new TreeException("detachLeftSubtree on empty tree");
        }

        BSTRefBased result = new BSTRefBased(); //the detached subtree is made into a new tree of its own and returned
        result.setRoot(root.right);
        root.right = null; //the connection to the right subtree is then nullified.

        return result;
    }

    //*******************WRITTEN BY DAHV REINHART*************************************************************
    //this method accepts a string and will call the insert method for insertion of an item containing this string
    //into the tree. this method is public and maybe called by users.
    public void insert(String word) {
        insertItem(root , word);
    }

    //this method is a protected method for use only within the BSTRefBased program. it will recursively search
    //through the tree to find the correct spot for insertion of the new string. Once it reaches this point,
    //a new node is created at that position.
    protected TreeNode insertItem(TreeNode r, String word) {
        WordRefs ins = new WordRefs(word);
        if (root == null) {
            root = new TreeNode(ins , null , null);
        }
        else if (r == null) { //base case. the end of a branch.
            r = new TreeNode(ins , null , null);
        }
        else if (word.compareTo(r.item.getWord()) < 0) { //searching left
            if (r.left == null) {
                r.left = new TreeNode(ins , null , null);
            }
            else {
               insertItem(r.left , word); 
            }
        }
        else {
            if (r.right == null) {
                r.right = new TreeNode(ins , null , null);
            }
            else {
                insertItem(r.right , word); //searching right
            }
        }
        return r;
    }

    //this method is public and maybe called by users. it will call the retrieveItem method. This method returns a WordRefs
    //item corresponding to the word being searched for.
    public WordRefs retrieve(String word) {
        TreeNode retItem = retrieveItem(root , word);
        if (retItem == null) { //if the item being searched for is not present in the tree, null is returned.
            return null;
        }
        return retItem.item;
    }

    //this method is a protected method for use only within the BSTRefBased program. this will recursively search through
    //the tree until the correct item is found. it will then return a reference to the node containing that item. No deletion
    //occures with this method!
    protected TreeNode retrieveItem(TreeNode r, String word) {
        if (r == null) { //base case if the root or one of the step-nodes is null. allows for backtracking.
            return null;
        }
        else if (r.item.getWord().equals(word)) { //base case. correct item found. return this item.
            return r;
        }
        else if (word.compareTo(r.item.getWord()) < 0) { //search left.
            return retrieveItem(r.left , word);
        }
        else if (word.compareTo(r.item.getWord()) > 0) { //search right.
            return retrieveItem(r.right , word);
        }
        return null;
    }

    //this method is public and maybe called by users. will call the deleteItem method.
    public void delete(String word) {
        deleteItem(root , word);
    }

    //this method is a protected method for use only within the BSTRefBased program. this method searches through the
    //tree for the item to be deleted. once found, it checks to see if this tiem is null. It then calles the deleteNode method
    //which will actually modify the tree references to delete the unwanted node.
    protected TreeNode deleteItem(TreeNode r, String word) {
        TreeNode targetNode = retrieveItem(r , word);
        if (targetNode == null) //if null, then the item to be deleted is not actually in the tree.
            return null;
        else {
            deleteNode(targetNode);
            return targetNode;
        }
    }

    //this method is a protected method for use only within the BSTRefBased program. This method will modify the references
    //in the tree such that the unwanted node passed in is removed from the tree. To do this, the parent of the node is found
    //since the reference modification must be done to the parent node as well as to the unwanted node, and all following
    //nodes as well.
    protected TreeNode deleteNode(TreeNode node) {
        TreeNode parent = callFindParent(node); //find the parent of the unwanted node.
        if (parent == null) { //if we are deleting the root, the parent node will be null. 
            parent = node; //this sets the unwanted node to the parent node in the above case.
        }
        if (node.left == null && node.right == null) { //unwanted node has no children
            if (parent.left == node) {
                parent.left = null;
                return node;
            }
            if (parent.right == node) {
                parent.right = null;
                return node;
            }
        }
        
        else if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) { //one child
            if (node.left != null) {
                if (parent.left == node) { //checking which side is correct for deletion
                    parent.left = node.left;
                    return node;
                }
                else if (parent.right == node) {
                    parent.right = node.right;
                    return node;
                }
            }
            else if (node.right != null) {
                if (parent.left == node) {
                    parent.left = node.left;
                    return node;
                }
                else if (parent.right == node) {
                    parent.right = node.right;
                    return node;
                }
            }
        }
        
        //must have 2 children at this point
        if (node.right == null) { //if we are deleting a root with only one subtree
            TreeNode successorL = node.left;
            node.item = successorL.item;
            deleteNode(successorL);
        }
        else {
            TreeNode successorR = findLeftMost(node.right);
            node.item = successorR.item;
            deleteNode(successorR);
        }
        return node;
    }

    //this method is a protected method for use only within the BSTRefBased program. will recursively search the tree for
    //the left most item starting at the position of the node passed in.
    protected TreeNode findLeftMost(TreeNode node) {
        if (node.left == null) { //base case, we have found the left most item.
            return node;
        }
        else {
            return findLeftMost(node.left);
        }
    }

    //this method is a protected method for use only within the BSTRefBased program. will delete the left most item in
    //the tree from the perspective of the node passed in.
    protected TreeNode deleteLeftMost(TreeNode node) {
        findLeftMost(node);
        TreeNode del = deleteNode(node);
        return del;
    }
    
    //this method is public and maybe called by users. calls the findParent method.
    public TreeNode callFindParent(TreeNode n) {
        return findParent(root , n , null);
    }
    
    //this method is a protected method for use only within the BSTRefBased program. this method will recursively find the
    //parent node of the node passed in.
    protected TreeNode findParent(TreeNode r , TreeNode n, TreeNode parent) {
        if (n == null || r == null) { //base case if the node being searched is null. allows for backtracking.
            return null;
        }
        else if (r != n) { 
            parent = findParent(r.left , n , r); //search left
            if (parent == null) {
                parent = findParent(r.right , n , r); //search right
            }
        }
        return parent; 
    }
    
    //this method is public and maybe called by users. this method will return an instance of the BSTIterator program
    //for the caller to use to iterate through the tree in either preorder, postorder or inorder.
    public Iterator<WordRefs> iterator() {
        return new BSTIterator(this);
    }
    //***********END OF FUNCTION CODE*********************************************************

    public static void main(String args[]) {
        BSTRefBased t;
        AbstractBinaryTree tt;
        int i;
        boolean result;
        String message;
        
        //testing the insert function of the tree. inserts one item and then checks if the root of the tree
        //has been correctly set to this new item.
        message = "Test 1: inserting 'humpty' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            result = t.getRootItem().getWord().equals("humpty"); //should be 'humpty' at this point
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));

        //testing multiple insertions on the tree. then detaches the left anad right subtrees and checks their
        //identity to ensure correct ordering of the insertion method.
        message = "Test 2: inserting 'humpty', 'dumpty', 'sat' -- ";
        t = new BSTRefBased();
        try {
            t.insert("humpty");
            t.insert("dumpty");
            t.insert("sat");
            result = t.getRootItem().getWord().equals("humpty");
            tt = t.detachLeftSubtree();
            result &= tt.getRootItem().getWord().equals("dumpty"); //should be in the left subtree
            tt = t.detachRightSubtree();
            result &= tt.getRootItem().getWord().equals("sat"); //should be in the right subtree
        } catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        //testing the retrieval method. multiple insertions are done to the tree, a retrieve call is made and then the identity
        //of the retrieved method is examined.
        message = "Test 3: retrieval of an item 'three' -- ";
        t = new BSTRefBased();
        result = false;
        try {
            t.insert("one");
            t.insert("two");
            t.insert("three");
            t.insert("four");
            t.insert("five");
            WordRefs retTest = t.retrieve("three");
            if (retTest.getWord().equals("three")) //should be 'three'. if null will trigger an exception
                result = true;
        }
        catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        //testing the deletion method. multiple items are inserted, deletion is called and then the position at
        //which the deleted item came from is checked to verify that it is now null (empty).
        message = "Test 4: inserting and deleting 'aaa', 'bbb', 'ccc' -- ";
        t = new BSTRefBased();
        result = false;
        try {
            t.insert("bbb");
            t.insert("ccc");
            t.insert("aaa");
            TreeNode test1 = t.getRoot().left;
            result = test1.item.getWord().equals("aaa");
            t.delete("aaa");
            result &= (t.getRoot().left == null); //since the node that was previously here was removed, this place should be null
        }
        catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
        
        //complex test involving many inserts, deletions, and order checks. after the items are inserted, the ordering is checked
        //to verify correct order. serveral deletions take place with order checking happening simultaneously and afterwards.
        //if any nodes are out of order, test will fail.
        message = "Test 5: inserting multiple nodes, deleting and then checking order -- ";
        t = new BSTRefBased();
        result = false;
        try {
            t.insert("ccc");
            t.insert("ddd");
            t.insert("bbb");
            t.insert("dbd");
            t.insert("ded");
            t.insert("dad");
            t.insert("dfd");
            t.insert("bab");
            t.insert("bdb");
            TreeNode test2 = t.getRoot().left;
            TreeNode test3 = t.getRoot().right; //testing identity of initial nodes.
            result = test2.item.getWord().equals("bbb");
            result &= test3.item.getWord().equals("ddd");
            result &= (t.getRoot().right.left.left.item.getWord().equals("dad"));
            t.delete("dad"); //leaf node is deleted
            result &= (t.getRoot().right.left.left == null);
            result &= (t.getRoot().left.right.item.getWord().equals("bdb"));
            result &= (t.getRoot().left.left.item.getWord().equals("bab"));
            t.delete("bbb"); //a node with two children is deleted
            result &= (t.getRoot().left.item.getWord().equals("bdb")); //should be 'bdb' that is now at this position
            result &= (t.getRoot().right.right.right.item.getWord().equals("dfd"));
            result &= (t.getRoot().right.item.getWord().equals("ddd"));
            t.delete("ddd"); //a second node with two children is deleted
            result &= (t.getRoot().right.item.getWord().equals("ded"));
            WordRefs retTest2 = t.retrieve("dfd");
            result &= retTest2.getWord().equals("dfd");
            
        }
        catch (Exception e) {
            result = false;
        }
        System.out.println(message + (result ? "passed" : "FAILED"));
    }
} 
