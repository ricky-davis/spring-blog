package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {


    @GetMapping(path={"/hello","/hello/{name}"})
    public String sayHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("test", "<em>Hello</em>");
        return "hello";
    }

    @GetMapping("/favnum/{num}")
    @ResponseBody
    public String getFavNum(@PathVariable int num){
        return "Fav Num is: "+num;
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }

    @GetMapping(path="/goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye";
    }

}
