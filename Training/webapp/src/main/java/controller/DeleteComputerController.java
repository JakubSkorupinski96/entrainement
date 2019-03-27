package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import services.ComputerServices;

@Controller
@RequestMapping("/DeleteComputer")
public class DeleteComputerController {

  @Autowired
  ComputerServices computerServices;

  @RequestMapping(method = RequestMethod.GET)
  public String get() {
    return "dashboard";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String post(@RequestParam("selection") String selection) {
    List<String> items = Arrays.asList(selection.split(","));
    for (String item : items) {
      computerServices.deleteComputer(item);
    }
    return "dashboard";
  }
}
