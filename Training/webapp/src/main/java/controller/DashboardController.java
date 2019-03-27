package controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dto.ComputerDTO;
import mapper.ComputerMapper;
import model.Computer;
import services.ComputerServices;

@Controller
@RequestMapping("/Dashboard")
public class DashboardController {

  @Autowired
  ComputerServices computerServices;

  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  private ArrayList<Computer> computers;
  private ArrayList<ComputerDTO> computerDTOs;

  @RequestMapping(method = RequestMethod.GET)
  public String get(@RequestParam("search") String searchReq, Model model) {

    boolean search = searchReq != null && searchReq != "" ? true : false;
    int nbPc;
    if (search) {
      computers = new ArrayList<Computer>(computerServices.searchComputers(searchReq));
      computerDTOs = computerMapper.computersToDTOs(computers);
      nbPc = computerServices.countSearch(searchReq);
    } else {
    computers = new ArrayList<Computer>(computerServices.listAllComputers());
    computerDTOs = computerMapper.computersToDTOs(computers);
    nbPc = computerServices.countAll();
    }
    model.addAttribute("list", computerDTOs);
    int pageSize = computers.size();
    int divider = pageSize != 0 ? pageSize : 1;
    int nbPage = nbPc / divider;
    model.addAttribute("size", nbPc);
    model.addAttribute("nbPages", nbPage);
    model.addAttribute("searchAttri", searchReq);

    return "dashboard";
  }
}
