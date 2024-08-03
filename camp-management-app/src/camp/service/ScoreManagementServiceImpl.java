package camp.service;

import camp.dto.ScoreDTO;
import camp.entity.Score;
import camp.repository.ScoreRepository;

import java.util.Optional;

public class ScoreManagementServiceImpl implements ScoreManagementService {

    private ScoreRepository scoreRepository;

    @Override
    public void save(Score add) {
    }

    @Override
    public void update(Long studentId, String subjectName, int round, int score) {
    }

    @Override
    public void inquireRoundGradeBySubject(Long studentId, String subjectName) {

    }
}
