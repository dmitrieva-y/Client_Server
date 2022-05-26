public enum Command {
    GET_ALL_PERSON("get_all_persons"),
    GET_PERSON("get_person"),
    CREATE_PERSON("create_person"),
    UPDATE_PERSON("update_person"),
    DELETE_PERSON("delete_person"),
    DELETE_ALL("delete");

    Command(String command) {
    }

    public static Command fromString(String value) {
        if (value != null) {
            for (Command com : Command.values()) {
                if (value.equalsIgnoreCase(com.name())) {
                    return com;
                }
            }
        }
        throw new IllegalArgumentException("No such value");
    }
}
