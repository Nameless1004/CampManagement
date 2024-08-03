package camp.dto;

import camp.entity.Score;
import camp.entity.Student;

public class StudentDTO {
    private Long id;
    private String name;

    public StudentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO(student.getId(), student.getName());
        return dto;
    }
}
