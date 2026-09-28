public class StudentLinkedList {

    private static class Node {
        Student student;
        Node next;

        Node(Student student) {
            this.student = student;
        }
    }

    private Node head;
    private int size;

    public boolean add(Student student) {
        if (student == null) {
            return false;
        }

        if (search(student.getStudentId()) != null) {
            return false;
        }

        Node newNode = new Node(student);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
        return true;
    }

    public Student search(String studentId) {
        Node current = head;

        while (current != null) {
            if (current.student.getStudentId().equalsIgnoreCase(studentId)) {
                return current.student;
            }

            current = current.next;
        }

        return null;
    }

    public boolean update(
            String studentId,
            String name,
            String programme,
            double marks
    ) {
        Student student = search(studentId);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        return true;
    }

    public boolean delete(String studentId) {
        if (head == null) {
            return false;
        }

        if (head.student.getStudentId().equalsIgnoreCase(studentId)) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            if (current.next.student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                current.next = current.next.next;
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public void displayAll() {
        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        Node current = head;
        int number = 1;

        System.out.println("\n===== STUDENT RECORDS =====");

        while (current != null) {
            System.out.println(number + ". " + current.student);
            current = current.next;
            number++;
        }

        System.out.println("===========================");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }
}