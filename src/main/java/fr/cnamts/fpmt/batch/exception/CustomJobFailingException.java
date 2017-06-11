package fr.cnamts.fpmt.batch.exception;

public abstract class CustomJobFailingException extends RuntimeException {

    /** serial version UID for class */
    private static final long serialVersionUID = 3157387266477589076L;

    public CustomJobFailingException(final String message) {
        super(message);
    }

    /**
     * Skip stack trace
     *
     * @see java.lang.Throwable#fillInStackTrace()
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
