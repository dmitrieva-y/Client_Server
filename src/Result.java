public class Result {

    public static String ok(StatusCode statusCode, String message) {
        return statusCode.getStatus() + " " + message;
    }

    public static String ok(StatusCode statusCode) {
        return statusCode.getStatus();
    }

    public static String error(int status, String errorMsg, String invalidParam) {
        StringBuilder sb = new StringBuilder("No correct : ");
        if (status == PersonValidator.INCORRECT_PARAM) {
            return sb.append(invalidParam).append("\r\n ")
                     .append(errorMsg).toString();
        }

        return null;
    }

    public static String error(StatusCode statusCode, String text) {
        return statusCode.getStatus() + " " + text;
    }

    public static String error(StatusCode statusCode) {
        return statusCode.getStatus();
    }
}
