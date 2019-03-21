package controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;
import model.Company;
import model.QCompany;
import services.CompanyServices;
import services.ComputerServices;

@Controller
@RequestMapping("/AddComputer")
public class AddComputerController {

  @Autowired
  ComputerServices computerServices;

  @Autowired
  CompanyServices companyServices;

  private static Logger logger = LoggerFactory.getLogger(AddComputerController.class);

  @RequestMapping(method = RequestMethod.GET)
  public String get(Model model) {
    List<Company> companies = companyServices.listAll();
    model.addAttribute("companies", companies);
    return "addComputer";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String post(@RequestParam("name") String name,
      @RequestParam("introduced") String introduced,
      @RequestParam("discontinued") String discontinued,
      @RequestParam("companyId") String companyId, Model model) {

    String jsp = "dashboard";
    String errorMessage = "default";
    boolean error = false;

    introduced += " 00:00:00";
    discontinued += " 00:00:00";

    try {
      computerServices.createComputer(name, introduced, discontinued, Integer.parseInt(companyId));
    } catch (NumberFormatException e) {
      // TODO: check exception
      errorMessage = "The selected company doesn't have an id";
      logger.error(errorMessage);
      error = true;
    } catch (ComputerNameException e) {
      errorMessage = "Computer name cannot be empty";
      logger.error(errorMessage);
      error = true;
    } catch (ComputerDateCoherenceException e) {
      errorMessage = "Introduced cannot be greater than Discontinued";
      logger.error(errorMessage);
      error = true;
    }

    if (error) {
      List<Company> companies = companyServices.listAll();
      jsp = "addComputer";
      model.addAttribute("errorMessage", errorMessage);
      model.addAttribute("error", error);
      model.addAttribute("name", name);
      model.addAttribute("companies", companies);
    }

    return jsp;
  }

}
