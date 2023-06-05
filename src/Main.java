import java.util.Scanner; //

class Node {
    int key;
    Node left, right;
    public Node(int item) {
        key = item;
        left = right = null;
    }
}
class BinaryTree {
    Node root;
    BinaryTree() {
        root = null;
    }
    void insert(int key) {
        root = insertRec(root, key);
    }
    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }
    void inorder() {
        inorderRec(root);
    }
    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }
    Node search(Node root, int key) {
        if (root == null || root.key == key)
            return root;
        if (key < root.key)
            return search(root.left, key);
        return search(root.right, key);
    }
    void delete(int key) {
        root = deleteRec(root, key);
    }
    Node deleteRec(Node root, int key) {
        if (root == null)
            return root;
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }
    int minValue(Node root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        while (true) {
            System.out.println("\nBinary Tree Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Inorder Traversal");
            System.out.println("3. Search");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the key to insert: ");
                    int key = scanner.nextInt();
                    tree.insert(key);
                    System.out.println("Key " + key + " inserted into the tree.");
                    break;
                case 2:
                    System.out.print("Inorder Traversal: ");
                    tree.inorder();
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter the key to search: ");
                    key = scanner.nextInt();
                    Node result = tree.search(tree.root, key);
                    if (result != null)
                        System.out.println("Key " + key + " found in the tree.");
                    else
                        System.out.println("Key " + key + " not found in the tree.");
                    break;
                case 4:
                    System.out.print("Enter the key to delete: ");
                    key = scanner.nextInt();
                    tree.delete(key);
                    System.out.println("Key " + key + " deleted from the tree.");
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}