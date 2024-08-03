package camp.entity;

public class Subject {
    private Long studentId;
    private String subjectName;
    private String subjectType;

    public Subject(Long studentId, String subjectName, String subjectType) {
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

}
