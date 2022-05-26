public class Result {
    public static String  ok(StatusCod statusCod, String message) {
        return statusCod.toString() + " " + message;
    }
    public static String  ok(StatusCod statusCod) {
        return statusCod.toString()  ;
    }

    public static String error(int status, String errorMsg, String invalidParam) {
        return null;
    }

    public static String error(StatusCod statusCod, String text) {
        return statusCod.toString() + " " + text;
    }

    public static String error(StatusCod statusCod) {
        return statusCod.toString();
    }
}
