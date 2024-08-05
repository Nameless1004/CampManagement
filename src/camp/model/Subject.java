package camp.model;

public class Subject {
    private String subjectId;
    private String subjectName;
    private String subjectType;

    public Subject(String seq, String subjectName, String subjectType) {
        this.subjectId = seq;
        this.subjectName = subjectName;
        this.subjectType = subjectType;
    }

    // Getter
    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public String getSubjectTypeKorean(){
        String korean = switch (subjectType){
            case "MANDATORY" -> "필수";
            case "CHOICE" -> "선택";
            default -> throw new IllegalStateException("Unexpected value: " + subjectType);
        };
        return korean;
    }

    @Override
    public String toString() {
        return "[id: " + getSubjectId() + " ]"+ "[ " + getSubjectTypeKorean() + " ]" + " " + subjectName;
    }
}
