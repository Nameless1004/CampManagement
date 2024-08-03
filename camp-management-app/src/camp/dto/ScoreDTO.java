package camp.dto;

import camp.entity.Score;
import camp.entity.Student;

import java.util.List;

public class ScoreDTO {
    private Long scoreId;
    private Long studentId;
    private String subjectName;
    private int round;
    private int score;
    private String grade;

    public ScoreDTO( Long scoreId, Long studentId, String subjectName, int round, int score, String grade) {
        this.scoreId = scoreId;
        this.studentId = studentId;
        this.subjectName = subjectName;
        this.round = round;
        this.score = score;
        this.grade = grade;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getRound() {
        return round;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public Long getScoreId() {
        return scoreId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public static ScoreDTO toDTO(Score score) {
        ScoreDTO dto = new ScoreDTO(score.getScoreId(), score.getStudentId(), score.getSubjectName(), score.getRound(), score.getScore(), score.getGrade());
        return dto;
    }
}
