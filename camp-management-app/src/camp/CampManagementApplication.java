package camp;

import camp.dto.SubjectDTO;
import camp.entity.Score;
import camp.entity.Student;
import camp.entity.Subject;
import camp.repository.*;
import camp.service.ScoreManagementService;
import camp.service.ScoreManagementServiceImpl;
import camp.service.StudentManagementService;
import camp.service.StudentManagementServiceImpl;

import java.util.*;

public class CampManagementApplication {
    Scanner sc = new Scanner(System.in);
    private Map<String, List<String>> subjectList = new HashMap<>();
    StudentManagementService studentManagementService;
    ScoreManagementService scoreManagementService;
    // 어플리케이션 시작
    public void start() {
        subjectList.put("MANDATORY", new ArrayList<>());
        subjectList.put("CHOICE", new ArrayList<>());
        List<String> mandatorySubject = subjectList.get("MANDATORY");
        List<String> choiceSubject = subjectList.get("CHOICE");
        mandatorySubject.add("Java");
        mandatorySubject.add("OOP");
        mandatorySubject.add("Kotlin");
        mandatorySubject.add("C++");
        mandatorySubject.add("Javascript");
        choiceSubject.add("C#");
        choiceSubject.add("Ruby");
        choiceSubject.add("Testing");

        StudentRepository studentRepository = new StudentRepositoryImpl();
        ScoreRepository scoreRepository = new ScoreRepositoryImpl();
        SubjectRepository subjectRepository = new SubjectRepositoryImpl();

        studentManagementService = new StudentManagementServiceImpl(studentRepository, subjectRepository);
        scoreManagementService = new ScoreManagementServiceImpl(studentRepository, scoreRepository);
        try{
            displayMainView();
        } catch(Exception e){ System.out.println(e); }
    }

    private void displayMainView() throws InterruptedException {
        boolean flag = true;
        while (flag) {
            System.out.println("\n==================================");
            System.out.println("내일배움캠프 수강생 관리 프로그램 실행 중...");
            System.out.println("1. 수강생 관리");
            System.out.println("2. 점수 관리");
            System.out.println("3. 프로그램 종료");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> displayStudentView(); // 수강생 관리
                case 2 -> displayScoreView(); // 점수 관리
                case 3 -> flag = false; // 프로그램 종료
                default -> {
                    System.out.println("잘못된 입력입니다.\n되돌아갑니다!");
                    Thread.sleep(2000);
                }
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }

    private void displayStudentView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent();
                case 2 -> studentManagementService.inquireStudents();
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private void createStudent() {
        System.out.print("등록할 수강생의 이름을 입력해주세요.");
        String name = sc.next();
        Student student = new Student(name);
        studentManagementService.add(student);

        List<Subject> selectSubject = new ArrayList<>();
        // 5개 선택
        while(selectSubject.size() < 3){
            // 필수 과목 3개 입력
            System.out.print("필수 과목: ");
            String input = sc.next();
            if(subjectList.get("MANDATORY").contains(input)){
                if(selectSubject.contains(input)){
                    System.out.println("중복 입력...");
                } else {
                    Subject s = new Subject(student.getId(), input, "MANDATORY");
                    selectSubject.add(s);
                }
            } else {
                System.out.println("잘못입력..");
            }
        }
        while(selectSubject.size() < 5){
            // 선택 과목 2개 입력
            System.out.print("선택 과목: ");
            String input = sc.next();
            if(subjectList.get("CHOICE").contains(input)){
                if(selectSubject.contains(input)){
                    System.out.println("중복 입력...");
                } else {
                    Subject s = new Subject(student.getId(), input, "CHOICE");
                    selectSubject.add(s);
                }
            } else {
                System.out.println("잘못입력..");
            }
        }
        System.out.println("과목 선택을 마치셨습니다.");
        for(Subject s : selectSubject){
            System.out.println(s.getSubjectType() + " 선택한 과목: " + s.getSubjectName());
        }
        studentManagementService.addSubjects(selectSubject);
        System.out.println("학생 등록 완료");
    }


    private void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore();
                case 2 -> updateScore();
                case 3 -> inquireRoundGradeBySubject();
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private void updateScore() {
        System.out.println("점수 수정을 시작합니다....");
        Long studentId = getStudentId();
        studentManagementService.inquireSubjects(studentId);
        System.out.print("과목: ");
        String subjectName = sc.next();
        System.out.print("회차: ");
        int round = sc.nextInt();
        System.out.print("점수: ");
        int score = sc.nextInt();

        scoreManagementService.update(studentId, subjectName, round, score);
    }

    private void inquireRoundGradeBySubject() {
        // scoreManagementService.inquireRoundGradeBySubject();
    }

    private void createScore() {
        System.out.println("점수 등록을 시작합니다....");
        Long studentId = getStudentId();
        studentManagementService.inquireSubjects(studentId);
        System.out.print("과목: ");
        String subjectName = sc.next();
        Optional<SubjectDTO> subjectDTO = studentManagementService.findSubject(studentId, subjectName);
        if(subjectDTO.isEmpty()){
            System.out.println("studentId: " + studentId + "는 과목: [" + subjectName +"]을 수강하고있지 않습니다.");
            return;
        }
        SubjectDTO sbjDto = subjectDTO.get();
        System.out.print("회차: ");
        int round = sc.nextInt();
        System.out.print("점수: ");
        int score = sc.nextInt();

        Score scoreData = new Score(studentId, sbjDto.getName(), sbjDto.getType(), round, score);
        scoreManagementService.save(scoreData);
    }


    private Long getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.nextLong();
    }
}
