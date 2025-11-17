package src.main.java.ui.cli1;

import src.main.java.users.Lecturer;

import java.util.List;

/**
 * Defines the operations required for managing lecturer data.
 * <p>
 * This interface is used for the same reason as {@link StudentService}:
 * it allows the CLI to work independently from the underlying data storage.
 * </p>
 */
public interface LecturerService
{
    /**
     * Adds a new lecturer to the system.
     *
     * @param id the lecturer's unique ID
     * @param name the lecturer's name
     * @return true if the lecturer was added; false if the ID already exists
     */
    boolean addLecturer(int id, String name);   // Add a lecturer

    /**
     * Updates a specific field of a lecturer.
     *
     * @param id the lecturer's ID
     * @param field the field to update (e.g., "firstName")
     * @param newValue the new value for that field
     * @return true if the update succeeded; false if invalid ID or field
     */
//    boolean updateLecturerField(int id, String field, String newValue);   // Update a field

    /**
     * Finds a lecturer by their unique ID.
     *
     * @param id the lecturer's ID
     * @return the Lecturer object if found; null otherwise
     */
    Lecturer findLecturerById(String id);   // Get a lecturer by ID

    /**
     * Retrieves every lecturer stored in the system.
     *
     * @return a list of all lecturers
     */
    List<Lecturer> getAllLecturers();   // Return all lecturers
}
