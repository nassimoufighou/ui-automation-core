package core.preconditions.response;

public class Response<T> {

    private int responseCode;
    private boolean isSuccessFul;
    private String responseBody;
    private String errorMessage;

    public Response(boolean isSuccessFul, int responseCode, String responseBody) {
        this.isSuccessFul = isSuccessFul;
        this.responseCode = responseCode;
        this.responseBody = responseBody;
    }

    public Response(boolean isSuccessFul, int responseCode, String responseBody, String errorMessage) {
        this.isSuccessFul = isSuccessFul;
        this.responseCode = responseCode;
        this.responseBody = responseBody;
        this.errorMessage = errorMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccessFul() {
        return isSuccessFul;
    }
}
