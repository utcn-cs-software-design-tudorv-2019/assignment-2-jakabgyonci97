package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepository extends JpaRepository<Course, Integer> {

    public Course findCourseByNameAndSession(String name,String session);

    public Course findCourseByCourseId(int courseId);
}
