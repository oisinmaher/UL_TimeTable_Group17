package src.main.java.ui.cli1;

import src.main.java.users.Lecturer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLecturerService implements LecturerService {

    private final Map<Integer, Lecturer> lecturers = new HashMap<>();

    public InMemoryLecturerService() {
        
        addLecturer(123, "Michael");
        addLecturer(1243, "Alan");
    }

//    @Override
    public boolean addLecturer(int id, String name) {
        if (lecturers.containsKey(id)) {
            return false;
        }
        lecturers.put(id, new Lecturer(name, id));
        return true;
    }

//    @Override
//    public boolean updateLecturerField(String id, String field, String newValue) {
//        Lecturer l = lecturers.get(id);
//        if (l == null) return false;
//
//        switch(field.toLowerCase()) {
//            case "name":
//                l.setName(newValue);
//                return true;
//            default:
//                return false;
//        }
//    }

    @Override
    public Lecturer findLecturerById(String id) {
        return lecturers.get(id);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return new ArrayList<>(lecturers.values());
    }
}
