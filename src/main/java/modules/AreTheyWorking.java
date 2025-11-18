package modules;

import persistence.LecturerCsvStorage;
import persistence.StudentCsvStorage;
import users.Lecturer;
import users.Student;

import java.util.ArrayList;
import java.util.List;

public class AreTheyWorking {

    public static void main(String[] args) throws Exception {
        /*
        String filePath = "src/main/resources/lecturersexample.csv";

        // 1️⃣ Generate 1000+ Lecturer objects
        List<Lecturer> lecturers = new ArrayList<>();
        for (int i = 1; i <= 14200; i++) {
            lecturers.add(new Lecturer(String.valueOf((15200 + i)), "Lecturer_" + (i+15200)));
        }

        // 2️⃣ Write them to CSV
        try (Writer writer = new FileWriter(filePath)) {
            StatefulBeanToCsv<Lecturer> beanToCsv = new StatefulBeanToCsvBuilder<Lecturer>(writer)
                    .withApplyQuotesToAll(false)
                    .build();
            beanToCsv.write(lecturers);
        }

        System.out.println("CSV with 1200 lecturers created!");

        // 3️⃣ Stream through CSV to find a lecturer by ID
        String searchId = "14200"; // example ID

        try (Reader reader = new FileReader(filePath)) {
            CsvToBean<Lecturer> csvToBean = new CsvToBeanBuilder<Lecturer>(reader)
                    .withType(Lecturer.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            boolean found = false;
            for (Lecturer lecturer : csvToBean) { // streaming one by one
                if (lecturer.getUserId().equals(searchId)) {
                    System.out.println("Found lecturer: " + lecturer);
                    found = true;
                    break; // stop after finding
                }
            }

            if (!found) {
                System.out.println("Lecturer with ID " + searchId + " not found.");
            }
        }
    */

        try {
            // 1️⃣ Create some sample lecturers
            List<Lecturer> lecturers = new ArrayList<>();
            lecturers.add(new Lecturer("1004", "Alex Smith"));
            lecturers.add(new Lecturer("1005", "Oisin Johnson"));
            lecturers.add(new Lecturer("1006", "James Brown"));

            LecturerCsvStorage storage = new LecturerCsvStorage();

            // 2️⃣ Save lecturers to CSV
            storage.saveAll(lecturers);
            System.out.println("Lecturers saved to CSV!");

            // 3️⃣ Load lecturers back from CSV
            List<Lecturer> loadedLecturers = storage.loadAll();

            // 4️⃣ Print them
            System.out.println("Lecturers loaded from CSV:");
            for (Lecturer l : loadedLecturers) {
                System.out.println(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            List<Student> students = new ArrayList<>();
            students.add(new Student("123", "Alex"));
            students.add(new Student("124", "Billy"));

            StudentCsvStorage studentCsvStorage = new StudentCsvStorage();
            studentCsvStorage.saveAll(students);
            System.out.println("Students saved to CSV!");

            List<Student> loadedStudents = studentCsvStorage.loadAll();
            System.out.println("Students loaded from CSV:");
            for (Student s : loadedStudents) {
                System.out.println(s);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        }
    }


