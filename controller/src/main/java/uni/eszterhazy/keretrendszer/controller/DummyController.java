package uni.eszterhazy.keretrendszer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DummyController {

    @GetMapping("/hello")
    public void hello(){
        System.out.println("Hello");
    }
}
