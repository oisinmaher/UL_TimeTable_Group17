import export.ExportService;
import export.dto.StudentCsvDto;
import export.io.CsvReaderUtil;
import export.mapper.StudentMapper;
import export.io.CsvWriterUtil;
import programCourse.CourseFull;
import programCourse.CourseSemester;
import programCourse.CourseYear;
import users.Student;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestCsv {

    public static void main(String[] args) {

        CourseFull ec121 = new CourseFull("EC121", "Bcom Econ");
        ec121.addCourseYear("1");
        CourseYear AY25ec121Year1;
        AY25ec121Year1 = ec121.getCourseYears().get(0);
        AY25ec121Year1.addSemester("Autumn");
        CourseSemester AY25ec121Y1sem1 = AY25ec121Year1.getSemesters().get(0);
        AY25ec121Y1sem1.addNewModule("EC101", "Intro to Econ");


        Student student = new Student("123456", "Alex", "EC121", "1");

        StudentCsvDto studentCsvDto = StudentMapper.flatten(student);

        ExportService exportService = new ExportService();

        String filepath = "D:\\UL Work\\Yr 2 Sem 1\\CS4013 - OOP\\TimetableProject\\UL_TimeTable_Group17\\src\\test\\resources\\csvstudentstest.csv";
        Path path = Paths.get(filepath);

        CsvWriterUtil<StudentCsvDto> writer = new CsvWriterUtil<>(path, StudentCsvDto.class);

        try {
            exportService.export(studentCsvDto, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        CsvReaderUtil<StudentCsvDto> reader = new CsvReaderUtil<>(path, StudentCsvDto.class);

        List<StudentCsvDto> students = new ArrayList<>();

        List<StudentCsvDto> studentCsvDtoList = reader.readAll();
        for (StudentCsvDto s: students){
            System.out.println(s.getStudentId());
        }


    }

}
