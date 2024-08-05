package camp.model;

public class Score {
    private String subjectId;
    private String subjectType;
    private String subjectName;
    private String studentId;
    private int round;
    private int score;
    private String grade;

    public Score(String studentId, String subjectId, String subjectType, String subjectName, int round, int score) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.subjectType = subjectType;
        this.subjectName = subjectName;
        this.round = round;
        this.score = score;
        this.grade = convertToGrade(subjectType, score);
    }

    public static String convertToGrade(String subjectType, int score){
        String grade = "";
        switch (subjectType){
            case "MANDATORY":
                if( 95 <= score){
                    grade = "A";
                } else if(90 <= score) {
                    grade = "B";
                } else if(80 <= score) {
                    grade = "C";
                } else if(70 <= score) {
                    grade = "D";
                } else if(60 <= score) {
                    grade = "F";
                } else{
                    grade = "N";
                }
                break;
            case "CHOICE":
                if( 90 <= score){
                    grade = "A";
                } else if(80 <= score) {
                    grade = "B";
                } else if(70 <= score) {
                    grade = "C";
                } else if(60 <= score) {
                    grade = "D";
                } else if(50 <= score) {
                    grade = "F";
                } else{
                    grade = "N";
                }
                break;
        }

        return grade;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getStudentId() {
        return studentId;
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

    public String getSubjectName() {
        return subjectName;
    }

    public void setScore(int score) {
        this.score = score;
        this.grade = convertToGrade(subjectType, score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "subjectId='" + subjectId + '\'' +
                ", subjectType='" + subjectType + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", studentId='" + studentId + '\'' +
                ", round=" + round +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                '}';
    }
}
