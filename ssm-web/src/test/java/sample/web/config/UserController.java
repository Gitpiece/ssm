package sample.web.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sample.User;

import javax.servlet.http.HttpServletRequest;

//省略import
//@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable("id") Integer id, HttpServletRequest req) {
        User user = new User();
        user.setID(id);
        user.setNAME("zhang");

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("helloword");
        return mv;
    }

}