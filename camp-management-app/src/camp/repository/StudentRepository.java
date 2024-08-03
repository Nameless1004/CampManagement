package camp.repository;

import camp.dto.StudentDTO;
import camp.dto.SubjectSTO;

import java.util.List;

public interface StudentRepository{

    void save(StudentDTO student);
    void save(StudentDTO student, List<SubjectSTO> subjects);
    StudentDTO findById(String studentId);
    List<SubjectSTO> findSubjectsById(String studentId);
    boolean isEmpty();
}
