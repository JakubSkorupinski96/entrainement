package exception;

public class ComputerException extends Exception{
  
  private Exceptions exceptions;
  private static final long serialVersionUID = 1L;
  
  public ComputerException(String message, Exceptions cause) {
    super(message);
    this.exceptions = cause;
  }
  
  public Exceptions getException() {
    return this.exceptions;
  }
  
  public void setException(Exceptions ex) {
    this.exceptions = ex;
  }

}
