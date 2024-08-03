package camp.service;

import camp.entity.Student;
import camp.entity.Subject;
import camp.repository.StudentRepository;
import camp.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

public class StudentManagementServiceImpl implements StudentManagementService {

    StudentRepository studentRepository;
    SubjectRepository subjectRepository;

    public StudentManagementServiceImpl(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public void add(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void addSubjects(List<Subject> subjects) {
        subjects.forEach(subjectRepository::save);
    }

    @Override
    public Optional<Subject> findSubject(Long studentId, String subjectName) {
        return subjectRepository.find(studentId, subjectName);
    }


    @Override
    public void inquireSubjects(Long studentId) {
        System.out.println("studentId: " + studentId + "의 수강 과목 리스트");
        Optional<List<Subject>> result = subjectRepository.findAllById(studentId);
        if(result.isPresent()) {
            List<Subject> subjects = result.get();
            for(Subject subject : subjects) {
                System.out.println("SubjectName: " + subject.getName() + " SubjectType: " + subject.getType());
            }
        }
        else{
            System.out.println("studentId: " + studentId + "가 수강중인 과목이 없습니다.");
        }
    }

    @Override
    public void inquireStudents(){
        System.out.println("- 수강생 목록 -");
        Optional<List<Student>> findall = studentRepository.findAll();
        if(findall.isPresent()){
            List<Student> students = findall.get();
            for(Student student : students){
                System.out.println("ID: " + student.getId() + " / " + "Name: " + student.getName());
            }
        }
        else{
            System.out.println("등록된 학생들이 없습니다.");
        }
    }

    @Override
    public boolean containsSubject(Long studentId, String subjectName) {
        Optional<List<Subject>> find = subjectRepository.findAllById(studentId);
        if(find.isEmpty()) return false;
        List<Subject> subjects = find.get();
        for(Subject subject : subjects){
            if(subject.getName().equals(subjectName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isValidStudentId(Long studentId) {
        return studentRepository.findById(studentId).isPresent();
    }
}
