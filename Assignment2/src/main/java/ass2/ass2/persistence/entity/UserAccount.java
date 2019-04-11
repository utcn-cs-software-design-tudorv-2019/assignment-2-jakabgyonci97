package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "useraccount")
public class UserAccount implements Serializable {
    public static final String[] ROLES = {"STUDENT","ADMIN"};
    public static boolean isRoleValid(String role){
        for(String itr: ROLES)
            if(itr.compareTo(role)==0) return true;
         return false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid",nullable = false)
    private int userId;

    @Column(name="username",length = 15, nullable = false)
    private String userName;

    @Column(name="userpassword",length = 10, nullable = false)
    private String userPassword;

    @Column(name="usertype",length = 20, nullable = false)
    private String userType;

    public UserAccount() {
    }

    public UserAccount(String userName, String userPassword, String userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
