package camp.repository;

import camp.dto.ScoreDTO;

public interface ScoreRepository {
    void save(ScoreDTO score);
    ScoreDTO findById(String studentId);
    boolean isEmpty();
}
