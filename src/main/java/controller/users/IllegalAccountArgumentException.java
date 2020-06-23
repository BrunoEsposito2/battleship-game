package controller.users;

public class IllegalAccountArgumentException extends IllegalArgumentException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IllegalAccountArgumentException() {
    }

    public IllegalAccountArgumentException(final String message) {
        super(message);
    }

    public IllegalAccountArgumentException(final String message, final Throwable ex) {
        super(message, ex);
    }

    public final String getMessage() {
        return super.getMessage();
    }


}
