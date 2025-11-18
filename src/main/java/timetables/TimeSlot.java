package timetables;
import modules.CourseModule;


/**
 * A TimeSlot is the place in the timetable that keeps the information of each user, room and module at a specific place
 * in the timetable
 */
public class TimeSlot{
    private String day;
    private String time;
    private String dayTime;
    //private LectureRoom lectureRoom;

    private CourseModule module;
    // group will hold list of students
    private Group group;

    public TimeSlot(String day, String time, CourseModule module){
        day = day.toLowerCase();
        // NEED CHECK FOR IF DAY IS mon tue wed thu fri

        this.day = day;
        this.time = time;
        // NEED CHECK IF TIME IS valid 24 hour clock no characters e.g 2240, NOT 22:40 or 2280

        dayTime = day + "" + time;
//        this.group = new Group(module);
    }

    /**
     * Compares a TimeSlot with another TimeSlot to check if same time
     * (based on the value returned by toString(), which is dayTime)
     * @param o the object to be compared.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        // Check for null and class type
        if (o == null || getClass() != o.getClass()) return false;

        // Casts the object to TimeSlot
        TimeSlot that = (TimeSlot) o;

         return this.dayTime.equals(that.dayTime);
    }
    @Override
    public int hashCode(){
        return this.toString().hashCode();
    }

    /**
     * Returns the timeslot object as a string
     * @return the time, module, student and room
     */
    @Override
    public String toString(){
        return dayTime;
    }

}
