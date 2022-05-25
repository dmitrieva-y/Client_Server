import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private Date berthday;

    public Person(String name, String surname, Date berthday) {
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

    public Date getBerthday() {
        return berthday;
    }

    public void setBerthday(Date berthday) {
        this.berthday = berthday;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", berthday=" + berthday;
    }

    //    public static void init() throws ParseException {
//        persons = new ArrayList<>();
//        Date date =  new SimpleDateFormat( "dd.MM.yyyy" ).parse( "28.12.1982");
//
//        persons.add(new Person("Bob", "Smitt", date ));
//    }
}
