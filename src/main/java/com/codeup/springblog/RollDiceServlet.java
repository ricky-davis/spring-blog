package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class RollDiceServlet {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "rollDice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model){
        Random rand = new Random();
        int roll = rand.nextInt(6)+1;
        model.addAttribute("guess",guess);
        model.addAttribute("roll",roll);
        return "rollDice";
    }


}
