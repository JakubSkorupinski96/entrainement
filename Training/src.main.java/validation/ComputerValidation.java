package validation;

import java.time.LocalDate;

import exception.ComputerException;
import exception.Exceptions;
import model.Company;

public class ComputerValidation {
  
  
  public void validateComputer(String name, LocalDate introduced, LocalDate discontinued, Company company) throws ComputerException {
    if (name == null || name.equals("")) {
      String message = "Computer name is null";
      throw new ComputerException(message, Exceptions.NullNameException);
    }
    if (((introduced != null) && (discontinued != null)) && introduced.isAfter(discontinued)) {
      String message = "introduction date cannot be greater than discontinued";
      throw new ComputerException(message, Exceptions.InvalidDateException);
    }
  }
}
