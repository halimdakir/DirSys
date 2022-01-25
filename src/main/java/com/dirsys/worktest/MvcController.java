package com.dirsys.worktest;

import com.dirsys.worktest.dto.UserModel;
import com.dirsys.worktest.entity.User;
import com.dirsys.worktest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
public class MvcController {
    @Autowired
    private WorkTestService workTestService;
    @Autowired
    private UserService userService;
    private String yearSelected;

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("")
    public String home() {
        return "home.html";
    }

    @RequestMapping("/percentage")
    public String percentage(Model model) {
        model.addAttribute("percentages", workTestService.getPercentageChangeInPopulationBetweenSomeYears());
        return "percentage.html";
    }
    @GetMapping("/select")
    public String getSelect(@ModelAttribute("Selected") SelectYear selected, Model model) {
        List<String> years = new ArrayList<>();
        years.add("2020");
        years.add("2019");
        years.add("2018");
        years.add("2017");
        years.add("2016");
        years.add("2015");
        model.addAttribute("years", years);
        return "select.html";
    }
    @PostMapping("/select")
    public String select(@ModelAttribute("Selected") SelectYear selected, Model model, RedirectAttributes redirect) {
        redirect.addFlashAttribute("SavedSelected", selected);
        yearSelected = selected.getYear();
        return "redirect:/sort";
    }
    @GetMapping("/sort")
    public String sort(Model model) {
        model.addAttribute("sorts", workTestService.sortPopulationPerLandscapeForEachYear(yearSelected));
        return "sort.html";
    }
    @RequestMapping("/population")
    public String population(Model model) {
        model.addAttribute("populations", workTestService.populationPerLandscapeBetweenSomeYears());
        return "population.html";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("userModel") UserModel userModel) {
        return "register.html";
    }

    @PostMapping("/register/new")
    public String createUser(@ModelAttribute("userModel") UserModel userModel, RedirectAttributes redirect) throws Exception {
        redirect.addFlashAttribute("SavedUser", userModel);
        User user = new User(userModel.getFullName(), userModel.getEmail(), userModel.getPassword());
        userService.registerNewUser(user);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public String logout(){
        return "logout";
    }


}
