package sample.web;

import com.icfcc.web.entity.SuccessBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sample.User;
import sample.UserServiceImpl2;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by hOward on 2015/5/31.
 */

@Controller
@RequestMapping("/helloword")
public class HelloWorldController {
    public final Log logger = LogFactory.getFactory().getInstance(this.getClass());

    public HelloWorldController() {
        logger.info("init hellowordcontroller");
    }

    @Autowired()
    private UserServiceImpl2 userServiceImpl2;

    @RequestMapping(method = RequestMethod.GET)
    public String helloWorld(Model model) {
        logger.info("helloword....................");
        List<User> users = userServiceImpl2.select(new User());
        model.addAttribute("data", users);
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

    @RequestMapping("/json/{id}")
    public SuccessBean viewJson(@PathVariable("id") Integer id) {
        User user = new User();
        user.setID(id);
        user.setNAME("SSM");
        user.setAGE(23);

        return new SuccessBean(user);
    }

}
