# 📖 프로젝트
* 프로젝트명: 캠프 관리 프로그램
  
# 👥 역할
* 강이원
    * 점수관리 -> 점수 수정
* 김동현
    * 수강생관리 -> 수강생 등록
* 김아름
    * 수강생관리 -> 수강생 조회
* 손지혁
    * 점수관리 -> 점수 등록
* 정재호
    * 점수관리 -> 특정 과목 회차별 등급 조회

# 📖 **기능 명세서**

## **필수 요구 사항**
### ⚙ **수강생 관리**
    
    - 주의‼️
        - 수강생의 고유번호는 중복될 수 없습니다.
    1. 수강생 정보를 등록할 수 있습니다.
| 등록 필수 정보 |
| --- |
| 고유 번호 |
| 이름 |
| 과목 목록 |

    2. 수강생 목록을 조회할 수 있습니다. 조회 형식은 자유입니다.
| 조회 필수 정보 |
| --- |
| 고유 번호 |
| 이름 |

### ⚙ **점수 관리**
    - 주의‼️
        - 등록하려는 과목의 회차 점수가 이미 등록되어 있다면 등록할 수 없습니다. 과목의 회차 점수가 중복되어 등록될 수 없습니다.
        - 회차에 10 초과 및 1 미만의 수가 저장될 수 없습니다. (회차 범위: 1 ~ 10)
        - 점수에 100 초과 및 음수가 저장될 수 없습니다. (점수 범위: 0 ~ 100)
    1. 수강생의 과목별 시험 회차 및 점수를 등록할 수 있습니다.
        - 점수를 등록하면 자동으로 등급이 추가 저장됩니다.
    2. 수강생의 과목별 회차 점수를 수정할 수 있습니다.
    3. 수강생의 특정 과목 회차별 등급을 조회할 수 있습니다.
        - 조회 형식은 자유입니다.
| 조회 필수 정보 |
| --- |
| 회차 |
| 등급 |

## **추가 요구 사항**
    
### ⚙ **수강생 관리**
    
    1. 수강생 상태를 관리할 수 있습니다.     
| 상태 종류 |
| --- |
| Green |
| Red |
| Yellow |
        
    2. 수강생 정보를 조회할 수 있습니다. 조회 형식은 자유입니다.    
| 조회 필수 정보 |
| --- |
| 고유 번호 |
| 이름 |
| 상태 |
| 선택한 과목명 |
        
    3. 수강생 정보를 수정할 수 있습니다.
        - 이름 또는 상태를 입력받아 수정하시면 됩니다.
| 수정 가능한 정보 |
| --- |
| 이름 |
| 상태 |
        
    4. 상태별 수강생 목록을 조회할 수 있습니다. 조회 형식은 자유입니다.
| 조회 필수 정보 |
| --- |
| 고유 번호 |
| 이름 |
        
    5. 수강생을 삭제할 수 있습니다.
        - 해당 수강생의 점수 기록도 함께 삭제됩니다.
    
### **점수 관리**
    
    1. 수강생의 과목별 평균 등급을 조회할 수 있습니다. 조회 형식은 자유입니다.
| 조회 필수 정보 |
| --- |
| 과목명 |
| 평균 등급 |
        
    2. 특정 상태 수강생들의 필수 과목 평균 등급을 조회합니다. 조회 형식은 자유입니다.
| 조회 필수 정보 |
| --- |
| 수강생 이름 |
| 필수 과목 평균 등급 |
