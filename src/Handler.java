import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Handler {
    private final Map<Integer, Person> personList;
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
        if (param.length < 2) return Result.error(StatusCod.BAD_REQUEST);
        int id;
        try {
            id = Integer.parseInt(param[1]);
        } catch (NumberFormatException e) {
            return Result.error(StatusCod.BAD_REQUEST);
        }
        if (personList.containsKey(id)) return personList.get(id).toString();
        return Result.error(StatusCod.NOT_FOUND, "Not found person con id :" + id);
    }

    public String delete() {
        personList.clear();
        return Result.ok(StatusCod.OK);
    }

    //обновление 1 поля: д.б.  id, название поля и новое значение
    public String update() {
        if (param.length < 4) {
            return Result.error(StatusCod.BAD_REQUEST, "parameters not enough.");
        }
        for (int i = 1; i < param.length; i++) {

        }
        //проверка соответствия названая парамета - изменяемому полю
        return null;
    }


    public String createPerson() {
        if (param.length < 6) {
            return Result.error(StatusCod.BAD_REQUEST);
        }
        //предполагается что не обязательных полей вообще нет в строке?
        String surname = param[1];
        String name = param[2];
        String lastname = param[3];
        String birthday = param[4];
        String phone = param[5];
        String department = param[6];
        String position = param[7];
        String head = param[8];

        return Result.error(StatusCod.OK);
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
