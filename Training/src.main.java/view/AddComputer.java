package view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controller.CompanyController;
import controller.ComputerController;
import dto.CompanyDTO;
import dto.ComputerDTO;
import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;
import mapper.ComputerMapper;
import model.Company;
import spring.SpringConfig;
import validator.ComputerValidator;

/**
 * . Servlet implementation class AddComputer
 */

@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
  ComputerController computerController = (ComputerController) context.getBean(ComputerController.class);
  CompanyController companyController = (CompanyController) context.getBean(CompanyController.class);
  
  private ComputerDTO computerDTO;
  private ComputerMapper computerMapper;
  private ComputerValidator computerValidator = new ComputerValidator();
  
  private static Logger logger = LoggerFactory.getLogger(AddComputer.class);
  
  List<Company> companies = companyController.listAll();
  CompanyDTO companyDTO = new CompanyDTO();


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
    
    String view = "/Dashboard";
    String errorMessage = "default";
    boolean error = false;
    
    try {
      computerController.create(name, introduced, discontinued, Integer.parseInt(companyId));
    } catch (NumberFormatException e) {
      //TODO: check exception
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
      view = "/addComputer.jsp";
      request.setAttribute("errorMessage", errorMessage);
      request.setAttribute("error", error);
      request.setAttribute("name", name);
      request.setAttribute("companies", companies);
    }
    
    ServletContext context = getServletContext();
    RequestDispatcher rd = context.getRequestDispatcher(view);
    rd.forward(request, response);
  }

}
