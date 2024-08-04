package camp;

import camp.repository.*;
import camp.service.ScoreManagementService;
import camp.service.ScoreManagementServiceImpl;
import camp.service.StudentManagementService;
import camp.service.StudentManagementServiceImpl;

public class AppConfig {
    private StudentRepository studentRepo;
    private SubjectRepository subjectRepo;
    private ScoreRepository scoreRepo;

    public StudentManagementService getStudentManagementService() {
        return new StudentManagementServiceImpl(getStudentRepository(), getSubjectRepository());
    }

    public ScoreManagementService getScoreManagementService() {
        return new ScoreManagementServiceImpl(getStudentRepository(), getScoreRepository());
    }

    private SubjectRepository getSubjectRepository() {
        if(subjectRepo == null) {
            subjectRepo = subjectRepository();
        }
        return subjectRepo;
    }

    private ScoreRepository getScoreRepository() {
        if(scoreRepo == null) {
            scoreRepo = scoreRepository();
        }
        return scoreRepo;
    }

    private StudentRepository getStudentRepository() {
        if(studentRepo == null) {
            studentRepo = studentRepository();
        }
        return studentRepo;
    }

    private ScoreRepository scoreRepository() {
        return new ScoreRepositoryImpl();
    }

    private StudentRepository studentRepository() {
        return new StudentRepositoryImpl();
    }

    private SubjectRepository subjectRepository() {
        return new SubjectRepositoryImpl();
    }
}
