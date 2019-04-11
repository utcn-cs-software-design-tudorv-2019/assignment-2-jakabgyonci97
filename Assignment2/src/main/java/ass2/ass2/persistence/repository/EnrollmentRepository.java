package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    public List<Enrollment> findAllByIdStudent(int idStudent);
}
