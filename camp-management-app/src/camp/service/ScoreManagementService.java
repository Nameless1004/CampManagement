package camp.service;

import camp.dto.ScoreDTO;

import java.util.List;

public interface ScoreManagementService {
    void add(ScoreDTO add);
    void update(ScoreDTO update);
    void inquireRoundGradeBySubject(Long studentId, String subjectName);
}

