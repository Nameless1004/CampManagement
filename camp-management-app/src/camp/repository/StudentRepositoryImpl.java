package camp.repository;

import camp.entity.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository{
    private Long index = 0L;
    private HashMap<Long,Student> students = new HashMap<>();

    @Override
    public void save(Student student) {
        student.setId(++index);
        students.put(index,student);
    }

    // Optional Empty이면 studentId가 등록 안되어있는 것.
    @Override
    public Optional<Student> findById(Long studentId) {
        if(students.containsKey(studentId)){
            return Optional.of(students.get(studentId));
        }
        return Optional.empty();
    }

    @Override
    public boolean isEmpty() {
        return students.isEmpty();
    }

    @Override
    public Optional<List<Student>> findAll() {
        if(students.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(students.values().stream().toList());
    }
}
