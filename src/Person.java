import java.time.LocalDate;
import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private LocalDate birthday;

    public Person(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBerthday() {
        return birthday;
    }

    public void setBerthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", birthday=" + birthday;
    }


}
