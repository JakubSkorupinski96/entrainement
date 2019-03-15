package controller;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Computer;
import spring.SpringConfig;

@Controller
@RequestMapping("/Dashboard")
public class DashboardController {
 
  ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
  ComputerController computerController = (ComputerController) context.getBean(ComputerController.class);
  
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  private ArrayList<Computer> computers;
  private ArrayList<ComputerDTO> computerDTOs;
  
  @RequestMapping(method = RequestMethod.GET)
  public String get(@RequestParam("search") String searchReq, Model model) {
    
    boolean search = searchReq != null ? true : false;
    int nbPc;
    if (search) {
      computers = new ArrayList<Computer>(computerController.search(searchReq));
      computerDTOs = computerMapper.computersToDTOs(computers);
      nbPc = computerController.countSearch(searchReq);
    } else {
      //System.out.println("hey listen: " + computerController.listAll());
      computers = new ArrayList<Computer>(computerController.listAll());
      computerDTOs = computerMapper.computersToDTOs(computers);
      nbPc = computerController.countAll();
    }
    model.addAttribute("list", computerDTOs);
    //request.setAttribute("list", computerDTOs);
    int pageSize = computers.size();
    int divider = pageSize != 0 ? pageSize : 1;
    int nbPage = nbPc / divider;
    model.addAttribute("size", nbPc);
    //request.setAttribute("size", nbPc);
    model.addAttribute("nbPages", nbPage);
    //request.setAttribute("nbPages", nbPage);
    model.addAttribute("searchAttri", searchReq);
    //request.setAttribute("searchAttri", searchReq);
    
    //request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    
    return "dashboard";
  }
}
