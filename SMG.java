import java.io.*;
import java.util.*;

class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | GPA: %.2f", id, name, gpa);
    }
}

class StudentManager {
    private List<Student> students;
    private final String filePath = "students.dat";

    public StudentManager() {
        students = new ArrayList<>();
        loadFromFile();
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
    }

    public boolean updateStudent(int id, String name, double gpa) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(name);
                s.setGpa(gpa);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public Student searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    public void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
    }

    public void sortByGpa() {
        students.sort(Comparator.comparingDouble(Student::getGpa).reversed());
    }

    public void sortById() {
        students.sort(Comparator.comparingInt(Student::getId));
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}

public class StudentManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. List All Students");
            System.out.println("6. Sort by Name");
            System.out.println("7. Sort by GPA");
            System.out.println("8. Sort by ID");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> searchStudent();
                case 5 -> manager.listStudents();
                case 6 -> {
                    manager.sortByName();
                    System.out.println("Sorted by name.");
                }
                case 7 -> {
                    manager.sortByGpa();
                    System.out.println("Sorted by GPA.");
                }
                case 8 -> {
                    manager.sortById();
                    System.out.println("Sorted by ID.");
                }
                case 9 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter GPA: ");
        double gpa = scanner.nextDouble();

        manager.addStudent(new Student(id, name, gpa));
        System.out.println("Student added.");
    }

    private static void updateStudent() {
        System.out.print("Enter ID of student to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New GPA: ");
        double gpa = scanner.nextDouble();

        if (manager.updateStudent(id, name, gpa)) {
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter ID of student to delete: ");
        int id = scanner.nextInt();
        if (manager.deleteStudent(id)) {
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        Student s = manager.searchStudent(id);
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("Student not found.");
        }
    }
}
