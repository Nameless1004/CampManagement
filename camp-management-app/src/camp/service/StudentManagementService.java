package camp.service;

import camp.dto.SubjectDTO;
import camp.entity.Student;
import camp.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface StudentManagementService {
    void add(Student student);
    void addSubjects(List<Subject> subjects);
    Optional<SubjectDTO> findSubject(Long studentId, String subjectName);
    void inquireSubjects(Long studentId);
    void inquireStudents();
}
