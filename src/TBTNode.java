/**
 * Represent node of the Threaded Binary Tree
 */
//commment
public class TBTNode {
    private int studentId;
    private String studentName;
    private TBTNode left;
    private TBTNode right;

    //true if the left points to predecessor
    boolean isLeftThread;
    //true if the right points to successor
    boolean isRightThread;

    public TBTNode(int studentId) {
        this.studentId = studentId;
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
}
