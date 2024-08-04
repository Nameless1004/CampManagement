package camp.service;

import camp.entity.Student;
import camp.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface StudentManagementService {
    void add(Student student);
    void addSubjects(List<Subject> subjects);
    boolean update(Long studentId, String name, String state);
    Optional<Subject> findSubject(Long studentId, String subjectName);
    void inquireSubjects(Long studentId);
    void inquireStudents();
    boolean containsSubject(Long studentId, String subjectName);
    boolean isValidStudentId(Long studentId);

    void inquireStudentsByState(String state);
}
