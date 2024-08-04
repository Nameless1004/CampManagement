package camp.repository;

import camp.entity.Score;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ScoreRepository {
    void save(Score score);
    Optional<List<Score>> findAllById(Long studentId);
    Optional<List<Score>> findAllByIdAndSubjectName(Long studentId, String subjectName);

    Optional<Score> find(Long studentId, String subjectName, int round);

    boolean isEmpty();
    void update(Long scoreId, int score);

    void delete(Long studentId);
}
