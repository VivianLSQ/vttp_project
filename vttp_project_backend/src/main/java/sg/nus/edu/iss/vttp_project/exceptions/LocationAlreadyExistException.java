package sg.nus.edu.iss.vttp_project.exceptions;

public class LocationAlreadyExistException extends Exception{
    public LocationAlreadyExistException() {
        super();
    }

    public LocationAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationAlreadyExistException(String message) {
        super(message);
    }

    public LocationAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
