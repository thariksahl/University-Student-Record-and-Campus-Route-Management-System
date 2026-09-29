import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentLinkedList studentList =
            new StudentLinkedList();

    private static final ActionStack actionStack =
            new ActionStack();

    private static final ServiceQueue serviceQueue =
            new ServiceQueue();

    private static final StudentBST studentBST =
            new StudentBST();

    private static final StudentHashTable hashTable =
            new StudentHashTable();

    private static final CampusGraph campusGraph =
            new CampusGraph();

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("==============================================");
        System.out.println(" UNIVERSITY STUDENT RECORD AND CAMPUS SYSTEM ");
        System.out.println("==============================================");

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1 -> addStudent();

                case 2 -> updateStudent();

                case 3 -> deleteStudent();

                case 4 -> studentList.displayAll();

                case 5 -> addServiceRequest();

                case 6 -> processServiceRequest();

                case 7 -> actionStack.display();

                case 8 -> studentBST.displayInOrder();

                case 9 -> searchStudentUsingHashing();

                case 10 -> addCampusLocation();

                case 11 -> removeCampusLocation();

                case 12 -> addCampusConnection();

                case 13 -> removeCampusConnection();

                case 14 -> campusGraph.displayConnections();

                case 15 -> traverseCampus();

                case 16 -> {
                    running = false;
                    System.out.println("Exiting system...");
                }

                default ->
                        System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Add Student Record");
        System.out.println("2. Update Student Record");
        System.out.println("3. Delete Student Record");
        System.out.println("4. Display All Records using Linked List");
        System.out.println("5. Add Service Request to Queue");
        System.out.println("6. Process Next Service Request");
        System.out.println("7. Display Recent Actions using Stack");
        System.out.println("8. Display Students using BST");
        System.out.println("9. Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS");
        System.out.println("16. Exit");
        System.out.println("===============================");
    }

    private static void addStudent() {

        System.out.println("\n--- Add Student ---");

        String id = readNonEmpty("Student ID: ");

        if (studentList.search(id) != null) {
            System.out.println("Student ID already exists.");
            return;
        }

        String name = readNonEmpty("Name: ");
        String programme = readNonEmpty("Programme: ");

        double marks = readMarks();

        Student student =
                new Student(id, name, programme, marks);

        studentList.add(student);
        studentBST.insert(student);
        hashTable.put(student);

        actionStack.push("Added student: " + id);

        System.out.println("Student added successfully.");
    }

    private static void updateStudent() {

        System.out.println("\n--- Update Student ---");

        String id = readNonEmpty("Enter Student ID: ");

        Student student = studentList.search(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        String name = readNonEmpty("New name: ");
        String programme = readNonEmpty("New programme: ");
        double marks = readMarks();

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);

        hashTable.put(student);

        actionStack.push("Updated student: " + id);

        System.out.println("Student updated successfully.");
    }

    private static void deleteStudent() {

        System.out.println("\n--- Delete Student ---");

        String id = readNonEmpty("Enter Student ID: ");

        Student student = studentList.search(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        studentList.delete(id);
        studentBST.delete(id);
        hashTable.remove(id);

        actionStack.push("Deleted student: " + id);

        System.out.println("Student deleted successfully.");
    }

    private static void addServiceRequest() {

        System.out.println("\n--- Add Service Request ---");

        String requestId =
                readNonEmpty("Request ID: ");

        String studentId =
                readNonEmpty("Student ID: ");

        if (studentList.search(studentId) == null) {
            System.out.println("Student not found.");
            return;
        }

        String description =
                readNonEmpty("Request description: ");

        ServiceRequest request =
                new ServiceRequest(
                        requestId,
                        studentId,
                        description
                );

        serviceQueue.addRequest(request);

        actionStack.push(
                "Added service request: " + requestId
        );

        System.out.println("Service request added.");
    }

    private static void processServiceRequest() {

        ServiceRequest request =
                serviceQueue.processNextRequest();

        if (request == null) {
            System.out.println("No service requests available.");
            return;
        }

        System.out.println("Processing:");
        System.out.println(request);

        actionStack.push(
                "Processed service request: "
                        + request.getRequestId()
        );
    }

    private static void searchStudentUsingHashing() {

        System.out.println("\n--- Hash Search ---");

        String id = readNonEmpty("Student ID: ");

        Student student = hashTable.search(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found:");
            System.out.println(student);
        }
    }

    private static void addCampusLocation() {

        String location =
                readNonEmpty("Enter campus location: ");

        if (campusGraph.addLocation(location)) {
            System.out.println("Location added.");
        } else {
            System.out.println(
                    "Location already exists or is invalid."
            );
        }
    }

    private static void removeCampusLocation() {

        String location =
                readNonEmpty("Enter campus location: ");

        if (campusGraph.removeLocation(location)) {
            System.out.println("Location removed.");
        } else {
            System.out.println("Location not found.");
        }
    }

    private static void addCampusConnection() {

        String location1 =
                readNonEmpty("First location: ");

        String location2 =
                readNonEmpty("Second location: ");

        if (campusGraph.addConnection(location1, location2)) {
            System.out.println("Connection added.");
        } else {
            System.out.println(
                    "Unable to add connection."
            );
        }
    }

    private static void removeCampusConnection() {

        String location1 =
                readNonEmpty("First location: ");

        String location2 =
                readNonEmpty("Second location: ");

        if (campusGraph.removeConnection(location1, location2)) {
            System.out.println("Connection removed.");
        } else {
            System.out.println(
                    "Connection not found."
            );
        }
    }

    private static void traverseCampus() {

        String start =
                readNonEmpty("Starting location: ");

        campusGraph.bfs(start);
    }

    private static String readNonEmpty(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }

    private static double readMarks() {

        while (true) {

            System.out.print("Marks (0-100): ");

            String input = scanner.nextLine().trim();

            try {

                double marks = Double.parseDouble(input);

                if (marks >= 0 && marks <= 100) {
                    return marks;
                }

                System.out.println(
                        "Marks must be between 0 and 100."
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid mark."
                );
            }
        }
    }
}