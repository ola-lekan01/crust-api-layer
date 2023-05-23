package africa.crust.exceptions;

public class GenericException extends Exception{

    private static final String ERROR_MESSAGE_400 = "HTTP Request failed with status code: 400";

    public GenericException() {
    }

    public GenericException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        String originalMessage = super.getMessage();
        if (originalMessage != null && originalMessage.equals(ERROR_MESSAGE_400)) {
            return ERROR_MESSAGE_400;
        }
        return originalMessage;
    }
}
