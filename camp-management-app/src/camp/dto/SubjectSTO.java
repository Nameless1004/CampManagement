package camp.dto;

public class SubjectSTO {
    private Long studentId;
    private String subjectName;
    private String subjectType;

    public SubjectSTO(String subjectName, String subjectType) {
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public SubjectSTO clone(SubjectSTO subject) {
        return new SubjectSTO(subjectName, subjectType);
    }
}
