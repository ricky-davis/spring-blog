package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{first}/and/{second}")
    @ResponseBody
    public String add(@PathVariable int first, @PathVariable int second){
        return ""+(first + second);
    }
    @GetMapping("/subtract/{first}/from/{second}")
    @ResponseBody
    public String subtract(@PathVariable int first, @PathVariable int second){
        return ""+(second - first);
    }
    @GetMapping("/multiply/{first}/and/{second}")
    @ResponseBody
    public String multiply(@PathVariable int first, @PathVariable int second){
        return ""+(first * second);
    }
    @GetMapping("/divide/{first}/by/{second}")
    @ResponseBody
    public String divide(@PathVariable int first, @PathVariable int second){
        return ""+(first / second);
    }
}
