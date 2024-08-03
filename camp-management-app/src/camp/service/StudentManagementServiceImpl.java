package camp.service;

import camp.dto.StudentDTO;
import camp.dto.SubjectDTO;
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
    public Optional<SubjectDTO> findSubject(Long studentId, String subjectName) {
        return subjectRepository.find(studentId, subjectName);
    }


    @Override
    public void inquireSubjects(Long studentId) {
        Optional<List<SubjectDTO>> result = subjectRepository.findAllById(studentId);
        if(result.isPresent()) {
            List<SubjectDTO> subjects = result.get();
            for(SubjectDTO subject : subjects) {
                System.out.println("SubjectName: " + subject.getName() + " SubjectType: " + subject.getType());
            }
        }
        else{
            System.out.println("studentId: " + studentId + "가 수강중인 과목이 없습니다.");
        }
    }

    @Override
    public void inquireStudents(){
        Optional<List<StudentDTO>> findall = studentRepository.findAll();
        if(findall.isPresent()){
            List<StudentDTO> students = findall.get();
            for(StudentDTO student : students){
                System.out.println("ID: " + student.getId() + " / " + "Name: " + student.getName());
            }
        }
        else{
            System.out.println("등록된 학생들이 없습니다.");
        }
    }
}
