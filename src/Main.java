import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 * Authors: Katrin Ten, Marina Rappoport
 */
public class Main {
    private static ThreadedBinaryTree tbt = new ThreadedBinaryTree();

    public static void main(String[] args) throws IOException {
        System.out.println("Print commands, each on new string\n"
                + "There is support for the following commands:\n"
                + "INSERT,studentId,studentName - to insert new student info;\n"
                + "INSERT also available from file - in format: studentId studentName;\n"
                + "REMOVE,studentId - to remove student with given id;\n"
                + "SEARCH,studentId - to find student with given id;\n"
                + "MINIMUM - to find student with minimum id;\n"
                + "MEDIAN - to find student with median id;\n"
                + "MAXIMUM - to remove student with maximum id;\n"
                + "INORDER - to print all students info inorder;\n"
                + "PREORDER - to print all students info preorder;\n"
                + "POSTORDER - to print all students info postorder;\n"
                + "EXIT - to exit the program\n");
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        if (status != JFileChooser.APPROVE_OPTION)
            System.out.println("no file Chosen");
        else fileInsert(chooser.getSelectedFile());
        Scanner scanner = new Scanner(System.in);
        String command = null;
        while (!(command = scanner.nextLine()).equalsIgnoreCase(Command.EXIT.toString())) {
            executeCommand(command.trim());
        }
    }

    private static void fileInsert(File file) throws IOException {
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            int id = scan.nextInt();
            String name = scan.nextLine();
            tbt.insert(id, name);
        }
    }

    private static void executeCommand(String commandString) {
        String commandArgs[] = commandString.split(",");
        commandString = commandArgs[0];
        if (commandString != null) {
            Command command = Command.fromString(commandString);
            if (command == null) {
                System.out.println("Unknown command");
                return;
            }
            switch (command) {
                case INSERT:
                    if (commandArgs.length > 2) {
                        int id = getId(commandArgs[1]);
                        if (id > 0) {
                            String name = commandArgs[2];
                            tbt.insert(id, name);
                        }
                    } else
                        System.out.println("Not enough parameters");
                    break;
                case REMOVE:
                    if (commandArgs.length > 1) {
                        int id = getId(commandArgs[1]);
                        if (id > 0) {
                            tbt.remove(id);
                        }
                    } else
                        System.out.println("Not enough parameters");
                    break;
                case SEARCH:
                    if (commandArgs.length > 1) {
                        int id = getId(commandArgs[1]);
                        if (id > 0) {
                            tbt.search(id);
                        }
                    } else
                        System.out.println("Not enough parameters");
                    break;
                case MINIMUM:
                    tbt.minimum();
                    break;
                case MEDIAN:
                    tbt.median();
                    break;
                case MAXIMUM:
                    tbt.maximum();
                    break;
                case INORDER:
                    tbt.inOrderTreeWalk();
                    break;
                case PREORDER:
                    tbt.preOrderTreeWalk();
                    break;
                case POSTORDER:
                    tbt.postOrderTreeWalk();
                    break;
            }
        }
    }

    private static int getId(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id");
            return -1;
        }
    }
}
