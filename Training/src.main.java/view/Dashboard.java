package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controller.ComputerController;
import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Computer;
import spring.SpringConfig;

/**
 * . Servlet implementation class Dashboard
 */
@WebServlet(
    name = "Dashboard",
    description = "Application's dashboard",
    urlPatterns = {"/Dashboard"}
  )
public class Dashboard extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    ComputerController computerController = (ComputerController) context.getBean(ComputerController.class);
    
    private static ComputerMapper computerMapper = ComputerMapper.getInstance();
    private ArrayList<Computer> computers;
    private ArrayList<ComputerDTO> computerDTOs;

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
      String searchReq = request.getParameter("search");
      boolean search = searchReq != null ? true : false;
      int nbPc;
      if (search) {
        computers = computerController.search(request.getParameter("search"));
        nbPc = Integer.parseInt(computerController.countSearch(searchReq));
      } else {
        computers = computerController.listAll();
        computerDTOs = computerMapper.computersToDTOs(computers);
        nbPc = Integer.parseInt(computerController.countAll());
      }
      request.setAttribute("list", computerDTOs);
      int pageSize = computers.size();
      int divider = pageSize != 0 ? pageSize : 1;
      int nbPage = nbPc / divider;
      request.setAttribute("size", nbPc);
      request.setAttribute("nbPages", nbPage);
      request.setAttribute("searchAttri", searchReq);
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
      doGet(request, response);
    }

}
