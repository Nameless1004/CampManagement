package camp.dto;

public class StudentDTO {
    private Long studentId;
    private String studentName;

    public StudentDTO(String studentName) {
        this.studentName = studentName;
    }

    // Getter
    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
