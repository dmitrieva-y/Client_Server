import java.time.LocalDate;
import java.util.*;

public class Controller {
    // лучше использовать для хранения personList класс Context
    private static Map<Integer, Person> personList;

    public Controller() {
        personList = new HashMap<>();
        //     init();
    }

    private void init() {
        personList.put(0, new Person("Bob", "Smitt", "1954, 7, 28"));
        personList.put(1, new Person("NICK", "Smitt", "1984, 7, 21"));
    }

    public String getAllPerson() {
        // при отсутствии элементов можно выводить пустой список
        if (personList == null) {
            return Result.error(StatusCode.NOT_FOUND);
        }

        return Result.ok(StatusCode.OK, getPersons().toString());
    }

    public List<Person> getPersons() {
        return new ArrayList<>(personList.values());
    }

    public String getPerson(String[] param) {
        // лучше добавить более точную проверку: не на кол-во параметров, а на их наличие (по имени)
        if (param.length < 2) {
            return Result.error(StatusCode.BAD_REQUEST);
        }

        int id;
        try {
            id = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            return Result.error(StatusCode.BAD_REQUEST);
        }

        if (personList.containsKey(id)) {
            return Result.ok(StatusCode.OK, personList.get(id).toString());
        }

        return Result.error(StatusCode.NOT_FOUND, "Not found person con id :" + id);
    }

    public String delete() {
        personList.clear();
        return Result.ok(StatusCode.OK);
    }

    //обновление 1 поля: д.б. метод id, название поля и новое значение
    public String update(String[] param) {
        // лучше добавить более точную проверку: не на кол-во параметров, а на их наличие (по имени)
        if (param.length < 4) {
            return Result.error(StatusCode.BAD_REQUEST, "parameters not enough.");
        }

        int id = Integer.parseInt(param[1]);
        Person person = personList.get(id);
        if (person == null) {
            return Result.error(StatusCode.BAD_REQUEST, " нет person c id: " + id);
        }

        String field = param[2];
        String value = param[3];

        // когда много if/else, код трудно читается, можно запутаться.
        if (field.equals("name")) {
            if (PersonValidator.validatePerson(value)) {
                person.setName(value);
            } else {
                Result.error(StatusCode.BAD_REQUEST, " не корректное значение поля " + field + "должны быть только буквы");
            }
        }

        if (field.equals("lastname")) {
            if (PersonValidator.validatePerson(value)) {
                person.setLastname(value);
            } else {
                Result.error(StatusCode.BAD_REQUEST, " не корректное значение поля " + field + "должны быть только буквы");
            }
        } else {
            Result.error(StatusCode.BAD_REQUEST, " не корректное поле " + field);
        }

        personList.put(id, person);
        return Result.ok(StatusCode.OK);
    }

    public String createPerson(String[] param) {
        Person person = new Person();
        Map<String, String> fields = Person.getMapFields();
        if (param.length < 7) {
            return Result.error(StatusCode.BAD_REQUEST, "parameters not enough.");
        }

        for (int i = 1; i < param.length; i++) {
            String field = param[i].split(":")[0];
            String value = param[i].split(":")[1];
            if (fields.containsKey(field)) {
                fields.put(field, fields.get(field) + value);
            } else {
                return Result.error(StatusCode.BAD_REQUEST, "не корректное имя поля " + field + "или значение " + value);
            }
        }

        person = person.setFields(fields);
        String validate = PersonValidator.validatePerson(person);
        if (!validate.isEmpty()) {
            return validate;
        }
        personList.put(person.getId(), person);
        System.out.println(getPersons());
        return Result.ok(StatusCode.OK);
    }

    public String deletePerson(String[] param) {
        // лучше добавить более точную проверку: не на кол-во параметров, а на их наличие (по имени)
        if (param.length < 2) {
            return Result.error(StatusCode.BAD_REQUEST);
        }

        int id;
        try {
            id = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            return Result.error(StatusCode.BAD_REQUEST);
        }

        // как вариант, можно не добавлять contains.
        // Использовать для проверки статуса удаления объект, возвращаемый remove()
        return personList.remove(id) == null
                ? Result.error(StatusCode.NOT_FOUND, "Not found person con id :" + id)
                : Result.ok(StatusCode.OK);
    }
}
