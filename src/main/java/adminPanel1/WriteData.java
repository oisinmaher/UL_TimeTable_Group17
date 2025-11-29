package adminPanel1;

import export.ExportService;
import export.dto.*;
import export.io.CsvWriterUtil;
import export.mapper.CourseFullMapper;
import export.mapper.StudentMapper;
import programCourse.CourseFull;
import users.Student;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Writes all domain objects to CSV files.
 * Mirrors the ReadData class.
 */
public class WriteData {

    public void writeAll() throws URISyntaxException {

        ExportService exportService = new ExportService();

        //Writing students
        Path studentPath = Paths.get(getClass().getClassLoader().getResource("resources/StudentData.csv").toURI());
        CsvWriterUtil<StudentCsvDto> studentWriter = new CsvWriterUtil<>(studentPath, StudentCsvDto.class);
        List<Student> domainStudents = new ArrayList<>(DataManager.students.values());
        List<StudentCsvDto> flatStudents = new ArrayList<>(domainStudents.size());
        for (Student student : domainStudents) {
            StudentCsvDto studentCsvDto = StudentMapper.flatten(student);
            flatStudents.add(studentCsvDto);
        }
        try {
            exportService.export(flatStudents, studentWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Path courseFullPath = Paths.get(getClass().getClassLoader().getResource("resources/CourseFull.csv").toURI());
        CsvWriterUtil<CourseFullCsvDto> courseFullWriter = new CsvWriterUtil<>(courseFullPath, CourseFullCsvDto.class);
        List<CourseFull> domainCourseFulls = new ArrayList<>(DataManager.courseFulls.values());
        List<CourseFullCsvDto> flatCourseFulls = new ArrayList<>(domainCourseFulls.size());
        for (CourseFull courseFull : domainCourseFulls) {
            CourseFullCsvDto csvDto = CourseFullMapper.flatten(courseFull);
            flatCourseFulls.add(csvDto);
        }
        try {
            exportService.export(flatCourseFulls,courseFullWriter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }




    }
}