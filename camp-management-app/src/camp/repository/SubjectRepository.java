package camp.repository;

import camp.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository {
    void save(Subject subject);
    Optional<List<Subject>> findAllById(Long studentId);
    boolean isEmpty();

    Optional<Subject> find(Long studentId, String subjectName);

    void delete(Long id);
}
