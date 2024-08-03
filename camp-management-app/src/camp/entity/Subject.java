package camp.entity;

public class Subject {
    private Long studentId;
    private String name;
    private String type;

    public Subject(Long studentId, String subjectName, String subjectType) {
        this.studentId = studentId;
        this.name = subjectName;
        this.type = subjectType;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
