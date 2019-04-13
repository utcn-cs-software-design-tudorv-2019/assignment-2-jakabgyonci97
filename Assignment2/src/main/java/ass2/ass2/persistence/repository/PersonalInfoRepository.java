package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PersonalInfoRepository extends JpaRepository<PersonalInformation, Integer> {

    public PersonalInformation findPersonalInformationByIdStudent(int idStudent);

    public PersonalInformation findByIdStudent(int idStudent);
}
