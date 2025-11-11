package src.main.java;
import src.main.java.modules.Module;
import src.main.java.rooms.LectureRoom;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Module oop = new Module("OOP", "CS4013");
        oop.setLecHours(6);

        System.out.println("Lecture hours=" + oop.getLecHours() + ", module=" + oop.getModuleCode() +
                ", name=" + oop.getModuleName());

        LectureRoom csg001 = new LectureRoom("CSG-001", " ", 100);

        csg001.setRoomType("Lecture rooms.Room");
        System.out.println("Roomtype=" + csg001.getRoomType() + ", name=" + csg001.getRoomID() +
                ", capacity=" + csg001.getMaxCapacity());
    }
}
