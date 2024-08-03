package camp.repository;

import camp.dto.SubjectDTO;
import camp.entity.Score;
import camp.entity.Student;
import camp.entity.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class SubjectRepositoryImpl implements SubjectRepository {

    private HashMap<Long, List<Subject>> subjects = new HashMap<>();

    @Override
    public void save(Subject subject) {
        Long studentId = subject.getStudentId();
        if (!subjects.containsKey(studentId)) {
            List<Subject> newList = new ArrayList<>();
            newList.add(subject);
            subjects.put(studentId, newList);
        }
        else{
            subjects.get(studentId).add(subject);
        }
    }

    @Override
    public Optional<List<SubjectDTO>> findAllById(Long studentId) {
        if(subjects.containsKey(studentId)) {
            return Optional.of(subjects.get(studentId).stream().map(SubjectDTO::toDTO).toList());
        }

        return Optional.empty();
    }

    @Override
    public boolean isEmpty() {
        return subjects.isEmpty();
    }

    @Override
    public Optional<SubjectDTO> find(Long studentId, String subjectName) {
        if(subjects.containsKey(studentId)) {
            return subjects.get(studentId).stream().map(SubjectDTO::toDTO).filter(x->x.getName().equals(subjectName)).findFirst();
        }

        return Optional.empty();
    }
}
