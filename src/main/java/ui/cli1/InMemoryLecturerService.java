package ui.cli1;

import users.Lecturer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLecturerService implements LecturerService {

    /**
     * Maps lectureId (as String) to Lecturer object
     */
    private final Map<String, Lecturer> lecturers = new HashMap<>();

    public InMemoryLecturerService() {
        
        addLecturer("123", "Michael");
        addLecturer("455", "Alan");
    }

//    @Override
    public boolean addLecturer(String id, String name) {
        if (lecturers.containsKey(id)) {
            return false;
        }
        lecturers.put(id, new Lecturer(name, id));
        return true;
    }

    @Override
    public boolean updateLecturerField(String id, String field, String newValue) {
        Lecturer l = lecturers.get(id);
        if (l == null) return false;

        switch(field.toLowerCase()) {
            case "name":
                l.setName(newValue);
                return true;
            default:
                return false;
        }
    }

    @Override
    public Lecturer findLecturerById(String id) {
        return lecturers.get(id);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return new ArrayList<>(lecturers.values());
    }
}
