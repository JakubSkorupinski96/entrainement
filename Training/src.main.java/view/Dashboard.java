package view;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.List;
//import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.slf4j.LoggerFactory;

import controller.ComputerController;
import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Computer;

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
    private static ComputerController computerController = ComputerController.getInstance();
    //private static ComputerDTO computerDTO = new ComputerDTO();
    private static ComputerMapper computerMapper = ComputerMapper.getInstance();
    private ArrayList<Computer> computers;
    private ArrayList<ComputerDTO> computerDTOs;


    private static Logger logger = LoggerFactory.getLogger(Dashboard.class);

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
      String searchReq = request.getParameter("search");
      boolean search = searchReq != null ? true : false;
      int nbPc;
      if (search) {
        //computers = computerController.search(request.getParameter("search"));
        nbPc = Integer.parseInt(computerController.countSearch(searchReq));
      } else {
        int queryPage = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        //computers = computerController.list(queryPage);
        computers = computerController.listAll();
        System.out.println(computers);
        computerDTOs = computerMapper.computersToDTOs(computers);
        //computers = computerController.listAll();
        //nbPc = Integer.parseInt(computerController.countAll());
      }
      request.setAttribute("list", computerDTOs);
      int pageSize = computers.size();
      int divider = pageSize != 0 ? pageSize : 1;
      //int nbPage = nbPc / divider;
      //request.setAttribute("size", nbPc);
      //request.setAttribute("nbPages", nbPage);
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
