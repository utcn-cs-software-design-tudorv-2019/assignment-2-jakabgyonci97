package ass2.ass2.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contactinformation")
public class ContactInformation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcontact", nullable = false)
    private int idContact;

    @Column(name = "idstudent")
    private int idStudent;

    @Column(name = "address", length = 20)
    private String address;

    @Column(name = "phonenumber", length = 10)
    private String phoneNumber;

    @Column(name = "emailaddress", length = 20)
    private String emailAddress;

    public ContactInformation(int idStudent, String address, String phoneNumber, String emailAddress) {
        this.idStudent = idStudent;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public ContactInformation() {
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "coninfoid=" + idContact +
                ", idStudent=" + idStudent +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public ContactInformation clone() {
        ContactInformation ci = new ContactInformation(this.idStudent, this.address, this.phoneNumber, this.emailAddress);
        return ci;
    }


}
