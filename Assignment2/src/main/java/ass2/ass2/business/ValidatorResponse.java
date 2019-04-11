package ass2.ass2.business;

public class ValidatorResponse {
    private boolean validity;
    private String message;

    public ValidatorResponse() {
        super();
    }

    public ValidatorResponse(boolean validity, String message) {
        this.validity = validity;
        this.message = message;
    }

    public boolean isValid() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ValidatorResponse{" +
                "validity=" + validity +
                ", message='" + message + '\'' +
                '}';
    }
}
