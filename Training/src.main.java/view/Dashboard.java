package view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import controller.ComputerController;
import model.Computer;

/**
 * . Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static ComputerController computerController = ComputerController.getInstance();
    private List<Computer> computers;



    //private static Logger logger = LoggerFactory.getLogger(Dashboard.class);

//    @Override
//    public void init() throws ServletException {
//
//    }

    /**
     * . DoGet de dashboard
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     *
     * @param request : request
     * @param response : response
     *
     * @throws ServletException : IOException
     * @throws IOException : IOException
     *
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      List<Computer> allComputer = computerController.listAll();
      computers = computerController.list(9);
      request.setAttribute("list", computers);
      int nbPc = allComputer.size();
      request.setAttribute("size", nbPc);
      request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     *
     * @param request : request
     * @param response : response
     *
     * @throws ServletException : ServletException
     * @throws IOException : IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //String page = request.getParameter("");
      doGet(request, response);
    }

}
