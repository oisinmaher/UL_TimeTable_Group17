package src.main.java.ui.cli1;

import src.main.java.users.Student;

import java.util.List;

/**
 * Defines the contract for all operations related to managing student data.
 * <p>
 * Using an <b>interface</b> here is the best choice because:
 * <ul>
 *     <li>It separates the <b>behaviour</b> (what the system can do) from the
 *         <b>implementation</b> (how it is done).</li>
 *     <li>It allows you to easily swap between different data storage methods,
 *         such as in-memory storage now and CSV-based storage later, without
 *         changing any CLI code.</li>
 *     <li>It keeps the system flexible and supports the addition of future
 *         implementations (e.g., database, JSON, or API-driven storage).</li>
 * </ul>
 * All menu sessions and admin commands rely only on this interface, meaning
 * they will continue to work even when you change the underlying data source.
 */
public interface StudentService 
{
    /**
     * Adds a new student to the system.
     *
     * @param id the unique identifier for the student
     * @param name the student's name
     * @return true if the student was added successfully; false if the ID already exists
     */
    boolean addStudent(int id, String name);   // Add a new student

    /**
     * Removes a student from the system.
     *
     * @param id the ID of the student to remove
     * @return true if the student was found and removed; false otherwise
     */
    boolean removeStudent(String id);   // Remove an existing student

    /**
     * Updates a specific field of a student (e.g., firstName or lastName).
     *
     * @param id the ID of the student to update
     * @param field the name of the field to update (e.g. "firstName")
     * @param newValue the new value to assign to that field
     * @return true if the update succeeded; false if the ID or field is invalid
     */
    boolean updateStudentField(String id, String field, String newValue);   // Update one attribute of a student

    /**
     * Searches for a student using their ID.
     *
     * @param id the ID of the student
     * @return the Student object if found; null otherwise
     */
    Student findStudentbyID(String id);   // Find a student by their ID

    /**
     * Retrieves a list of all students currently stored in the system.
     *
     * @return a list of all Student objects
     */
    List<Student> getAllStudents();   // Get all students in the system
}
