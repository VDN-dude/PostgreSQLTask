package tms.entity;

public class User {
    private long id;
    private String firstname;
    private String lastname;
    private int age;
    private String phoneNumber;

    public User(String firstname, String lastname, int age, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public User(long id, String firstname, String lastname, int age, String phoneNumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public User() {
    }

    public long getId() {
        return id;
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
