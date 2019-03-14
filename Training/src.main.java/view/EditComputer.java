package view;

import java.io.IOException;
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
import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;
import model.Company;
import spring.SpringConfig;

/**
 * . Servlet implementation class EditComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
  ComputerController computerController = (ComputerController) context.getBean(ComputerController.class);
  CompanyController companyController = (CompanyController) context.getBean(CompanyController.class);
  

  private static Logger logger = LoggerFactory.getLogger(AddComputer.class);
  
  private String view = "/Dashboard";
  
  List<Company> companies = companyController.listAll();
  
  /**
   * @see HttpServlet#HttpServlet()
   */
  public EditComputer() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * . DoGet de editComputer
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

    String id = request.getParameter("computerId");
    String oldName = request.getParameter("computerName");
    String introduced = request.getParameter("computerIntroduced");
    String discontinued = request.getParameter("computerDiscontinued");
    String companyName = request.getParameter("companyName");
    String companyId = request.getParameter("CompanyId");
    request.setAttribute("id", id);
    request.setAttribute("name", oldName);
    request.setAttribute("introduced", introduced);
    request.setAttribute("discontinued", discontinued);
    request.setAttribute("companyName", companyName);
    request.setAttribute("companyId", companyId);
    System.out.println(companyId);
    request.getRequestDispatcher("/editComputer.jsp").forward(request, response);
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
    String oldName = request.getParameter("oldName");
    String name = request.getParameter("name");
    String introduced = request.getParameter("introduced") + " 00:00:00";
    String discontinued = request.getParameter("discontinued") + " 00:00:00";
    String id = request.getParameter("companyId");
    
    String view = "/Dashboard";
    String errorMessage = "";
    boolean error = false;
    
    try {
      computerController.update(oldName, name, introduced, discontinued, Integer.parseInt(id));
    } catch (NumberFormatException e) {
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
      view = "/editComputer.jsp";
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

