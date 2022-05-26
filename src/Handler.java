import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler {
    private Map<Integer, Person> personList;
    private String[] param;

    public Handler(String[] param) {
        this.personList = new HashMap<>();
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
        return personList.toString();
    }

    public String getPerson() {
        int id;
        try {
            id = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            return Result.error(StatusCod.BAD_REQUEST);
        }
        if(personList.containsKey(id)) return personList.get(id).toString();
        return Result.error(StatusCod.NOT_FOUND, "Not found person con id :" + id);
    }

    public String delete() {
        personList.clear();
        return Result.ok(StatusCod.OK);
    }

    public String update() {
        //
        return null;
    }


    public String createPerson() {
        if (param.length < 6) {
            return Result.error(StatusCod.BAD_REQUEST);
        }
        for (int i = 2; i < param.length; i++) {
            // validate
        }
        return Result.error(StatusCod.OK);
    }

    public String deletePerson(int id) {
        if (personList.containsKey(id)) {
            personList.remove(id);
            return Result.ok(StatusCod.OK);
        }else return Result.error(StatusCod.NOT_FOUND, "Not found person con id :" + id);
    }


}
