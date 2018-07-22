import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Authors:
 * Katrin Ten, 309893386
 * Marina Rappoport, 342432044
 * <p>
 * The program allows to handle student info (student name and id):
 * insert, remove, find according to id, find minimum|maximum|median id,
 * print all info in different order(preorder, inorder, postorder)
 * Supported input from the console and from the external file.
 * Output is to console.
 */
public class Main {
    private static ThreadedBinaryTree tbt = new ThreadedBinaryTree();

    public static void main(String[] args) throws IOException {
        System.out.println("Print commands, each on new string\n"
                + "There is support for the following commands:\n"
                + "INSERT,studentId,studentName - to insert new student info;\n"
                + "REMOVE,studentId - to remove student with given id;\n"
                + "SEARCH,studentId - to find student with given id;\n"
                + "MINIMUM - to find student with minimum id;\n"
                + "MEDIAN - to find student with median id;\n"
                + "MAXIMUM - to remove student with maximum id;\n"
                + "PREDECESSOR,id - to find student with predecessor id;\n"
                + "SUCCESSOR,id - to find student with successor id;\n"
                + "INORDER - to print all students info inorder;\n"
                + "PREORDER - to print all students info preorder;\n"
                + "POSTORDER - to print all students info postorder;\n"
                + "EXIT - to exit the program\n");
        JFileChooser chooser = new JFileChooser();
        int status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION)
            fileInsert(chooser.getSelectedFile());
        else {
            System.out.println("No file chosen, will use console input");
            Scanner scanner = new Scanner(System.in);
            String command = null;
            while (!(command = scanner.nextLine()).equalsIgnoreCase(Command.EXIT.toString())) {
                executeCommand(command.trim());
            }
        }
    }

    private static void fileInsert(File file) throws IOException {
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String command = scan.nextLine();
            executeCommand(command);
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
                        if (id > 0) tbt.remove(id);
                    } else
                        System.out.println("Not enough parameters");
                    break;
                case SEARCH:
                    if (commandArgs.length > 1) {
                        int id = getId(commandArgs[1]);
                        if (id > 0) tbt.search(id);
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
                case PREDECESSOR:
                    if (commandArgs.length > 1) {
                        int id = getId(commandArgs[1]);
                        if (id > 0) tbt.predecessor(id);
                    } else
                        System.out.println("Not enough parameters");
                    break;
                case SUCCESSOR:
                    if (commandArgs.length > 1) {
                        int id = getId(commandArgs[1]);
                        if (id > 0) tbt.successor(id);
                    } else
                        System.out.println("Not enough parameters");
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

    /**
     * parse integer from string
     *
     * @param arg string argument
     * @return parsed integer
     */
    private static int getId(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.out.println("Invalid id");
            return -1;
        }
    }
}
