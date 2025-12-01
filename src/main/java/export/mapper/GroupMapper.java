package export.mapper;

import export.dto.GroupCsvDto;
import modules.CourseModule;
import programCourse.CourseFull;
import timetables.Group;
import timetables.TimeSlot;
import rooms.Room;

import java.util.ArrayList;
import java.util.List;


public class GroupMapper {

    public static GroupCsvDto flatten(Group group) {

        List<String> rooms = new ArrayList<>();
        for (TimeSlot ts : group.getTimeSlots()) {
            rooms.add(ts.getRoom().getRoomID());
        }
        String roomIds = String.join(",", rooms);

        List<String> timeslots = new ArrayList<>();
        for(TimeSlot ts : group.getTimeSlots()){
            timeslots.add(ts.getTime());
        }
        String usedTimes = String.join(",", timeslots);

        List<String> courseCodes = new ArrayList<>();
        for(CourseFull cf : group.getCoursesTakingThisModule()){
            courseCodes.add(cf.getCode());
        }
        String courses = String.join(",", courseCodes);

        return new GroupCsvDto(
                group.getCourseModule().getModuleCode(),
                courses,
                group.getTeacherObject().getUserId(),
                roomIds
        );

    }

    public static Group inflate(GroupCsvDto groupCsvDto) {

        CourseModule module = CourseModule.getModuleFromCode(groupCsvDto.getModuleCode());

        Group group = new Group(module, new ArrayList<>());

        String teacherId = groupCsvDto.getTeacher();
        if (teacherId != null && !teacherId.isEmpty()) {
            group.setTeacher(teacherId);
        }

        String[] courseFulls = groupCsvDto.getCourseCode().split(",");
        for(String courseCode : courseFulls){
            CourseFull cf = CourseFull.getCourseFromCode(courseCode);
            group.getCoursesTakingThisModule().add(cf);
        }

        String[] rooms = groupCsvDto.getRooms().split(",");
        for(String roomId : rooms){
            Room room = Room.getRoomFromId(roomId);
            group.getRooms().add(room);
        }

        return group;

    }
}
