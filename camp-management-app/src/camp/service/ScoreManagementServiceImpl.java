package camp.service;

import camp.dto.ScoreDTO;
import camp.dto.StudentDTO;
import camp.entity.Score;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ScoreManagementServiceImpl implements ScoreManagementService {

    private StudentRepository studentRepository;
    private ScoreRepository scoreRepository;

    public ScoreManagementServiceImpl(StudentRepository studentRepository, ScoreRepository scoreRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void save(Score add) {
        scoreRepository.save(add);
    }

    @Override
    public void update(Long studentId, String subjectName, int round, int score) {
        Optional<ScoreDTO> scoreDTO = scoreRepository.find(studentId, subjectName, round);
        // find == null 이면 해당없음
        scoreDTO.ifPresentOrElse( (dto)-> scoreRepository.update(dto.getScoreId(), score),
                ()->{
                    throw new RuntimeException("studentId : " + studentId + " subjectName : " + subjectName + " round : " + round + " score : " + score +"에 해당하는 data가 저장소에 없습니다.");
                });
    }

    @Override
    public void inquireRoundGradeBySubject(Long studentId, String subjectName) {
        Optional<List<ScoreDTO>> allByIdAndSubjectName = scoreRepository.findAllByIdAndSubjectName(studentId, subjectName);
        if(allByIdAndSubjectName.isPresent()) {
            List<ScoreDTO> find = allByIdAndSubjectName.get();
            // 회 차 값으로 정렬
            find.sort(Comparator.comparingInt(ScoreDTO::getRound));
            for(ScoreDTO scoreDTO : find) {
                Optional<StudentDTO> student = studentRepository.findById(studentId);
                if(student.isPresent()) {
                    String name = student.get().getName();
                    int round =  scoreDTO.getRound();
                    String grade = scoreDTO.getGrade();
                    System.out.println(MessageFormat.format("{0} {1} {2}회 차 {3}등급",name, subjectName, round, grade));
                } else {
                    // student id에 해당하는 학생이 없음
                    return;
                }
            }
        }
        else{
            System.out.println("studentId : " + studentId + " subjectName : " + subjectName + "에 저장된 점수가 없습니다.");
        }
    }
}
