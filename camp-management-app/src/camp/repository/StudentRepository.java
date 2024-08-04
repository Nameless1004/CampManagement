package camp.repository;

import camp.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository{
    void save(Student student);
    void update(Student student);
    Optional<Student> findById(Long studentId);
    Optional<List<Student>> findAllByState(String state);
    boolean isEmpty();
    Optional<List<Student>> findAll();
}
