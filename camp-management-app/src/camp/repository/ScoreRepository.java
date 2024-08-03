package camp.repository;

import camp.dto.ScoreDTO;
import camp.entity.Score;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ScoreRepository {
    void save(Score score);
    Optional<List<ScoreDTO>> findAllById(Long studentId);
    Optional<List<ScoreDTO>> findAllByIdAndSubjectName(Long studentId, String subjectName);

    Optional<ScoreDTO> find(Long studentId, String subjectName, int round);

    boolean isEmpty();
    void update(Long scoreId, int score);
}
