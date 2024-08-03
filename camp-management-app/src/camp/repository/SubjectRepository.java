package camp.repository;

import camp.dto.SubjectSTO;

import java.util.List;

public interface SubjectRepository {
    void save(String studentId, List<SubjectSTO> subjects);
    List<SubjectSTO> findById(String studentId);
    boolean isEmpty();
}
