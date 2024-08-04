package camp.service;

import camp.entity.Score;
import camp.entity.Student;
import camp.entity.Subject;
import camp.repository.ScoreRepository;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreManagementServiceImpl implements ScoreManagementService {

    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    private final SubjectRepository subjectRepository;

    public ScoreManagementServiceImpl(StudentRepository studentRepository, ScoreRepository scoreRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void save(Score add) {
        scoreRepository.save(add);
    }

    @Override
    public void delete(Long studentId){
        scoreRepository.delete(studentId);
    }


    @Override
    public void update(Long studentId, String subjectName, int round, int score) {
        Optional<Score> scoreDTO = scoreRepository.find(studentId, subjectName, round);
        // find == null 이면 해당없음
        scoreDTO.ifPresentOrElse((dto) -> scoreRepository.update(dto.getScoreId(), score), () -> {
            throw new RuntimeException("studentId : " + studentId + " subjectName : " + subjectName + " round : " + round + " score : " + score + "에 해당하는 data가 저장소에 없습니다.");
        });
    }

    @Override
    public void inquireRoundGradeBySubject(Long studentId, String subjectName) {
        Optional<List<Score>> allByIdAndSubjectName = scoreRepository.findAllByIdAndSubjectName(studentId, subjectName);
        if (allByIdAndSubjectName.isPresent()) {
            List<Score> find = allByIdAndSubjectName.get();
            // 회 차 값으로 정렬
            find.sort((x, y) -> x.getRound().compareTo(y.getRound()));
            for (Score scoreDTO : find) {
                Optional<Student> student = studentRepository.findById(studentId);
                if (student.isPresent()) {
                    String name = student.get().getName();
                    int round = scoreDTO.getRound();
                    String grade = scoreDTO.getGrade();
                    System.out.println(MessageFormat.format("{0} {1} {2}회 차 {3}등급", name, subjectName, round, grade));
                } else {
                    // student id에 해당하는 학생이 없음
                    return;
                }
            }
        } else {
            System.out.println("studentId : " + studentId + " subjectName : " + subjectName + "에 저장된 점수가 없습니다.");
        }
    }

    @Override
    public void inquireAverageGradeBySubject(Long studentId) {
        System.out.println(studentId + "의 과목별 평균 등급을 조회합니다...");
        System.out.println("-------------------------------");
        Optional<List<Subject>> subjectsResult = subjectRepository.findAllById(studentId);
        if (subjectsResult.isEmpty()) {
            System.out.println("수강하고 있는 과목이 없습니다.");
            return;
        }

        List<Subject> subjectList = subjectsResult.get();

        for (Subject subject : subjectList) {
            String subjectName = subject.getName();

            Optional<String> averageGrade = getAverageGradeBySubject(studentId, subjectName);
            if (averageGrade.isPresent()) {
                String grade = averageGrade.get();
                String message = MessageFormat.format("{0}의 {1}과목의 평균 등급은 {2}등급 입니다.", studentId, subjectName, grade);
                System.out.println(message);
            } else {
                System.out.println("점수 기록이 존재하지 않습니다. studentId : " + studentId + " subjectName : " + subjectName);
            }
        }
        System.out.println("-------------------------------");
    }

    @Override
    public Optional<String> getAverageGradeBySubject(Long studentId, String subjectName) {
        Optional<List<Score>> allByIdAndSubjectName = scoreRepository.findAllByIdAndSubjectName(studentId, subjectName);

        if (allByIdAndSubjectName.isPresent()) {
            List<Score> find = allByIdAndSubjectName.get();
            int count = find.size();
            String subjectType = find.get(0).getSubjectType();
            AtomicInteger sum = new AtomicInteger();
            find.forEach(x -> sum.addAndGet(x.getScore()));
            int avg = (int) (sum.get() / (double) count);
            return Optional.of(Score.toGrade(subjectType, avg));
        }

        return Optional.empty();
    }

    @Override
    public void inquireMandatorySubjectAverageGradeByState(String state) {
        System.out.println(state + "인 수강생들의 필수 과목 평균 등급을 조회합니다...");
        System.out.println("-------------------------------");
        Optional<List<Student>> students = studentRepository.findAllByState(state);
        if (students.isPresent()) {
            List<Student> studentList = students.get();
            for (Student student : studentList) {
                Optional<List<Subject>> subjects = subjectRepository.findAllById(student.getId());

                if (subjects.isEmpty()) {
                    System.out.println("id: " + student.getId() + "가 수강하고 있는 과목이 없습니다.");
                    continue;
                }

                List<Subject> subjectList = subjects.get();
                List<Subject> mandatoryList = subjectList.stream().filter(x -> x.getType().equals("MANDATORY")).toList();

                if (mandatoryList.isEmpty()) {
                    System.out.println("id: " + student.getId() + "가 수강하고 있는 필수 과목이 없습니다.");
                    continue;
                }
                System.out.println(student.getId() +"의 필수 과목 평균 등급");
                for (Subject subject : mandatoryList) {
                    Optional<String> averageGrade = getAverageGradeBySubject(student.getId(), subject.getName());
                    System.out.println("-------------------------------");
                    if(averageGrade.isPresent()){
                        String grade = averageGrade.get();
                        System.out.println("과목: " + subject.getName() + " 등급: " + grade + "등급");
                    } else {
                        System.out.println("점수 기록이 존재하지 않습니다. studentId : " + student.getName() + " subjectName : " + subject.getName());
                    }
                    System.out.println("-------------------------------");
                }
            }
        } else {
            System.out.println(state + "상태의 수강생이 존재하지 않습니다.");
        }
        System.out.println("-------------------------------");
    }
}
