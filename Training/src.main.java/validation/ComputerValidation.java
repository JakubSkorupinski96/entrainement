package validation;

import java.time.LocalDate;

import exception.ComputerException;
import exception.Exceptions;
import model.Company;

public class ComputerValidation {
  
  public void validateComputerName(String name) throws ComputerException {
    if (name == null || name.equals("")) {
      String message = "Computer name is null";
      throw new ComputerException(message);
    }
  }
  
  public void validateComputerDates(LocalDate introduced, LocalDate discontinued) throws ComputerException {
    if (((introduced != null) && (discontinued != null)) && introduced.isAfter(discontinued)) {
      String message = "introduction date cannot be greater than discontinued";
      throw new ComputerException(message);
    }
  }
}
