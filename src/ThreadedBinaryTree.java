/**
 * Represents data structure Threaded Binary Tree
 */
public class ThreadedBinaryTree {

    private TBTNode root;
    private TBTNode median = null;
    private int smaller = 0, bigger = 0;

    public ThreadedBinaryTree() {
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
        if (parent == null) {
            root = newNode;
            median = newNode;
            bigger = +1;
        }

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
        //find the new median after insert
        if (newNode.getStudentId() < median.getStudentId()) smaller = smaller + 1;
        else if (newNode.getStudentId() > median.getStudentId()) bigger = bigger + 1;
        medianUpdate();
        return root;
    }

    /**
     * Function to delete a node with key specified from the Threaded Binary Tree
     *
     * @param key key to insert
     * @return new root of the tree
     */
    public TBTNode remove(int key) {
        TBTNode node = root;
        TBTNode parent = null;
        boolean isFound = false;
        while (node != null) {
            if (key == node.getStudentId()) {
                isFound = true;
                break;
            }
            parent = node;
            if (key < node.getStudentId()) {
                if (!node.isLeftThread())
                    node = node.getLeft();
                else
                    break;
            } else {
                if (!node.isRightThread())
                    node = node.getRight();
                else
                    break;
            }
        }

        if (isFound) {
            //found the new median before delete the the student
            if (node.getStudentId() < median.getStudentId()) smaller = smaller - 1;
            else if (node.getStudentId() >= median.getStudentId()) bigger = bigger - 1;
            medianUpdate();
        }
        if (!isFound)
            System.out.println("No student with the id: " + key + "\n");
            // Two Children
        else if (!node.isLeftThread() && !node.isRightThread())
            root = removeCase3(root, node);

            // Only one Child
        else if (!node.isLeftThread() || !node.isRightThread())
            root = removeCase2(root, parent, node);

            // No children
        else
            root = removeCase1(root, parent, node);

        return root;
    }

    /**
     * Case of deletion when node has no children
     *
     * @param root   root of the TBT
     * @param parent parent of the node to be deleted
     * @param node   node to be deleted
     * @return new root
     */
    private TBTNode removeCase1(TBTNode root, TBTNode parent, TBTNode node) {
        // If Node to be deleted is root
        if (parent == null)
            root = null;

            // if Node to be deleted is left of its parent
        else if (node == parent.getLeft()) {
            parent.setLeftThread(true);
            parent.setLeft(node.getLeft());
        }
        // if Node to be deleted is right of its parent
        else {
            parent.setRightThread(true);
            parent.setRight(node.getRight());
        }
        return root;
    }

    /**
     * Case of deletion when node has one child
     *
     * @param root   root of the TBT
     * @param parent parent of the node to be deleted
     * @param node   node to be deleted
     * @return new root
     */
    private TBTNode removeCase2(TBTNode root, TBTNode parent, TBTNode node) {
        TBTNode child;

        // node to be deleted has left child.
        if (!node.isLeftThread())
            child = node.getLeft();

            // node to be deleted has right child.
        else
            child = node.getRight();

        // Node to be deleted is root Node.
        if (parent == null)
            root = child;

            // Node is left child of its parent.
        else if (node == parent.getLeft())
            parent.setLeft(child);
        else
            parent.setRight(child);

        // Find successor and predecessor
        TBTNode successor = successor(node);
        TBTNode predecessor = predecessor(node);

        // If ptr has left subtree.
        if (!node.isLeftThread())
            predecessor.setRight(successor);

            // If ptr has right subtree.
        else {
            if (!node.isRightThread())
                successor.setLeft(predecessor);
        }

        return root;
    }

    /**
     * Case of deletion when node has two children
     *
     * @param root root of the TBT
     * @param node node to be deleted
     * @return new root
     */
    private TBTNode removeCase3(TBTNode root, TBTNode node) {
        // Find inorder successor and its parent.
        TBTNode parentSuccessor = node;
        TBTNode successor = node.getRight();

        // Find leftmost child of successor
        while (successor.getLeft() != null) {
            parentSuccessor = successor;
            successor = successor.getLeft();
        }

        node.copyInfo(successor);

        if (successor.isLeftThread() && successor.isRightThread())
            root = removeCase1(root, parentSuccessor, successor);
        else
            root = removeCase2(root, parentSuccessor, successor);

        return root;
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
            if (key < node.getStudentId() && !node.isLeftThread()) node = node.getLeft();
            else if (key < node.getStudentId() && node.isLeftThread()) node = null;
            else if (!node.isRightThread()) node = node.getRight();
            else node = null;
        }
        if (node == null) System.out.println("Student not found");
        else
            System.out.printf("Student found!student details ID:%9d\t Name:%s\n", node.getStudentId(), node.getStudentName());
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
     * Function to find the successor to given Node
     *
     * @param node given Node
     * @return if the given Node is the maximum in the tree return null
     * else return Node with successor id number
     */
    public TBTNode successor(TBTNode node) {
        if (node.getRight() == null) {
            System.out.print("there no successor to this Node");
            return null;
        } else if (node.isRightThread())
            return node.getRight();
        else {
            node = node.getRight();
            while (!node.isLeftThread()) node = node.getLeft();
            return node;
        }
    }

    /**
     * Function to find the predecessor to given Node
     *
     * @param node given Node
     * @return if the given Node is the minimum in the tree return null
     * else return Node with predecessor id number
     */
    public TBTNode predecessor(TBTNode node) {
        if (node.getLeft() == null) {
            System.out.print("there no predecessor to this Node");
            return null;
        } else if (node.isLeftThread())
            return node.getLeft();
        else {
            node = node.getLeft();
            while (!node.isRightThread()) node = node.getRight();
            return node;
        }
    }

    /**
     * Function to find minimum of the tree
     * print the minimum student id and name
     *
     * @return Node with minimum id
     */
    public TBTNode minimum() {
        TBTNode node = root;
        if (node != null)
            node = mostLeft(node);
        System.out.printf("The minimum ID is: %9d\t Student name: %s\n", node.getStudentId(), node.getStudentName());
        return node;
    }

    /**
     * Function to find maximum of the tree
     * print the maximum student id and name
     *
     * @return Node with maximum id
     */
    public TBTNode maximum() {
        TBTNode node = root;
        if (node != null)
            while (node.getRight() != null) node = node.getRight();
        System.out.printf("The maximum ID is: %9d\t Student name: %s\n", node.getStudentId(), node.getStudentName());
        return node;
    }

    private void medianUpdate() {
        if ((smaller + 1) < bigger) {
            median = successor(median);
            smaller = smaller + 1;
            bigger = bigger - 1;
        } else if (smaller > bigger) {
            median = predecessor(median);
            smaller = smaller - 1;
            bigger = bigger + 1;
        }
    }

    /**
     * print the details of the median student
     *
     * @return
     */
    public TBTNode median() {
        if (median == null) System.out.println("No students");
        else
            System.out.printf("Median student details: ID: %9d\t Name: %s\n", median.getStudentId(), median.getStudentName());
        return median;
    }

    /**
     * Function print the students in tree in pre-order
     */
    public void preOrderTreeWalk() {
        preOrderTreeWalk(root);
    }

    private void preOrderTreeWalk(TBTNode node) {
        if (node != null) {
            System.out.printf("%9d\t %s\n", node.getStudentId(), node.getStudentName());
            if (!node.isLeftThread()) preOrderTreeWalk(node.getLeft());
            if (!node.isRightThread()) preOrderTreeWalk(node.getRight());
        }
    }

    /**
     * Function print the students in tree in in-order
     */
    public void inOrderTreeWalk() {
        TBTNode node = mostLeft(root);
        while (node != null) {
            System.out.printf("%9d\t %s\n", node.getStudentId(), node.getStudentName());
            if (node.isRightThread()) node = node.getRight();
            else node = mostLeft(node.getRight());
        }
    }

    /**
     * @return the most left in the subtree of given node
     */
    private TBTNode mostLeft(TBTNode node) {
        if (node != null)
            while ((node.getLeft() != null) && (!node.isLeftThread())) node = node.getLeft();
        return node;
    }

    /**
     * Function print the students in tree in post-order
     */
    public void postOrderTreeWalk() {
        postOrderTreeWalk(root);
    }

    private void postOrderTreeWalk(TBTNode node) {
        if (node != null) {
            if (!node.isLeftThread()) postOrderTreeWalk(node.getLeft());
            if (!node.isRightThread()) postOrderTreeWalk(node.getRight());
            System.out.printf("%9d\t %s\n", node.getStudentId(), node.getStudentName());
        }
    }
}
