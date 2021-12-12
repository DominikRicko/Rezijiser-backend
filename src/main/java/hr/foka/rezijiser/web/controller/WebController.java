package hr.foka.rezijiser.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = {"", "/", "/index"})
public class WebController {
    
    @RequestMapping(method=RequestMethod.GET)
    public String requestMethodName() {
        return "index.html";
    }
    
}
