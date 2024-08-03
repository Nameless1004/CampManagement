package camp.dto;

import camp.entity.Student;
import camp.entity.Subject;

public class SubjectDTO {
    private String name;
    private String type;

    public SubjectDTO(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public static SubjectDTO toDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO(subject.getSubjectName(), subject.getSubjectType());
        return dto;
    }
}
