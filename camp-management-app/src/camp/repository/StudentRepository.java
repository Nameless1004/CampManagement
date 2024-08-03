package camp.repository;

import camp.dto.StudentDTO;
import camp.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository{
    void save(Student student);
    Optional<StudentDTO> findById(Long studentId);
    boolean isEmpty();
    Optional<List<StudentDTO>> findAll();
}
