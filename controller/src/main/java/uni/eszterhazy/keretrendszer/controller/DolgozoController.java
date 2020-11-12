package uni.eszterhazy.keretrendszer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uni.eszterhazy.keretrendszer.exceptions.DolgozoAlreadyAdded;
import uni.eszterhazy.keretrendszer.model.Dolgozo;
import uni.eszterhazy.keretrendszer.model.Reszleg;
import uni.eszterhazy.keretrendszer.service.DolgozoService;

@Controller
public class DolgozoController {

    @Autowired
    @Qualifier("dolgozoService")
    DolgozoService service;

    @ModelAttribute(value = "dolgozo")
    public Dolgozo create(){
        return new Dolgozo();
    }

    @GetMapping(value = "/dolgozok")
    public ModelAndView getDolgozok(){
        ModelAndView mav = new ModelAndView("dolgozolist.jsp");
        System.out.println(service.getAllDolgozo());
        mav.addObject("dolgozok", service.getAllDolgozo());
        return mav;
    }

    @GetMapping(value = "/dolgozok2")
    public String getDolgozok2(Model model){
        model.addAttribute("dolgozok", service.getAllDolgozo());
        return "dolgozolist";
    }

    @GetMapping(value = "/dolgozo/{id}")
    public String getDolgozoById(@PathVariable String id, Model model){
        model.addAttribute("dolgozo", service.getDolgozoById(id));
        return "dolgozodetails.jsp";
    }

    @GetMapping(value = "addDolgozo")
    public String addDolgozoForm(Model model){
        model.addAttribute("reszlegek", Reszleg.values());
        return "dolgozoForm.jsp";
    }

    @PostMapping(value = "addDolgozo")
    public String addDolgozo(@ModelAttribute("dolgozo") Dolgozo dolgozo, Model model){
        System.out.println(dolgozo);
        try {
            service.addDolgozo(dolgozo);
        } catch (DolgozoAlreadyAdded dolgozoAlreadyAdded) {
            dolgozoAlreadyAdded.printStackTrace();
        }
        return "redirect:dolgozo/"+dolgozo.getId();
    }

}
