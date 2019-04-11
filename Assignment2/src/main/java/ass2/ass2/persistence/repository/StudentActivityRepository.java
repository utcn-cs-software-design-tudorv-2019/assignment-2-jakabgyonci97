package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.StudentActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StudentActivityRepository extends JpaRepository<StudentActivity, Integer> {

    public List<StudentActivity> findAllByIdStudent(int idStudent);

}
