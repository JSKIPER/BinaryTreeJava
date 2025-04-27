import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // creating object of binary tree class
        BinaryTree binaryTree = new BinaryTree();
        // options for joption pane
        String[] options = {"Display", "Insert", "Remove","Find"};
        // try catch for errors
        try{
            // infinite loop
            while(true){
                // message dialogue with buttons declearing integer and store users input in it
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Select an action",
                        "Binary Tree",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                // if user closing windows he will get bye output
                if (choice == -1) {
                    JOptionPane.showMessageDialog(null, "Bye!");
                    break;
                }
                // switch case
                switch (choice) {
                    case 0: // display
                        // calling function in binary tree to display it returning String
                        JOptionPane.showMessageDialog(null, binaryTree.display());
                        break;

                    case 1: // insert
                        // asking user for input and calling the function
                        String insertValue = JOptionPane.showInputDialog(null, "Enter a Stromg to insert:");
                        binaryTree.insert(insertValue);
                        break;

                    case 2: // Remove
                        // asking user for input and calling the function
                        String deleteValue = JOptionPane.showInputDialog(null, "Enter a String to delete: ");
                        binaryTree.delete(deleteValue);
                        break;

                    case 3: // find
                        // asking user for input and calling the function
                        String findValue = JOptionPane.showInputDialog(null, "Enter a String to find: ");
                        binaryTree.find(findValue);

                        // if wrong choice
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice.");
                }

            }
            // if user wrote invalid value like integer
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Invalid value.");
        }


    }
}
// class for node that has String value and links to 2 other nodes
class Node {
    String value;
    Node left, right;
    //Constructor
    public Node(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

// BinaryTree class for all operation
class BinaryTree {
    // declaring veriables displayValues is used for storing string of putput of values of binary tree
    private String displayValues;
    private Node root;
    // constructor to declare root node as null
    public BinaryTree() {
        root = null;
    }

    // Function to Insert a node maintaining alphabetical order
    public void insert(String value) {
        // calling recursion and updating root
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, String value) {
        // if node equals to null we find right spot and stopping recursion creating new node with users value
        if (node == null) {
            return new Node(value);
        }
        // finding right spot by comparing values and calling recursions
        if (value.compareTo(node.value) < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insertRecursive(node.right, value);
        }
        // to update root
        return node;
    }

    // Delete a specified item
    public void delete(String value) {
        Node parent = null;
        Node current = root;

        // Find the node to delete
        while (current != null && !current.value.equals(value)) {
            parent = current;
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        // Node not found
        if (current == null) {
            JOptionPane.showMessageDialog(null, "Value not found");
            return;
        }

        // Node with two children
        if (current.left != null && current.right != null) {
            Node successorParent = current;
            Node successor = current.right;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.value = successor.value;
            current = successor;
            parent = successorParent;
        }

        // Node with zero or one child
        Node child = null;

        if (current.left != null) {
            child = current.left;  // Use the left child if it exists
        } else {
            child = current.right; // Otherwise, use the right child
        }

        if (parent == null) {
            root = child; // Deleting root node
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }
        JOptionPane.showMessageDialog(null, value +" Deleted");
    }

    //  Output the entire contents of the tree in order
    public String display() {
        // reseting display values and calling recursive function to output value of binary tree in order and returnig string for JOptionPane
        displayValues ="";
        displayRecursive(root);
        return displayValues.toString();


    }

    private void displayRecursive(Node node) {
        // we are going to the lowest left then stopping recursion adding value to string the calling right child and doing again until we went over all values from lowest to biggest
        if (node != null) {
            displayRecursive(node.left);
            displayValues += node.value + "\n";
            displayRecursive(node.right);
        }
    }
    // function to find specific value
    public void find(String value){
        // while loop until we find our node if current == null node is not in tree, we just comparing number and making current left or right
        Node current = root;
        while (current != null) {
            if (value.toLowerCase().compareTo(current.value.toLowerCase()) < 0) {
                current = current.left;
            }else if (value.toLowerCase().compareTo(current.value.toLowerCase()) > 0) {
                current = current.right;
            }else{
                JOptionPane.showMessageDialog(null, "Value found");

                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Value not found");

    }

}