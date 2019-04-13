package ass2.ass2.business;

import ass2.ass2.persistence.entity.*;
import ass2.ass2.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service()
public class LoginService {
    private static final int USERNAME_LENGTH = 15;
    private static final int PASSWORD_LENGTH = 10;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentRepository studentRepository;

    private Validator validator;

    public LoginService() {
        validator = new Validator();
    }

    public UserAccount loginUser(UserAccount user) {
        ValidatorResponse vrUserName = validator.validateUserName(user.getUserName(), USERNAME_LENGTH, Validator.CheckType.CHECK_ALL);
        ValidatorResponse vrPassword = validator.validatePassword(user.getUserPassword(), PASSWORD_LENGTH, Validator.CheckType.CHECK_ALL);

        if (!vrUserName.isValid() && !vrPassword.isValid())
            return null;
        return userRepository.findByUserNameAndUserPassword(user.getUserName(), user.getUserPassword());
    }

    public Student loginAsStudent(UserAccount user) {
        return studentRepository.findStudentByUserid(user.getUserId());
    }

}
