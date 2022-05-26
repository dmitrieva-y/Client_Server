import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class PersonValidator {

    private static final Pattern NAME_MASK = compile("^[azAZаяАЯ]$");
    private static final int INCORRECT_PARAM = 0;

    public static String validatePerson(Person person) {

        String name = person.getName();
        if (name == null || name.isEmpty() || !NAME_MASK.matcher(name).matches()) {
            return Result.error(INCORRECT_PARAM, "name not valid", name);
        }else return null;
    }
}
