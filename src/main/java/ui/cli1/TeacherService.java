package src.main.java.ui.cli1;

import src.main.java.users.Teacher;

import java.util.List;

/**
 * Defines the operations required for managing Teacher data.
 * <p>
 * This interface is used for the same reason as {@link StudentService}:
 * it allows the CLI to work independently from the underlying data storage.
 * </p>
 */
public interface TeacherService
{
    /**
     * Adds a new Teacher to the system.
     *
     * @param id the Teacher's unique ID
     * @param name the Teacher's name
     * @return true if the Teacher was added; false if the ID already exists
     */
    boolean addTeacher(String id, String name);   // Add a Teacher

    /**
     * Updates a specific field of a Teacher.
     *
     * @param id the Teacher's ID
     * @param field the field to update (e.g., "firstName")
     * @param newValue the new value for that field
     * @return true if the update succeeded; false if invalid ID or field
     */
    boolean updateTeacherField(String id, String field, String newValue);   // Update a field

    /**
     * Finds a Teacher by their unique ID.
     *
     * @param id the Teacher's ID
     * @return the Teacher object if found; null otherwise
     */
    Teacher findTeacherById(String id);   // Get a Teacher by ID

    /**
     * Retrieves every Teacher stored in the system.
     *
     * @return a list of all Teachers
     */
    List<Teacher> getAllTeachers();   // Return all Teachers
}
