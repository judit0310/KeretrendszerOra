package uni.eszterhazy.keretrendszer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import uni.eszterhazy.keretrendszer.service.DolgozoService;


public class DolgozoController {

    @Autowired
    @Qualifier("dolgozoService")
    DolgozoService service;

    public void printAll(){
        System.out.println(service.getAllDolgozo());
    }

}
