package sample.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sample.User;
import sample.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by hOward on 2015/5/31.
 */

@Controller
@RequestMapping("/helloword")
public class HelloWorldController {
    public static final Log logger = LogFactory.getFactory().getInstance(HelloWorldController.class);

    public HelloWorldController() {
        System.out.println("init hellowordcontroller");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(Model model) {
        logger.info("helloword....................");
        User user = new User();
        user.setID(12);
        user.setNAME("SSM");
        user.setAGE(32);
        model.addAttribute("user", user);
        model.addAttribute("message", "Hello World!");
        return "sample/helloword";
    }

    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Integer id) {
        User user = new User();
        user.setID(id);
        user.setNAME("SSM");
        user.setAGE(23);

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("sample/helloword");
        return mv;
    }


    @RequestMapping("/exception")
    public ModelAndView exception(Model model, HttpServletResponse response) {
        response.setStatus(500);
        if (true) {
            throw new IllegalArgumentException("server internal error");
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("helloword");
        return mv;
    }

}
