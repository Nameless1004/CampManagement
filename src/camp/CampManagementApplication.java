package camp;

import camp.model.Score;
import camp.model.Student;
import camp.model.StudentState;
import camp.model.Subject;

import java.util.*;

public class CampManagementApplication {
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static final String INDEX_TYPE_SCORE = "SC";
    // 데이터 저장소
    private static List<Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<Score> scoreStore;
    // 과목 타입
    private static final String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static final String SUBJECT_TYPE_CHOICE = "CHOICE";
    // index 관리 필드
    private static int studentIndex;
    private static int subjectIndex;
    private static int scoreIndex;
    // 스캐너
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        setInitData();
        try {
            displayMainView();
        } catch (Exception e) {
            System.out.println("\n오류 발생!\n프로그램을 종료합니다.");
            System.out.println(e);
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
            System.out.println();
            underline();
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
            underline();
            System.out.println("수강생 관리 실행 중...");
            System.out.println("1. 수강생 등록");
            System.out.println("2. 수강생 목록 조회");
            System.out.println("3. 수강생 정보 조회");
            System.out.println("4. 수강생 상태 지정");
            System.out.println("5. 수강생 정보 수정");
            System.out.println("6. 상태별 수강생 목록 조회");
            System.out.println("7. 수강생 삭제");
            System.out.println("8. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createStudent(); // 수강생 등록
                case 2 -> inquireStudent(); // 수강생 목록 조회
                case 3 -> inquireStudentInfo(); // 수강생 정보 조회
                case 4 -> setStudentState(); //수강생 상태 지정
                case 5 -> updateStudentInfo(); //수강생 정보 수정
                case 6 -> inquireStudentListByState(); // 상태별 수강생 목록 조회
                case 7 -> deleteStudent(); // 수강생 삭제
                case 8 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    private static void updateStudentInfo() {
        underline();
        System.out.println("수강생의 정보를 수정합니다...");
        //수정할 수강생 Id 입력
        String studentId = getStudentId();
        Student student = null;
        //데이터베이스에 입력받은 Id와 일치하는 수강생 찾기
        for (Student s : studentStore) {
            if (s.getStudentId().equals(studentId)) {
                student = s;
                break;
            }
        }

        if(student == null){
            return;
        }
        //수정할 이름,상태 입력 받기
        System.out.print("이름: ");
        String newName = sc.next();
        System.out.print("상태: ");

        try {
            StudentState newState = StudentState.valueOf(sc.next());

            student.setName(newName);
            student.setState(newState);

            System.out.println("수강생의 정보가 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("state입력이 잘못되었습니다.");
        }
        underline();
    }

    private static void setStudentState() {
        System.out.println();
        underline();

        System.out.println("수강생의 상태를 지정합니다...");
        String studentId = getStudentId();
        if(studentStore.stream().filter(x->x.getStudentId().equals(studentId)).findFirst().isEmpty()){
            System.out.println("수강생 id가 존재하지 않습니다.");
            underline();
            System.out.println();
            return;
        }

        System.out.print("상태: Green, Yellow, Red 중에 입력하시오: ");
        String state = sc.next();


        try {
            StudentState studentState = StudentState.valueOf(state);
            Optional<Student> find = studentStore.stream().filter(x -> x.getStudentId().equals(studentId)).findFirst();
            if (find.isPresent()) {
                Student student = find.get();
                student.setState(studentState);
            } else {
                System.out.println("존재하지 않는 수강생 id입니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 상태 입력입니다.");
        } finally {
            underline();
            System.out.println();
        }
    }

    // 수강생 삭제
    private static void deleteStudent() {
        System.out.println("수강생을 삭제합니다..!");

        // 저희가 학생 데이터가 얽혀있는곳에서 삭제를 해줘야 한다.
        // 학생데이터가 들어가있는 곳이 어디어디어디일까요
        // scoreStore, studentStore, subjectScore

        // 학생 아이디를 입력받는다.
        String studentId = getStudentId();

        //studentId 있는지 처리
        Optional<Student> find = studentStore.stream().filter(x -> x.getStudentId().equals(studentId)).findFirst();
        if (find.isEmpty()) {
            System.out.println("해당 id인 학생이 존재하지 않습니다.");
            return;
        }

        // studentStore -> 학생 저장소
        // 여기서 studentId가 같은 student를 삭제하면 되겠쬬 << 조건
        // for
        for (int i = studentStore.size() - 1; i >= 0; i--) {
            // i번째 요소를 store에서 가져와서 변수에 담아주세요. hint : get
            Student currentStudent = studentStore.get(i);
            // 만약에 현재 학생의 아이디가 입력받은 아이디 라면
            if (currentStudent.getStudentId().equals(studentId)) {
                // 저희가 삭제해야할 student를 찾은거죠
                studentStore.remove(i);
            }
        }

        // scoreStore도 동일하게 진행을 해주시면 됩니다.
        // 외우시면 안되고 이해를 하고 가셔야됩니다.
        // [0] [1] [2] [3] [4] : index
        // size = 5 => 4번이마지막입니다. 왜냐 -> index 0부터 시작이기 때문에
        // size - 1을 해줘야 마지막 인덱스입니다.
        // String -> length();
        // Array -> length;
        // Collection -> size();
        for (int i = scoreStore.size() - 1; i >= 0; i--) {
            // scoreStore -> Score 타입을 반환해야하는데
            // 지금 받으시는 변수는 Student타입이어서 오류가 난다.
            Score currentScore = scoreStore.get(i);
            if (currentScore.getStudentId().equals(studentId)) {
                scoreStore.remove(i);
            }
        }

        // 위에 쓴 거랑 동일한 기능 ( 람다, Predicate )
        // studentStore.removeIf( x -> x.getStudentId().equals(studentId));
        // scoreStore.removeIf( x -> x.getStudentId().equals(studentId));
        System.out.println("삭제 성공!...");
    }

    // 상태별 수강생 목록 조회
    private static void inquireStudentListByState() {
        try {
            System.out.println();
            underline();

            System.out.println("상태별 수강생 목록을 조회합니다...");

            System.out.print("조회할 상태(Green, Yellow, Red): ");
            String state = sc.next();

            System.out.println("---------------------------------");
            System.out.println("【 " + "\u001B[34m" + state + " 수강생 리스트 ( ID | NAME )" + "\u001B[0m" + " 】 ");
            System.out.println("---------------------------------");
            StudentState studentState = StudentState.valueOf(state);
            List<Student> students = studentStore.stream().filter(x -> x.getState() == studentState).toList();
            if (students.isEmpty()) {
                System.out.println(studentState.name() + "인 학생이 없습니다.");
            } else {
                for (Student student : students) {
                    System.out.printf("   %-4s   |", student.getStudentId());
                    System.out.printf("  %-6s  %n", student.getStudentName());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 상태 입력입니다.");
        }  finally {
            underline();
            System.out.println();
        }
    }

    // 수강생 정보 조회
    private static void inquireStudentInfo() {
        underline();
        System.out.println();
        System.out.println("~~수강생 정보를 조회합니다.~~");
        String inputID = getStudentId();
        Student student = null;

        // 입력받은 studentID값과 같은 student 찾기
        for(int index = 0; index < studentStore.size(); index++) {
            Student s = studentStore.get(index);
            if (s.getStudentId().equals(inputID)) {
                student = s;
                break;
            }
        }

        // null값 처리해주기
        if(student == null) {
            System.out.println("학생을 찾을 수 없습니다.");
            //inquireStudentInfo();
            // 종료
            return;
        }
        // 출력
        System.out.println("학생 고유 번호: " + student.getStudentId());
        System.out.println("학생 이름 : " + student.getStudentName());
        System.out.println("학생 상태 : " + student.getState());

        printSubjectLists(student.getStudentId());

        System.out.println("\n수강생 정보 조회 성공!");
        System.out.println();
        underline();
    }

    // 수강생 등록
    private static void createStudent() {
        //수강생 이름 및 고유번호 등록
        underline();
        System.out.println("수강생을 등록합니다...");
        System.out.print("수강생 이름 입력: ");
        String studentName = sc.next();
        Student student = new Student(sequence(INDEX_TYPE_STUDENT), studentName); // 수강생 인스턴스 생성 예시 코드
        //과목 리스트 출력
        System.out.println("---------------------------------");
        System.out.println("【 " + "\u001B[34m" + "필수과목" + "\u001B[0m" + " 】 " + "Java/객체지향/Spring/JPA/MySQL");
        System.out.println("【 " + "\u001B[34m" + "선택과목" + "\u001B[0m" + " 】 " + "디자인 패턴/Spring Security/Redis/MongoDB");
        System.out.println("---------------------------------");

        //필수 과목 개수 입력
        System.out.print("필수 과목 개수 입력 (3~5개): ");
        int mendatoryNum = sc.nextInt();
        //필수 과목 개수 조건 확인 (아닐 경우 등록 X)
        if (mendatoryNum != 3 && mendatoryNum != 4 && mendatoryNum != 5) {
            System.out.println("수강생 등록 실패!");
            underline();
            return;
        }
        //선택 과목 개수 입력
        System.out.print("선택 과목 개수 입력 (2~4개): ");
        int choiceNum = sc.nextInt();
        //선택 과목 개수 조건 확인 (아닐 경우 등록 X)
        if (choiceNum != 2 && choiceNum != 3 && choiceNum != 4) {
            System.out.println("수강생 등록 실패!");
            underline();
            return;
        }
        List<String> mendatoryNames = new ArrayList<>();
        mendatoryNames.add("Java");
        mendatoryNames.add("객체지향");
        mendatoryNames.add("Spring");
        mendatoryNames.add("JPA");
        mendatoryNames.add("MySQL");
        List<String> choiceNames = new ArrayList<>();
        choiceNames.add("디자인 패턴");
        choiceNames.add("Spring Security");
        choiceNames.add("Redis");
        choiceNames.add("MongoDB");

        //필수 과목 등록
        for (int i = 0; i < mendatoryNum; i++) {
            System.out.print("필수 과목 입력: ");
            String mendatoryName = sc.next();
            //필수 과목 검증
            if (!(mendatoryNames.contains(mendatoryName))) {
                System.out.println("수강생 등록 실패!");
                underline();
                return;
            }
            for (Subject s : subjectStore) {
                if (s.getSubjectName().equals(mendatoryName)) {
                    student.addSubject(s); //필수 과목 등록
                }
            }

        }
        //선택 과목 등록
        for (int i = 0; i < choiceNum; i++) {
            System.out.print("선택 과목 입력: ");
            sc.next();
            String choiceName = sc.nextLine();
            //필수 과목 검증
            if (!(choiceNames.contains(choiceName))) {
                System.out.println("수강생 등록 실패!");
                underline();
                return;
            }
            for (Subject s : subjectStore) { //선택 과목이 맞는지 확인
                if (s.getSubjectName().equals(choiceName)) {
                    student.addSubject(s); //선택 과목 등록
                }
            }
        }
        // 기능 구현
        studentStore.add(student);
        System.out.println("수강생 등록 성공!");
        underline();
    }


    // 수강생 목록 조회
    private static void inquireStudent() {
        System.out.println();
        underline();
        System.out.println("~~~ 수강생 목록을 조회합니다 ~~~");
        if (studentStore.isEmpty()) {   // 등록된 수강생 없을 시 종료
            System.out.println("\n등록된 수강생이 없습니다.\n");
            underline();
            return;
        }
        // 수강생 목록 출력
        for (Student student : studentStore) {
            System.out.println("고유 번호: " + student.getStudentId());
            System.out.println("이름 : " + student.getStudentName());
        }
        System.out.println("~~~ 수강생 목록 조회 성공! ~~~");
        underline();
    }

    private static void displayScoreView() {
        boolean flag = true;
        while (flag) {
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급 조회");
            System.out.println("5. 특정 상태 수강생들의 필수 과목 평균 등급 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");
            int input = sc.nextInt();

            switch (input) {
                case 1 -> createScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> inquireAvgDegreeBySubject(); // 수강생의 과목별 평균 등급 조회
                case 5 -> inquireAvgManDegreeByState(); // 특정 상태 수강생들의 필수 과목 평균 등급 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }

    // 수강생의 과목별 평균 등급 조회
    private static void inquireAvgDegreeBySubject() {
        System.out.println(); underline();
        String studentId = getStudentId();

        List<Score> studentScoreList = getScoreListByStudent(studentId);
        if(studentScoreList.isEmpty()) {
            System.out.println("\n입력된 수강생의 점수가 없습니다.\n");
            underline();
            return;
        }

        Map<String, Integer[]> subjectScore = new HashMap<>();
        // 학생의 수강 과목과 수강 과목의 총 점수 및 과목 수를 저장하는 맵
        for (Score score : studentScoreList) {
            int sumScore = score.getScore();
            int count = 1;
            // 입력된 수강생의 과목별 총점 계산
            if(subjectScore.containsKey(score.getSubjectName())) {
                sumScore += subjectScore.get(score.getSubjectName())[0];
                count = subjectScore.get(score.getSubjectName())[1] + 1;
            }
            subjectScore.put(score.getSubjectName(), new Integer[]{sumScore, count});
        }

        // 결과 출력
        System.out.println("~~~ 학생 id" + studentId + "의 과목별 평균 등급을 조회합니다. ~~~");
        for (Map.Entry<String, Integer[]> entry : subjectScore.entrySet()) {
            int avgScore = entry.getValue()[0] / entry.getValue()[1];
            String subjectType = "MANDATORY";
            for (Score score : studentScoreList)
                if (score.getSubjectName().equals(entry.getKey())) {
                    subjectType = score.getSubjectType();
                    break;
                }
            String avgGrade = Score.convertToGrade(subjectType, avgScore);
            System.out.println("과목명 : " + entry.getKey() + ", 평균 등급 : " + avgGrade);
        }
        System.out.println("~~~ 조회 성공! ~~~");
        underline();
    }

    // 특정 상태 수강생들의 필수 과목 평균 등급 조회
    private static void inquireAvgManDegreeByState() {
        System.out.print("조회하고 싶은 수강생들의 상태를 입력하세요 (Green, Yellow, Red) : ");
        String state = sc.next();

        try{
            StudentState.valueOf(state);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 상태 입력값입니다.");
            underline();
            return;
        }

        List<Student> studentsByState = new ArrayList<>();
        // 특정 상태의 수강생들 리스트에 저장
        for (Student student : studentStore) {
            if (student.getState() == StudentState.valueOf(state)) studentsByState.add(student);
        }
        // 특정 상태의 수강생들 존재 X
        if(studentsByState.isEmpty()) {
            System.out.println("\n" + state + "상태의 학생이 없습니다.\n");
            underline();
            return;
        }

        System.out.println("~~~ " + state + "상태 학생들의 필수 과목 평균 등급을 조회합니다. ~~~");
        for (Student student : studentsByState) {
            List<Score> scoreListByStudent = getScoreListByStudent(student.getStudentId());

            if(studentsByState.size() == 1 && scoreListByStudent.isEmpty()) {
                System.out.println("\n입력된 점수가 없습니다.\n");
                underline();
                return;
            }

            int sumScore = 0;
            int count = 0;
            for (Score score : scoreListByStudent) {
                if (!score.getSubjectType().equals("MANDATORY")) continue;
                sumScore += score.getScore();
                count++;
            }
            if (count == 0) continue;
            String avgGrade = Score.convertToGrade("MANDATORY", sumScore / count);
            System.out.println(student.getStudentName() + "학생 : " + avgGrade);
        }
        System.out.println("~~~ 조회 성공! ~~~");
        underline();
    }

    private static String getStudentId() {
        printStudentList();
        System.out.print("\n관리할 수강생의 번호를 입력하시오...");
        return sc.next();
    }

    // 수강생의 과목별 시험 회차 및 점수 등록
    private static void createScore() {
        System.out.println();
        underline();

        System.out.println("시험 점수를 등록합니다...");

        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        if(studentStore.stream().filter(x->x.getStudentId().equals(studentId)).findFirst().isEmpty()){
            System.out.println("존재하지 않는 수강생 id입니다.");
            underline();
            return;
        }

        List<Subject> subjectList = getSubjectListByStudent(studentId);
        printSubjectLists(studentId);

        if(subjectList.isEmpty()) {
            underline();
            System.out.println();
            return;
        }

        System.out.print("등록할 과목을 선택해주세요(id 입력): ");
        String selectedSubjectId = sc.next();
        Subject selectedSubject = subjectList.stream().filter(x -> x.getSubjectId().equals(selectedSubjectId)).findFirst().orElse(null);

        if (selectedSubject == null) {
            System.out.println("잘못된 과목 id 입니다. id : " + selectedSubjectId);
            underline();
            System.out.println();
            return;
        }

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

        Score scoreData = new Score(studentId, selectedSubject.getSubjectId(), selectedSubject.getSubjectType(), selectedSubject.getSubjectName(), round, score);
        scoreStore.add(scoreData);

        System.out.println("\n점수 등록 성공!");
        underline();
        System.out.println();
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

        if (subjectList.isEmpty()) {
            System.out.println("수강중인 과목이 없습니다.");
            return;
        }

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
        underline();
        System.out.println();
        System.out.println("~~수강생의 과목별 회차 점수 수정합니다.~~");
        String studentId = getStudentId(); // 관리할 수강생 고유 번호
        printSubjectLists(studentId);
        // 기능 구현 (수정할 과목 및 회차, 점수)
        System.out.println("수정할 과목의 고유 번호를 써주세요.");
        System.out.print("번호 : ");
        // 과목의 고유 번호 얻음
        String input1 = sc.next();
        // 과목 회차 번호
        int input2 = 0;
        while (true) {
            System.out.println("수정할 과목의 회차 번호를 써주세요.");
            System.out.println("(회차 범위: 1 ~ 10)");
            System.out.print("번호 : ");
            input2 = sc.nextInt();
            if (input2 < 0 || input2 > 10) {
                System.out.println("회차의 범위는 1 ~ 10 입니다.");
                // 종료
                return;
            } else {
                break;
            }
        }
        // 과목 점수
        int input3 = 0;
        while (true) {
            System.out.println("점수를 써주세요.");
            System.out.println("(점수 범위: 0 ~ 100)");
            System.out.print("점수 : ");
            input3 = sc.nextInt();
            if (input3 < 0 || input3 > 100) {
                System.out.println("점수의 범위는 0 ~ 100점 입니다.");
                // 종료
                return;
            } else {
                break;
            }
        }
        System.out.println("시험 점수를 수정합니다...");
        // 기능 구현
        int finalInput = input2;
        Optional<Score> result = scoreStore.stream()
                .filter(x -> x.getStudentId().equals(studentId) &&
                        x.getSubjectId().equals(input1) &&
                        x.getRound() == finalInput)
                .findFirst();

        if (result.isPresent()) { // 값이 있냐 없냐
            result.get().setScore(input3); // 값이 있는지 없는지 확인을 했기 떄문에 get()을 바로 쓸 수 있음
            System.out.println("점수 수정 성공!");
            System.out.println("과목 고유번호 : " + input1 + "  " + "회차 번호 : " + input2 + "  " + "수정된 점수 : " + input3);
        } else {
            System.out.println("점수 수정에 실패했습니다.");
            // 종료
            return;
        }
        System.out.println();
        underline();
    }

    // 수강생의 특정 과목 회차별 등급 조회
    private static void inquireRoundGradeBySubject() {
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

    private static void underline() {
        System.out.println("================================");
    }
}
