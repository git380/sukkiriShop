package exception;

public class ChumonException extends Exception {
    public ChumonException() { }
    public ChumonException(String msg) {
        super(msg);
    }
    public ChumonException(String msg, Throwable except) {
        super(msg, except);
    }
}