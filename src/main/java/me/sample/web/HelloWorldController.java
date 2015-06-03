package me.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * Created by hOward on 2015/5/31.
 */

@Controller
public class HelloWorldController {

    public HelloWorldController(){
        System.out.println("init");
    }

    @RequestMapping(value = "/helloword",method = RequestMethod.GET)
    public String helloWorld(Model model) {
        System.out.println("helloword....................");
        model.addAttribute("message", "Hello World!");
        return "helloword";
    }

}
