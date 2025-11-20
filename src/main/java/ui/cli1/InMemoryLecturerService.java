package src.main.java.ui.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryLecturerService implements LecturerService {

    private final Map<String, Lecturer> lecturers = new HashMap<>();

    public InMemoryLecturerService() {
        
        addLecturer("L001", "Michael", "English");
        addLecturer("L002", "Alan", "Ryan");
    }

    @Override
    public boolean addLecturer(String id, String firstName, String lastName) {
        if (lecturers.containsKey(id)) {
            return false;
        }
        lecturers.put(id, new Lecturer(id, firstName, lastName));
        return true;
    }

    @Override
    public boolean updateLecturerField(String id, String field, String newValue) {
        Lecturer l = lecturers.get(id);
        if (l == null) return false;

        switch (field.toLowerCase()) {
            case "firstname":
            case "first_name":
                l.setFirstName(newValue);
                return true;
            case "lastname":
            case "last_name":
                l.setLastName(newValue);
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
