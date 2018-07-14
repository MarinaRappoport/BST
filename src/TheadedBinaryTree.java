/**
 * Represents data structure Threaded Binary Tree
 */
public class TheadedBinaryTree {

    private TBTNode root;

    public TheadedBinaryTree() {
        this.root = null;
    }

    /**
     * Function to insert key into Threaded Binary Tree
     *
     * @param key key to insert
     */
    public void insert(int key) {

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

    /**
     * Funcrion to find the successor to given Node
     * if the given Node is the maximum in the tree return null
     * else return Node with successor id number
     */
    public TBTNode successor(TBTNode node) {
        if (node.getRight() == null) {
            System.out.print("there no successor to this Node");
            return null;
        } else if (node.isRightThread)
            return node.getRight();
        else {
            node = node.getRight();
            while (!node.isLeftThread) node = node.getLeft();
            return node;
        }
    }

    /**
     * Funcrion to find the predecessor to given Node
     * if the given Node is the minimum in the tree return null
     * else return Node with predecessor id number
     */
    public TBTNode predecessor(TBTNode node) {
        if (node.getLeft() == null) {
            System.out.print("there no predecessor to this Node");
            return null;
        } else if (node.isLeftThread)
            return node.getLeft();
        else {
            node = node.getLeft();
            while (!node.isRightThread) node = node.getRight();
            return node;
        }
    }

    /**
     * Function to find minimum of the tree
     * print the minimum student id and name
     * @return Node with minimum id
     */
    public TBTNode minimum() {
        TBTNode node = root;
        if (node != null)
            while (node.getLeft() != null) node = node.getLeft();
        System.out.printf("The minimum ID is: %9d\t Student name: %s", node.getStudentId(), node.getStudentName());
        return node;
    }

    /**
     * Function to find maximum of the tree
     * print the maximum student id and name
     * @return Node with maximum id
     */
    public TBTNode maximum() {
        TBTNode node = root;
        if (node != null)
            while (node.getRight() != null) node = node.getRight();
        System.out.printf("The maximum ID is: %9d\t Student name: %s", node.getStudentId(), node.getStudentName());
        return node;
    }

    public TBTNode median() {
        return null;
    }

    /**
     * Function print the students in tree in pre-order
     */
    public void PreoerderTreeWalk(TBTNode node) {
        if (node != null) {
            System.out.printf("%9d\t &s\n", node.getStudentId(), node.getStudentName());
            if (!node.isLeftThread) PreoerderTreeWalk(node.getLeft());
            if (!node.isRightThread) PreoerderTreeWalk(node.getRight());
        }
    }

    /**
     * Function print the students in tree in in-order
     */
    public void InorderTreeWalk(TBTNode node) {
        node = null;
    }

    /**
     * @return the most left in the subtree of given node
     */
    private TBTNode MostLeft(TBTNode node) {
        if (node != null)
            while ((node.getLeft() != null) && (!node.isLeftThread)) node = node.getLeft();
        return node;
    }

    /**
     * Function print the students in tree in post-order
     */
    public void PostorderTreeWalk(TBTNode node) {
        if (node != null) {
            if (!node.isLeftThread) PostorderTreeWalk(node.getLeft());
            if (!node.isRightThread) PostorderTreeWalk(node.getRight());
            System.out.printf("%9d\t &s\n", node.getStudentId(), node.getStudentName());
        }
    }
}
