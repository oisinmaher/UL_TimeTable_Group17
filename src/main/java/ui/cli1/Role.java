package src.main.java.ui.cli1;

/**
 * Represents the different categories of users who can interact with the system
 * through the hybrid command-line interface (CLI).
 * <p>
 * Each role determines which type of interface and features the user can access:
 * </p>
 * <ul>
 *     <li><b>STUDENT</b> – Uses a menu-based interface to view personal details
 *         or timetable-related information.</li>
 *     <li><b>LECTURER</b> – Uses a menu-based interface to access lecturer-specific
 *         options such as viewing personal details.</li>
 *     <li><b>ADMIN</b> – Uses a command-based interface with full management
 *         capabilities (e.g., adding, removing, or updating students and lecturers).</li>
 * </ul>
 *
 * This enum allows the system to clearly distinguish user permissions and
 * route each user to the appropriate interaction mode.
 */
public enum Role 
{
    /** Standard student user; interacts with the system through menu options. */
    STUDENT,

    /** Lecturer user; similar to student but with lecturer-specific options. */
    LECTURER,

    /** Administrator user; uses command-based controls with full system access. */
    ADMIN
}
