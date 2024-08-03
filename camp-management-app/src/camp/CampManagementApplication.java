package camp;

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
    StudentManagementService studentManagementService;
    ScoreManagementService scoreManagementService;
    private final Map<String, List<String>> subjectList = new HashMap<>();

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
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println(e);
        }
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
        HashSet<String> set = new HashSet<>();
        // 5개 선택
        while (selectSubject.size() < 3) {
            // 필수 과목 3개 입력
            System.out.println("선택 가능한 과목");
            List<String> mandatorySubject = subjectList.get("MANDATORY");
            for (String n : mandatorySubject) {
                if (set.contains(n)) continue;
                System.out.println("[ " + n + " ]");
            }

            System.out.print("필수 과목: ");
            String input = sc.next();
            if (subjectList.get("MANDATORY").contains(input)) {
                if (set.contains(input)) {
                    System.out.println("중복 입력...");
                } else {
                    Subject s = new Subject(student.getId(), input, "MANDATORY");
                    selectSubject.add(s);
                    set.add(input);
                }
            } else {
                System.out.println("잘못입력..");
            }
        }
        set.clear();
        while (selectSubject.size() < 5) {
            // 선택 과목 2개 입력
            for (String n : subjectList.get("CHOICE")) {
                if (set.contains(n)) continue;
                System.out.println("[ " + n + " ]");
            }
            System.out.print("선택 과목: ");
            String input = sc.next();
            if (subjectList.get("CHOICE").contains(input)) {
                if (set.contains(input)) {
                    System.out.println("중복 입력...");
                } else {
                    Subject s = new Subject(student.getId(), input, "CHOICE");
                    selectSubject.add(s);
                    set.add(input);
                }
            } else {
                System.out.println("잘못입력..");
            }
        }
        System.out.println("과목 선택을 마치셨습니다.");
        for (Subject s : selectSubject) {
            System.out.println(s.getType() + " 선택한 과목: " + s.getName());
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
        printStudents();
        Long studentId = getStudentId();

        if (!checkstudentIdIsValid(studentId)) {
            System.out.println("등록되지 않은 id입니다.");
        }

        printStudentSubjects(studentId);

        System.out.print("과목: ");
        String subjectName = sc.next();
        if (!containsSubjectList(studentId, subjectName)) {
            System.out.println("수강중인 과목이 아닙니다.");
            return;
        }
        System.out.print("회차: ");
        int round = 0;
        while (true) {
            round = sc.nextInt();
            if (round < 1 || 10 < round) {
                System.out.println("1 ~ 10사이로 입력해주세요.");
            } else {
                break;
            }
        }
        System.out.print("점수: ");
        int score = 0;
        while (true) {
            score = sc.nextInt();
            if (round < 0 || 100 < round) {
                System.out.println("0 ~ 100사이로 입력해주세요.");
            } else {
                break;
            }
        }

        scoreManagementService.update(studentId, subjectName, round, score);
    }

    private void inquireRoundGradeBySubject() {
        printStudents();
        Long studentId = getStudentId();
        if(!checkstudentIdIsValid(studentId)) {
            System.out.println("등록되지 않은 id입니다.");
            return;
        }

        printStudentSubjects(studentId);
        System.out.print("과목: ");
        String subjectName = sc.next();
        if (!containsSubjectList(studentId, subjectName)) {
            System.out.println("수강중인 과목이 아닙니다.");
            return;
        }
        scoreManagementService.inquireRoundGradeBySubject(studentId, subjectName);
    }

    private void createScore() {
        System.out.println("점수 등록을 시작합니다....");
        printStudents();
        Long studentId = getStudentId();

        if (!checkstudentIdIsValid(studentId)) {
            System.out.println("등록되지 않은 id입니다.");
            return;
        }

        printStudentSubjects(studentId);
        System.out.print("과목: ");
        String subjectName = sc.next();
        if (!containsSubjectList(studentId, subjectName)) {
            System.out.println("수강중인 과목이 아닙니다.");
            return;
        }
        Optional<Subject> subjectDTO = studentManagementService.findSubject(studentId, subjectName);
        if (subjectDTO.isEmpty()) {
            System.out.println("studentId: " + studentId + "는 과목: [" + subjectName + "]을 수강하고있지 않습니다.");
            return;
        }
        Subject sbjDto = subjectDTO.get();
        System.out.print("회차: ");
        int round = 0;
        while (true) {
            round = sc.nextInt();
            if (round < 1 || 10 < round) {
                System.out.println("1 ~ 10사이로 입력해주세요.");
            } else {
                break;
            }
        }
        System.out.print("점수: ");
        int score = 0;
        while (true) {
            score = sc.nextInt();
            if (round < 0 || 100 < round) {
                System.out.println("0 ~ 100사이로 입력해주세요.");
            } else {
                break;
            }
        }

        Score scoreData = new Score(studentId, sbjDto.getName(), sbjDto.getType(), round, score);
        scoreManagementService.save(scoreData);
    }

    private boolean containsSubjectList(Long studentId, String subjectName) {
        return studentManagementService.containsSubject(studentId, subjectName);
    }

    private boolean checkstudentIdIsValid(Long studentId) {
        return studentManagementService.isValidStudentId(studentId);
    }

    private void printStudents() {
        studentManagementService.inquireStudents();
    }

    private void printStudentSubjects(Long studentId) {
        studentManagementService.inquireSubjects(studentId);
    }

    private Long getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.nextLong();
    }
}
