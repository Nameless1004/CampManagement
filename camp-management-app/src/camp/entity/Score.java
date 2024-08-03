package camp.entity;

import camp.dto.ScoreDTO;

public class Score {
    private Long scoreId;
    private Long studentId;
    private String subjectName;
    private String subjectType;
    private Integer round;
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

    public Integer getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public void converToGrade(int score) {
        this.grade = grade;
    }

    public boolean compByValues(Score comp){
        if (comp.getStudentId().longValue() == this.studentId.longValue() &&
                comp.getSubjectName().equals(this.subjectName) &&
                comp.getSubjectType().equals(this.subjectType) &&
                comp.getGrade().equals(this.grade) &&
                comp.getRound().intValue() == this.round.intValue() &&
                comp.getScore().intValue() == this.score.intValue()) {
            return true;
        }

        return false;
    }

    public Long getScoreId(){
        return scoreId;
    }

    public void setScore(int score){
        this.score = score;
        converToGrade(score);
    }

    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }
}
