/**
 * Represents data structure Threaded Binary Tree
 */
public class TheadedBinaryTree {

    private TBTNode root;

    public TheadedBinaryTree() {
        this.root = null;
    }

    /**
     * Function to insert new student info into Threaded Binary Tree
     *
     * @param key key to insert
     * @return new root of the tree
     */
    public TBTNode insert(int key, String studentName) {
        TBTNode newNode = new TBTNode(key, studentName);
        TBTNode node = root;
        TBTNode parent = null; //parent of the node to be inserted
        while (node != null) {
            if (key == node.getStudentId()) {
                System.out.println("There is already student with the same id");
                return root;
            } else {
                parent = node;
                //go to the left subtree
                if (key < node.getStudentId()) {
                    if (node.isLeftThread()) break;
                    else node = node.getLeft();
                }
                //go to the right subtree
                else {
                    if (node.isRightThread()) break;
                    else node = node.getRight();
                }
            }
        }
        //in case the tree is empty
        if (parent == null) root = newNode;
            //if key less than parent key put new node to the left
        else if (key < parent.getStudentId()) {
            newNode.setLeft(parent.getLeft());
            newNode.setRight(parent);
            parent.setLeftThread(false);
            parent.setLeft(newNode);
        }
        //if key more than parent key put new node to the right
        else {
            newNode.setLeft(parent);
            newNode.setLeftThread(true);
            newNode.setRight(parent.getRight());
            parent.setRightThread(false);
            parent.setRight(newNode);
        }
        return root;
    }

    /**
     * Function to delete a node with key specified from the Threaded Binary Tree
     *
     * @param key key to insert
     * @return new root of the tree
     */
    public TBTNode remove(int key) {
        return null;
    }

    /**
     * Function to find node with the key specified
     *
     * @param key key to find
     * @return TBTNode that contains key
     */
    public TBTNode search(int key) {
        TBTNode node = root;
        while (node != null && key != node.getStudentId()) {
            if (key < node.getStudentId()) node = node.getLeft();
            else node = node.getRight();
        }
        return node;
    }

//    /**
//     * @param node
//     * @param key
//     * @return
//     */
//    public TBTNode searchRecursive(TBTNode node, int key) {
//        if (node == null || key == node.getStudentId())
//            return node;
//        if (key < node.getStudentId()) return searchRecursive(node.getLeft(), key);
//        else return searchRecursive(node.getRight(), key);
//    }

    public TBTNode successor(TBTNode TBTNode) {
        return null;
    }

    public TBTNode predecessor(TBTNode TBTNode) {
        return null;
    }

    /**
     * Function to find minimum of the tree
     *
     * @return Node with minimum key
     */
    public TBTNode minimum() {
        TBTNode node = root;
        if (node != null)
            while (node.getLeft() != null) node = node.getLeft();
        return node;
    }

    /**
     * Function to find maximum of the tree
     *
     * @return Node with maximum key
     */
    public TBTNode maximum() {
        TBTNode node = root;
        if (node != null)
            while (node.getRight() != null) node = node.getRight();
        return node;
    }

    public TBTNode median() {
        return null;
    }

    public void preoerder() {

    }

    public void inorder() {

    }

    public void postorder() {

    }
}
