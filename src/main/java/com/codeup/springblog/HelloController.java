package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping(path={"/hello","/hi"})
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String helloName(@PathVariable String name){
        return "Howdy, " + name;
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
