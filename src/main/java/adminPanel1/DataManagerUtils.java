package adminPanel1;

public class DataManagerUtils {

        public static void clearAll() {
            DataManager.students.clear();
            DataManager.teachers.clear();
            DataManager.rooms.clear();
            DataManager.groups.clear();
            DataManager.courseFulls.clear();
            DataManager.courseYears.clear();
            DataManager.courseSemesters.clear();
            DataManager.courseModules.clear();
            DataManager.timeSlots.clear();
        }
    }

