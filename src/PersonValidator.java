import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class PersonValidator {

    private static final Pattern NAME_MASK = compile("^[a-zA-Zа-яА-Я]+$");
    private static final Pattern DEPARTMENT_MASK = compile("^[a-zA-Zа-яА-Я]+(_\\w*)?$");
//    private static final Pattern PHONE_MASK = compile("^\\+?[78](-\\d{3}){2}(-\\d{2}){2}$");
    private static final Pattern PHONE_MASK = compile("^\\+?[78](-\\d{3}){2}(-\\d{2}){2}$");
    public static final int INCORRECT_PARAM = 0;


    public static String validatePerson(Person person) {
        // по всем полям?
        if (!validatePerson(person.getName())) {
            return Result.error(INCORRECT_PARAM, "name not valid", person.getName());
        }

        if (!validatePerson(person.getLastname())) {
            return Result.error(INCORRECT_PARAM, "lastname not valid", person.getLastname());
        }

        if (!validatePhone(person.getPhone_number())) {
            return Result.error(INCORRECT_PARAM, "phone not valid", person.getPhone_number());
        }

        if (!validateDepartment(person.getDepartment())) {
            return Result.error(INCORRECT_PARAM, "department not valid", person.getDepartment());
        }

        return "";
    }

    public static boolean validatePerson(String textParam) {
        return textParam != null && !textParam.isEmpty() && NAME_MASK.matcher(textParam).matches();
    }

    public static boolean validatePhone(String phone) {
        return phone != null && !phone.isEmpty() && PHONE_MASK.matcher(phone).matches();
    }

    public static boolean validateDepartment(String department) {
        return department != null && !department.isEmpty() && DEPARTMENT_MASK.matcher(department).matches();
    }
 }
