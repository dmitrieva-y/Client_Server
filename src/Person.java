import javax.xml.crypto.Data;

public class Person {
    private String name;
    private String surname;
    private Data berthday;

    public Person(String name, String surname, Data berthday) {
        this.name = name;
        this.surname = surname;
        this.berthday = berthday;
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

    public Data getBerthday() {
        return berthday;
    }

    public void setBerthday(Data berthday) {
        this.berthday = berthday;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", berthday=" + berthday;
    }
}
