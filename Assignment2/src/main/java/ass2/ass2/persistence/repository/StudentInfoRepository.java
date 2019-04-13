package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentInfoRepository extends JpaRepository<StudentInformation, Integer> {

    public StudentInformation findStudentInformationByStudentId(int studentId);

    public StudentInformation findStudentInformationByIdStudent(int idStudent);
}
