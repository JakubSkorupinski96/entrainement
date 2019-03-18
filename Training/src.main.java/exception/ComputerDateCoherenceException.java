package exception;

public class ComputerDateCoherenceException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * . ComputerDateCoherenceException contrustor
   *
   * @param message : error message
   */
  public ComputerDateCoherenceException(String message) {
    super(message);
  }
}
