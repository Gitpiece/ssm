package sample.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hOward on 2015/5/31.
 */

@Controller public class HelloWorldController { public static final  Log logger = LogFactory.getFactory().getInstance(HelloWorldController.class);
    public HelloWorldController() {
        System.out.println("init");
    }

    @RequestMapping(value = "/helloword", method = RequestMethod.GET)
    public String helloWorld(Model model) {
        logger.info("helloword....................");
        model.addAttribute("message", "Hello World!");
        return "helloword";
    }

    @RequestMapping(value = "/hellojson", method = RequestMethod.GET)
    public
    @ResponseBody
    Shop helloWorldJSON(@RequestParam(value = "name") String name) {
        logger.info("helloWorldJSON....................");
        Shop shop = new Shop();
        shop.setName(name);
        return shop;
    }

    public class Shop {

        private String name;

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    @RequestMapping("/helloword/{id}")
    public ModelAndView view(@PathVariable("id") Long id, HttpServletRequest req) {
        User user = new User();
        user.setId(id);
        user.setName("SSM");

        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("helloword");
        return mv;
    }


    @RequestMapping("/helloword/exception")
    public ModelAndView exception(Model model,HttpServletResponse response) {
        response.setStatus(500);
        if(true){
            throw new IllegalArgumentException("server internal error");
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("helloword");
        return mv;
    }


}
