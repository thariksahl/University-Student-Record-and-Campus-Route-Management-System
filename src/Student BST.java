public class StudentBST {

    private static class Node {
        Student student;
        Node left;
        Node right;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node root;

    public boolean insert(Student student) {
        if (student == null) {
            return false;
        }

        if (root == null) {
            root = new Node(student);
            return true;
        }

        return insertRecursive(root, student);
    }

    private boolean insertRecursive(Node current, Student student) {

        int comparison = student.getStudentId()
                .compareToIgnoreCase(current.student.getStudentId());

        if (comparison == 0) {
            return false;
        }

        if (comparison < 0) {

            if (current.left == null) {
                current.left = new Node(student);
                return true;
            }

            return insertRecursive(current.left, student);

        } else {

            if (current.right == null) {
                current.right = new Node(student);
                return true;
            }

            return insertRecursive(current.right, student);
        }
    }

    public Student search(String studentId) {
        Node current = root;

        while (current != null) {

            int comparison = studentId
                    .compareToIgnoreCase(current.student.getStudentId());

            if (comparison == 0) {
                return current.student;
            }

            if (comparison < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("BST is empty.");
            return;
        }

        System.out.println("\n===== STUDENTS USING BST =====");
        inOrder(root);
        System.out.println("==============================");
    }

    private void inOrder(Node node) {

        if (node == null) {
            return;
        }

        inOrder(node.left);

        System.out.println(node.student);

        inOrder(node.right);
    }

    public boolean delete(String studentId) {

        if (search(studentId) == null) {
            return false;
        }

        root = deleteRecursive(root, studentId);
        return true;
    }

    private Node deleteRecursive(Node node, String studentId) {

        if (node == null) {
            return null;
        }

        int comparison = studentId
                .compareToIgnoreCase(node.student.getStudentId());

        if (comparison < 0) {

            node.left = deleteRecursive(node.left, studentId);

        } else if (comparison > 0) {

            node.right = deleteRecursive(node.right, studentId);

        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMinimum(node.right);

            node.student = successor.student;

            node.right = deleteRecursive(
                    node.right,
                    successor.student.getStudentId()
            );
        }

        return node;
    }

    private Node findMinimum(Node node) {

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}