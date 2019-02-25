package view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.CompanyController;
import model.Company;

/**
 * . Servlet implementation class Companies
 */
@WebServlet("/Companies")
public class Companies extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private static CompanyController companyController = CompanyController.getInstance();
  private List<Company> companies;

  // private static Logger logger = LoggerFactory.getLogger(Dashboard.class);

//      @Override
//      public void init() throws ServletException {
  //
//      }

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Companies() {
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
    //List<Company> allCompanies = computerController.listAll();
    companies = companyController.listCompanies(1);
    request.setAttribute("list", companies);
    //int nbPc = allComputer.size();
    //request.setAttribute("size", nbPc);
    request.getRequestDispatcher("/companies.jsp").forward(request, response);
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
    doGet(request, response);
  }


}
