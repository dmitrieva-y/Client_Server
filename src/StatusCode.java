
// можно вместо своего Enum'а использовать уже имеющиеся коды из пакета java.net.HttpURLConnection
public enum StatusCode {
    NOT_FOUND("404 NOT FOUND"),
    BAD_REQUEST("400 BAD REQUEST"),
    OK("200 OK");

    private String status;

    StatusCode(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
