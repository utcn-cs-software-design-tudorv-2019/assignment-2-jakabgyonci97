package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ContactInfoRepository extends JpaRepository<ContactInformation, Integer> {
    public ContactInformation findByIdStudent(int idStudent);
}
