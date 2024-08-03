package camp.service;

import camp.dto.StudentDTO;

import java.util.List;

public interface StudentManagementService {
    void add(StudentDTO student);
    List<StudentDTO> findAll();
}
