package sg.nus.edu.iss.vttp_project.exceptions;

public class UpdateException extends Exception{
    public UpdateException() {
        super();
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }
}
