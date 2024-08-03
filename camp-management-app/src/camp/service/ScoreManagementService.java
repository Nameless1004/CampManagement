package camp.service;

import camp.entity.Score;

import java.util.List;

public interface ScoreManagementService {
    void save(Score add);
    void update(Long studentId, String subjectName, int round, int score);
    void inquireRoundGradeBySubject(Long studentId, String subjectName);
}

