package ass2.ass2.persistence.repository;

import ass2.ass2.persistence.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<UserAccount,Integer> {


    public UserAccount findUserByUserNameAndUserPassword(String userName, String userPassword);

}
