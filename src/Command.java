public enum Command {
    GET_ALL_PERSON("get_all_persons"),
    GET_PERSON("get_person"),
    CREATE_PERSON("create_person"),
    UPDATE_PERSON("update_person"),
    DELETE_PERSON("delete_person"),
    DELETE_ALL("delete_all_persons");

    private String command;
    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Command fromString(String value) {
        if (value != null) {
            for (Command com : Command.values()) {
                if (value.equalsIgnoreCase(com.getCommand())) {
                    return com;
                }
            }
        }

        throw new IllegalArgumentException("No such value");
    }
}
