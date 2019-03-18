package exception;

public class ComputerNameException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * . ComputerNameException constructor
   * @param message : error message
   */
  public ComputerNameException(String message) {
    super(message);
  }

}
