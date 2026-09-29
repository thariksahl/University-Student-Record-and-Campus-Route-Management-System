import java.util.LinkedList;
import java.util.List;

public class StudentHashTable {

    private static final int SIZE = 10;

    private final List<Student>[] table;

    @SuppressWarnings("unchecked")
    public StudentHashTable() {

        table = new List[SIZE];

        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(String studentId) {

        return Math.floorMod(
                studentId.toUpperCase().hashCode(),
                SIZE
        );
    }

    public boolean put(Student student) {

        if (student == null) {
            return false;
        }

        int index = hash(student.getStudentId());

        List<Student> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {

            if (bucket.get(i).getStudentId()
                    .equalsIgnoreCase(student.getStudentId())) {

                bucket.set(i, student);
                return false;
            }
        }

        bucket.add(student);
        return true;
    }

    public Student search(String studentId) {

        int index = hash(studentId);

        for (Student student : table[index]) {

            if (student.getStudentId()
                    .equalsIgnoreCase(studentId)) {

                return student;
            }
        }

        return null;
    }

    public boolean remove(String studentId) {

        int index = hash(studentId);

        List<Student> bucket = table[index];

        for (int i = 0; i < bucket.size(); i++) {

            if (bucket.get(i).getStudentId()
                    .equalsIgnoreCase(studentId)) {

                bucket.remove(i);
                return true;
            }
        }

        return false;
    }

    public void display() {

        System.out.println("\n===== HASH TABLE =====");

        for (int i = 0; i < SIZE; i++) {

            System.out.print("Bucket " + i + ": ");

            if (table[i].isEmpty()) {
                System.out.println("Empty");
                continue;
            }

            for (Student student : table[i]) {
                System.out.print(
                        "[" + student.getStudentId() + "] "
                );
            }

            System.out.println();
        }

        System.out.println("======================");
    }
}