package src.main.java.ui.cli1;

import java.util.List;

public interface CourseFullService {
    public boolean createCourse(String courseCode, String courseName);

    public String getNameFromCode(String courseCode);

    public List<String> listAllCourses();

    public List<String> listCourseYears(String courseCode);
}
