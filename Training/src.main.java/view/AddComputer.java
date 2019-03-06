package view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.CompanyController;
import controller.ComputerController;
import dto.ComputerDTO;
import exception.ComputerException;
import exception.Exceptions;
import mapper.ComputerMapper;
import model.Company;
import validation.ComputerValidation;

/**
 * . Servlet implementation class AddComputer
 */

@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private ComputerController computerController = ComputerController.getInstance();
  private ComputerDTO computerDTO;
  private ComputerMapper computerMapper;
  private ComputerValidation computerValidator = new ComputerValidation();
  
  private CompanyController companyController = CompanyController.getInstance();
  
  private static Logger logger = LoggerFactory.getLogger(AddComputer.class);
  
  private String view = "/Dashboard";


  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * . DoGet de addComputer
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   *
   * @param request  : request
   * @param response : response
   *
   * @throws ServletException : IOException
   * @throws IOException      : IOException
   *
   */

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<Company> companies = companyController.listAll();
    request.setAttribute("companies", companies);
    request.getRequestDispatcher("/addComputer.jsp").forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   *
   * @param request  : request
   * @param response : response
   *
   * @throws ServletException : ServletException
   * @throws IOException      : IOException
   */

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String name = request.getParameter("name");
    String introduced = request.getParameter("introduced") + " 00:00:00";
    String discontinued = request.getParameter("discontinued") + " 00:00:00";
    String companyId = request.getParameter("companyId");
//    try {
//      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
//      computerValidator.validateComputer(name, LocalDate.parse(introduced,formatter), LocalDate.parse(discontinued,formatter));
//    } catch (ComputerException e) {
//      logger.error("This computer does not match the required format");
//      Exceptions cause = e.getException();
//      String errorMessage;
//      switch(cause) {
//      case InvalidDateException:
//        logger.error("Introduced cannot be greater than Discontinued");
//        errorMessage = "Introduced cannot be greater than Discontinued";
//        request.setAttribute(errorMessage, errorMessage);
//        this.getServletContext().getRequestDispatcher(view).forward(request, response);
//        break;
//      case NullNameException:
//        logger.error("Name cannot be null");
//        errorMessage = "Name cannot be null";
//        request.setAttribute(errorMessage, errorMessage);
//        this.getServletContext().getRequestDispatcher(view).forward(request, response);
//        break;
//      default:
//        logger.error("An error have occured while creating this computer");
//        errorMessage = "An error have occured while creating this computer";
//        request.setAttribute(errorMessage, errorMessage);
//        this.getServletContext().getRequestDispatcher(view).forward(request, response);
//        break;
//      }
//    }
    try {
      computerController.create(name, introduced, discontinued, Integer.parseInt(companyId));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ComputerException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    ServletContext context = getServletContext();
    RequestDispatcher rd = context.getRequestDispatcher(view);
    rd.forward(request, response);
  }

}
