package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    public Student findStudentByStudentid(int studentid);

    public Student findStudentByUserid(int userid);

}
