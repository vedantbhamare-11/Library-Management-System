import java.util.ArrayList;

public class Subject {
    private int id;
    private String name;

     public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public static Subject findById(ArrayList<Subject> subjects, int id) {
        for (Subject subject : subjects) {
            if (subject.getId() == id) {
                return subject;
            }
        }
        return null; // ID not found
    }
}
