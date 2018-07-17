/**
 * Represents commands, that are supported by the program
 */
public enum Command {
    INSERT, REMOVE, SEARCH, MINIMUM, MEDIAN, MAXIMUM, SUCCESSOR, PREDECESSOR, PREORDER, INORDER, POSTORDER, EXIT;

    public static Command fromString(String string) {
        for (Command value : values()) {
            if (string.equalsIgnoreCase(value.toString())) return value;
        }
        return null;
    }
}
