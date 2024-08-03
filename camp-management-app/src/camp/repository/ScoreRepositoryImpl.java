package camp.repository;

import camp.dto.ScoreDTO;
import camp.entity.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ScoreRepositoryImpl implements ScoreRepository{

    private long index = 0;
    private HashMap<Long, Score> scores = new HashMap<>();

    @Override
    public void save(Score score) {
        Long studentId = score.getStudentId();
        score.setScoreId(++index);
        scores.put(index, score);
    }

    @Override
    public Optional<List<ScoreDTO>> findAllById(Long studentId) {
        List<ScoreDTO> result = scores.values().stream().filter(x->x.getStudentId().longValue() == studentId.longValue()).map(ScoreDTO::toDTO).toList();
        if(result.isEmpty()) return Optional.empty();

        return Optional.of(result);
    }

    @Override
    public Optional<ScoreDTO> find(Long studentId, String subjectName, int round) {
        for(Score score : scores.values()) {
            if(score.getStudentId().longValue() == studentId.longValue() &&
                score.getSubjectName().equals(subjectName) &&
                score.getRound() == round) {
                return Optional.of(ScoreDTO.toDTO(score));
            }
        }
        return Optional.empty();
    }


    @Override
    public boolean isEmpty() {
        return scores.isEmpty();
    }

    @Override
    public void update(Long scoreId, int score) {
        scores.get(scoreId).setScore(score);
    }
}
