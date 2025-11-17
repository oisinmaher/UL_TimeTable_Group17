package src.main.java.adminPanel;

/**
 * This class will read in data from csv files and initialise objects
 * The data will be strings so it will have to create new objects
 * The order of reading in matters, as well as sometimes having to reread info
 * for example Student have TimeTable variable, and TimeTable has Student variable
 * So for instance we can Create Student object first and not have it point to a timetable (as there is no available object)
 * Create a timetable and point it to aforementioned student as well as then pointing that student to new instance
 */
public class ReadData {

}
