package tms.dto;

public class RegUserDTO {

    private final String firstname;
    private final String lastname;
    private final int age;
    private final String phoneNumber;

    public RegUserDTO(String firstname, String lastname, int age, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "RegUserDTO{" +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
