import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class PersonValidator {

    private static final Pattern NAME_MASK = compile("^[a-zA-Zа-яА-Я]+$");
    private static final Pattern PHONE_MASK = compile("^\\+?[78]-\\d{3}-\\d{3}(-\\d{2}){2}$");
    private static final int INCORRECT_PARAM = 0;


    public static String validatePerson(Person person) {
        // по всем полям?
        if (!noValidatePerson(person.getName()))
            return Result.error(INCORRECT_PARAM, "name not valid", person.getName());
        else return Result.ok(StatusCod.OK);
    }

    public static boolean noValidatePerson(String textParam) {
        return textParam == null || textParam.isEmpty() || !NAME_MASK.matcher(textParam).matches();
    }

    public static String validatePhone(String textParam) {
        if (textParam == null || textParam.isEmpty() || !PHONE_MASK.matcher(textParam).matches()) {
            return Result.error(INCORRECT_PARAM, "name not valid", textParam);
        } else return Result.ok(StatusCod.OK);
    }
}
/* Написать однопоточный http-сервер на сокетах, позволяющий производить CRUD-операции над коллекцией элементов Person.
* GET-запрос - получение данных:
	       get_all_persons - получаем все элементы (аргументов нет).
	       get_person - получаем элемент по id
- POST-запрос: create_person: surname* name* lastname* birthday phone_number* (маска +N_NNN_NNN_NN_NN) department* position* head
               update_person name \ lastname ... - обновление 1-го поля
	       delete_person - удаление элемента по id
	       delete_all_persons - удаление всех элементов
- Реализовать валидацию параметров запроса (на существование / корректность данного параметра) с выводом сообщений об ошибках пользователю
* */