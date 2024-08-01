package camp.model;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private List<Subject> subjectList;

    public Student(String seq, String studentName) {
        this.studentId = seq;
        this.studentName = studentName;
        this.subjectList = new ArrayList<Subject>();
    }

    // Getter
    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }


    // 원본은 수정 못하게 복사본을 줍니다.
    public List<Subject> getSubjectList() {
        return List.copyOf(subjectList);
    }

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }
}
