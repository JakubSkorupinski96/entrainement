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

import controller.CompanyController;
import controller.ComputerController;
import model.Company;

/**
 * . Servlet implementation class EditComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private ComputerController computerController = ComputerController.getInstance();
  private CompanyController companyController = CompanyController.getInstance();

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

    List<Company> companies = companyController.listAll();
    request.setAttribute("companies", companies);

    String id = request.getParameter("computerId");
    String oldName = request.getParameter("computerName");
    String introduced = request.getParameter("computerIntroduced");
    String discontinued = request.getParameter("computerDiscontinued");
    String companyName = request.getParameter("companyName");
    String companyId = request.getParameter("CompanyId");
    //Computer computer = computerController.show(oldName);
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
    computerController.update(oldName, name, introduced, discontinued, Integer.parseInt(id));
    ServletContext context = getServletContext();
    RequestDispatcher rd = context.getRequestDispatcher("/Dashboard");
    rd.forward(request, response);
  }

}
