public class Result {
    public static String  ok(StatusCod statusCod, String message) {
        return statusCod.getStatus() + " " + message;
    }
    public static String  ok(StatusCod statusCod) {
        return statusCod.getStatus()  ;
    }

    public static String error(int status, String errorMsg, String invalidParam) {
        StringBuilder sb = new StringBuilder("No correct : ");
        if (status == 0){
            return sb.append(invalidParam).append("\r\n ").append(errorMsg).toString();
        }
        return null;
    }

    public static String error(StatusCod statusCod, String text) {
        return statusCod.getStatus() + " " + text;
    }

    public static String error(StatusCod statusCod) {
        return statusCod.getStatus();
    }
}
