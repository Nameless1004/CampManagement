package camp.service;

import camp.entity.Score;

import java.util.Optional;

public interface ScoreManagementService {
    void save(Score add);
    void delete(Long studentId);
    void update(Long studentId, String subjectName, int round, int score);
    void inquireRoundGradeBySubject(Long studentId, String subjectName);
    void inquireAverageGradeBySubject(Long studentId);

    Optional<String> getAverageGradeBySubject(Long studentId, String subjectName);

    void inquireMandatorySubjectAverageGradeByState(String state);
}

