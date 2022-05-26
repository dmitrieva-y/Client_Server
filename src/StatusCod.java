public enum StatusCod {
    NOT_FOUND("404 NOT FOUND"), BAD_REQUEST("400 BAD REQUEST"), OK("200 OK");

    private String status;
    StatusCod(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
