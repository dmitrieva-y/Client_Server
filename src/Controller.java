import java.time.LocalDate;
import java.util.*;

public class Controller {
    private static Map<Integer, Person> personList;
    private String[] param;

    public Controller(String[] param) {
        personList = new HashMap<>();
        this.param = param;
        init();
    }

    private void init() {
        personList.put(0, new Person("Bob", "Smitt", LocalDate.of(1914, 7, 28)));
    }

    public String getAllPerson() {
        if (personList.isEmpty()) {
            return Result.error(StatusCod.NOT_FOUND);
        }
        return Result.ok(StatusCod.OK,getPersons().toString());
    }

    public List<Person> getPersons() {
        return new ArrayList<>(personList.values());
    }

    public String getPerson() {
        if (param.length < 2) return Result.error(StatusCod.BAD_REQUEST);
        int id;
        try {
            id = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            return Result.error(StatusCod.BAD_REQUEST);
        }
        if (personList.containsKey(id)) {
            return Result.ok(StatusCod.OK, personList.get(id).toString());
        }
        return Result.error(StatusCod.NOT_FOUND, "Not found person con id :" + id);
    }


    public String delete() {
        personList.clear();
        return Result.ok(StatusCod.OK);
    }

    //обновление 1 поля: д.б. метод id, название поля и новое значение
    public String update() {
        if (param.length < 4) {
            return Result.error(StatusCod.BAD_REQUEST, "parameters not enough.");
        }
        int id = Integer.parseInt(param[1]);
        Person person = personList.get(id);
        if (person == null) return Result.error(StatusCod.BAD_REQUEST, " нет person c id: " + id);
        String field = param[2];
        String value = param[3];
        if (field.equals("name")) {
            if (PersonValidator.validatePerson(value)) {
                person.setName(value);

            } else
                Result.error(StatusCod.BAD_REQUEST, " не корректное значение поля " + field + "должны быть только буквы");
        } else if (field.equals("lastname")) {
            if (PersonValidator.validatePerson(value)) {
                person.setLastname(value);
            } else
                Result.error(StatusCod.BAD_REQUEST, " не корректное значение поля " + field + "должны быть только буквы");
        } else Result.error(StatusCod.BAD_REQUEST, " не корректное поле " + field);
        personList.put(id, person);
        return Result.ok(StatusCod.OK);
    }

    public String createPerson() {
        Person person = new Person();
        Map<String, String> fields = Person.getMapFields();
        if (param.length < 7) {
            return Result.error(StatusCod.BAD_REQUEST, "parameters not enough.");
        }
        for (int i = 1; i < param.length; i++) {
            String field = param[i].split(":")[0];
            String value = param[i].split(":")[1];
            if (fields.containsKey(field)) {
                fields.put(field, fields.get(field) + value);
            } else {
                return Result.error(StatusCod.BAD_REQUEST, "не корректное имя поля " + field + "или значение " + value);
            }

        }
        person = person.setFields(fields);
        PersonValidator.validatePerson(person);
        personList.put(person.getId(), person);
        System.out.println(getPersons());
        return Result.ok(StatusCod.OK);
    }

    public String deletePerson() {
        if (param.length < 2) return Result.error(StatusCod.BAD_REQUEST);
        int id;
        try {
            id = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            return Result.error(StatusCod.BAD_REQUEST);
        }
        if (personList.containsKey(id)) {
            personList.remove(id);
            return Result.ok(StatusCod.OK);
        } else return Result.error(StatusCod.NOT_FOUND, "Not found person con id :" + id);
    }


}
