package camp.dto;

public class ScoreDTO {
    private Long studentId;
    private String subjectName;
    private String subjectType;
    private Integer round;
    private Integer score;
    private String grade;

    public ScoreDTO(Long studentId, String subjectName, String subjectType, Integer round, Integer score, String grade) {
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
        this.round = round;
        this.score = score;
        converToGrade(score);
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getGrade() {
        return grade;
    }

    public void converToGrade(int score) {
        this.grade = grade;
    }
}
