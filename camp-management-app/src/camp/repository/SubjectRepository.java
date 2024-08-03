package camp.repository;

import camp.dto.SubjectDTO;
import camp.entity.Student;
import camp.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    void save(Subject subject);
    Optional<List<SubjectDTO>> findAllById(Long studentId);
    boolean isEmpty();

    Optional<SubjectDTO> find(Long studentId, String subjectName);
}
