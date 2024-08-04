package camp.entity;

public class Score {
    private final Long studentId;
    private final String subjectName;
    private final String subjectType;
    private final Integer round;
    private Long scoreId;
    private Integer score;
    private String grade;

    public Score(Long studentId, String subjectName, String subjectType, Integer round, Integer score) {
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

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public Integer getRound() {
        return round;
    }

    public Long getScoreId() {
        return scoreId;
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
        converToGrade(score);
    }

    public String getGrade() {
        return grade;
    }

    public static String toGrade(String subjectType, int score){
        String grade = "";
        if (subjectType.equals("MANDATORY")) {
            if (95 <= score) {
                grade = "A";
            } else if (90 <= score) {
                grade = "B";
            } else if (80 <= score) {
                grade = "C";
            } else if (70 <= score) {
                grade = "D";
            } else if (60 <= score) {
                grade = "F";
            } else {
                grade = "N";
            }
        } else {
            if (90 <= score) {
                grade = "A";
            } else if (80 <= score) {
                grade = "B";
            } else if (70 <= score) {
                grade = "C";
            } else if (60 <= score) {
                grade = "D";
            } else if (50 <= score) {
                grade = "F";
            } else {
                grade = "N";
            }
        }
        return grade;
    }
    public void converToGrade(int score) {
        String grade = "";
        if (subjectType.equals("MANDATORY")) {
            if (95 <= score) {
                grade = "A";
            } else if (90 <= score) {
                grade = "B";
            } else if (80 <= score) {
                grade = "C";
            } else if (70 <= score) {
                grade = "D";
            } else if (60 <= score) {
                grade = "F";
            } else {
                grade = "N";
            }
        } else {
            if (90 <= score) {
                grade = "A";
            } else if (80 <= score) {
                grade = "B";
            } else if (70 <= score) {
                grade = "C";
            } else if (60 <= score) {
                grade = "D";
            } else if (50 <= score) {
                grade = "F";
            } else {
                grade = "N";
            }
        }
        this.grade = grade;
    }
}
