package camp.repository;

import camp.dto.StudentDTO;
import camp.entity.Student;

import java.util.HashMap;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository{

    private HashMap<Long,Student> students = new HashMap<>();

    @Override
    public void save(Student student) {
        students.put(student.getId(),student);
    }

    // Optional Empty이면 studentId가 등록 안되어있는 것.
    @Override
    public Optional<StudentDTO> findById(Long studentId) {
        if(students.containsKey(studentId)){
            return Optional.of(StudentDTO.toDTO(students.get(studentId)));
        }
        return Optional.empty();
    }

    @Override
    public boolean isEmpty() {
        return students.isEmpty();
    }
}
