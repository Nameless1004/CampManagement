package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.Subject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Notification
 * Java, 객체지향이 아직 익숙하지 않은 분들은 위한 소스코드 틀입니다.
 * main 메서드를 실행하면 프로그램이 실행됩니다.
 * model 의 클래스들과 아래 (// 기능 구현...) 주석 부분을 완성해주세요!
 * 프로젝트 구조를 변경하거나 기능을 추가해도 괜찮습니다!
 * 구현에 도움을 주기위한 Base 프로젝트입니다. 자유롭게 이용해주세요!
 */
public class CampManagementApplication {
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;

    // 과목 타입
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";

    // index 관리 필드
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    // 스캐너
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
        }
    }

    // 초기 데이터 생성
    private static void setInitData() {
        studentStore = new ArrayList<>();
        subjectStore = List.of(
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Java",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "객체지향",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "JPA",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MySQL",
                        SUBJECT_TYPE_MANDATORY
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "디자인 패턴",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Spring Security",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "Redis",
                        SUBJECT_TYPE_CHOICE
                ),
                new Subject(
                        sequence(INDEX_TYPE_SUBJECT),
                        "MongoDB",
                        SUBJECT_TYPE_CHOICE
                )
        );
        scoreStore = new ArrayList<>();
    }

    // index 자동 증가
    private static String sequence(String type) {
        switch (type) {
            case INDEX_TYPE_STUDENT -> {
                studentIndex++;
                return INDEX_TYPE_STUDENT + studentIndex;
            }
            case INDEX_TYPE_SUBJECT -> {
                subjectIndex++;
                return INDEX_TYPE_SUBJECT + subjectIndex;
            }
            default -> {
                scoreIndex++;
                return INDEX_TYPE_SCORE + scoreIndex;
            }
        }
    }

    private static void displayMainView() throws InterruptedException {
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

    private static void displayStudentView() {
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
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생 등록


    private static void createStudent() {
        //수강생 이름 및 고유번호 등록
        System.out.println("\n수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        // 기능 구현 (필수 과목, 선택 과목)
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        System.out.print("필수 과목 개수 입력: ");
        int mendatoryNum = sc.nextInt();
        for (int i = 0; i < mendatoryNum; i++) {
            System.out.print("필수 과목 입력: ");
            String mendatoryName = sc.next();
            for (Subject s : subjectStore) {
                if (s.getSubjectName().equals(mendatoryName)) {
                    student.addSubject(s); //필수 과목 등록
                }
            }
        }

        System.out.print("선택 과목 개수 입력: ");
        int choiceNum = sc.nextInt();
        for (int i = 0; i < choiceNum; i++) {
            System.out.print("선택 과목 입력: ");
            String choiceName = sc.next();
            for (Subject s : subjectStore) {
                if (s.getSubjectName().equals(choiceName)) {
                    student.addSubject(s); //선택 과목 등록
                }
            }
        }


        // 기능 구현
        studentStore.add(student);
        System.out.println("수강생 등록 성공!\n");
    }


    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println("\n수강생 목록을 조회합니다...");
        // 기능 구현
        if (studentStore.isEmpty()) {
            System.out.println("등록된 수강생이 없습니다.");
            System.out.println("수강생 목록 조회 종료.");
        } else {
            for (Student student : studentStore) {
                System.out.println("학생 고유 번호: " + student.getStudentId());
                System.out.println("학생 이름 : " + student.getStudentName());
            }
            System.out.println("\n수강생 목록 조회 성공!");
        }
    }

    private static void displayScoreView() {
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
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static String getStudentId() {
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        System.out.println("시험 점수를 등록합니다...");
        List<Subject> subjectList = getSubjectListByStudent(studentId);
        printSubjectLists(studentId);

        System.out.print("등록할 과목을 선택해주세요(id 입력): ");
        String selectedSubjectId = sc.next();
        Subject selectedSubject = subjectList.stream().filter(x -> x.getSubjectId().equals(selectedSubjectId)).findFirst().orElse(null);

        int round = 0;
        while (true) {
            System.out.print("등록할 회차를 입력해주세요: ");
            round = Integer.parseInt(sc.next());
            List<Score> scoreList = getScoreListByStudent(studentId);
            int temp = round;
            if (scoreList.stream().anyMatch(x -> x.getSubjectId().equals(selectedSubject.getSubjectId()) && x.getRound() == temp)) {
                System.out.println("동일 회차가 결과가 등록되어있습니다.");
            } else if (round < 0 || round > 10) {
                System.out.println("회차의 범위는 1 ~ 10회 입니다.");
            } else {
                break;
            }
        }

        int score = 0;
        while (true) {
            System.out.print("등록할 점수를 입력해주세요: ");
            score = Integer.parseInt(sc.next());
            if (score < 0 || score > 100) {
                System.out.println("점수의 범위는 0 ~ 100점 입니다.");
            } else {
                break;
            }
        }

        Score scoreData = new Score(studentId, selectedSubject.getSubjectId(), selectedSubject.getSubjectType(), round, score);
        scoreStore.add(scoreData);

        System.out.println("\n점수 등록 성공!");
    }


    // 특정학생이 수강중인 과목 목록 출력
    private static void printStudentList() {
        System.out.println("---------------------------------");
        System.out.println("【 " + "\u001B[34m" + "수강생 리스트 ( ID | NAME )" + "\u001B[0m" + " 】 ");
        System.out.println("---------------------------------");
        for (int i = 0; i < studentStore.size(); i++) {
            Student subject = studentStore.get(i);
            System.out.printf("   %-4s   |", subject.getStudentId());
            System.out.printf("  %-6s  %n", subject.getStudentName());
            System.out.println("---------------------------------");
        }
    }

    // 특정학생이 수강중인 과목 목록 출력
    private static void printSubjectLists(String studentId) {
        List<Subject> subjectList = getSubjectListByStudent(studentId);
        System.out.println("---------------------------------");
        System.out.println("【 " + "\u001B[34m" + studentId + "\u001B[0m" + " 】 " + "수강 과목 리스트");
        System.out.println("---------------------------------");
        for (int i = 0; i < subjectList.size(); i++) {
            Subject subject = subjectList.get(i);
            System.out.printf("   %-4s   |", subject.getSubjectId());
            System.out.printf("  %-2s  |", subject.getSubjectTypeKorean());
            System.out.printf("   %-6s    %n", subject.getSubjectName());
            System.out.println("---------------------------------");
        }
    }

    // 수강생의 과목별 회차 점수 수정
    private static void updateRoundScoreBySubject() {
        printStudentList();
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        printSubjectLists(studentId);
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("수정할 과목의 고유 번호를 써주세요.");
        System.out.print("번호 : ");
        // 과목의 고유 번호 얻음
        String input1 = sc.next();

//


        int input2 = 0;
        while (true) {
            System.out.println("수정할 과목의 회차 번호를 써주세요.");
            System.out.println("(회차 범위: 1 ~ 10)");
            System.out.print("번호 : ");
            input2 = sc.nextInt();
            if (input2 < 0 || input2 > 10) {
                System.out.println("회차의 범위는 1 ~ 10 입니다.");
            } else {
                break;
            }
        }

        int input3 = 0;
        while (true) {
            System.out.println("점수를 써주세요.");
            System.out.println("(점수 범위: 0 ~ 100)");
            System.out.print("점수 : ");
            input3 = sc.nextInt();
            if (input3 < 0 || input3 > 100) {
                System.out.println("점수의 범위는 0 ~ 100점 입니다.");
            } else {
                break;
            }
        }
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        // var 부분을 뭘로 구현해야 제대로 실행되는 지 모르겠음.
        // 오류 처리 어떻게 진행할지 확인하기
        int finalInput = input2;
        Optional<Score> result = scoreStore.stream()
                .filter(x -> x.getStudentId().equals(studentId) &&
                        x.getSubjectId().equals(input1) &&
                        x.getRound() == finalInput)
                .findFirst();
        //
        if (result.isPresent()) { // 값이 있냐 없냐
            result.get().setScore(input3); // 값이 있는지 없는지 확인을 했기 떄문에 get()을 바로 쓸 수 있음
            System.out.println("점수 수정 성공!");
            System.out.println("과목 고유번호 : " + input1 + "  " + "회차 번호 : " + input2 + "  " + "수정된 점수 : " + input3);
        } else {
            System.out.println("점수 수정에 실패했습니다.");
        }
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
        printStudentList();
        String studentId = getStudentId();

        // 조회할 과목 선택
        printSubjectLists(studentId);
        System.out.println("조회할 과목을 선택해주세요.(과목 ID 입력): ");
        String selectSubjectId = sc.next();

        // 스코어 저장해둘 리스트 생성
        ArrayList<Score> scoreList = new ArrayList<>();
        for (int i = 0; i < scoreStore.size(); ++i) {
            // 현재스코어
            Score currentScore = scoreStore.get(i);
            if (currentScore.getStudentId().equals(studentId) && currentScore.getSubjectId().equals(selectSubjectId)) {
                scoreList.add(currentScore);
            }
        }

        scoreList.sort(Comparator.comparingInt(Score::getRound));
        for (int i = 0; i < scoreList.size(); ++i) {
            Score currentScore = scoreList.get(i);
            int round = currentScore.getRound();
            String grade = currentScore.getGrade();
            System.out.println(round + "회차 : " + grade + "등급");
        }
        System.out.println("회차별 등급을 조회합니다...");
    }

    // 학생아이디로 수강중인 과목 목록 가져오기
    private static List<Subject> getSubjectListByStudent(String studentId) {
        for (Student student : studentStore) {
            if (student.getStudentId().equals(studentId)) {
                return student.getSubjectList();
            }
        }

        return new ArrayList<>();
    }

    // 학생아이디로 점수 데이터들 가져오기
    private static List<Score> getScoreListByStudent(String studentId) {
        return scoreStore.stream().filter(x -> x.getStudentId().equals(studentId)).toList();
    }

}
