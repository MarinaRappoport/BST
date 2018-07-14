/**
 * Represent node of the Threaded Binary Tree
 */
public class TBTNode {
    private int studentId;
    private String studentName;
    private TBTNode left;
    private TBTNode right;

    //true if the left points to predecessor
    private boolean isLeftThread;
    //true if the right points to successor
    private boolean isRightThread;

    /**
     * Constructor to build TBT node
     *
     * @param studentId   student id
     * @param studentName student name
     */
    public TBTNode(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.isLeftThread = true;
        this.isRightThread = true;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public TBTNode getLeft() {
        return left;
    }

    public void setLeft(TBTNode left) {
        this.left = left;
    }

    public TBTNode getRight() {
        return right;
    }

    public void setRight(TBTNode right) {
        this.right = right;
    }

    public boolean isLeftThread() {
        return isLeftThread;
    }

    public void setLeftThread(boolean leftThread) {
        isLeftThread = leftThread;
    }

    public boolean isRightThread() {
        return isRightThread;
    }

    public void setRightThread(boolean rightThread) {
        isRightThread = rightThread;
    }
}
