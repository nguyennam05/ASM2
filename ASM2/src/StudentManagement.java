import java.util.*;

class Student {
    private String studentId;
    private String studentName;
    private double marks;

    // Constructor
    public Student(String studentId, String studentName, double marks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.marks = marks;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Method to rank students based on marks
    public String getRanking() {
        if (marks < 5.0) {
            return "Fail";
        } else if (marks < 6.5) {
            return "Medium";
        } else if (marks < 7.5) {
            return "Good";
        } else if (marks < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + studentName + ", Marks: " + marks + ", Rank: " + getRanking();
    }
}

public class StudentManagement {
    private List<Student> students = new ArrayList<>();

    // Add a new student
    public void addStudent(String studentId, String studentName, double marks) {
        students.add(new Student(studentId, studentName, marks));
    }

    // Edit student details
    public void editStudent(String studentId, String newName, double newMarks) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                student.setStudentName(newName);
                student.setMarks(newMarks);
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    // Delete a student
    public void deleteStudent(String studentId) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId().equals(studentId)) {
                iterator.remove();
                System.out.println("Student removed successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    // Search for a student by ID
    public void searchStudent(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with ID " + studentId + " not found.");
    }

    // Merge sort method to sort students by marks in descending order
    public void mergeSort(List<Student> studentList, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;

            // Sort first and second halves
            mergeSort(studentList, left, middle);
            mergeSort(studentList, middle + 1, right);

            // Merge the sorted halves
            merge(studentList, left, middle, right);
        }
    }

    // Helper method to merge two halves
    private void merge(List<Student> studentList, int left, int middle, int right) {
        // Calculate the sizes of two subarrays to be merged
        int n1 = middle - left + 1;
        int n2 = right - middle;

        // Create temporary subarrays
        List<Student> leftArray = new ArrayList<>(n1);
        List<Student> rightArray = new ArrayList<>(n2);

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++)
            leftArray.add(studentList.get(left + i));
        for (int j = 0; j < n2; j++)
            rightArray.add(studentList.get(middle + 1 + j));

        // Initial indices for subarrays and merged array
        int i = 0, j = 0, k = left;

        // Merge the temporary arrays back into the original list
        while (i < n1 && j < n2) {
            if (leftArray.get(i).getMarks() >= rightArray.get(j).getMarks()) {
                studentList.set(k, leftArray.get(i));
                i++;
            } else {
                studentList.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray if any
        while (i < n1) {
            studentList.set(k, leftArray.get(i));
            i++;
            k++;
        }

        // Copy remaining elements of rightArray if any
        while (j < n2) {
            studentList.set(k, rightArray.get(j));
            j++;
            k++;
        }
    }

    // Sort students by marks using merge sort
    public void sortStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the list to sort.");
        } else {
            mergeSort(students, 0, students.size() - 1);
            System.out.println("Students sorted by marks in descending order:");
            displayAllStudents();
        }
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the list.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Sort Students");
            System.out.println("6. Display All Students");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character left after nextInt()

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String id = scanner.nextLine().trim(); // Trim extra spaces
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine().trim(); // Accept and trim input with spaces and diacritics
                    System.out.print("Enter student marks: ");
                    double marks = scanner.nextDouble();
                    management.addStudent(id, name, marks);
                    break;
                case 2:
                    System.out.print("Enter student ID to edit: ");
                    String editId = scanner.nextLine().trim();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine().trim(); // Accept and trim input with spaces and diacritics
                    System.out.print("Enter new marks: ");
                    double newMarks = scanner.nextDouble();
                    management.editStudent(editId, newName, newMarks);
                    break;
                case 3:
                    System.out.print("Enter student ID to delete: ");
                    String deleteId = scanner.nextLine().trim();
                    management.deleteStudent(deleteId);
                    break;
                case 4:
                    System.out.print("Enter student ID to search: ");
                    String searchId = scanner.nextLine().trim();
                    management.searchStudent(searchId);
                    break;
                case 5:
                    management.sortStudents();
                    break;
                case 6:
                    management.displayAllStudents();
                    break;
                case 7:
                    System.out.println("Exiting the program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
