package view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CompanyController;
import controller.ComputerController;
import model.Company;

/**
 * . Servlet implementation class AddComputer
 */

@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private ComputerController computerController = ComputerController.getInstance();
  private CompanyController companyController = CompanyController.getInstance();


  /**
   * @see HttpServlet#HttpServlet()
   */
  public AddComputer() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * . DoGet de companies
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
    //companies = companyController.list(1);
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
    computerController.create(name, introduced, discontinued, Integer.parseInt(companyId));
    doGet(request, response);
  }

}
