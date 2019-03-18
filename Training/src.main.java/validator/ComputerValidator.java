package validator;

import java.time.LocalDate;

import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;

public class ComputerValidator {

  /**
   * . Validates that the computer name is not null
   *
   * @param name : computer name
   * @throws ComputerNameException : name must not be null exception
   */
  public void validateComputerName(String name) throws ComputerNameException {
    if (name == null || name.equals("")) {
      String message = "Computer name is null";
      throw new ComputerNameException(message);
    }
  }

  // TODO: format exception

  /**
   * . Validates that introduction date is not greater than discontinued
   * @param introduced : introduction date
   * @param discontinued : discontinued date
   * @throws ComputerDateCoherenceException : date coherence exception
   */
  public void validateComputerDates(LocalDate introduced, LocalDate discontinued)
      throws ComputerDateCoherenceException {
    if (((introduced != null) && (discontinued != null)) && introduced.isAfter(discontinued)) {
      String message = "introduction date cannot be greater than discontinued";
      throw new ComputerDateCoherenceException(message);
    }
  }
}
