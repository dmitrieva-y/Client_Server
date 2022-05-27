import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Person {
    private static int count = 0;
    private int id;
    private String surname;
    private String name;
    private String lastname;
    private String phone_number;
    private String department;
    private String position;
    private String head;
    private LocalDate birthday;

    public Person() {
    }

    public Person setFields(Map<String, String> param) {
        Person person = new Person();
        person.setId(count++);
        // проверка на null в обязательном поле
        person.setName(param.get("name"));
        person.setLastname(param.get("lastname"));
        person.setSurname(param.get("surname"));
        person.setDepartment(param.get("department"));
        person.setHead(param.get("department"));
        person.setPhone_number(param.get("phone_number"));
        person.setHead(param.get("head"));
        return person;
    }

    public String getLastname() {
        return lastname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    public Person(String name, String lastname, LocalDate birthday) {
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.id = count++;
    }

    public static int getCount() {
        return count;
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

    public int getId() {
        return id;
    }

    public static Map<String, String> getMapFields() {
        Map<String, String> fields = new HashMap<>();
        try {
            Class clazz = Class.forName("Person");
            Field[] fs = clazz.getDeclaredFields();
            for (Field f : fs) {
                fields.put(f.getName(), "");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fields;
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", lastname =" + lastname;
    }


}
